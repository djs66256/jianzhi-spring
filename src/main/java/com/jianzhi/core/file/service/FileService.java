package com.jianzhi.core.file.service;

import com.jianzhi.core.file.model.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by daniel on 15/7/19.
 */
public interface FileService {

    void saveDistinct(File file, MultipartFile fileData);

}
