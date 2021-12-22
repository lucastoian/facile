package Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;
import org.postgresql.util.ServerErrorMessage;


public class UtenteDaoImpl implements UtenteDao{


    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
    private static final String key = "LucaDamonChiaveTopSecret";
    private Encryption enc;

    private List<Utente> utenti;

    public UtenteDaoImpl(){

        this.utenti = new ArrayList<>();
        String query = "SELECT * FROM utente";
        try {

            enc = new Encryption(key);

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();

            while(result.next()){
                Utente u = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                utenti.add(u);
            }
            System.out.println("Success");
        } catch (SQLException ex) {
           System.out.println("Errore");
        }
        catch (Exception e) {
            System.out.println(e);
         }

    }

    @Override
    public List<Utente> getAllUtenti() {

        return utenti;

    }

    @Override
    public void updateUtenti(Utente u) {
        
        
        
    }

    @Override
    public void deleteUtente(Utente u) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addUtente(Utente u) throws  PSQLException, SQLException {

        
        String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?)";
        
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getName());
            pst.setString(2, u.getSurname());
            pst.setString(3, u.getEmail());
            pst.setString(4, enc.encrypt(u.getPassword()));
            pst.setString(5, u.getCodFiscale());
            
            
            pst.executeUpdate();

            utenti.add(u);
            System.out.println("UTENTE REGISTRATO CON SUCCESSO");

        
        
    }

    @Override
    public boolean validateLogin(String email, String passwor) {
        String query = "SELECT * FROM utente WHERE email = ? AND password = ?";


        try {


            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, enc.encrypt(passwor));

            ResultSet result = pst.executeQuery();

                while(result.next()){
                return true;
                }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        catch(Exception e){
            System.out.println(e);
        }

       
        System.out.println("UTENTE NON TROVATO, REGISTRATI PRIMA");
        return false;
    }
    
}

/**
 * catch (PSQLException e) {
            
             // "23514" indica la violazione di una check constraint
             // https://www.postgresql.org/docs/9.2/errcodes-appendix.html
            
            if ("23514".equals(e.getSQLState())) {
                ServerErrorMessage postgresError = e.getServerErrorMessage();
                if (postgresError != null) {
                    String constraint = postgresError.getConstraint();
                    if ("utente_cognome_check".equalsIgnoreCase(constraint)) {
                        throw new IllegalArgumentException("Il cognome deve avere almeno 4 caratteri");
                    }
                    if ("utente_email_check".equalsIgnoreCase(constraint)) {
                        throw new IllegalArgumentException("L'email deve avere almeno 6 caratteri");
                    }
                    if ("utente_nome_check".equalsIgnoreCase(constraint)) {
                        throw new IllegalArgumentException("Il nome deve avere almeno 4 caratteri");
                    }
                    if ("utente_password_check".equalsIgnoreCase(constraint)) {
                        throw new IllegalArgumentException("La password deve avere almeno 9 caratteri");
                    }
                    if ("utente_codfiscale_check".equalsIgnoreCase(constraint)) {
                        throw new IllegalArgumentException("Il codice fiscale deve essere formato da 16 caratteri");
                    }
                }
            } 
            
        }
 */