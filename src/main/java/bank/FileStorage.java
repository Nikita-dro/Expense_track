package bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileStorage {
    private final File file;
    private HashMap<String, Object> values;

    public FileStorage(File file){
        this.file = file;
        this.values = new HashMap<>();
    }

    public File getFile() {return file;}
    public HashMap<String, Object> getValues() {return values;}

    public void setValues() throws IOException {
        this.values = readValues();
    }

    private HashMap<String, Object> readValues() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<HashMap<String, Object>>() {});
    }

    public static FileStorage createFile() throws IOException {
        File new_file = new File("src/main/resources/user.json");
        if (!new_file.exists()){
            new_file.createNewFile();
        }
        return new FileStorage(new_file);
    }
}
