package repositories.bd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import core.MysqlDb;
import entities.Classe;
import repositories.IClasseRepository;

public class ClasseRepository extends MysqlDb implements IClasseRepository {
     private final String SQL_SELECT_ALL="SELECT * FROM `classe`";
     private final String SQL_SELECT_BY_LIBELLE="SELECT * FROM `classe` where libelle like ?";
     private final String SQL_SELECT_BY_ID="SELECT * FROM `classe` where id_classe=?";
     private final String SQL_INSERT="INSERT INTO `classe` (`libelle`) VALUES (?)";
     private final String SQL_UPDATE="UPDATE `classe` SET `libelle` = ? WHERE `classe`.`id_classe` = ?;";
    @Override
    public int insert(Classe classe) {
        int result=0;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_INSERT);
            ps.setString(1,classe.getLibelle());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return result;
    }
    

    @Override
    public int update(Classe classe) {
        int result=0;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_UPDATE);
            ps.setString(1,classe.getLibelle());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return result;
    }

    @Override
    public Classe findById(int id) {
        Classe classe=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                classe =new Classe(
                        rs.getInt("id"),
                        rs.getString("libelle")
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return classe;
    }

    @Override
    public List<Classe> findAll() {
        List<Classe> classes=new ArrayList<>();
        this.ouvrirConnexionBd();
        try {
            Statement stm=conn.createStatement();
            ResultSet rs=    stm.executeQuery(SQL_SELECT_ALL);
            while(rs.next()){
                Classe cl =new Classe(
                         rs.getInt("id"),
                         rs.getString("libelle")
                );
              classes.add(cl);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return classes;
        
        
    }

    @Override
    public Classe findByLibelle(String libelle) {
        Classe classe=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_BY_LIBELLE);
            ps.setString(2,libelle);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                classe =new Classe(
                        rs.getInt("id"),
                        rs.getString("libelle")
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return classe;
       
        
    }
    
}
