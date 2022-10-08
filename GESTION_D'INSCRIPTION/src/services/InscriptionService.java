package services;

import java.time.LocalDate;
import java.util.List;

import entities.AffectationClasse;
import entities.Classe;
import entities.Etudiant;
import entities.Inscription;
import entities.Professeur;
import entities.User;
import repositories.IAffectationRepository;
import repositories.IAnneeRepository;
import repositories.IClasseRepository;
import repositories.IEtudiantRepository;
import repositories.IInscriptionRepository;
import repositories.IProfesseurRepository;
import repositories.IUserRepository;


public class InscriptionService implements IInscriptionService{
    IAffectationRepository iAffectationRepository;
    IAnneeRepository iAnneeRepository;
    IClasseRepository iClasseRepository;
    IEtudiantRepository iEtudiantRepository;
    IInscriptionRepository iInscriptionRepository;
    IProfesseurRepository iProfesseurRepository;
    IUserRepository iUserRepository;
    

    
    
    public InscriptionService(IAffectationRepository iAffectationRepository, IAnneeRepository iAnneeRepository,
            IClasseRepository iClasseRepository, IEtudiantRepository iEtudiantRepository,
            IInscriptionRepository iInscriptionRepository, IProfesseurRepository iProfesseurRepository,
            IUserRepository iUserRepository) {
        this.iAffectationRepository = iAffectationRepository;
        this.iAnneeRepository = iAnneeRepository;
        this.iClasseRepository = iClasseRepository;
        this.iEtudiantRepository = iEtudiantRepository;
        this.iInscriptionRepository = iInscriptionRepository;
        this.iProfesseurRepository = iProfesseurRepository;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public boolean inscription(Etudiant etu, Classe classe, String annee) {
        Inscription ins =new Inscription();
        if(etu.getId()==0){
            int id=iEtudiantRepository.insert(etu);
            etu.setId(id);
        }
        ins.setClasse(classe);
        ins.setEtu(etu);
        ins.setDateInscription(LocalDate.now());
        ins.setAnnee(annee);
        return iInscriptionRepository.insert(ins)!=0;
    }

    @Override
    public boolean ajouterClasse(Classe cl) {
        return iClasseRepository.insert(cl)!=0;
    }

    @Override
    public List<Classe> listerClasse() {
        return iClasseRepository.findAll();
    }

    @Override
    public boolean modifierClasse(Classe cl) {
        return iClasseRepository.update(cl)!=0;
    }

    @Override
    public List<Etudiant> ListerInscristUneClasse(int idClasse, String annee) {
        return iEtudiantRepository.findAll(annee, idClasse);
    }

    @Override
    public boolean ajouterProfesseur(Professeur prof) {
        return iProfesseurRepository.insert(prof)!=0;
    }

    @Override
    public List<Professeur> listerProfesseur() {
        return iProfesseurRepository.findAll();
    }

    @Override
    public List<Etudiant> listerEtudiant() {
        return iEtudiantRepository.findAll();
    }

    @Override
    public void affecter(Professeur prof, List<Classe> classes, String annee) {
        //Premiere Inscription
        if(prof.getId()==0){
            int id= iProfesseurRepository.insert(prof);
            prof.setId(id);
        }
        System.out.println( classes.size());
        classes.stream()
                .filter(cl->cl.getAction().compareToIgnoreCase("add")==0)
                .forEach(cl->{
                AffectationClasse ins =new AffectationClasse();
                ins.setClasse(cl);
                ins.setProf(prof);
                ins.setAnnee(annee);
                iAffectationRepository.insert(ins);
        });      
    }

    @Override
    public List<Classe> listerClasseUnProfesseur(int idProf, String anne) {
        return iClasseRepository.findAll();
    }

    @Override
    public User connexion(String login, String password) {
        return iUserRepository.findLoginAndPassword(login, password);
    }

    @Override
    public List<String> listerAnneeScolaire() {
        return iAnneeRepository.findAll();
    }

    @Override
    public Classe rechercherClasseParLibelle(String libelle) {
        return iClasseRepository.findByLibelle(libelle);
    }

    @Override
    public Professeur rechercherProfesseurParNci(String nci) {
        return iProfesseurRepository.findByNci(nci);
    }

    @Override
    public Etudiant rechercherEtudiantParMatricule(String matricule) {
        return iEtudiantRepository.findByMatricule(matricule);
    }

    @Override
    public List<Etudiant> ListerInscristAnne(String annee) {
       return iEtudiantRepository.findAll(annee);
    }
    
}
