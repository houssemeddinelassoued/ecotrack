package com.ecotrack;

import com.ecotrack.domain.Employe;
import com.ecotrack.domain.Entreprise;
import com.ecotrack.domain.ActifIT;
import com.ecotrack.domain.CategorieActif;
import com.ecotrack.domain.ConsoJournaliere;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Point d'entrée de l'application EcoTrack.
 * 
 * <p>EcoTrack est une application SaaS B2B de suivi de l'empreinte carbone
 * du parc informatique des entreprises (Green IT).</p>
 * 
 * <p>Cette classe Main démontre l'utilisation des entités du domaine métier
 * dans un contexte multi-tenant.</p>
 * 
 * @author EcoTrack Team
 * @version 1.0
 * @since Java 21
 */
public class Main {
    
    /**
     * Méthode principale démarrant l'application EcoTrack.
     * 
     * <p>Démontre la création et manipulation des entités du domaine :
     * <ul>
     *   <li>Création d'une entreprise (tenant)</li>
     *   <li>Création d'un employé</li>
     *   <li>Provisioning d'un actif IT</li>
     *   <li>Enregistrement de consommation</li>
     * </ul>
     * </p>
     * 
     * @param args arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        System.out.println("=== EcoTrack - Green IT Carbon Tracking ===\n");
        
        // Création d'une entreprise (Tenant)
        Entreprise entreprise = creerEntreprise();
        afficherEntreprise(entreprise);
        
        // Création d'un employé
        Employe employe = creerEmploye(entreprise.getId());
        afficherEmploye(employe);
        
        // Création d'une catégorie d'actif
        CategorieActif categorie = creerCategorieActif(entreprise.getId());
        afficherCategorie(categorie);
        
        // Provisioning d'un actif IT
        ActifIT actif = creerActifIT(entreprise.getId(), categorie.getId(), employe.getId());
        afficherActif(actif);
        
        // Enregistrement d'une consommation journalière
        ConsoJournaliere conso = creerConsommation(entreprise.getId(), actif.getId());
        afficherConsommation(conso);
        
        System.out.println("\n[OK] Demonstration terminee avec succes !");
        System.out.println("[INFO] Isolation multi-tenant respectee pour l'entreprise: " + entreprise.getNom());
    }
    
    /**
     * Crée une entreprise exemple.
     * 
     * @return entreprise créée avec données de démonstration
     */
    private static Entreprise creerEntreprise() {
        return new Entreprise(
            UUID.randomUUID(),
            "GreenTech Solutions",
            "12345678901234",
            "42 Rue de l'Innovation, 75001 Paris",
            "France",
            "contact@greentech.fr",
            LocalDateTime.now(),
            true
        );
    }
    
    /**
     * Crée un employé exemple.
     * 
     * @param entrepriseId identifiant de l'entreprise (isolation)
     * @return employé créé
     */
    private static Employe creerEmploye(UUID entrepriseId) {
        return new Employe(
            UUID.randomUUID(),
            entrepriseId,
            "Dupont",
            "Marie",
            "marie.dupont@greentech.fr",
            "IT",
            "EMP001",
            LocalDate.of(2023, 1, 15),
            true
        );
    }
    
    /**
     * Crée une catégorie d'actif exemple.
     * 
     * @param entrepriseId identifiant de l'entreprise
     * @return catégorie créée
     */
    private static CategorieActif creerCategorieActif(UUID entrepriseId) {
        return new CategorieActif(
            UUID.randomUUID(),
            entrepriseId,
            "LAPTOP",
            "Ordinateur Portable",
            "Laptop professionnel standard",
            65.0, // kWh moyen par an
            false
        );
    }
    
    /**
     * Crée un actif IT exemple.
     * 
     * @param entrepriseId identifiant de l'entreprise
     * @param categorieId identifiant de la catégorie
     * @param employeId identifiant de l'employé assigné
     * @return actif IT créé
     */
    private static ActifIT creerActifIT(UUID entrepriseId, UUID categorieId, UUID employeId) {
        return new ActifIT(
            UUID.randomUUID(),
            entrepriseId,
            categorieId,
            employeId,
            "SN123456789",
            "Dell",
            "Latitude 7430",
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 3, 15),
            "EN_SERVICE",
            "Siège Paris"
        );
    }
    
    /**
     * Crée une consommation journalière exemple.
     * 
     * @param entrepriseId identifiant de l'entreprise
     * @param actifId identifiant de l'actif IT
     * @return consommation créée
     */
    private static ConsoJournaliere creerConsommation(UUID entrepriseId, UUID actifId) {
        return new ConsoJournaliere(
            1L,
            actifId,
            entrepriseId,
            LocalDate.now(),
            new BigDecimal("0.178"), // kWh consommé aujourd'hui
            LocalDateTime.now(),
            "AUTO_SENSOR"
        );
    }
    
    /**
     * Affiche les informations d'une entreprise.
     * 
     * @param entreprise entreprise à afficher
     */
    private static void afficherEntreprise(Entreprise entreprise) {
        System.out.println("[ENTREPRISE] Creee:");
        System.out.println("   Nom: " + entreprise.getNom());
        System.out.println("   SIRET: " + entreprise.getSiret());
        System.out.println("   Pays: " + entreprise.getPays());
        System.out.println("   ID: " + entreprise.getId());
        System.out.println();
    }
    
    /**
     * Affiche les informations d'un employé.
     * 
     * @param employe employé à afficher
     */
    private static void afficherEmploye(Employe employe) {
        System.out.println("[EMPLOYE] Cree:");
        System.out.println("   Nom: " + employe.getPrenom() + " " + employe.getNom());
        System.out.println("   Email: " + employe.getEmail());
        System.out.println("   Departement: " + employe.getDepartement());
        System.out.println("   Matricule: " + employe.getMatricule());
        System.out.println("   Entreprise ID: " + employe.getEntrepriseId());
        System.out.println();
    }
    
    /**
     * Affiche les informations d'une catégorie d'actif.
     * 
     * @param categorie catégorie à afficher
     */
    private static void afficherCategorie(CategorieActif categorie) {
        System.out.println("[CATEGORIE] Creee:");
        System.out.println("   Code: " + categorie.getCode());
        System.out.println("   Libelle: " + categorie.getLibelle());
        System.out.println("   Conso moyenne: " + categorie.getConsommationMoyenneKwh() + " kWh/an");
        System.out.println();
    }
    
    /**
     * Affiche les informations d'un actif IT.
     * 
     * @param actif actif à afficher
     */
    private static void afficherActif(ActifIT actif) {
        System.out.println("[ACTIF IT] Cree:");
        System.out.println("   Marque/Modele: " + actif.getMarque() + " " + actif.getModele());
        System.out.println("   Numero de serie: " + actif.getNumeroSerie());
        System.out.println("   Statut: " + actif.getStatut());
        System.out.println("   Localisation: " + actif.getLocalisation());
        System.out.println("   Employe assigne: " + actif.getEmployeId());
        System.out.println();
    }
    
    /**
     * Affiche les informations d'une consommation journalière.
     * 
     * @param conso consommation à afficher
     */
    private static void afficherConsommation(ConsoJournaliere conso) {
        System.out.println("[CONSOMMATION] Enregistree:");
        System.out.println("   Date: " + conso.getDateConsommation());
        System.out.println("   Consommation: " + conso.getConsoKwh() + " kWh");
        System.out.println("   Source: " + conso.getSource());
        System.out.println("   Actif ID: " + conso.getActifItId());
        System.out.println();
    }
}