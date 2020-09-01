package com.iacg.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author IrvinCG
 */
public class EncriptarPassword {
    public static void main(String[] args) {
     
        String miPass="123";
        System.out.println(encriptarPassword(miPass));
    }
    public static String encriptarPassword(String password){
           BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
           return encoder.encode(password);
    }
}
