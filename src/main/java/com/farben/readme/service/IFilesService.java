package com.farben.readme.service;

import com.farben.readme.util.response.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IFilesService {

    Response upload(MultipartFile file) throws Exception;

    Response uploads(HttpServletRequest request) throws Exception;

    Response download(String name, HttpServletResponse response) throws Exception;

}
