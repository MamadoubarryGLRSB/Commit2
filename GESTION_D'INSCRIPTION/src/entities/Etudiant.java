package entities;

public class Etudiant extends User{
    private String matricule;
    private String tuteur;
    private Classe classe;

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    
    public Etudiant(String matricule, String tuteur, String nom_complet, String login, String password) {
        super(nom_complet, login, password);
        this.matricule = matricule;
        this.tuteur = tuteur;
        this.role="ROLE_ETUDIANT";
    }

    public Etudiant(String matricule, String tuteur, int id, String nom_complet, String login, String password) {
        super(id, nom_complet, login, password);
        this.matricule = matricule;
        this.tuteur = tuteur;
         this.role="ROLE_ETUDIANT";
    }

    public Etudiant() {
         this.role="ROLE_ETUDIANT";
    }

    
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTuteur() {
        return tuteur;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public void add(Etudiant etu) {
    }
    
    
}
