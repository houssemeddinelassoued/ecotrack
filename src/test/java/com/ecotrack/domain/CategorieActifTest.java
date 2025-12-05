package com.ecotrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité CategorieActif")
class CategorieActifTest {

    private UUID id;
    private UUID entrepriseId;
    private String code;
    private String libelle;
    private String description;
    private Double consommationMoyenneKwh;
    private boolean estGlobal;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        entrepriseId = UUID.randomUUID();
        code = "LAPTOP";
        libelle = "Ordinateur Portable";
        description = "Laptop professionnel standard";
        consommationMoyenneKwh = 65.0;
        estGlobal = false;
    }

    @Nested
    @DisplayName("Tests des Constructeurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur vide doit créer une instance")
        void constructeurVide_CreeInstance() {
            CategorieActif categorie = new CategorieActif();
            
            assertNotNull(categorie);
            assertNull(categorie.getId());
            assertFalse(categorie.isEstGlobal());
        }

        @Test
        @DisplayName("Le constructeur complet doit assigner tous les champs")
        void constructeurComplet_AssigneTousLesChamps() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, code, libelle, 
                                                          description, consommationMoyenneKwh, estGlobal);
            
            assertEquals(id, categorie.getId());
            assertEquals(entrepriseId, categorie.getEntrepriseId());
            assertEquals(code, categorie.getCode());
            assertEquals(libelle, categorie.getLibelle());
            assertEquals(description, categorie.getDescription());
            assertEquals(consommationMoyenneKwh, categorie.getConsommationMoyenneKwh());
            assertFalse(categorie.isEstGlobal());
        }
    }

    @Nested
    @DisplayName("Tests des Getters et Setters")
    class GetterSetterTests {

        private CategorieActif categorie;

        @BeforeEach
        void setUp() {
            categorie = new CategorieActif();
        }

        @Test
        @DisplayName("getId/setId fonctionnent")
        void getId_setId_Fonctionnent() {
            UUID testId = UUID.randomUUID();
            categorie.setId(testId);
            assertEquals(testId, categorie.getId());
        }

        @Test
        @DisplayName("getEntrepriseId/setEntrepriseId fonctionnent")
        void getEntrepriseId_setEntrepriseId_Fonctionnent() {
            UUID entrepriseId = UUID.randomUUID();
            categorie.setEntrepriseId(entrepriseId);
            assertEquals(entrepriseId, categorie.getEntrepriseId());
        }

        @Test
        @DisplayName("getCode/setCode fonctionnent")
        void getCode_setCode_Fonctionnent() {
            categorie.setCode("SERVER");
            assertEquals("SERVER", categorie.getCode());
        }

        @Test
        @DisplayName("getLibelle/setLibelle fonctionnent")
        void getLibelle_setLibelle_Fonctionnent() {
            categorie.setLibelle("Serveur");
            assertEquals("Serveur", categorie.getLibelle());
        }

        @Test
        @DisplayName("getDescription/setDescription fonctionnent")
        void getDescription_setDescription_Fonctionnent() {
            categorie.setDescription("Description test");
            assertEquals("Description test", categorie.getDescription());
        }

        @Test
        @DisplayName("getConsommationMoyenneKwh/setConsommationMoyenneKwh fonctionnent")
        void getConsoMoyenne_setConsoMoyenne_Fonctionnent() {
            categorie.setConsommationMoyenneKwh(150.5);
            assertEquals(150.5, categorie.getConsommationMoyenneKwh());
        }

        @Test
        @DisplayName("isEstGlobal/setEstGlobal fonctionnent")
        void isEstGlobal_setEstGlobal_Fonctionnent() {
            categorie.setEstGlobal(true);
            assertTrue(categorie.isEstGlobal());
            
            categorie.setEstGlobal(false);
            assertFalse(categorie.isEstGlobal());
        }
    }

    @Nested
    @DisplayName("Tests des Cas Limites")
    class EdgeCaseTests {

        @Test
        @DisplayName("Catégorie globale sans entrepriseId")
        void categorieGlobale_SansEntrepriseId() {
            CategorieActif categorie = new CategorieActif(id, null, code, libelle, 
                                                          description, consommationMoyenneKwh, true);
            
            assertNull(categorie.getEntrepriseId());
            assertTrue(categorie.isEstGlobal());
        }

        @Test
        @DisplayName("Consommation moyenne nulle")
        void consommationMoyenne_Nulle() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, code, libelle, 
                                                          description, 0.0, estGlobal);
            
            assertEquals(0.0, categorie.getConsommationMoyenneKwh());
        }

        @Test
        @DisplayName("Consommation moyenne null autorisée")
        void consommationMoyenne_NullAutorisee() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, code, libelle, 
                                                          description, null, estGlobal);
            
            assertNull(categorie.getConsommationMoyenneKwh());
        }

        @Test
        @DisplayName("Consommation très élevée")
        void consommationTresElevee() {
            Double consoElevee = 100000.0;
            CategorieActif categorie = new CategorieActif(id, entrepriseId, code, libelle, 
                                                          description, consoElevee, estGlobal);
            
            assertEquals(consoElevee, categorie.getConsommationMoyenneKwh());
        }
    }

    @Nested
    @DisplayName("Tests de Scénarios Métier")
    class BusinessScenarioTests {

        @Test
        @DisplayName("Création catégorie laptop standard")
        void creationCategorieLaptop() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, "LAPTOP", 
                                                          "Ordinateur Portable", 
                                                          "Laptop professionnel", 65.0, false);
            
            assertEquals("LAPTOP", categorie.getCode());
            assertEquals(65.0, categorie.getConsommationMoyenneKwh());
            assertFalse(categorie.isEstGlobal());
        }

        @Test
        @DisplayName("Création catégorie serveur haute consommation")
        void creationCategorieServeur() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, "SERVER", 
                                                          "Serveur", 
                                                          "Serveur de production", 500.0, false);
            
            assertEquals("SERVER", categorie.getCode());
            assertEquals(500.0, categorie.getConsommationMoyenneKwh());
        }

        @Test
        @DisplayName("Catégorie globale partagée entre entreprises")
        void categorieGlobalePartagee() {
            CategorieActif categorie = new CategorieActif(id, null, "MONITOR", 
                                                          "Écran", 
                                                          "Écran standard", 30.0, true);
            
            assertTrue(categorie.isEstGlobal());
            assertNull(categorie.getEntrepriseId());
        }

        @Test
        @DisplayName("Modification consommation moyenne")
        void modificationConsommationMoyenne() {
            CategorieActif categorie = new CategorieActif(id, entrepriseId, code, libelle, 
                                                          description, 65.0, estGlobal);
            
            categorie.setConsommationMoyenneKwh(70.0);
            assertEquals(70.0, categorie.getConsommationMoyenneKwh());
        }
    }
}
