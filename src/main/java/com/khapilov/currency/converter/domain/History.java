package com.khapilov.currency.converter.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Entity
@Table(name = "history_converter")
public class History {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "convert_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "value_from")
    private Double valueFrom;

    @Column(name = "value_to")
    private Double valueTo;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_from", referencedColumnName = "id")
    private Currency fromCurrency;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_to", referencedColumnName = "id")
    private Currency toCurrency;

    public History() {
    }

    public History(User user, Date date, Double valueFrom, Double valueTo, Currency fromCurrency, Currency toCurrency) {
        this.user = user;
        this.date = date;
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Double valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Double getValueTo() {
        return valueTo;
    }

    public void setValueTo(Double valueTo) {
        this.valueTo = valueTo;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }
}
