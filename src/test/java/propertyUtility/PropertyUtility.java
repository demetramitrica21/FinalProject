package propertyUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
//helper-ul property utility este folosit pentru a citi valorile fisierelor de tip .properties,.json;
//pentru a evita scrierea de cod repetitiv;

public class PropertyUtility {
    public Properties properties;

    public PropertyUtility(String fileName) {
       loadFile(fileName);
    }

    //O metoda care incarca un fisier properties in functie de numele fisierului;
    public void loadFile(String fileName) {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/inputData/" + fileName + "Data.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //facem o metoda care scoate toate cheile si valorile intr-un Hashmap;
    public Map<String, Object> getAllProperties() {
        Map<String, Object> allProperties = new HashMap<>();
        for(String key : properties.stringPropertyNames()){
            String value = getPropertyValue(key);
            allProperties.put(key,value);
        }
        return allProperties;
    }

    //facem o metoda care sa scoata valoarea unei proprietati pe baza de cheie;
    //fisierele properties functioneaza ca un HashMap(cheie,valoare);
    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public List<String> getPropertiesAsList(String key){
        String value = getPropertyValue(key);
        if(value!=null && value.contains(",")){
            return Arrays.asList(value.split(","));
        }
        return new ArrayList<>(List.of(value));
    }
}
