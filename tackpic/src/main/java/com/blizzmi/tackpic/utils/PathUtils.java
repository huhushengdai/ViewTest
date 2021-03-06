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
    public static  String BASE_URL = "";

    private static final String BASE_PATH_IMG;//保存图片文件夹

    private static final String BASE_PATH_VOICE;

    private static final String BASE_PATH_IMG_ORIGINAL;//原图

    private static final String CAMERA_TEMP_PICTURE = "cameraTempPicture.jpg";//调用系统相机拍照生成的临时照片

    static {
//        String baseUrl = BaseApp.getInstance().getCacheDir().getAbsolutePath() + "/";
        BASE_URL = Environment.getExternalStorageDirectory() + "/Download/";
//        //图片缓存地址
//        File thumbnailDir = mkdirs(new File(baseUrl, "img/thumbnail"));
//        BASE_PATH_IMG_THUMBNAIL = thumbnailDir.getAbsolutePath() + "/";//缩略图地址
        File originalDir = mkdirs(new File(BASE_URL, "img/original"));
        BASE_PATH_IMG_ORIGINAL = originalDir.getAbsolutePath() + "/";//原图地址
//        //语音缓存地址
//        File voiceDir = mkdirs(new File(baseUrl, "voice"));
        BASE_PATH_VOICE = BASE_URL + "voice/";
        BASE_PATH_IMG = BASE_URL + "img/";
    }

    /**
     * 创建文件目录
     */
    private static File mkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
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
     *
     * @param imgName 图片文件名
     * @return 保存路径
     */
    public static String getImgPath(String imgName) {
        mkdirs(new File(BASE_PATH_IMG));
        return new StringBuilder(BASE_PATH_IMG).append(imgName).toString();
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


    /**
     * @param file
     * @Description 删除文件或文件夹
     */
    public static void delete(File file) {
        if (!file.exists()) {
            return; // 不存在直接返回
        }

        if (file.isFile()) {
            file.delete(); // 若是文件则删除后返回
            return;
        }

        // 若是目录递归删除后,并最后删除目录后返回
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete(); // 如果是空目录，直接删除
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]); // 递归删除子文件或子文件夹
            }
            file.delete(); // 删除最后的空目录
        }
        return;
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

    public static String getCameraTempPicture() {
        return new StringBuilder(BASE_URL).append(CAMERA_TEMP_PICTURE).toString();
    }
}
