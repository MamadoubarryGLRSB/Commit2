package repositories.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.MysqlDb;
import entities.Etudiant;
import repositories.IEtudiantRepository;

public class EtudiantRepository extends MysqlDb implements IEtudiantRepository {
    private final String SQL_INSERT="INSERT INTO `user` (`nom_complet`, `login`, `password`, `role`,`matricule`, `tuteur`) VALUES (?,?,?,?,?, ?)";
    private final String SQL_SELECT_ALL="select * from user where role like 'ROLE_ETUDIANT' ";
    private final String SQL_SELECT_INSCRITS_ANNEE="select * from user u,inscription i,classe c where "
                                                     + "  role like 'ROLE_ETUDIANT' and  "
                                                     + "  u.id_user=i.etu_id and i.classe_id=c.id_classe"
                                                     + "  and annee_scolaire like ?";
    private final String SQL_SELECT_BY_MATRICULE="select * from user where role like 'ROLE_ETUDIANT' and matricule like ? ";
    private final String SQL_SELECT_INSCRITS_ANNEE_CLASSE="select * from user u,inscription i,classe c where "
                                                     + "  role like 'ROLE_ETUDIANT' and  "
                                                     + "  u.id_user=i.etu_id and i.classe_id=c.id_classe"
                                                     + "  and annee_scolaire like ?"
                                                     + "  and c.id_classe=?";
    ClasseRepository classeRepository=new ClasseRepository();
    @Override
    public int insert(Etudiant etu) {
        int result=0;
        this.ouvrirConnexionBd();
        try {
                ps=conn.prepareStatement(SQL_INSERT);
                ps.setString(1,etu.getNom_complet());
                ps.setString(2,etu.getLogin());
                ps.setString(3,etu.getPassword());
                ps.setString(4,etu.getRole());  
                ps.setString(5,etu.getMatricule());
                ps.setString(6,etu.getTuteur());
                ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return result;
    }
    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants=new ArrayList<>();
        this.ouvrirConnexionBd();
        try {
            Statement stm=conn.createStatement();
            ResultSet rs=    stm.executeQuery(SQL_SELECT_ALL);
            while(rs.next()){
                    Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                                rs.getString("tuteur"), 
                                                rs.getInt("id_user"), 
                                                rs.getString("nom_complet"), 
                                                rs.getString("login"), 
                                                rs.getString("password"));
                    etu.setClasse(classeRepository.findById(rs.getInt("id_classe")));
                etudiants.add(etu);
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return etudiants;
    }

    @Override
    public List<Etudiant> findAll(String annee) {
        List<Etudiant> etudiants=new ArrayList<>();
        this.ouvrirConnexionBd();
        
        try {
            ps=conn.prepareStatement(SQL_SELECT_INSCRITS_ANNEE);
            ps.setString(1,annee);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                    Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                                rs.getString("tuteur"), 
                                                rs.getInt("id_user"), 
                                                rs.getString("nom_complet"), 
                                                rs.getString("login"), 
                                                rs.getString("password"));
                    etu.setClasse(classeRepository.findById(rs.getInt("id_classe")));
                etudiants.add(etu);
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return etudiants;
    }
    
    @Override
    public Etudiant findByMatricule(String matricule) {
        Etudiant etudiant=null;
        this.ouvrirConnexionBd();
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_SELECT_BY_MATRICULE);
            ps.setString(1,matricule);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                                etudiant =new Etudiant(rs.getString("matricule"), 
                                rs.getString("tuteur"), 
                                rs.getInt("id_user"), 
                                rs.getString("nom_complet"), 
                                rs.getString("login"), 
                                rs.getString("password"));
                etudiant.setClasse(classeRepository.findById(rs.getInt("classe_id")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return etudiant;
    }
    @Override
    public List<Etudiant> findAll(String annee, int idclasse) {
        List<Etudiant> etudiants=new ArrayList<>();
        this.ouvrirConnexionBd();
        
        try {
            ps=conn.prepareStatement(SQL_SELECT_INSCRITS_ANNEE_CLASSE);
            ps.setString(1, annee);
            ps.setInt(2, idclasse);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                                Etudiant etu =new Etudiant(rs.getString("matricule"), 
                                rs.getString("tuteur"), 
                                rs.getInt("user_id"), 
                                rs.getString("nom_complet"), 
                                rs.getString("login"), 
                                rs.getString("password"));
                etu.setClasse(classeRepository.findById(rs.getInt("classe_id")));
                etudiants.add(etu);
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return etudiants;
    }
    
}
