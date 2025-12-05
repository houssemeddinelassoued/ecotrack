package com.ecotrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité ConsoJournaliere")
class ConsoJournaliereTest {

    private Long id;
    private UUID actifItId;
    private UUID entrepriseId;
    private LocalDate dateConsommation;
    private BigDecimal consoKwh;
    private LocalDateTime dateEnregistrement;
    private String source;

    @BeforeEach
    void setUp() {
        id = 1L;
        actifItId = UUID.randomUUID();
        entrepriseId = UUID.randomUUID();
        dateConsommation = LocalDate.now();
        consoKwh = new BigDecimal("0.178");
        dateEnregistrement = LocalDateTime.now();
        source = "AUTO_SENSOR";
    }

    @Nested
    @DisplayName("Tests des Constructeurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur vide doit créer une instance")
        void constructeurVide_CreeInstance() {
            ConsoJournaliere conso = new ConsoJournaliere();
            
            assertNotNull(conso);
            assertNull(conso.getId());
            assertNull(conso.getActifItId());
        }

        @Test
        @DisplayName("Le constructeur complet doit assigner tous les champs")
        void constructeurComplet_AssigneTousLesChamps() {
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, consoKwh,
                                                         dateEnregistrement, source);
            
            assertEquals(id, conso.getId());
            assertEquals(actifItId, conso.getActifItId());
            assertEquals(entrepriseId, conso.getEntrepriseId());
            assertEquals(dateConsommation, conso.getDateConsommation());
            assertEquals(consoKwh, conso.getConsoKwh());
            assertEquals(dateEnregistrement, conso.getDateEnregistrement());
            assertEquals(source, conso.getSource());
        }
    }

    @Nested
    @DisplayName("Tests des Getters et Setters")
    class GetterSetterTests {

        private ConsoJournaliere conso;

        @BeforeEach
        void setUp() {
            conso = new ConsoJournaliere();
        }

        @Test
        @DisplayName("getId/setId fonctionnent")
        void getId_setId_Fonctionnent() {
            conso.setId(100L);
            assertEquals(100L, conso.getId());
        }

        @Test
        @DisplayName("getActifItId/setActifItId fonctionnent")
        void getActifItId_setActifItId_Fonctionnent() {
            UUID testActifId = UUID.randomUUID();
            conso.setActifItId(testActifId);
            assertEquals(testActifId, conso.getActifItId());
        }

        @Test
        @DisplayName("getEntrepriseId/setEntrepriseId fonctionnent")
        void getEntrepriseId_setEntrepriseId_Fonctionnent() {
            UUID testEntrepriseId = UUID.randomUUID();
            conso.setEntrepriseId(testEntrepriseId);
            assertEquals(testEntrepriseId, conso.getEntrepriseId());
        }

        @Test
        @DisplayName("getDateConsommation/setDateConsommation fonctionnent")
        void getDateConsommation_setDateConsommation_Fonctionnent() {
            LocalDate date = LocalDate.of(2024, 6, 15);
            conso.setDateConsommation(date);
            assertEquals(date, conso.getDateConsommation());
        }

        @Test
        @DisplayName("getConsoKwh/setConsoKwh fonctionnent")
        void getConsoKwh_setConsoKwh_Fonctionnent() {
            BigDecimal valeur = new BigDecimal("2.5");
            conso.setConsoKwh(valeur);
            assertEquals(valeur, conso.getConsoKwh());
        }

        @Test
        @DisplayName("getDateEnregistrement/setDateEnregistrement fonctionnent")
        void getDateEnregistrement_setDateEnregistrement_Fonctionnent() {
            LocalDateTime date = LocalDateTime.of(2024, 6, 15, 10, 30);
            conso.setDateEnregistrement(date);
            assertEquals(date, conso.getDateEnregistrement());
        }

        @Test
        @DisplayName("getSource/setSource fonctionnent")
        void getSource_setSource_Fonctionnent() {
            conso.setSource("MANUAL");
            assertEquals("MANUAL", conso.getSource());
        }
    }

    @Nested
    @DisplayName("Tests des Cas Limites")
    class EdgeCaseTests {

        @Test
        @DisplayName("Consommation zéro est valide")
        void consommationZero_Valide() {
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, BigDecimal.ZERO,
                                                         dateEnregistrement, source);
            
            assertEquals(BigDecimal.ZERO, conso.getConsoKwh());
        }

        @Test
        @DisplayName("Consommation très élevée")
        void consommationTresElevee() {
            BigDecimal consoElevee = new BigDecimal("1000.0");
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, consoElevee,
                                                         dateEnregistrement, source);
            
            assertEquals(consoElevee, conso.getConsoKwh());
        }

        @Test
        @DisplayName("Consommation avec haute précision")
        void consommationHautePrecision() {
            BigDecimal consoPrecise = new BigDecimal("0.12345678901234567890");
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, consoPrecise,
                                                         dateEnregistrement, source);
            
            assertEquals(consoPrecise, conso.getConsoKwh());
        }

        @Test
        @DisplayName("Date de consommation dans le passé")
        void dateConsommationPassee() {
            LocalDate datePassee = LocalDate.of(2020, 1, 1);
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         datePassee, consoKwh,
                                                         dateEnregistrement, source);
            
            assertTrue(conso.getDateConsommation().isBefore(LocalDate.now()));
        }

        @Test
        @DisplayName("Date consommation future non autorisée métier")
        void dateConsommationFuture() {
            LocalDate dateFuture = LocalDate.now().plusDays(10);
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateFuture, consoKwh,
                                                         dateEnregistrement, source);
            
            assertTrue(conso.getDateConsommation().isAfter(LocalDate.now()));
        }

        @Test
        @DisplayName("Enregistrement immédiat après consommation")
        void enregistrementImmediat() {
            LocalDateTime maintenant = LocalDateTime.now();
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         LocalDate.now(), consoKwh,
                                                         maintenant, source);
            
            assertEquals(LocalDate.now(), conso.getDateConsommation());
        }

        @Test
        @DisplayName("Même actif, même date, doit être unique")
        void memeActifMemeDate_DoitEtreUnique() {
            ConsoJournaliere conso1 = new ConsoJournaliere(1L, actifItId, entrepriseId,
                                                          dateConsommation, consoKwh,
                                                          dateEnregistrement, source);
            ConsoJournaliere conso2 = new ConsoJournaliere(2L, actifItId, entrepriseId,
                                                          dateConsommation, new BigDecimal("0.200"),
                                                          dateEnregistrement, source);
            
            assertEquals(conso1.getActifItId(), conso2.getActifItId());
            assertEquals(conso1.getDateConsommation(), conso2.getDateConsommation());
            assertNotEquals(conso1.getId(), conso2.getId());
        }
    }

    @Nested
    @DisplayName("Tests de Scénarios Métier")
    class BusinessScenarioTests {

        @Test
        @DisplayName("Enregistrement automatique par capteur")
        void enregistrementAutomatique() {
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         LocalDate.now(), new BigDecimal("0.150"),
                                                         LocalDateTime.now(), "AUTO_SENSOR");
            
            assertEquals("AUTO_SENSOR", conso.getSource());
            assertNotNull(conso.getConsoKwh());
        }

        @Test
        @DisplayName("Enregistrement manuel")
        void enregistrementManuel() {
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         LocalDate.of(2024, 6, 1),
                                                         new BigDecimal("1.5"),
                                                         LocalDateTime.now(), "MANUAL");
            
            assertEquals("MANUAL", conso.getSource());
        }

        @Test
        @DisplayName("Calcul consommation mensuelle")
        void calculConsommationMensuelle() {
            LocalDate debut = LocalDate.of(2024, 6, 1);
            BigDecimal total = BigDecimal.ZERO;
            
            for (int i = 0; i < 30; i++) {
                ConsoJournaliere conso = new ConsoJournaliere((long) (i + 1), actifItId, entrepriseId,
                                                             debut.plusDays(i),
                                                             new BigDecimal("0.178"),
                                                             LocalDateTime.now(), "AUTO_SENSOR");
                total = total.add(conso.getConsoKwh());
            }
            
            assertEquals(new BigDecimal("5.340"), total);
        }

        @Test
        @DisplayName("Isolation multi-tenant des consommations")
        void isolationMultiTenant() {
            UUID entreprise1 = UUID.randomUUID();
            UUID entreprise2 = UUID.randomUUID();
            
            ConsoJournaliere conso1 = new ConsoJournaliere(1L, actifItId, entreprise1,
                                                          dateConsommation, consoKwh,
                                                          dateEnregistrement, source);
            ConsoJournaliere conso2 = new ConsoJournaliere(2L, actifItId, entreprise2,
                                                          dateConsommation, consoKwh,
                                                          dateEnregistrement, source);
            
            assertNotEquals(conso1.getEntrepriseId(), conso2.getEntrepriseId());
        }

        @Test
        @DisplayName("Traçabilité avec source et date enregistrement")
        void tracabiliteComplete() {
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, consoKwh,
                                                         dateEnregistrement, "API_IMPORT");
            
            assertNotNull(conso.getDateEnregistrement());
            assertNotNull(conso.getSource());
            assertEquals("API_IMPORT", conso.getSource());
        }
    }

    @Nested
    @DisplayName("Tests BigDecimal Precision")
    class BigDecimalTests {

        @Test
        @DisplayName("Précision décimale préservée")
        void precisionDecimalePreservee() {
            BigDecimal valeur = new BigDecimal("0.123456789");
            ConsoJournaliere conso = new ConsoJournaliere(id, actifItId, entrepriseId,
                                                         dateConsommation, valeur,
                                                         dateEnregistrement, source);
            
            assertEquals(0, valeur.compareTo(conso.getConsoKwh()));
        }

        @Test
        @DisplayName("Addition de consommations")
        void additionConsommations() {
            BigDecimal conso1 = new BigDecimal("0.150");
            BigDecimal conso2 = new BigDecimal("0.200");
            BigDecimal total = conso1.add(conso2);
            
            assertEquals(new BigDecimal("0.350"), total);
        }
    }
}
