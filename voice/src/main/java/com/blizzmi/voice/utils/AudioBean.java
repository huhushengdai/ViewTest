package com.blizzmi.voice.utils;

import android.media.MediaRecorder;

/**
 * Date： 2016/9/2
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class AudioBean {
    public static final int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;//设置音源为Micphone
    public static final int OUT_PUT_FORMAT = MediaRecorder.OutputFormat.AMR_NB;//设置封装格式
    public static final int AUDIO_ENCODER = MediaRecorder.AudioEncoder.AMR_NB;//设置编码格式

    public static final String SUFFIX = ".AMR";//后缀名
//    public static final String AUDIO_DIR ;//保存路径
    static {
//        AUDIO_DIR = DemoApplication.getInstance().getCacheDir().getAbsolutePath()+"/";
//        AUDIO_DIR = Environment.getExternalStorageDirectory()+"/Download/";
//        AUDIO_DIR = Environment.getExternalStorageDirectory()+"/Download/";
    }

    /**
     * 文件名称
     */
    private String name;
    private long time;

    public AudioBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * 获取文件地址
     * @return 文件地址
     */
    public String getPath(){
        return PathUtils.getVoicePath(name);
//        return AUDIO_DIR+name+SUFFIX;
    }
}
