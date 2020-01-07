package com.github.hygoni.dormitory.service;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

@Service
public class MessageService {
    static final String MESSAGES_PATH = "./src/main/resources/exception.yml";
    public String getMessage(String code) {
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
        Yaml yaml = new Yaml();

        try {
            InputStream inputStream = new FileInputStream(MESSAGES_PATH);
            Map<String, Object> obj = yaml.load(inputStream);
            StringTokenizer st = new StringTokenizer(code, ".");
            String token = st.nextToken();

            while(st.hasMoreTokens()){
                obj = (Map<String, Object>) obj.get(token);
                token = st.nextToken();
            }

            return (String) obj.get(token);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
