package com.ecotrack.domain;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité racine - Le Tenant (Client payeur)
 * Représente l'entreprise cliente qui souscrit au service EcoTrack.
 * Assure l'isolation des données (Multi-tenancy).
 */
public class Entreprise {

    private UUID id;
    private String nom;
    private String siret;
    private String adresse;
    private String pays;
    private String emailContact;
    private LocalDateTime dateCreation;
    private boolean actif;

    /**
     * Constructeur vide
     */
    public Entreprise() {
    }

    /**
     * Constructeur complet
     */
    public Entreprise(UUID id, String nom, String siret, String adresse, String pays, 
                      String emailContact, LocalDateTime dateCreation, boolean actif) {
        this.id = id;
        this.nom = nom;
        this.siret = siret;
        this.adresse = adresse;
        this.pays = pays;
        this.emailContact = emailContact;
        this.dateCreation = dateCreation;
        this.actif = actif;
    }

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
