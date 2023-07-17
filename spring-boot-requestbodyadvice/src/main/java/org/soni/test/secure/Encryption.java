package org.soni.test.secure;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {


    public String encryptString(String input, String secureKey) throws UnsupportedEncodingException {
        String en = secureKey + ":" + input;
        byte[] bytes = en.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String decryptString(String input, String secureKey) throws UnsupportedEncodingException {
        byte[] bytes = Base64.getDecoder().decode(input);
        String result = new String(bytes, StandardCharsets.UTF_8);
        result = result.replace(secureKey + ":", "");
        return result;
    }
}
