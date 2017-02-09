package com.blizzmi.emoji;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Date： 2016/12/2
 * Description:
 * emoji表情工具
 * @author WangLizhi
 * @version 1.0
 */
public class EmojiUtils {

    /**
     * 获取emoji表情列表
     * @param context
     * @return
     */
    public static List<String> getEmojiList(Context context){
        try {
            return readEmojiXml(getEmojiXml(context));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getEmojiXml(Context context){
        if (context == null){
            return null;
        }
        return context.getResources().openRawResource(R.raw.emoji_info);
    }

    private static List<String> readEmojiXml(InputStream is) throws XmlPullParserException, IOException {
        if (is == null){
            return null;
        }
        ArrayList<String> emojiList = new ArrayList<>(80);//集合
        String emoji;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");
        int eventCode = parser.getEventType();
        while (eventCode != XmlPullParser.END_DOCUMENT) {
            switch (eventCode) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if ("string".equals(parser.getName())) {
                        emoji = parser.nextText();
                        if (TextUtils.isEmpty(emoji)||"1".equals(emoji)){
                            break;
                        }
                        emojiList.add(
                                getEmojiStringByUnicode(
                                        Integer.parseInt(
                                                emoji.replace("0x",""), 16)));

                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
                default:
                    break;
            }
            eventCode = parser.next();
        }

        return emojiList;
    }

    private static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
