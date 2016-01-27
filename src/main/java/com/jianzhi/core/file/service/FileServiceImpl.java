package com.jianzhi.core.file.service;

import com.jianzhi.core.file.dao.FileDao;
import com.jianzhi.core.file.model.File;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Value("${file.path.image.head}")
    private String headImagePath;

    @Override
    public void saveDistinct(File file, MultipartFile fileData) {
        fileDao.save(file);
        //TODO: save to local
    }

    @Override
    public String saveHeadImage(User user, MultipartFile file) throws Exception{
        String name = UUID.randomUUID().toString().replace("-","");
        String originalName = file.getOriginalFilename();
        String extensionName = originalName.substring(originalName.lastIndexOf(".")+1);
        String myFileName = name + "." + extensionName;

        String path = headImagePath + myFileName;

        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(file.getBytes());
        outputStream.close();

        return myFileName;
    }
}
