package com.jianzhi.core.company.service;

import com.jianzhi.core.company.dao.CompanyDao;
import com.jianzhi.core.company.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 16/1/4.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public Company findById(Long id) {
        return findById(id);
    }
}
