package Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoImpl implements UtenteDao{


    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

    private List<Utente> utenti;

    public UtenteDaoImpl(){

        this.utenti = new ArrayList<>();
        String query = "SELECT * FROM utente";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();

            while(result.next()){
                Utente u = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                utenti.add(u);
            }
            System.out.println("Success");
        } catch (SQLException ex) {
           System.out.println("Err");
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
    public void addUtente(Utente u) {
        String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getName());
            pst.setString(2, u.getSurname());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getPassword());
            pst.setString(5, u.getCodFiscale());
            pst.executeUpdate();

            utenti.add(u);
            System.out.println("UTENTE REGISTRATO CON SUCCESSO");

        } catch (SQLException ex) {
           System.out.println("Err");
        }
        
    }

    @Override
    public boolean validateLogin(String email, String passwor) {
        String query = "SELECT * FROM utente WHERE email = ? AND password = ?";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, passwor);

            ResultSet result = pst.executeQuery();

                while(result.next()){
                return true;
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

       
        System.out.println("UTENTE NON TROVATO, REGISTRATI PRIMA");
        return false;
    }
    
}
