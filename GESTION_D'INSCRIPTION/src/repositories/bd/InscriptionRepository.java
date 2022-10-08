package repositories.bd;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import core.MysqlDb;
import entities.Inscription;
import repositories.IInscriptionRepository;

public class InscriptionRepository extends MysqlDb implements IInscriptionRepository {
    private final String SQL_INSERT="INSERT INTO `inscription` (`user_id`, `classe_id`, `annee_scolaire`,date) VALUES (?, ?, ?,?)";
    @Override
    public int insert(Inscription insc) {
        this.ouvrirConnexionBd();
        int result=0;
        try {
            //recuperez le dernier id qui a ete inserer
            ps=conn.prepareStatement(SQL_INSERT);
            ps.setInt(1,insc.getEtu().getId());
            ps.setInt(2,insc.getClasse().getId());
            ps.setString(3,insc.getAnnee());
            ps.setString(4,insc.getDateInscription().format(DateTimeFormatter.ISO_DATE));
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBd();
        return result;
    }
    
}
