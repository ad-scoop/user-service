package com.adscoop.userservice.congfig;


import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by thokle on 03/02/2017.
 */
@Singleton
public class AEService {
    private static Logger logger = LoggerFactory.getLogger(AEService.class);



    public  String encrypt(String strToEncrypt) {


        try {
           return Base64.getEncoder().encodeToString(strToEncrypt.getBytes("UTF-8"));
        } catch (Exception e) {
                logger.debug(e.getMessage());


        }
        return null;
    }

    public  String decrypt(String strToDecrypt) {

        try {
           byte[] b =  Base64.getDecoder().decode(strToDecrypt);

           return new String(b, "UTF-8");
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }

        return null;
    }
}
