package com.ecotrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité Entreprise")
class EntrepriseTest {

    private UUID id;
    private String nom;
    private String siret;
    private String adresse;
    private String pays;
    private String emailContact;
    private LocalDateTime dateCreation;
    private boolean actif;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        nom = "GreenTech Solutions";
        siret = "12345678901234";
        adresse = "42 Rue de l'Innovation, 75001 Paris";
        pays = "France";
        emailContact = "contact@greentech.fr";
        dateCreation = LocalDateTime.now();
        actif = true;
    }

    @Nested
    @DisplayName("Tests des Constructeurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur vide doit créer une instance non-null")
        void constructeurVide_DoitCreerInstance() {
            Entreprise entreprise = new Entreprise();
            
            assertNotNull(entreprise);
            assertNull(entreprise.getId());
            assertNull(entreprise.getNom());
            assertFalse(entreprise.isActif());
        }

        @Test
        @DisplayName("Le constructeur complet doit assigner tous les champs")
        void constructeurComplet_DoitAssignerTousLesChamps() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateCreation, actif);
            
            assertEquals(id, entreprise.getId());
            assertEquals(nom, entreprise.getNom());
            assertEquals(siret, entreprise.getSiret());
            assertEquals(adresse, entreprise.getAdresse());
            assertEquals(pays, entreprise.getPays());
            assertEquals(emailContact, entreprise.getEmailContact());
            assertEquals(dateCreation, entreprise.getDateCreation());
            assertTrue(entreprise.isActif());
        }

        @Test
        @DisplayName("Le constructeur doit accepter des valeurs null")
        void constructeurComplet_DoitAccepterNull() {
            Entreprise entreprise = new Entreprise(null, null, null, null, null, 
                                                   null, null, false);
            
            assertNotNull(entreprise);
            assertNull(entreprise.getId());
            assertFalse(entreprise.isActif());
        }
    }

    @Nested
    @DisplayName("Tests des Getters et Setters")
    class GetterSetterTests {

        private Entreprise entreprise;

        @BeforeEach
        void setUp() {
            entreprise = new Entreprise();
        }

        @Test
        @DisplayName("getId/setId doivent fonctionner")
        void getId_setId_Fonctionnent() {
            UUID testId = UUID.randomUUID();
            entreprise.setId(testId);
            assertEquals(testId, entreprise.getId());
        }

        @Test
        @DisplayName("getNom/setNom doivent fonctionner")
        void getNom_setNom_Fonctionnent() {
            entreprise.setNom("TechCorp");
            assertEquals("TechCorp", entreprise.getNom());
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", "A", "Société à Responsabilité Limitée"})
        @DisplayName("setNom accepte différentes valeurs")
        void setNom_AccepteDifferentesValeurs(String nom) {
            entreprise.setNom(nom);
            assertEquals(nom, entreprise.getNom());
        }

        @Test
        @DisplayName("getSiret/setSiret doivent fonctionner")
        void getSiret_setSiret_Fonctionnent() {
            entreprise.setSiret("98765432109876");
            assertEquals("98765432109876", entreprise.getSiret());
        }

        @ParameterizedTest
        @ValueSource(strings = {"123", "12345678901234", "ABC123"})
        @DisplayName("setSiret accepte différents formats")
        void setSiret_AccepteDifferentsFormats(String siret) {
            entreprise.setSiret(siret);
            assertEquals(siret, entreprise.getSiret());
        }

        @Test
        @DisplayName("getAdresse/setAdresse doivent fonctionner")
        void getAdresse_setAdresse_Fonctionnent() {
            String adresse = "10 Boulevard Haussmann, 75009 Paris";
            entreprise.setAdresse(adresse);
            assertEquals(adresse, entreprise.getAdresse());
        }

        @Test
        @DisplayName("getPays/setPays doivent fonctionner")
        void getPays_setPays_Fonctionnent() {
            entreprise.setPays("Belgique");
            assertEquals("Belgique", entreprise.getPays());
        }

        @Test
        @DisplayName("getEmailContact/setEmailContact doivent fonctionner")
        void getEmailContact_setEmailContact_Fonctionnent() {
            entreprise.setEmailContact("info@company.com");
            assertEquals("info@company.com", entreprise.getEmailContact());
        }

        @Test
        @DisplayName("getDateCreation/setDateCreation doivent fonctionner")
        void getDateCreation_setDateCreation_Fonctionnent() {
            LocalDateTime date = LocalDateTime.of(2024, 1, 1, 10, 0);
            entreprise.setDateCreation(date);
            assertEquals(date, entreprise.getDateCreation());
        }

        @Test
        @DisplayName("isActif/setActif doivent fonctionner")
        void isActif_setActif_Fonctionnent() {
            entreprise.setActif(true);
            assertTrue(entreprise.isActif());
            
            entreprise.setActif(false);
            assertFalse(entreprise.isActif());
        }
    }

    @Nested
    @DisplayName("Tests des Cas Limites")
    class EdgeCaseTests {

        @Test
        @DisplayName("Entreprise inactive doit être gérée")
        void entrepriseInactive_DoitEtreGeree() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateCreation, false);
            
            assertFalse(entreprise.isActif());
        }

        @Test
        @DisplayName("Date de création dans le futur autorisée")
        void dateCreationFuture_Autorisee() {
            LocalDateTime dateFuture = LocalDateTime.now().plusYears(1);
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateFuture, actif);
            
            assertTrue(entreprise.getDateCreation().isAfter(LocalDateTime.now()));
        }

        @Test
        @DisplayName("Deux entreprises avec UUIDs différents")
        void deuxEntreprises_UUIDsDifferents() {
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();
            
            Entreprise e1 = new Entreprise(id1, "Entreprise 1", siret, adresse, pays, 
                                          emailContact, dateCreation, actif);
            Entreprise e2 = new Entreprise(id2, "Entreprise 2", siret, adresse, pays, 
                                          emailContact, dateCreation, actif);
            
            assertNotEquals(e1.getId(), e2.getId());
        }

        @Test
        @DisplayName("Email vide autorisé")
        void emailVide_Autorise() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   "", dateCreation, actif);
            
            assertEquals("", entreprise.getEmailContact());
        }
    }

    @Nested
    @DisplayName("Tests de Scénarios Métier")
    class BusinessScenarioTests {

        @Test
        @DisplayName("Création entreprise complète valide")
        void creationEntrepriseComplete() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateCreation, actif);
            
            assertNotNull(entreprise.getId());
            assertNotNull(entreprise.getNom());
            assertNotNull(entreprise.getSiret());
            assertTrue(entreprise.isActif());
        }

        @Test
        @DisplayName("Désactivation d'une entreprise")
        void desactivationEntreprise() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateCreation, true);
            
            entreprise.setActif(false);
            assertFalse(entreprise.isActif());
        }

        @Test
        @DisplayName("Modification des coordonnées")
        void modificationCoordonnees() {
            Entreprise entreprise = new Entreprise(id, nom, siret, adresse, pays, 
                                                   emailContact, dateCreation, actif);
            
            entreprise.setAdresse("Nouvelle Adresse");
            entreprise.setEmailContact("nouveau@email.com");
            
            assertEquals("Nouvelle Adresse", entreprise.getAdresse());
            assertEquals("nouveau@email.com", entreprise.getEmailContact());
        }
    }
}
