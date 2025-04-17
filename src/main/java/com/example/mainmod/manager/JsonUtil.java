package com.example.mainmod.manager;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonUtil {
    public static String readFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line).append('\n');
            return sb.toString();
        }
    }
}
