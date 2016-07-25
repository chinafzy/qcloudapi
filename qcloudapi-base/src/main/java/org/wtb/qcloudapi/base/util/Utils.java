package org.wtb.qcloudapi.base.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {
    private static final String DT_FMT = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(String value) {
        if (value == null || value.isEmpty())
            return null;

        try {
            return new SimpleDateFormat(DT_FMT).parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDate(Date value) {
        if (value == null)
            return null;

        return new SimpleDateFormat(DT_FMT).format(value);
    }

    public static String unescape(String s) {
        Properties ps = new Properties();
        try {
            ps.load(new StringReader("x=" + s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ps.getProperty("x");
    }

    public static String escape(String s) {

        Properties ps = new Properties();
        ps.setProperty("x", s);

        StringWriter sw = new StringWriter(s.length() * 2);
        try {
            ps.store(sw, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sw.toString()
                // skip the first line because it is the comment.
                .split("(\\r\\n|\\r|\\n)", 2)[1]
                        // x=YYYYYYYYYYYY, skip the leading two chars "x="
                        .substring(2);
    }

}
