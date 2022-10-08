package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlDb implements IDatabase {
    protected Connection conn;
    protected PreparedStatement ps;

    @Override
    public void ouvrirConnexionBd() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Ouverture de la connexion
            try {
                conn=     DriverManager.getConnection("jdbc:mysql://localhost:3306/java_l3_gestion_inscriptions ",
                 "root", "");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
            }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void fermerConnexionBd() {
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
    }
    public void preparerRequete(String sQL_INSERT) {
    }
    public int executeMisAJour() {
        return 0;
    }
    public void setString(int i, String libelle) {
    }
    
}
