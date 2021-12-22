package Utente;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

public interface UtenteDao {
    

    public List<Utente> getAllUtenti();
    public void updateUtenti(Utente u);
    public void deleteUtente(Utente u);
    public void addUtente(Utente u) throws PSQLException, SQLException;
    public boolean validateLogin(String email, String password);


}
