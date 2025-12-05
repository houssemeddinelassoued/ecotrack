package com.ecotrack.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entité ActifIT - Matériel informatique physique
 * Représente un actif IT (Laptop, Serveur, Moniteur, Imprimante) appartenant à l'entreprise.
 * Garantit l'isolation par entreprise et peut être assigné à un employé.
 */
public class ActifIT {

    private UUID id;
    private UUID entrepriseId;
    private UUID categorieActifId;
    private UUID employeId;
    private String numeroSerie;
    private String marque;
    private String modele;
    private LocalDate dateAcquisition;
    private LocalDate dateMiseEnService;
    private String statut;
    private String localisation;

    /**
     * Constructeur vide
     */
    public ActifIT() {
    }

    /**
     * Constructeur complet
     */
    public ActifIT(UUID id, UUID entrepriseId, UUID categorieActifId, UUID employeId, 
                   String numeroSerie, String marque, String modele, LocalDate dateAcquisition, 
                   LocalDate dateMiseEnService, String statut, String localisation) {
        this.id = id;
        this.entrepriseId = entrepriseId;
        this.categorieActifId = categorieActifId;
        this.employeId = employeId;
        this.numeroSerie = numeroSerie;
        this.marque = marque;
        this.modele = modele;
        this.dateAcquisition = dateAcquisition;
        this.dateMiseEnService = dateMiseEnService;
        this.statut = statut;
        this.localisation = localisation;
    }

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(UUID entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public UUID getCategorieActifId() {
        return categorieActifId;
    }

    public void setCategorieActifId(UUID categorieActifId) {
        this.categorieActifId = categorieActifId;
    }

    public UUID getEmployeId() {
        return employeId;
    }

    public void setEmployeId(UUID employeId) {
        this.employeId = employeId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public LocalDate getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(LocalDate dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public LocalDate getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(LocalDate dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
