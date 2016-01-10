package com.jianzhi.core.company.service;

import com.jianzhi.core.company.model.Company;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 16/1/4.
 */
public interface CompanyService {

    void save(Company company);
    Company findById(Long id);
}
