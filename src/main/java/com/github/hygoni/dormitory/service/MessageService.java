package com.github.hygoni.dormitory.service;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Service
public class MessageService {
    static final String MESSAGES_PATH = "./resources/exception.yml";
    public String getMessage(String code) {
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(MESSAGES_PATH);
        Map<String, String> obj = yaml.load(inputStream);
        return obj.get(code);
    }
}
