package utils;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private String SECRET_KEY;
    // private static String SECRET_KEY;
        // = "123459";
    
    private static final String SALT = "Chhornkakada!!!!";

    public String encrypt(String strToEncrypt, String pw) {
        this.SECRET_KEY = pw;
        try {
            // Create default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                          0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec
                = new IvParameterSpec(iv);
  
            // Create SecretKeyFactory object
            SecretKeyFactory factory
                = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");
            
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                SECRET_KEY.toCharArray(), SALT.getBytes(),
                65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                tmp.getEncoded(), "AES");
  
            Cipher cipher = Cipher.getInstance(
                "AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,
                        ivspec);
            // Return encrypted string
            return Base64.getEncoder().encodeToString(
                cipher.doFinal(strToEncrypt.getBytes(
                    StandardCharsets.UTF_8)));
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: password not yet input. "
                               );
        }
        return null;
    }
  
    // This method use to decrypt to string
    // public static String decrypt(String strToDecrypt, String SECRET_KEY){
    public String decrypt(String strToDecrypt, String SECRET_KEY){
        try {
  
            // Default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                          0, 0, 0, 0, 0, 0, 0, 0 };
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec
                = new IvParameterSpec(iv);
  
            // Create SecretKeyFactory Object
            SecretKeyFactory factory
                = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");
  
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                SECRET_KEY.toCharArray(), SALT.getBytes(),
                65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                tmp.getEncoded(), "AES");
  
            Cipher cipher = Cipher.getInstance(
                "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,
                        ivspec);
            // Return decrypted string
            return new String(cipher.doFinal(
                Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: password incorrect!"
                            //    + e.toString());
                               );
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        AES SMS = new AES();
        AES s1 = new AES();

        // System.out.print("Enter password: ");
        // SMS.setSECRET_KEY(sc.nextLine());
        
        // System.out.print("Enter password: ");
        // s1.setSECRET_KEY(sc.nextLine());
        
        // System.out.print("Enter text: ");
        // String getText = sc.nextLine();
        // String encode = SMS.encrypt("I love you", "123456");
        // System.out.println(encode);
        // String encode1 = SMS.encrypt("I love you", "1234567");
        // System.out.println(encode1);

        // System.out.println("-------------------------");
        // System.out.print("Enter password to decode: ");
        System.out.println(s1.decrypt("tr0zw2gX72//5lLmHQdXF62t7GYU1qKlQyUEcyIjH13rR/yHfUFSnuRFfuo7DuT1", "123456"));
        System.out.println(s1.decrypt("VPdcQhIuB2Iq/jOoVi+IygAp8JQaOYVqKKuEe935Efg=", "123456"));
        System.out.println(s1.decrypt("m68/R7bNmZbith7G9xM70ZfU06eWwDidpatrI4UhWudVgQ60zEMX5cZjllgUw/1F", "1253456"));


    }
}
