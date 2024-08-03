package com.timelog1;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Personne {
    private String nom;
    private int numeroID;
    //private String dateEmbauche;
    //private String numeroAssuranceSocial;
    public Personne() {
    }
    public Personne(String nom, int numeroID) {
        this.nom = nom;
        this.numeroID = numeroID;
    }
    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }

    /*public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getNumeroAssuranceSocial() {
        return numeroAssuranceSocial;
    }

    public void setNumeroAssuranceSocial(String numeroAssuranceSocial) {
        this.numeroAssuranceSocial = numeroAssuranceSocial;
    }*/

    public static boolean verificationEmploye(String nom, int id){
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "listofEmploy.json"; 
        try {
            ArrayList<Personne> person = objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Personne>>() {});
            for (Personne people : person) {
                if (people.nom.equals(nom) && people.numeroID == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean contains(ArrayList<Personne> personnes, Personne employe){
        boolean contains = false;
        for (Personne personne : personnes) {
            if (personne.equalsPer(employe)) {
                return true;
            }
        }
        return false;

    }
   
    public boolean equalsPer(Personne person){
        if(this.nom.equals(person.nom) && this.numeroID == person.numeroID){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "Je suis "+ this.nom + "et j'ai l'ID " + this.numeroID;
    }

    
}
