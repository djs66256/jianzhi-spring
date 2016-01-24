package com.jianzhi.core.file.service;

import com.jianzhi.core.file.dao.FileDao;
import com.jianzhi.core.file.model.File;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

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

    @Override
    public void saveHeadImage(User user, MultipartFile file) throws Exception{
        FileOutputStream outputStream = new FileOutputStream(user.getId().toString());
        outputStream.write(file.getBytes());
        outputStream.close();
    }
}
