package com.ecotrack.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité ConsoJournaliere - Donnée de télémétrie
 * Représente la consommation énergétique journalière d'un actif IT.
 * Table de faits (Time-Series) avec fort volume de données.
 */
public class ConsoJournaliere {

    private Long id;
    private UUID actifItId;
    private UUID entrepriseId;
    private LocalDate dateConsommation;
    private BigDecimal consoKwh;
    private LocalDateTime dateEnregistrement;
    private String source;

    /**
     * Constructeur vide
     */
    public ConsoJournaliere() {
    }

    /**
     * Constructeur complet
     */
    public ConsoJournaliere(Long id, UUID actifItId, UUID entrepriseId, LocalDate dateConsommation, 
                            BigDecimal consoKwh, LocalDateTime dateEnregistrement, String source) {
        this.id = id;
        this.actifItId = actifItId;
        this.entrepriseId = entrepriseId;
        this.dateConsommation = dateConsommation;
        this.consoKwh = consoKwh;
        this.dateEnregistrement = dateEnregistrement;
        this.source = source;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getActifItId() {
        return actifItId;
    }

    public void setActifItId(UUID actifItId) {
        this.actifItId = actifItId;
    }

    public UUID getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(UUID entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public LocalDate getDateConsommation() {
        return dateConsommation;
    }

    public void setDateConsommation(LocalDate dateConsommation) {
        this.dateConsommation = dateConsommation;
    }

    public BigDecimal getConsoKwh() {
        return consoKwh;
    }

    public void setConsoKwh(BigDecimal consoKwh) {
        this.consoKwh = consoKwh;
    }

    public LocalDateTime getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
