package com.jianzhi.core.file.service;

import com.jianzhi.core.file.dao.FileDao;
import com.jianzhi.core.file.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Value("{file.path.image}")
    private String imagePath;

    @Override
    public void saveDistinct(File file, MultipartFile fileData) {
        fileDao.save(file);
        //TODO: save to local
    }

}
