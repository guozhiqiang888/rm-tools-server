package com.farben.readme.service.impl;

import com.farben.readme.service.IFilesService;
import com.farben.readme.util.response.Response;
import com.farben.readme.util.response.ResponseCode;
import com.farben.readme.util.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class FilesServiceImpl implements IFilesService {

    private final static Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Override
    public Response upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            logger.error("====>upload one file failed: {}", ResponseCode.FILE_NOT_SELECT_ERROR.getMsg());
            return ResponseUtil.error(ResponseCode.FILE_NOT_SELECT_ERROR);
        }

        String filePath = new File("files_upload").getAbsolutePath();
        logger.info("====>filePath: {}", filePath);
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        fileUpload = new File(filePath, file.getOriginalFilename());
        if (fileUpload.exists()) {
            logger.error("====>upload one file failed: {}", ResponseCode.FILE_IS_EXIST_ERROR.getMsg());
            return ResponseUtil.error(ResponseCode.FILE_IS_EXIST_ERROR);
        }

        try {
            file.transferTo(fileUpload);
            logger.error("====>upload one file success<====");
            return ResponseUtil.success();
        } catch (IOException e) {
            logger.error("====>upload one file IOException: ", e);
            return ResponseUtil.error(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public Response uploads(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (MultipartFile file : files) {
            upload(file);
        }

        return ResponseUtil.success();
    }

    @Override
    public Response download(String name, HttpServletResponse response) throws Exception {
        logger.info("====>file name: {}", name);
        File file = new File(name);

        if (!file.exists()) {
            logger.error("====>download file failed: {}", ResponseCode.FILE_NOT_EXIST_ERROR.getMsg());
            return ResponseUtil.error(ResponseCode.FILE_NOT_EXIST_ERROR);
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        return ResponseUtil.success();
    }
}
