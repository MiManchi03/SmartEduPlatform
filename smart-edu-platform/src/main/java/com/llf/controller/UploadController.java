package com.llf.controller;

import com.llf.pojo.Result;
import com.llf.utils.AliyunOSSOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    /*本地磁盘存储方案
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        LOGGER.info("接收到的参数:{},{},{}", name, age, file);
        //获取原始文件名,并把它的扩展名拆出来
        String originalFilename = file.getOriginalFilename();
        //originalFilename.lastIndexOf("."):找到文件名中最后一个.的位置
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成UUID，即使文件名一致也不会混乱
        String uId = UUID.randomUUID().toString();
        //将生成的UUID与切割获取到的文件扩展名拼接一起等到一个新的且是唯一的文件名
        String fileName = uId + substring;
        //保存文件，注：不建议用uId + originalFilename直接拼，因为名字可能过长
        file.transferTo(new File("D:\\User\\IdeaImages\\" + fileName));
        return Result.success();
    }*/
    @PostMapping("/upload")
    public  Result upload(MultipartFile file) throws Exception {
    LOGGER.info("文件上传:{}",file.getOriginalFilename());
    //将文件交给阿里云OSS储存管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        LOGGER.info("文件上传后在OSS中的URL:{}",url);
        return Result.success(url);
    }
}
