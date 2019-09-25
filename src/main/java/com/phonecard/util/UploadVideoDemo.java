package com.phonecard.util;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;


public class UploadVideoDemo {

    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAI4FwSF5EdAH6McXRjKVQH";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "sfBiEHsOLnx7N85MI9ANwLQY95QHRV";
    
    public static DefaultAcsClient aliyunClient;
    static{
        aliyunClient = new DefaultAcsClient(
        DefaultProfile.getProfile("cn-shanghai",accessKeyId,accessKeySecret));
    }
    /**
     * 获取视频上传地址和凭证
     * @param title
     * @param fileName
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(String title,String fileName) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(fileName);
        JSONObject userData = new JSONObject();
        JSONObject messageCallback = new JSONObject();
//        messageCallback.put("CallbackURL", "http://xxxxx");
//        messageCallback.put("CallbackType", "http");
//        userData.put("MessageCallback", messageCallback.toJSONString());
//        JSONObject extend = new JSONObject();
//        extend.put("MyId", "user-defined-id");
//        userData.put("Extend", extend.toJSONString());
//        request.setUserData(userData.toJSONString());
        return aliyunClient.getAcsResponse(request);
    }

    /**
     * 刷新凭证
     * @param videoId
     * @return
     * @throws Exception
     */
    public static RefreshUploadVideoResponse refreshUploadVideo(String videoId) throws Exception {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        return aliyunClient.getAcsResponse(request);
    }

    /**
     * 本地文件上传接口
     * accessKeyId
     * accessKeySecret
     * @param title
     * @param fileName
     */
    public static String testUploadVideo(String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(10 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(5);
        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setIsShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        request.setStorageLocation("outin-74db9a8ade8b11e993da00163e1c35d5.oss-cn-shanghai.aliyuncs.com");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        /*System.out.print("RequestId=" + response.getRequestId() + "\n");*/  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }   
    
    
    /*******************************************************阿里云视频播放*******************************************/
    
    /*以下为调用示例*/
	public static String getPlayInfoList(String videoId){
	    GetPlayInfoResponse response = new GetPlayInfoResponse();
	    //GetVideoPlayAuthResponse response1 = new GetVideoPlayAuthResponse();
	    try {
	        response = getPlayInfo(aliyunClient,videoId);
	        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
	        //播放地址
	        /*for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
	            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
	        }*/
	        System.out.println("PlayInfo.PlayURL = " +playInfoList.get(playInfoList.size() - 1).getPlayURL());
	        return playInfoList.get(playInfoList.size() - 1).getPlayURL();
	        
	      /*  response1 = getVideoPlayAuth(client,videoId);
	        //播放凭证
	        System.out.print("PlayAuth = " + response1.getPlayAuth() + "\n");
	        //VideoMeta信息
	        System.out.print("VideoMeta.Title = " + response1.getVideoMeta().getTitle() + "\n");*/
	        
	    } catch (Exception e) {
	    	System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    	return "err";
	    }
	}
	
	/*获取播放地址函数*/
	public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,String videoId) throws Exception {
	    GetPlayInfoRequest request = new GetPlayInfoRequest();
	    request.setVideoId(videoId);
	    return client.getAcsResponse(request);
	}
	
	/*获取播放凭证函数*/
	public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client,String videoId) throws Exception {
	    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	    request.setVideoId(videoId);
	    return client.getAcsResponse(request);
	}

}
