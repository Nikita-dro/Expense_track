package bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class User {
    private String email;
    private String password;
    private String fullName;
    private ObjectMapper mapper;

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.mapper = new ObjectMapper();
    }

    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getFullName() {return fullName;}

    public static boolean checkEmail(){
        return false;
    }

//    public static void authorization(String id_user, String name_user) throws IOException {
////        ObjectMapper mapper = new ObjectMapper();
//        User user = new User(id_user, name_user);
//        File file = checkFile(user.mapper, user);
//        HashMap<String, Object> values = user.mapper.readValue(file, new TypeReference<HashMap<String, Object>>() {});
//        System.out.println(values);
//    }

//    public static void registerUser(String idUser, String nameUser){
//
//        File file = checkFile(user.mapper, user);
//    }

    private static File checkFile(ObjectMapper mapper, User user) throws IOException {
        File file = new File("src/main/resources/user.json");
        if (!file.exists()){
            file.createNewFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, user);
        };
        return file;
    }
}