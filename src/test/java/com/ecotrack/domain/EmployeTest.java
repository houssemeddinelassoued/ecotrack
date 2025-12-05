package com.ecotrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires complets pour la classe Employe
 * Objectif : Couverture > 90%
 * Framework : JUnit 5
 */
@DisplayName("Tests de l'entité Employe")
class EmployeTest {

    private UUID employeId;
    private UUID entrepriseId;
    private String nom;
    private String prenom;
    private String email;
    private String departement;
    private String matricule;
    private LocalDate dateEmbauche;
    private boolean actif;

    @BeforeEach
    void setUp() {
        employeId = UUID.randomUUID();
        entrepriseId = UUID.randomUUID();
        nom = "Dupont";
        prenom = "Jean";
        email = "jean.dupont@example.com";
        departement = "IT";
        matricule = "EMP001";
        dateEmbauche = LocalDate.of(2023, 1, 15);
        actif = true;
    }

    @Nested
    @DisplayName("Tests des Constructeurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur vide doit créer une instance non-null")
        void constructeurVide_DoitCreerInstanceNonNull() {
            // Act
            Employe employe = new Employe();

            // Assert
            assertNotNull(employe);
            assertNull(employe.getId());
            assertNull(employe.getEntrepriseId());
            assertNull(employe.getNom());
            assertNull(employe.getPrenom());
            assertNull(employe.getEmail());
            assertNull(employe.getDepartement());
            assertNull(employe.getMatricule());
            assertNull(employe.getDateEmbauche());
            assertFalse(employe.isActif());
        }

        @Test
        @DisplayName("Le constructeur complet doit assigner tous les champs correctement")
        void constructeurComplet_DoitAssignerTousLesChamps() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateEmbauche, actif);

            // Assert
            assertNotNull(employe);
            assertEquals(employeId, employe.getId());
            assertEquals(entrepriseId, employe.getEntrepriseId());
            assertEquals(nom, employe.getNom());
            assertEquals(prenom, employe.getPrenom());
            assertEquals(email, employe.getEmail());
            assertEquals(departement, employe.getDepartement());
            assertEquals(matricule, employe.getMatricule());
            assertEquals(dateEmbauche, employe.getDateEmbauche());
            assertTrue(employe.isActif());
        }

        @Test
        @DisplayName("Le constructeur doit accepter des valeurs null")
        void constructeurComplet_DoitAccepterValeursNull() {
            // Act
            Employe employe = new Employe(null, null, null, null, null,
                    null, null, null, false);

            // Assert
            assertNotNull(employe);
            assertNull(employe.getId());
            assertNull(employe.getEntrepriseId());
            assertNull(employe.getNom());
            assertNull(employe.getPrenom());
            assertNull(employe.getEmail());
            assertNull(employe.getDepartement());
            assertNull(employe.getMatricule());
            assertNull(employe.getDateEmbauche());
            assertFalse(employe.isActif());
        }

        @Test
        @DisplayName("Le constructeur doit créer un employé inactif")
        void constructeurComplet_DoitCreerEmployeInactif() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateEmbauche, false);

            // Assert
            assertFalse(employe.isActif());
        }
    }

    @Nested
    @DisplayName("Tests des Getters et Setters")
    class GetterSetterTests {

        private Employe employe;

        @BeforeEach
        void setUp() {
            employe = new Employe();
        }

        @Test
        @DisplayName("getId/setId doivent fonctionner correctement")
        void getId_setId_DoiventFonctionner() {
            // Arrange
            UUID id = UUID.randomUUID();

            // Act
            employe.setId(id);

            // Assert
            assertEquals(id, employe.getId());
        }

        @Test
        @DisplayName("setId doit accepter null")
        void setId_DoitAccepterNull() {
            // Act
            employe.setId(null);

            // Assert
            assertNull(employe.getId());
        }

        @Test
        @DisplayName("getEntrepriseId/setEntrepriseId doivent fonctionner correctement")
        void getEntrepriseId_setEntrepriseId_DoiventFonctionner() {
            // Arrange
            UUID entrepriseId = UUID.randomUUID();

            // Act
            employe.setEntrepriseId(entrepriseId);

            // Assert
            assertEquals(entrepriseId, employe.getEntrepriseId());
        }

        @Test
        @DisplayName("setEntrepriseId rejette null (validation active)")
        void setEntrepriseId_RejetteNull() {
            // Act & Assert
            assertThrows(NullPointerException.class, () -> employe.setEntrepriseId(null));
        }

        @Test
        @DisplayName("getNom/setNom doivent fonctionner correctement")
        void getNom_setNom_DoiventFonctionner() {
            // Act
            employe.setNom("Martin");

            // Assert
            assertEquals("Martin", employe.getNom());
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", "   ", "A", "Jean-Pierre-François-Marie"})
        @DisplayName("setNom doit accepter différentes valeurs")
        void setNom_DoitAccepterDifferentesValeurs(String nom) {
            // Act
            employe.setNom(nom);

            // Assert
            assertEquals(nom, employe.getNom());
        }

        @Test
        @DisplayName("getPrenom/setPrenom doivent fonctionner correctement")
        void getPrenom_setPrenom_DoiventFonctionner() {
            // Act
            employe.setPrenom("Marie");

            // Assert
            assertEquals("Marie", employe.getPrenom());
        }

        @Test
        @DisplayName("setPrenom doit accepter null")
        void setPrenom_DoitAccepterNull() {
            // Act
            employe.setPrenom(null);

            // Assert
            assertNull(employe.getPrenom());
        }

        @Test
        @DisplayName("getEmail/setEmail doivent fonctionner correctement")
        void getEmail_setEmail_DoiventFonctionner() {
            // Act
            employe.setEmail("test@example.com");

            // Assert
            assertEquals("test@example.com", employe.getEmail());
        }

        @Test
        @DisplayName("setEmail valide un email correct")
        void setEmail_ValideEmailCorrect() {
            // Act
            employe.setEmail("valide@example.com");

            // Assert
            assertEquals("valide@example.com", employe.getEmail());
        }

        @Test
        @DisplayName("getDepartement/setDepartement doivent fonctionner correctement")
        void getDepartement_setDepartement_DoiventFonctionner() {
            // Act
            employe.setDepartement("Marketing");

            // Assert
            assertEquals("Marketing", employe.getDepartement());
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", "IT", "Ressources Humaines", "R&D"})
        @DisplayName("setDepartement doit accepter différentes valeurs")
        void setDepartement_DoitAccepterDifferentesValeurs(String dept) {
            // Act
            employe.setDepartement(dept);

            // Assert
            assertEquals(dept, employe.getDepartement());
        }

        @Test
        @DisplayName("getMatricule/setMatricule doivent fonctionner correctement")
        void getMatricule_setMatricule_DoiventFonctionner() {
            // Act
            employe.setMatricule("EMP999");

            // Assert
            assertEquals("EMP999", employe.getMatricule());
        }

        @Test
        @DisplayName("setMatricule doit accepter null")
        void setMatricule_DoitAccepterNull() {
            // Act
            employe.setMatricule(null);

            // Assert
            assertNull(employe.getMatricule());
        }

        @Test
        @DisplayName("getDateEmbauche/setDateEmbauche doivent fonctionner correctement")
        void getDateEmbauche_setDateEmbauche_DoiventFonctionner() {
            // Arrange
            LocalDate date = LocalDate.of(2024, 6, 1);

            // Act
            employe.setDateEmbauche(date);

            // Assert
            assertEquals(date, employe.getDateEmbauche());
        }

        @Test
        @DisplayName("setDateEmbauche doit accepter null")
        void setDateEmbauche_DoitAccepterNull() {
            // Act
            employe.setDateEmbauche(null);

            // Assert
            assertNull(employe.getDateEmbauche());
        }

        @Test
        @DisplayName("isActif/setActif doivent fonctionner correctement")
        void isActif_setActif_DoiventFonctionner() {
            // Act
            employe.setActif(true);

            // Assert
            assertTrue(employe.isActif());

            // Act
            employe.setActif(false);

            // Assert
            assertFalse(employe.isActif());
        }
    }

    @Nested
    @DisplayName("Tests des Cas Limites et Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Employé sans entrepriseId viole l'isolation multi-tenant")
        void employeSansEntrepriseId_VioleIsolation() {
            // Act
            Employe employe = new Employe(employeId, null, nom, prenom, email,
                    departement, matricule, dateEmbauche, actif);

            // Assert - RISQUE MÉTIER DÉTECTÉ
            assertNull(employe.getEntrepriseId(), 
                "RISQUE: Un employé sans entrepriseId viole le principe d'isolation");
        }

        @Test
        @DisplayName("Date d'embauche dans le futur (cas limite)")
        void dateEmbaucheFuture_CasLimite() {
            // Arrange
            LocalDate dateFuture = LocalDate.now().plusDays(30);

            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateFuture, actif);

            // Assert - RISQUE MÉTIER
            assertTrue(employe.getDateEmbauche().isAfter(LocalDate.now()),
                "RISQUE: Date d'embauche dans le futur autorisée");
        }

        @Test
        @DisplayName("Date d'embauche très ancienne")
        void dateEmbaucheAncienne_DoitEtreAcceptee() {
            // Arrange
            LocalDate dateAncienne = LocalDate.of(1980, 1, 1);

            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateAncienne, actif);

            // Assert
            assertEquals(dateAncienne, employe.getDateEmbauche());
        }

        @Test
        @DisplayName("Employé inactif doit être géré correctement")
        void employeInactif_DoitEtreGere() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateEmbauche, false);

            // Assert
            assertFalse(employe.isActif());
            assertNotNull(employe.getDateEmbauche());
        }

        @Test
        @DisplayName("Deux employés avec des UUIDs différents")
        void deuxEmployes_DoiventAvoirUUIDsDifferents() {
            // Arrange
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            // Act
            Employe employe1 = new Employe(id1, entrepriseId, nom, prenom, email,
                    departement, "MAT001", dateEmbauche, actif);
            Employe employe2 = new Employe(id2, entrepriseId, nom, prenom, email,
                    departement, "MAT002", dateEmbauche, actif);

            // Assert
            assertNotEquals(employe1.getId(), employe2.getId());
        }

        @Test
        @DisplayName("Même matricule pour deux employés (pas de validation)")
        void memeMatricule_PasDValidation() {
            // Act
            Employe employe1 = new Employe(UUID.randomUUID(), entrepriseId, nom, prenom, email,
                    departement, "MAT001", dateEmbauche, actif);
            Employe employe2 = new Employe(UUID.randomUUID(), entrepriseId, nom, prenom, email,
                    departement, "MAT001", dateEmbauche, actif);

            // Assert - RISQUE MÉTIER
            assertEquals(employe1.getMatricule(), employe2.getMatricule(),
                "RISQUE: Pas de validation d'unicité du matricule");
        }

        @Test
        @DisplayName("Email vide autorisé (pas de validation)")
        void emailVide_PasDValidation() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, "",
                    departement, matricule, dateEmbauche, actif);

            // Assert
            assertEquals("", employe.getEmail());
        }

        @Test
        @DisplayName("Tous les champs String peuvent être vides")
        void tousChampsString_PeuventEtreVides() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, "", "", "",
                    "", "", dateEmbauche, actif);

            // Assert
            assertEquals("", employe.getNom());
            assertEquals("", employe.getPrenom());
            assertEquals("", employe.getEmail());
            assertEquals("", employe.getDepartement());
            assertEquals("", employe.getMatricule());
        }
    }

    @Nested
    @DisplayName("Tests de Scénarios Métier")
    class BusinessScenarioTests {

        @Test
        @DisplayName("Création d'un employé complet valide")
        void creationEmployeCompletValide() {
            // Act
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateEmbauche, actif);

            // Assert
            assertNotNull(employe.getId());
            assertNotNull(employe.getEntrepriseId());
            assertNotNull(employe.getNom());
            assertNotNull(employe.getPrenom());
            assertNotNull(employe.getEmail());
            assertNotNull(employe.getDepartement());
            assertNotNull(employe.getMatricule());
            assertNotNull(employe.getDateEmbauche());
            assertTrue(employe.isActif());
        }

        @Test
        @DisplayName("Modification du statut actif d'un employé")
        void modificationStatutActif() {
            // Arrange
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    departement, matricule, dateEmbauche, true);

            // Act
            employe.setActif(false);

            // Assert
            assertFalse(employe.isActif());
        }

        @Test
        @DisplayName("Changement de département")
        void changementDepartement() {
            // Arrange
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, email,
                    "IT", matricule, dateEmbauche, actif);

            // Act
            employe.setDepartement("Marketing");

            // Assert
            assertEquals("Marketing", employe.getDepartement());
        }

        @Test
        @DisplayName("Mise à jour de l'email")
        void miseAJourEmail() {
            // Arrange
            Employe employe = new Employe(employeId, entrepriseId, nom, prenom, 
                    "ancien@example.com", departement, matricule, dateEmbauche, actif);

            // Act
            employe.setEmail("nouveau@example.com");

            // Assert
            assertEquals("nouveau@example.com", employe.getEmail());
        }

        @Test
        @DisplayName("Employés de la même entreprise")
        void employesMemeEntreprise() {
            // Arrange
            UUID memeEntrepriseId = UUID.randomUUID();

            // Act
            Employe employe1 = new Employe(UUID.randomUUID(), memeEntrepriseId, "Dupont", "Jean",
                    "jean@example.com", "IT", "MAT001", dateEmbauche, actif);
            Employe employe2 = new Employe(UUID.randomUUID(), memeEntrepriseId, "Martin", "Marie",
                    "marie@example.com", "HR", "MAT002", dateEmbauche, actif);

            // Assert
            assertEquals(employe1.getEntrepriseId(), employe2.getEntrepriseId());
            assertNotEquals(employe1.getId(), employe2.getId());
        }
    }

    @Nested
    @DisplayName("Tests de Robustesse")
    class RobustnessTests {

        @Test
        @DisplayName("Modification successive de tous les champs")
        void modificationSuccessiveTousChamps() {
            // Arrange
            Employe employe = new Employe();

            // Act & Assert
            employe.setId(employeId);
            assertEquals(employeId, employe.getId());

            employe.setEntrepriseId(entrepriseId);
            assertEquals(entrepriseId, employe.getEntrepriseId());

            employe.setNom(nom);
            assertEquals(nom, employe.getNom());

            employe.setPrenom(prenom);
            assertEquals(prenom, employe.getPrenom());

            employe.setEmail(email);
            assertEquals(email, employe.getEmail());

            employe.setDepartement(departement);
            assertEquals(departement, employe.getDepartement());

            employe.setMatricule(matricule);
            assertEquals(matricule, employe.getMatricule());

            employe.setDateEmbauche(dateEmbauche);
            assertEquals(dateEmbauche, employe.getDateEmbauche());

            employe.setActif(actif);
            assertEquals(actif, employe.isActif());
        }

        @Test
        @DisplayName("Réassignation multiple des UUIDs")
        void reassignationMultipleUUIDs() {
            // Arrange
            Employe employe = new Employe();
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            // Act
            employe.setId(id1);
            assertEquals(id1, employe.getId());

            employe.setId(id2);
            assertEquals(id2, employe.getId());

            employe.setId(null);
            assertNull(employe.getId());
        }

        @Test
        @DisplayName("Valeurs extrêmes pour les chaînes")
        void valeursExtremesChaines() {
            // Arrange
            Employe employe = new Employe();
            String longString = "A".repeat(1000);

            // Act & Assert
            employe.setNom(longString);
            assertEquals(longString, employe.getNom());

            employe.setEmail(longString + "@example.com");
            assertEquals(longString + "@example.com", employe.getEmail());
        }
    }
}
