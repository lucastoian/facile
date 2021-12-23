package Utente;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;
/**
 * 
Host ec2-34-255-134-200.eu-west-1.compute.amazonaws.com
Database du00brnb1t9ok
User ykeiygtowzihlm
Port 5432
Password 70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c
URI postgres://ykeiygtowzihlm:70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c@ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok
Heroku CLI heroku pg:psql postgresql-rigid-02569 --app datavoto
 */
public interface UtenteDao {
    

    public List<Utente> getAllUtenti();
    public void updateUtenti(Utente u);
    public void deleteUtente(Utente u);
    public void addUtente(Utente u) throws PSQLException, SQLException;
    public boolean validateLogin(String email, String password);


}
