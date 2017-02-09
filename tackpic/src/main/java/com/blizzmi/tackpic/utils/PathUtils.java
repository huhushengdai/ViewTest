package com.blizzmi.tackpic.utils;


import android.os.Environment;

import java.io.File;

/**
 * Date： 2016/10/21
 * Description:
 * 地址获取工具类
 *
 * @author WangLizhi
 * @version 1.0
 */
public class PathUtils {
    private static final String BASE_PATH_IMG_THUMBNAIL;//缩略图
    private static final String BASE_PATH_IMG_ORIGINAL;//原图

    private static final String BASE_PATH_VOICE;

    static {
//        String baseUrl = BaseApp.getInstance().getCacheDir().getAbsolutePath() + "/";
        String baseUrl = Environment.getExternalStorageDirectory() + "/Download/";
        //图片缓存地址
        File thumbnailDir = mkdirs(new File(baseUrl, "img/thumbnail"));
        BASE_PATH_IMG_THUMBNAIL = thumbnailDir.getAbsolutePath() + "/";//缩略图地址
        File originalDir = mkdirs(new File(baseUrl, "img/original"));
        BASE_PATH_IMG_ORIGINAL = originalDir.getAbsolutePath() + "/";//原图地址
        //语音缓存地址
        File voiceDir = mkdirs(new File(baseUrl, "voice"));
        BASE_PATH_VOICE = voiceDir.getAbsolutePath() + "/";
    }

    /**
     * 创建文件目录
     */
    private static File mkdirs(File file){
        if (!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * 创建缩略图路径
     *
     * @return 图片存放地址
     */
    public static String createThumbnailImgPath() {
        return new StringBuilder(BASE_PATH_IMG_THUMBNAIL).append(createName()).toString();
    }

    /**
     * 创建原图路径
     *
     * @return 图片存放地址
     */
    public static String createOriginalImgPath() {
        return new StringBuilder(BASE_PATH_IMG_ORIGINAL).append(createName()).toString();
    }

    /**
     * 创建语音地址
     *
     * @return 语音存放地址
     */
    public static String createVoicePath() {
        return new StringBuilder(BASE_PATH_VOICE).append(createName()).toString();
    }

    /**
     * 生成文件名
     * obj_+10位时间戳+_5位随机数
     *
     * @return obj_1234567890_12345
     */
    public static String createName() {
        int random = (int) Math.round(Math.random() * (99999 - 1) + 1);
        return new StringBuilder(19)
                .append("obj_").append(System.currentTimeMillis() / 1000)
                .append("_").append(String.format("%05d", random)).toString();
    }

    /**
     * 指定文件名，获取文件保存路径
     * 缩略图路径
     *
     * @param imgName 图片文件名
     * @return 保存路径
     */
    public static String getThumbnailImgPath(String imgName) {
        mkdirs(new File(BASE_PATH_IMG_THUMBNAIL));
        return new StringBuilder(BASE_PATH_IMG_THUMBNAIL).append(imgName).toString();
    }

    /**
     * 获取原图路径
     *
     * @param imgName 图片名称
     * @return 路径
     */
    public static String getOriginalImgPath(String imgName) {
        mkdirs(new File(BASE_PATH_IMG_ORIGINAL));
        return new StringBuilder(BASE_PATH_IMG_ORIGINAL).append(imgName).toString();
    }

    /**
     * 指定文件名，获取文件保存路径
     *
     * @param voiceName 语音文件名
     * @return 保存路径
     */
    public static String getVoicePath(String voiceName) {
        mkdirs(new File(BASE_PATH_VOICE));
        return new StringBuilder(BASE_PATH_VOICE).append(voiceName).toString();
    }
}
