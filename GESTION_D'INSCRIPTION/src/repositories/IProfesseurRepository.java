package repositories;

import java.util.List;

import entities.Professeur;

public interface IProfesseurRepository {
    public int insert(Professeur prof);
    public Professeur findById(int id);
    public List<Professeur> findAll();
    public Professeur findByNci(String nci);
}
