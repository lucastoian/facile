import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQL {

    //#region gestioneCandidati 

    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
    

    public static void writeToDatabase(String name, String surname){
        String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
        String user = "ykeiygtowzihlm";
        String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

        String query = "INSERT INTO candidati (nome, cognome) VALUES (?,?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.executeUpdate();
            System.out.println("Success");
        } catch (SQLException ex) {
           System.out.println("Err");
        }
    }

    public static void SysoutQueryToDatabase(String query){
        String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
        String user = "ykeiygtowzihlm";
        String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while(result.next()){
                System.out.println(result.getString(1) + " " + result.getString(2));
            }
        } catch (SQLException ex) {
           System.out.println("Err");
        }
    }

    public static ResultSet queryToDatabase(String query){
        String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
        String user = "ykeiygtowzihlm";
        String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return result;
            
        } catch (SQLException ex) {
           System.out.println("Err");
           
        }
        return null;
        
    }

    public static void DeleteCandidatoFromDatabase(String name, String surname){
        String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
        String user = "ykeiygtowzihlm";
        String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
        String query = "DELETE FROM candidati WHERE nome = ? AND cognome = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Err");
           
        }
       
        
    }

    public static void UpdateCandidatoFromDatabase(String name, String surname){
        String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
        String user = "ykeiygtowzihlm";
        String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
        String query = "DELETE FROM candidati WHERE nome = ? AND cognome = ?";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Err");
           
        }
       
        
    }

    //#endregion

    //#region oldLogin

    // public static void registerToDatabase(String nome, String cognome, String passwor, String email, String codFiscale) throws SQLException{
    //     String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    //     String user = "ykeiygtowzihlm";
    //     String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
    //     String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?)";

        
    //         Connection con = DriverManager.getConnection(url, user, password);
    //         PreparedStatement pst = con.prepareStatement(query);
            
    //         pst.setString(1, nome);
    //         pst.setString(2, cognome);
    //         pst.setString(4, passwor);
    //         pst.setString(3, email);
    //         pst.setString(5, codFiscale);
    //         pst.executeUpdate();

    //         System.out.println("UTENTE REGISTRATO CON SUCCESSO");
        

    // }


    // public static boolean login(String email, String passwor) throws SQLException{

    //     String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    //     String user = "ykeiygtowzihlm";
    //     String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
    //     String query = "SELECT * FROM utente WHERE email = ? AND password = ?";

    //     Connection con = DriverManager.getConnection(url, user, password);
    //     PreparedStatement pst = con.prepareStatement(query);

    //     pst.setString(1, email);
    //     pst.setString(2, passwor);

    //     ResultSet result = pst.executeQuery();

    //     while(result.next()){
    //        return true;
    //     }
       
    //     System.out.println("UTENTE NON TROVATO, REGISTRATI PRIMA");
    //     return false;

    // }

    //#endregion

    //#region altro


    //#endregion

}
