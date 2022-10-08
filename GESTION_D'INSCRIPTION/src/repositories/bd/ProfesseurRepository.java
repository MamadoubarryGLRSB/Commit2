package repositories.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.MysqlDb;
import entities.Professeur;
import repositories.IProfesseurRepository;

public class ProfesseurRepository extends MysqlDb implements IProfesseurRepository {
    private final String SQL_INSERT="INSERT INTO `user` (`nom_complet`, `login`, `password`, `role`,`nci`, `grade`) VALUES (?,?,?,?,?, ?)";
    private final String SQL_SELECT_ALL="select * from user where role like 'ROLE_PROFESSEUR' ";
    private final String SQL_SELECT_BY_ID="select * from user where role like 'ROLE_PROFESSEUR' and id_user=? ";
    private final String SQL_SELECT_BY_NCI="select * from user where role like 'ROLE_PROFESSEUR' and nci like ? ";
    AffectationRepository affDao=new AffectationRepository();
    @Override
    public int insert(Professeur prof) {
        int result=0;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_INSERT);
            ps.setString(1,prof.getNom_complet());
            ps.setString(2,prof.getLogin());
            ps.setString(3,prof.getPassword());
            ps.setString(4,prof.getRole());  
            ps.setString(5,prof.getNci());
            ps.setString(6,prof.getGrade());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                prof.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return result;
    }

    @Override
    public Professeur findById(int id) {
        Professeur prof=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
               prof.setClasseEnseignees(affDao.findByProfesseur( rs.getInt("user_id")));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            this.fermerConnexionBd();
            return prof;
    }

    @Override
    public List<Professeur> findAll() {
        List<Professeur> profs=new ArrayList<>();
        this.ouvrirConnexionBd();
        try {
            Statement stm=conn.createStatement();
            ResultSet rs=    stm.executeQuery(SQL_SELECT_ALL);
            while(rs.next()){
                Professeur prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
              profs.add(prof);
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return profs;
        
    }

    @Override
    public Professeur findByNci(String nci) {
        Professeur prof=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_BY_NCI);
            ps.setString(1, nci);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                  prof.setClasseEnseignees(affDao.findByProfesseur( rs.getInt("id_user")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return prof;
    }
    
}
