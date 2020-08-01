package com.khapilov.currency.converter.repos;

import com.khapilov.currency.converter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ross Khapilov
 * @version 1.0 01.08.2020
 */
public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
