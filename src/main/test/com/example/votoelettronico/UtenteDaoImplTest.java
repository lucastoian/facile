package com.example.votoelettronico;

import org.junit.jupiter.api.Test;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/*la classe permette di testare i principali metodi di gestione degli utente dell'applicazione*/
class UtenteDaoImplTest {

    @Test
    void loginUtente() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        assertTrue(new UtenteDaoImpl().loginUtente("email@gmail.com","password"));
        assertFalse(new UtenteDaoImpl().loginUtente("pog", "gers"));
        assertFalse(new UtenteDaoImpl().loginUtente(null, null));
        assertFalse(new UtenteDaoImpl().loginUtente("email@gmail.com", null));
        assertFalse(new UtenteDaoImpl().loginUtente(null, "password"));

    }

    @Test
    void addUtente() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        Utente u = new Utente("nomone","cognomone","email@gmail.com","password","STNLCU00H23Z129P");

        new UtenteDaoImpl().addUtente(u);
        Utente result = new UtenteDaoImpl().getUtente("email@gmail.com","password");
        assertEquals(u,result);
    }

    @Test
    void getUtente() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        Utente u = new Utente("nomone","cognomone","email@gmail.com","VGjFHaHcfKGi4qRFnx+1qw==","STNLCU00H23Z129P");

        Utente result = new UtenteDaoImpl().getUtente("email@gmail.com","password");
        assertEquals(u,result);
    }

    @Test
    void deleteUtente() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        new UtenteDaoImpl().deleteUtente("email@gmail.com");

        Utente result = new UtenteDaoImpl().getUtente("email@gmail.com","password");
        assertNull(result);


    }




}