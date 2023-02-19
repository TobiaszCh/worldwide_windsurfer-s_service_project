package com.project.weatherservice.client.config;
import org.springframework.stereotype.Component;
@Component
public class Decryptor {
    public String decrypt(String example) {
        StringBuilder stringBuilder = new StringBuilder();
        int key = 6;
        char[] chars = example.toCharArray();
        for (char ch: chars) {
            ch -= key;
            stringBuilder.append(ch);
        }
        return String.valueOf(stringBuilder);
    }
}
