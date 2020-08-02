package com.khapilov.currency.converter.domain;

import javax.persistence.*;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Entity
@Table(name = "currency_table")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cbr_id", unique = true)
    private String cbrId;

    @Column(name = "num_code")
    private Integer numCode;

    @Column(name = "char_code")
    private String charCode;

    @Column
    private Integer nominal;

    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCbrId() {
        return cbrId;
    }

    public void setCbrId(String cbrId) {
        this.cbrId = cbrId;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Currency)) {
            return false;
        }
        Currency c = (Currency) obj;
        return c.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
