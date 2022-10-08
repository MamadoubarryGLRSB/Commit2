package services;

import java.util.List;

import entities.Classe;
import entities.Etudiant;
import entities.Professeur;
import entities.User;

public interface IInscriptionService {
   public boolean inscription(Etudiant etu,Classe classe,String annee);
   public boolean ajouterClasse(Classe cl);
   public List<Classe> listerClasse();
   public boolean modifierClasse(Classe cl);
   public List<Etudiant>  ListerInscristUneClasse(int idClasse,String annee);
   public boolean ajouterProfesseur(Professeur prof);
   public List<Professeur> listerProfesseur();
   public List<Etudiant> listerEtudiant();
   public void affecter(Professeur prof,List<Classe> classes,String annee);
   public List<Classe> listerClasseUnProfesseur(int idProf,String anne);
   public User connexion(String login,String password);
   public List<String> listerAnneeScolaire();
   public Classe rechercherClasseParLibelle(String libelle);
   public Professeur rechercherProfesseurParNci(String nci);
   public Etudiant rechercherEtudiantParMatricule(String matricule);
   public List<Etudiant>  ListerInscristAnne(String annee);
     
}
