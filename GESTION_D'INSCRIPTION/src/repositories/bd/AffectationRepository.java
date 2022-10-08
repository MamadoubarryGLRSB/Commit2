package repositories.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.MysqlDb;
import entities.AffectationClasse;
import repositories.IAffectationRepository;

public class AffectationRepository extends MysqlDb implements IAffectationRepository {
    private final String SQL_INSERT="INSERT INTO `classe_prof` (`classe_id`, `prof_id`, `anne`) VALUES (?, ?,?)";
    private final String SQL_SELECT_BY_PROFESSEUR="SELECT * FROM `classe_prof` where prof_id=?";
    ClasseRepository classeRepository=new ClasseRepository();
    @Override
    public AffectationClasse insert(AffectationClasse affClasse) {
        this.ouvrirConnexionBd();
        try {
                //recuperez le dernier id qui a ete inserer
                ps=conn.prepareStatement(SQL_INSERT);
                ps.setInt(1,affClasse.getClasse().getId());
                ps.setInt(2,affClasse.getProf().getId());
                ps.setString(3,affClasse.getAnnee());
                ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return affClasse;
    }

    @Override
    public List<AffectationClasse> findByProfesseur(int id_prof) {
        List<AffectationClasse> affectationClasses=new ArrayList<>();
        this.ouvrirConnexionBd();
        try {
                //recuperez le dernier id qui a ete inserer
                ps=conn.prepareStatement(SQL_SELECT_BY_PROFESSEUR);
                ps.setInt(1, id_prof);
                ps.executeUpdate();
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                        AffectationClasse affect=new AffectationClasse(
                        rs.getInt("id_classe_prof"),
                        classeRepository.findById( rs.getInt("classe_id")),
                        null,
                        rs.getString("anne")
                        
                    );
                    affectationClasses.add(affect);
                }
                                
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return affectationClasses;
    }

    
}
