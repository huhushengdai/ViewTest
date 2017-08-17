package com.huhushengdai.expression;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Xml;

import com.blizzmi.mliao.ui.BaseApp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Date： 2016/12/2
 * Description:
 * emoji表情工具
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ExpressionUtils {

    private static final String EXPRESSION_RES_TYPE = "mipmap";//表情图片资源所在的类型
    private static final int EXPRESSION_XML = R.raw.expression_info;

    private static ExpressionUtils mInstance;
    private String mPacketName;//包名
    private Map<String, String> mExpressionMap;

    public static ExpressionUtils getInstance() {
        if (mInstance == null) {
            synchronized (ExpressionUtils.class) {
                if (mInstance == null) {
                    mInstance = new ExpressionUtils();
                }
            }
        }
        return mInstance;
    }

    private ExpressionUtils() {
        Context context = BaseApp.getInstance();
        try {
            mExpressionMap = readExpressionXml(context);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPacketName = context.getPackageName();
    }

    /**
     * 获取表情定义列表
     */
    public Map<String, String> getExpressionList() {
        return mExpressionMap;
    }

    /**
     * 解析xml
     *
     * @param context 读取xml所需
     * @return 表情 map
     * @throws XmlPullParserException
     * @throws IOException
     */
    private Map<String, String> readExpressionXml(Context context) throws XmlPullParserException, IOException {
        if (context == null) {
            return null;
        }
        InputStream is = context.getResources().openRawResource(EXPRESSION_XML);
        if (is == null) {
            return null;
        }
        HashMap<String, String> expressionList = new HashMap<>();//表情列表
        String expression = "";//表情
        String drawableId = "";//图片资源中的id
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");
        int eventCode = parser.getEventType();
        while (eventCode != XmlPullParser.END_DOCUMENT) {
            switch (eventCode) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if ("string".equals(parser.getName())) {
                        expression = parser.nextText();
                    } else if ("key".equals(parser.getName())) {
                        drawableId = parser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("dict".equals(parser.getName())) {
                        expressionList.put(expression, drawableId);
                        expression = "";
                        drawableId = "";
                    }
                    break;
                default:
                    break;
            }
            eventCode = parser.next();
        }

        return expressionList;
    }

    /**
     * 获取图片表情替换文字 结果
     *
     * @return 被替换后的内容
     */
    public SpannableString getReplace(String text, Context context) {
        Resources resources = context.getResources();

        SpannableString spannable = new SpannableString(text);
        int start;
        int end;
        int from = 0;
        String content;

        while (true) {
            start = text.indexOf("[", from);
            from = start + 1;
            /**
             * from 不用end
             * 防止[文字[表情] 之类的情况
             */
            end = text.indexOf("]", from);
            if (start == -1 || end == -1) {
                break;
            }
            content = text.substring(start, end + 1);
            if (mExpressionMap.containsKey(content)) {
                ImageSpan imageSpan = createImageSpan(context
                        , getExpressionId(resources, mExpressionMap.get(content)
                        ));

                spannable.setSpan(imageSpan, start, end + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannable;
    }

    /**
     * 获取可以替换文字的图片
     *
     * @param context    上下文
     * @param resourceId 图片资源id
     * @return 图片替换文字的内容
     */
    public ImageSpan createImageSpan(Context context, @DrawableRes int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                resourceId);
        bitmap = Bitmap.createScaledBitmap(bitmap, 55, 55, true);
        return new ImageSpan(context, bitmap);
    }

    /**
     * 获取表情资源id
     *
     * @param idName 表情资源的名称
     * @return 表情资源id
     */
    public
    @DrawableRes
    int getExpressionId(Resources res, String idName) {
        if (res == null) {
            return 0;
        }
        return res.getIdentifier(
                idName
                , EXPRESSION_RES_TYPE, mPacketName);
    }

    /**
     * 获取表情资源的名称
     * @param key 表情key  [sleep]
     * @return 表情资源的名称
     */
    public String getExpressionName(String key) {
        return mExpressionMap == null ? null : mExpressionMap.get(key);
    }
}
