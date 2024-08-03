package com.timelog1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Projet {
    private String dateDeDebut;
    private String dateDeFIn;
    private String nom;
    private int nombreHeureTotal;
    private ArrayList<Discipline> listOfDisciplines;
    private ArrayList<Personne> employe;

    // Constructeurs, getters et setters
    public Projet() {}

    public Projet(String dateDeDebut, String dateDeFIn, String nom, int nombreHeureTotal, ArrayList<Discipline> listOfDisciplines, ArrayList<Personne> employe) {
        this.dateDeDebut = dateDeDebut;
        this.dateDeFIn = dateDeFIn;
        this.nom = nom;
        this.nombreHeureTotal = nombreHeureTotal;
        this.listOfDisciplines = listOfDisciplines;
        this.employe = employe;
    }

    public String getDateDeDebut() {
        return dateDeDebut;
    }

    public void setDateDeDebut(String dateDeDebut) {
        this.dateDeDebut = dateDeDebut;
    }

    public String getDateDeFIn() {
        return dateDeFIn;
    }

    public void setDateDeFIn(String dateDeFIn) {
        this.dateDeFIn = dateDeFIn;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreHeureTotal() {
        return nombreHeureTotal;
    }

    public void setNombreHeureTotal(int nombreHeureTotal) {
        this.nombreHeureTotal = nombreHeureTotal;
    }

    public ArrayList<Discipline> getListOfDisciplines() {
        return listOfDisciplines;
    }

    public void setListOfDisciplines(ArrayList<Discipline> listOfDisciplines) {
        this.listOfDisciplines = listOfDisciplines;
    }

    public ArrayList<Personne> getEmploye() {
        return employe;
    }

    public void setEmploye(ArrayList<Personne> employe) {
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "dateDeDebut='" + dateDeDebut + '\'' +
                ", dateDeFIn='" + dateDeFIn + '\'' +
                ", nom='" + nom + '\'' +
                ", nombreHeureTotal=" + nombreHeureTotal +
                ", listOfDisciplines=" + listOfDisciplines +
                ", employe=" + employe +
                '}';
    }


    public static ArrayList<Projet> listofProjets(Personne personne){

        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "listOfprojet.json"; 
        try {
            ArrayList<Projet> listOfProjets = objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Projet>>() {});
            /*for (Projet projet : listOfProjets) {
                boolean contains = false;
                for (Personne personne2 : projet.getEmploye()) {
                    if (personne2.equalsPer(personne)) {
                        contains = true;
                        
                    }
                }
                if (!contains) {
                    listOfProjets.remove(projet);
                    System.out.println(projet.toString());
                }
               
            }*/
            listOfProjets.removeIf(project -> !Personne.contains(project.getEmploye(), personne));
            return listOfProjets;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
