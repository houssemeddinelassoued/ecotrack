package com.ecotrack.domain;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Objects;

/**
 * Entité Employe - Utilisateur final
 * Représente un employé de l'entreprise cliente.
 * Garantit l'isolation par entreprise (Multi-tenancy).
 */
public class Employe {

    private UUID id;
    private UUID entrepriseId;
    private String nom;
    private String prenom;
    private String email;
    private String departement;
    private String matricule;
    private LocalDate dateEmbauche;
    private boolean actif;

    /**
     * Constructeur vide
     */
    public Employe() {
    }

    /**
     * Constructeur complet
     */
    public Employe(UUID id, UUID entrepriseId, String nom, String prenom, String email, 
                   String departement, String matricule, LocalDate dateEmbauche, boolean actif) {
        this.id = id;
        this.entrepriseId = entrepriseId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.departement = departement;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.actif = actif;
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
        Objects.requireNonNull(entrepriseId, "entrepriseId ne peut pas être null");
        this.entrepriseId = entrepriseId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email invalide");
        }
        this.email = email;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
