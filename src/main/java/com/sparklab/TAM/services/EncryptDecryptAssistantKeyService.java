package com.sparklab.TAM.services;

import com.sparklab.TAM.model.AssistantKey;
import com.sparklab.TAM.repositories.AssistantKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EncryptDecryptAssistantKeyService {

    private final AssistantKeyRepository assistantKeyRepository;

    static Map<String, String> env = System.getenv();
    private static final String secretKey = env.get("API_KEY_SECRET");


    public static String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public String saveAssistantKey(String apikey) {

        AssistantKey assistantKey = new AssistantKey();
        assistantKey.setAssistantKey(encrypt(apikey));
        assistantKeyRepository.save(assistantKey);
        return "Assistant Key Saved Successfully";
    }

    public String getAssistantKey() {
        AssistantKey assistantKey = assistantKeyRepository.findAll().get(0);
        return decrypt(assistantKey.getAssistantKey());
    }



}
