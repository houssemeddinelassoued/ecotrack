package com.ecotrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité ActifIT")
class ActifITTest {

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

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        entrepriseId = UUID.randomUUID();
        categorieActifId = UUID.randomUUID();
        employeId = UUID.randomUUID();
        numeroSerie = "SN123456789";
        marque = "Dell";
        modele = "Latitude 7430";
        dateAcquisition = LocalDate.of(2024, 3, 1);
        dateMiseEnService = LocalDate.of(2024, 3, 15);
        statut = "EN_SERVICE";
        localisation = "Siège Paris";
    }

    @Nested
    @DisplayName("Tests des Constructeurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur vide doit créer une instance")
        void constructeurVide_CreeInstance() {
            ActifIT actif = new ActifIT();
            
            assertNotNull(actif);
            assertNull(actif.getId());
            assertNull(actif.getEntrepriseId());
        }

        @Test
        @DisplayName("Le constructeur complet doit assigner tous les champs")
        void constructeurComplet_AssigneTousLesChamps() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, statut, localisation);
            
            assertEquals(id, actif.getId());
            assertEquals(entrepriseId, actif.getEntrepriseId());
            assertEquals(categorieActifId, actif.getCategorieActifId());
            assertEquals(employeId, actif.getEmployeId());
            assertEquals(numeroSerie, actif.getNumeroSerie());
            assertEquals(marque, actif.getMarque());
            assertEquals(modele, actif.getModele());
            assertEquals(dateAcquisition, actif.getDateAcquisition());
            assertEquals(dateMiseEnService, actif.getDateMiseEnService());
            assertEquals(statut, actif.getStatut());
            assertEquals(localisation, actif.getLocalisation());
        }
    }

    @Nested
    @DisplayName("Tests des Getters et Setters")
    class GetterSetterTests {

        private ActifIT actif;

        @BeforeEach
        void setUp() {
            actif = new ActifIT();
        }

        @Test
        @DisplayName("getId/setId fonctionnent")
        void getId_setId_Fonctionnent() {
            UUID testId = UUID.randomUUID();
            actif.setId(testId);
            assertEquals(testId, actif.getId());
        }

        @Test
        @DisplayName("getEntrepriseId/setEntrepriseId fonctionnent")
        void getEntrepriseId_setEntrepriseId_Fonctionnent() {
            UUID testEntrepriseId = UUID.randomUUID();
            actif.setEntrepriseId(testEntrepriseId);
            assertEquals(testEntrepriseId, actif.getEntrepriseId());
        }

        @Test
        @DisplayName("getCategorieActifId/setCategorieActifId fonctionnent")
        void getCategorieActifId_setCategorieActifId_Fonctionnent() {
            UUID testCategorieId = UUID.randomUUID();
            actif.setCategorieActifId(testCategorieId);
            assertEquals(testCategorieId, actif.getCategorieActifId());
        }

        @Test
        @DisplayName("getEmployeId/setEmployeId fonctionnent")
        void getEmployeId_setEmployeId_Fonctionnent() {
            UUID testEmployeId = UUID.randomUUID();
            actif.setEmployeId(testEmployeId);
            assertEquals(testEmployeId, actif.getEmployeId());
        }

        @Test
        @DisplayName("setEmployeId peut être null (actif non assigné)")
        void setEmployeId_PeutEtreNull() {
            actif.setEmployeId(null);
            assertNull(actif.getEmployeId());
        }

        @Test
        @DisplayName("getNumeroSerie/setNumeroSerie fonctionnent")
        void getNumeroSerie_setNumeroSerie_Fonctionnent() {
            actif.setNumeroSerie("ABC987654321");
            assertEquals("ABC987654321", actif.getNumeroSerie());
        }

        @Test
        @DisplayName("getMarque/setMarque fonctionnent")
        void getMarque_setMarque_Fonctionnent() {
            actif.setMarque("HP");
            assertEquals("HP", actif.getMarque());
        }

        @Test
        @DisplayName("getModele/setModele fonctionnent")
        void getModele_setModele_Fonctionnent() {
            actif.setModele("EliteBook 850");
            assertEquals("EliteBook 850", actif.getModele());
        }

        @Test
        @DisplayName("getDateAcquisition/setDateAcquisition fonctionnent")
        void getDateAcquisition_setDateAcquisition_Fonctionnent() {
            LocalDate date = LocalDate.of(2023, 6, 1);
            actif.setDateAcquisition(date);
            assertEquals(date, actif.getDateAcquisition());
        }

        @Test
        @DisplayName("getDateMiseEnService/setDateMiseEnService fonctionnent")
        void getDateMiseEnService_setDateMiseEnService_Fonctionnent() {
            LocalDate date = LocalDate.of(2023, 6, 15);
            actif.setDateMiseEnService(date);
            assertEquals(date, actif.getDateMiseEnService());
        }

        @Test
        @DisplayName("getStatut/setStatut fonctionnent")
        void getStatut_setStatut_Fonctionnent() {
            actif.setStatut("HORS_SERVICE");
            assertEquals("HORS_SERVICE", actif.getStatut());
        }

        @Test
        @DisplayName("getLocalisation/setLocalisation fonctionnent")
        void getLocalisation_setLocalisation_Fonctionnent() {
            actif.setLocalisation("Bureau Lyon");
            assertEquals("Bureau Lyon", actif.getLocalisation());
        }
    }

    @Nested
    @DisplayName("Tests des Cas Limites")
    class EdgeCaseTests {

        @Test
        @DisplayName("Actif sans employé assigné (en stock)")
        void actifSansEmploye_EnStock() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, null,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, "EN_STOCK", localisation);
            
            assertNull(actif.getEmployeId());
            assertEquals("EN_STOCK", actif.getStatut());
        }

        @Test
        @DisplayName("Actif sans entrepriseId viole l'isolation")
        void actifSansEntrepriseId_VioleIsolation() {
            ActifIT actif = new ActifIT(id, null, categorieActifId, employeId,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, statut, localisation);
            
            assertNull(actif.getEntrepriseId());
        }

        @Test
        @DisplayName("Date mise en service avant acquisition")
        void dateMiseEnService_AvantAcquisition() {
            LocalDate acquisition = LocalDate.of(2024, 6, 1);
            LocalDate miseEnService = LocalDate.of(2024, 5, 1);
            
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       numeroSerie, marque, modele, acquisition,
                                       miseEnService, statut, localisation);
            
            assertTrue(actif.getDateMiseEnService().isBefore(actif.getDateAcquisition()));
        }

        @Test
        @DisplayName("Numéro de série null autorisé")
        void numeroSerie_NullAutorise() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       null, marque, modele, dateAcquisition,
                                       dateMiseEnService, statut, localisation);
            
            assertNull(actif.getNumeroSerie());
        }
    }

    @Nested
    @DisplayName("Tests de Scénarios Métier")
    class BusinessScenarioTests {

        @Test
        @DisplayName("Provisioning d'un laptop neuf")
        void provisioningLaptopNeuf() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, null,
                                       numeroSerie, "Dell", "Latitude 7430",
                                       LocalDate.now(), null, "EN_STOCK", "Magasin");
            
            assertNull(actif.getEmployeId());
            assertNull(actif.getDateMiseEnService());
            assertEquals("EN_STOCK", actif.getStatut());
        }

        @Test
        @DisplayName("Assignation d'un actif à un employé")
        void assignationActifAEmploye() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, null,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       null, "EN_STOCK", localisation);
            
            UUID newEmployeId = UUID.randomUUID();
            actif.setEmployeId(newEmployeId);
            actif.setDateMiseEnService(LocalDate.now());
            actif.setStatut("EN_SERVICE");
            
            assertEquals(newEmployeId, actif.getEmployeId());
            assertNotNull(actif.getDateMiseEnService());
            assertEquals("EN_SERVICE", actif.getStatut());
        }

        @Test
        @DisplayName("Mise hors service d'un actif")
        void miseHorsService() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, "EN_SERVICE", localisation);
            
            actif.setStatut("HORS_SERVICE");
            actif.setEmployeId(null);
            
            assertEquals("HORS_SERVICE", actif.getStatut());
            assertNull(actif.getEmployeId());
        }

        @Test
        @DisplayName("Changement de localisation")
        void changementLocalisation() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, statut, "Paris");
            
            actif.setLocalisation("Lyon");
            assertEquals("Lyon", actif.getLocalisation());
        }

        @Test
        @DisplayName("Réassignation à un autre employé")
        void reassignationAutreEmploye() {
            ActifIT actif = new ActifIT(id, entrepriseId, categorieActifId, employeId,
                                       numeroSerie, marque, modele, dateAcquisition,
                                       dateMiseEnService, statut, localisation);
            
            UUID nouveauEmployeId = UUID.randomUUID();
            actif.setEmployeId(nouveauEmployeId);
            
            assertEquals(nouveauEmployeId, actif.getEmployeId());
            assertNotEquals(employeId, actif.getEmployeId());
        }
    }
}
