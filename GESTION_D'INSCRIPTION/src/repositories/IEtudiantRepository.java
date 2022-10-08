package repositories;

import java.util.List;

import entities.Etudiant;

public interface IEtudiantRepository {
    public int insert(Etudiant etu);
    public List<Etudiant> findAll();
    public List<Etudiant> findAll(String annee);
    public Etudiant findByMatricule(String matricule);
    public List<Etudiant> findAll(String annee,int idclasse);

}
