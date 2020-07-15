package com.farben.readme.controller;

import com.farben.readme.annotation.Auth;
import com.farben.readme.constant.Constant;
import com.farben.readme.service.IFilesService;
import com.farben.readme.util.response.Response;
import com.farben.readme.util.response.ResponseCode;
import com.farben.readme.util.response.ResponseUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Scope("prototype")
@RequestMapping(value = "/files")
@Api(tags = "FilesController", description = "FilesController | Files upload and download Module")
public class FilesController {

    private final static Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private IFilesService filesService;

    /**
     * upload api (one file)
     *
     * @param file
     * @return
     */
    @Auth
    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response upload(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
                           @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
                           @RequestParam("file") MultipartFile file) {
        logger.info("==>upload one file requestId: {}", requestId);
        try {
            return filesService.upload(file);
        } catch (Exception e) {
            logger.error("==>upload one file exception: ", e);
            return ResponseUtil.error(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * upload api (Multi file)
     *
     * @param request
     * @return
     */
    @Auth
    @PostMapping(path = "/uploads", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response uploads(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
                            @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
                            HttpServletRequest request) {
        logger.info("==>upload multi files requestId: {}", requestId);
        try {
            return filesService.uploads(request);
        } catch (Exception e) {
            logger.error("==>upload multi files exception: ", e);
            return ResponseUtil.error(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    @Auth
    @GetMapping(path = "/download/{name}")
    public String download(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
                              @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
                              @PathVariable String name, HttpServletResponse response) {
        logger.info("==>download file requestId: {}, name: {}", requestId, name);
        try {
            Response download = filesService.download(name, response);
            return download.getCode();
        } catch (Exception e) {
            logger.error("==>download file exception: ", e);
//            return ResponseUtil.error(ResponseCode.FILE_DOWNLOAD_ERROR);
            return ResponseCode.FILE_DOWNLOAD_ERROR.getCode();
        }
    }
}
