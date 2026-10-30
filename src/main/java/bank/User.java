package bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class User {
    private final String email;
    private final String password;
    private final String fullName;
    private final ObjectMapper mapper;

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.mapper = new ObjectMapper();
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }
    public String getPassword() {return password;}
    public String getFullName() {return fullName;}

    public static boolean checkEmail(FileStorage storage, String email) throws IOException {
        if (storage.getFile().length() == 0){
            return true;
        }
        storage.setValues();
        return !storage.getValues().containsKey(email);
    }

    public static boolean checkPass(){
        return false;
    }

    public static void registerUser(FileStorage storage, String email, String password, String fullName) throws IOException {
        User user = new User(email, password, fullName);
        File file = storage.getFile();
        HashMap<String, Object> values = storage.getValues();
        values.put(email, user);
        user.mapper.writerWithDefaultPrettyPrinter().writeValue(file, values);
    }
}