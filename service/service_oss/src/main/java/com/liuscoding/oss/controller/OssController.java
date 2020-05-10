package com.liuscoding.oss.controller;

import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.oss.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className: OssController
 * @description:
 * @author: liusCoding
 * @create: 2020-05-09 16:16
 */

@Api(tags = "阿里云OSS文件上传")
@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin
public class OssController {

    private final IOssService ossService;

    public OssController(IOssService ossService) {
        this.ossService = ossService;
    }

    @ApiOperation("上传文件")
    @PostMapping
    public ResultVo uploadOssFile(@ApiParam("文件") MultipartFile file){

        String url = ossService.uploadFileAvatar(file);
        return ResultVo.ok().data("url",url);
    }
}
