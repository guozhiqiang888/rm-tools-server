package com.farben.readme.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.FileNotFoundException;
import java.io.IOException;

public class XssCleanUtil {
    /**
     * allow the label as below
     * (a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img)
     * allow the property as below
     * (a>href,img>src,align,alt,height,width,title)
     */

    private static final Whitelist whitelist = Whitelist.basicWithImages();

    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        // add style property to all labels
        whitelist.addAttributes(":all", "style");
    }

    public static String cleanXss(String content) {
        if (content == null || content.length() == 0) {
            return "";
        }
        content =  Jsoup.clean(content, "", whitelist, outputSettings);
        return content;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String text = "<a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss";
        text = cleanXss(text);
        System.out.println(text);
    }
}
