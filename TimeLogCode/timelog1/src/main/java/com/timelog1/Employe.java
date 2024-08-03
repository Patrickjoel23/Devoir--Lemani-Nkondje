package com.timelog1;
public class Employe extends Personne {
    public Employe(String nom, int numeroID) {
        super(nom, numeroID);
        //TODO Auto-generated constructor stub
    }

    private String poste;
    private double tauxHoraire;

    // Getters and Setters
    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
}
