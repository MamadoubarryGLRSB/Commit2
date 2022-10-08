package repositories;

import java.util.List;

import entities.Classe;
public interface IClasseRepository {
    public int insert(Classe classe);
    public int  update(Classe classe);
    public Classe findById(int id);
    public List<Classe> findAll();
    public Classe findByLibelle(String libelle);
    
}
