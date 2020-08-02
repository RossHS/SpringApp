package com.khapilov.currency.converter.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Entity
@Table(name = "rates")
public class Rate {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Column(name = "rate_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "rate_value")
    private Double value;

    public Integer getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}