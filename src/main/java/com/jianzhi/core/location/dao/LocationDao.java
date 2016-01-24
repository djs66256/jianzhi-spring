package com.jianzhi.core.location.dao;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationDao extends JpaRepository<Location, Long>, LocationDaoCustom {

    Location findByCompany(Company company);

}