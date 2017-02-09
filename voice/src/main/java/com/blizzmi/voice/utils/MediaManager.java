package com.blizzmi.voice.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Date： 2016/7/22
 * Description:
 * 媒体管理工具类
 * 此时只有录音管理
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MediaManager implements Runnable {

    private static final String TAG = "MediaManager";
    private MediaRecorder mRecorder;
    private Activity mContext;
    private long startTime;
    private boolean isStart;
    private AudioBean mAudioBean;
    private long time;
    private RecordingOnClickListener listener;
    private int BASE = 1;

    public MediaManager(Activity context) {
        mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        if (!isPermission()) {
            initPermission();
        }
    }

    /**
     * 权限初始化
     */
    private void initPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mContext,
                Manifest.permission.RECORD_AUDIO)) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            Toast.makeText(mContext, "你已经拒绝了申请", Toast.LENGTH_SHORT).show();

        } else {
            ActivityCompat.requestPermissions(mContext,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                    102);
        }
    }

    /**
     * 是否有权限
     */
    private boolean isPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 开始录音
     */
    public void radioStart() {
        if (!isPermission()) {
            initPermission();
            return;
        }
        if (isStart) {
            return;
        }
        mAudioBean = new AudioBean(PathUtils.createName());
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
        }
        //设置音源为Micphone
        mRecorder.setAudioSource(AudioBean.AUDIO_SOURCE);
        //设置封装格式
        mRecorder.setOutputFormat(AudioBean.OUT_PUT_FORMAT);
        mRecorder.setOutputFile(mAudioBean.getPath());//输出文件地址
        //设置编码格式
        mRecorder.setAudioEncoder(AudioBean.AUDIO_ENCODER);

        try {

            mRecorder.prepare();
            //录音
            mRecorder.start();

            isStart = true;
            startTime = System.currentTimeMillis();
        } catch (Exception e) {
//            Log.e(TAG, "prepare() failed");
        }

    }

    public interface RecordingOnClickListener {
        public void decibelValue(int x);
    }

    public RecordingOnClickListener getListener() {
        return listener;
    }

    public void setListener(RecordingOnClickListener listener) {
        this.listener = listener;
    }

    public AudioBean radioStop() {
        time = System.currentTimeMillis() - startTime;
        if (time < 1000) {
            try {
                Thread.sleep(1000 - time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = 1000;
        }
        stop();
        Log.i(TAG, "语音存放地址:" + mAudioBean.getPath());
        mAudioBean.setTime(time);
        AudioBean audio = mAudioBean;
        mAudioBean = null;
        return audio;
    }

    private void stop() {
        if (mRecorder == null) {
            isStart = false;
            return;
        }

        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        isStart = false;
    }


    @Override
    public void run() {
        if (mRecorder == null) {
            isStart = false;
            return;
        }

        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        isStart = false;
    }
}
