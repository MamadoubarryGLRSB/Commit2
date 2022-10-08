package repositories.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.MysqlDb;
import entities.User;
import repositories.IUserRepository;

public class UserRepository extends MysqlDb implements IUserRepository {
    private final String SQL_SELECT_LOGIN_PASSWORD="SELECT * FROM `user` where login like ? and password like ?";
    @Override
    public User findLoginAndPassword(String login, String password) {
        User user=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user =new User(
                       rs.getInt("id"),  
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password")
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return user;
    }
    
}
