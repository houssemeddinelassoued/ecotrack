package com.ecotrack.domain;

import java.util.UUID;

/**
 * Entité CategorieActif - Type de matériel informatique
 * Représente le référentiel des catégories d'actifs IT (Laptop, Serveur, Moniteur, etc.).
 * Peut être partagé globalement ou spécifique à une entreprise.
 */
public class CategorieActif {

    private UUID id;
    private UUID entrepriseId;
    private String code;
    private String libelle;
    private String description;
    private Double consommationMoyenneKwh;
    private boolean estGlobal;

    /**
     * Constructeur vide
     */
    public CategorieActif() {
    }

    /**
     * Constructeur complet
     */
    public CategorieActif(UUID id, UUID entrepriseId, String code, String libelle, 
                          String description, Double consommationMoyenneKwh, boolean estGlobal) {
        this.id = id;
        this.entrepriseId = entrepriseId;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.consommationMoyenneKwh = consommationMoyenneKwh;
        this.estGlobal = estGlobal;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getConsommationMoyenneKwh() {
        return consommationMoyenneKwh;
    }

    public void setConsommationMoyenneKwh(Double consommationMoyenneKwh) {
        this.consommationMoyenneKwh = consommationMoyenneKwh;
    }

    public boolean isEstGlobal() {
        return estGlobal;
    }

    public void setEstGlobal(boolean estGlobal) {
        this.estGlobal = estGlobal;
    }
}
