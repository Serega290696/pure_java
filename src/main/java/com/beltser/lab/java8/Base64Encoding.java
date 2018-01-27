package com.beltser.lab.java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class Base64Encoding {

    private static final String testString = "just secret here. 3 2 1:#$%!";

    public static void main(String[] args) {
        try {

            // Basic encoder
            // Encode
            String base64encodedString = Base64.getEncoder().encodeToString(
                    testString.getBytes("utf-8"));
            System.out.println("Base64 Encoded String (Basic to A-Za-z0-9+/) :\n" + base64encodedString);
            // Decode
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println(new String(base64decodedBytes, "utf-8"));
            System.out.println();


            // URL encoder
            // encode
            base64encodedString = Base64.getUrlEncoder().encodeToString(
                    testString.getBytes("utf-8"));
            System.out.println("Base64 Encoded String (URL to A-Za-z0-9+_) :\n" + base64encodedString);

            System.out.println(
                    new String(Base64.getUrlDecoder().decode(base64encodedString), "utf-8"));
            System.out.println();


            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);

        } catch(UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
}
