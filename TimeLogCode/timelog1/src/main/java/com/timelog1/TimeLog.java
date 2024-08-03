package com.timelog1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
class Log {
    private int idEmploye;
    private String nomProjet;
    private String nomDiscipline;
    private String heureDebut;
    private String heureFin;
    private float heureTravaille;

    // Constructeur par défaut
    public Log() {}

    // Constructeur avec paramètres
    public Log(int idEmploye, String nomProjet, String nomDiscipline, String heureDebut, String heureFin, float heureTravaille) {
        this.idEmploye = idEmploye;
        this.nomProjet = nomProjet;
        this.nomDiscipline = nomDiscipline;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.heureTravaille = heureTravaille;
    }

    // Getters et Setters
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getNomDiscipline() {
        return nomDiscipline;
    }

    public void setNomDiscipline(String nomDiscipline) {
        this.nomDiscipline = nomDiscipline;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public float getHeureTravaille() {
        return heureTravaille;
    }

    public void setHeureTravaille(float heureTravaille) {
        this.heureTravaille = heureTravaille;
    }

    @Override
    public String toString() {
        return "Log{" +
                "idEmploye=" + idEmploye +
                ", nomProjet='" + nomProjet + '\'' +
                ", nomDiscipline='" + nomDiscipline + '\'' +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", heureTravaille=" + heureTravaille +
                '}';
    }
}

public class TimeLog {
    public static Personne personneConnect = new Personne();
    public static Projet projetAcommencer = new Projet();
    public static Discipline disciplineAcommencer;
    public static void main(String[] args) {
        afficherPageAccueil();   
    }

    public  static void afficherPageAccueil() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Page d'Authentification ===");
        // Ajoutez le code spécifique pour la page d'accueil ici
        System.out.print("2. Entrer votre nom d'usager:\t");
        String nomUsager = scanner.nextLine();
        personneConnect.setNom(nomUsager);;
        System.out.print("1. Entrer votre identifiant:\t");
        int idEmploye = scanner.nextInt();
        personneConnect.setNumeroID(idEmploye);
        if (Personne.verificationEmploye(nomUsager, idEmploye)){
            System.out.println("\n\n\n\n\n\n");
            afficherPageMenu();
        }else{
            System.out.println("Veuillez reessayer");
            System.out.println("\n\n\n\n\n\n");
            afficherPageAccueil();
        }
    }

    public static int afficherPageMenu() {
        System.out.println("=== Menu TimeLog ===");
        // Ajoutez le code spécifique pour la page de profil ici
        
        int option = -1;
        while (!(option == 1 || option == 2 || option == 3) ) {
            System.out.println("Veuillez choisir l'une des options suivantes");
            System.out.println("1. Detuber une activite");
            System.out.println("2. Terminer une activite");
            System.out.println("3. Se Deconnecter");
            System.out.print("Choisissez une option: ");
            Scanner scanner = new Scanner(System.in); 
            option = scanner.nextInt();
            switch (option) {
                case 3:
                    afficherPageAccueil();
                    break;
                case 1:
                    afficherPageListOfProjet(personneConnect.getNumeroID(), personneConnect.getNom());
                    break;
                case 2:
                    terminerActivite(personneConnect.getNumeroID());
                default:
                    break;
            }
        }
        System.out.println("\n\n\n\n\n\n");
        return option;
        

    }

    public static void afficherPageListOfProjet(int idUser, String nomEmploye) {
        ArrayList<Projet> listofProjets = Projet.listofProjets(new Personne(nomEmploye, idUser));
        System.out.println("=== Choisir un Projet ===");
        int nombreProj = 0;
        for (Projet projet : listofProjets) {
            System.out.println(""+(++nombreProj)+". "+ projet.getNom());

        }
        if (nombreProj == 0) {
            System.out.println("Vous n'avez aucun projet a selectionner");
            System.out.println("\n\n\n\n\n\n");
            afficherPageAccueil();
            return;
        }
        if (nombreProj >= 1) {
            int option = -1;
            while (option < 1 || option > nombreProj) {
                System.out.print("Faites votre choix:\t");
                Scanner scanner = new Scanner(System.in); 
                option = scanner.nextInt();
            }
            projetAcommencer = listofProjets.get(option - 1);
            affichePageDiscipline(projetAcommencer);

        }
        
    }

    public static void affichePageDiscipline(Projet projet){
        System.out.println("=== Choisir une Discipline ===");
        ArrayList<Discipline> discipline = projet.getListOfDisciplines();
        int nombreEL = 0;
        for (Discipline discipline2 : discipline) {
            System.out.println(""+(++nombreEL)+". "+ discipline2.getNom());
        }
        if (nombreEL == 0) {
            System.out.println("Vous n'avez aucune discipline a selectionner");
            System.out.println("\n\n\n\n\n\n");
            afficherPageAccueil();
            return;
        }
        if (nombreEL >= 1) {
            int option = -1;
            while (option < 1 || option > nombreEL) {
                System.out.print("Faites votre choix:\t");
                Scanner scanner = new Scanner(System.in); 
                option = scanner.nextInt();
            }
            disciplineAcommencer = discipline.get(option - 1);
            

            
            

        }
        
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Définir un formatteur de temps
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Formater l'heure
        String formattedTime = currentDateTime.format(formatter);
        Log newLog = new Log(personneConnect.getNumeroID(),projetAcommencer.getNom(),disciplineAcommencer.getNom(), formattedTime, "", 0);
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "logs.json";
        

        try {
             ArrayList<Log> logs = new ArrayList<>();
            File file = new File(filePath);
            if (file.exists() && file.length() != 0) {
                logs = objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Log>>() {});
            }else {
                // Si le fichier n'existe pas, créer un fichier avec un tableau JSON vide
                //file.createNewFile();
                objectMapper.writeValue(file, logs);
            }
            boolean exists = isNumeroIDInLogs(logs, personneConnect.getNumeroID());
            if (!exists) {
               // Ajouter le nouvel log à la liste
            logs.add(newLog);
            objectMapper.writeValue(new File(filePath), logs);
            System.out.println("Vous avez bien debute une activite !");
            System.out.println("\n\n\n\n\n\n");
            afficherPageAccueil();
            }else{
                System.out.println("Vous avez actuellemnt une tache en cours!");
                System.out.println("\n\n\n\n\n\n");
                afficherPageAccueil();
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static boolean isNumeroIDInLogs(ArrayList<Log> logs, int numeroID) {
        for (Log log : logs) {
            if (log.getIdEmploye() == numeroID && log.getHeureFin().equals("")) {
                return true;
            }
        }
        return false;
    }
    public static void terminerActivite(int numeroID){
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "logs.json";
        ArrayList<Log> logs = new ArrayList<>();
        ArrayList<Projet> listOfProjets = new ArrayList<Projet>();
        try {
           

           File file = new File(filePath);
           if (file.exists() && file.length() != 0) {
               logs = objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Log>>() {});
               
           }else{
            System.out.println("Aucune activite debute pour pouvoir terminer!! \nVeuillez d'abord debuter une activite");
            afficherPageAccueil();
           }
           ArrayList<Log> projets = getProjetsByNumeroID(logs, numeroID);
           if (projets.size() == 0) {
            System.out.println("Vous n avez debute aucune activite !");
            System.out.println("\n\n\n\n");
            afficherPageAccueil();
            return;
            
           }
           for (Log log : projets) {
            if (log.getHeureFin().equals("")) {
                String temp = log.getHeureDebut();
                LocalTime starTime = LocalTime.parse(temp);
                Log projet = log;
                projets.remove(log);
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Définir un formatteur de temps
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                // Formater l'heure
                String formattedTime = currentDateTime.format(formatter);
                LocalTime endTime = LocalTime.parse(formattedTime);
                Duration duration = Duration.between(starTime, endTime);
                float hours = duration.toHours();
                projet.setHeureTravaille(hours);
                projet.setHeureFin(formattedTime);
                projets.add(projet);
                objectMapper.writeValue(new File(filePath), projets);
                System.out.println("Vous avez bien terminer une activite !");
                System.out.println("\n\n\n\n\n\n");
                afficherPageAccueil();
            }
        }
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
    public static ArrayList<Log> getProjetsByNumeroID(ArrayList<Log> logs, int numeroID) {
        ArrayList<Log> result = new ArrayList<Log>();
        for (Log log : logs) {
            if (log.getIdEmploye() == numeroID) {
                result.add(log);
            }
        }
        return result;
    }
}

