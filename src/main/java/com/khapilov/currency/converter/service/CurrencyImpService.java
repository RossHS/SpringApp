package com.khapilov.currency.converter.service;

import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Service
public interface CurrencyImpService {
    void importCurrency();

    void importCurrency(GregorianCalendar date);
}
