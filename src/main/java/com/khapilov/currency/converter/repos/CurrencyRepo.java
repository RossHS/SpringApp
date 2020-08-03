package com.khapilov.currency.converter.repos;

import com.khapilov.currency.converter.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
    Currency findByCharCode(String charCode);

    Currency findByCbrId(String id);
}
