package com.yl.demo.common.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/28
 */
@Slf4j
@Component
public class FastDFSUtils {

    @Autowired
    private FastFileStorageClient fileStorageClient;

    @Value("${upload.base-url}")
    private String baseUrl;


    public  String upload(MultipartFile multipartFile){
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
                throw new RuntimeException("上传文件有问题");
            }
        } catch (IOException e) {
            log.error("校验文件内容失败....{}", e);
            throw new RuntimeException("校验文件内容失败"+e.getMessage());
        }
        try {
            // 上传到FastDFS
            // 获取文件扩展名
            String extension = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
            // 上传
            StorePath storePath = fileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), extension, null);
            // 返回路径
            return baseUrl + storePath.getFullPath();
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败！....{}", e);
            throw  new RuntimeException("【文件上传】上传文件失败！"+e.getMessage());
        }
    }
}
