package com.khapilov.currency.converter.repos;

import com.khapilov.currency.converter.domain.History;
import com.khapilov.currency.converter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Repository
public interface HistoryConverterRepo extends JpaRepository<History, Integer> {
    List<History> findByUser(User user);
}
