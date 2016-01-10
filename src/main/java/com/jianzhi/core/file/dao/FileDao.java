package com.jianzhi.core.file.dao;

import com.jianzhi.core.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long>{
}
