package com.phonecard.controller;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
import com.phonecard.common.ServletResult;
import com.phonecard.config.PropertiesConfig;
import com.phonecard.util.FileUploadUtil;
import com.phonecard.util.UUIDUtil;
import com.phonecard.util.UploadVideoDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@Api(tags = "视频上传")
@RequestMapping("/video")
public class VideoController {

    
    private String localPath = PropertiesConfig.filePath;

    @ApiOperation("本地_上传视频文件")
    @PostMapping("/saveVideo")
    public ServletResult saveVideo(MultipartFile file) throws Exception {
        if (file == null) {
            return ServletResult.createByErrorMessage("文件不能为空");
        }

        String fileName = UUIDUtil.build();
        //上传后缀
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        Short listType = FileUploadUtil.getFileType(fileExt);
        //绝对地址
        String fullName = fileName + fileExt;

        long begin = System.currentTimeMillis();

        boolean result = FileUploadUtil.fileUpload(file, fullName);

        long aliyunTime = System.currentTimeMillis();
        System.out.println("上传阿里云耗时：" + (aliyunTime - begin) / 1000 + "秒");

        if (result){

            String pathRoot = localPath;

            long aliyunBeginTime = System.currentTimeMillis();
            System.out.println("开始上传阿里云视频服务器耗时：" + (aliyunBeginTime - begin) / 1000 + "秒");

            String videoId = UploadVideoDemo.testUploadVideo(fileName, pathRoot + fullName);

            long aliyunVideoTime = System.currentTimeMillis();
            System.out.println("上传阿里云视频服务器耗时：" + (aliyunVideoTime - begin) / 1000 + "秒");

            String videoAddress = UploadVideoDemo.getPlayInfoList(videoId);
            Map<String,Object> map = new HashMap<>(2);
            map.put("url",videoAddress);
            map.put("listType",listType);
            return ServletResult.createBySuccess(map);
        }else {
            return ServletResult.createByErrorMessage("上传失败");
        }
    }
    @PostMapping("/url")
    @ApiOperation("获取地址")
    public ServletResult getUrl(@RequestParam String videoId){
        String videoAddress = UploadVideoDemo.getPlayInfoList(videoId);
        Map<String,Object> map = new HashMap<>(2);
        String fileExt = videoAddress.substring(videoAddress.lastIndexOf('.'));
        System.out.println(videoAddress);
        Short listType = FileUploadUtil.getFileType(fileExt);
        map.put("url",videoAddress);
        map.put("listType",listType);
        return ServletResult.createBySuccess(map);
    }
    @PostMapping("/auth")
    @ApiOperation("获取凭证")
    public ServletResult getUploadAuth(@RequestParam String title,
                                  @RequestParam String fileName){
        try {
            CreateUploadVideoResponse response = UploadVideoDemo.createUploadVideo(title,fileName);
            return ServletResult.createBySuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ServletResult.createByErrorMessage(e.getMessage());
        }
    }
    @PostMapping("/refresh")
    @ApiOperation("刷新凭证")
    public ServletResult refreshAuth(@RequestParam String videoId){
        try {
            RefreshUploadVideoResponse response = UploadVideoDemo.refreshUploadVideo(videoId);
            return ServletResult.createBySuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ServletResult.createByErrorMessage(e.getMessage());
        }
    }
}
