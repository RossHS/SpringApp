package com.khapilov.currency.converter.repos;

import com.khapilov.currency.converter.domain.Currency;
import com.khapilov.currency.converter.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Repository
public interface RateRepo extends JpaRepository<Rate, Integer> {
    List<Rate> findAllByDateOrderByValueAsc(Date date);

    Rate findFirstByCurrencyOrderByDateDesc(Currency currency);
}
