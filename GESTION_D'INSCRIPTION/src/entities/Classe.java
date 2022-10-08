package entities;
public class Classe {
    private int id;
    private String libelle;
    private String action="null";
    private static int nbr;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public Classe() {
        nbr+=1;
        id=nbr;
    }

    public Classe(String libelle) {
        this.libelle = libelle;
        nbr+=1;
        id=nbr;
    }

    public Classe(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibellle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Classe [id=" + id + ", libelle=" + libelle + "]";
    }

    
}
