package com.jianzhi.core.company.dao;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 16/1/4.
 */
public interface CompanyDao extends JpaRepository<Company, Long> {
    Company findByUser(User user);
}
