package com.example.votoelettronico;

import com.example.votoelettronico.Encryption;
import org.junit.jupiter.api.Test;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {


    /*
    * il test verifica il corretto funzionamento della funzione di encryption delle passoword
    * */
    @Test
    void encrypt() throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        Encryption enc = new Encryption("LucaDamonChiaveTopSecret");

        String test1 = Encryption.encrypt("password");
        String test2 = Encryption.encrypt("123456768");
        String test3 = Encryption.encrypt("Qualcosad!Segret0%");
        String test4 = Encryption.encrypt("$@#^%#&$%&@#^^&");

        assertEquals("VGjFHaHcfKGi4qRFnx+1qw==",test1);
        assertEquals("mhR6Ob8PHifoEo+sq6CTPg==",test2);
        assertEquals("A99hEZm+TKHChiDO8M1Oo4ovH9DJwOgo",test3);
        assertEquals("FbVwLGzNgmeXPQexQKFUrQ==",test4);
    }

}