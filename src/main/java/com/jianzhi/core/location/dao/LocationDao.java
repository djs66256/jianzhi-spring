package com.jianzhi.core.location.dao;

import com.jianzhi.core.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 16/1/4.
 */
public interface LocationDao extends JpaRepository<Location, Long> {
}
