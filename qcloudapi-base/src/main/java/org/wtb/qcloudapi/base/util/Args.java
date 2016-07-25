package org.wtb.qcloudapi.base.util;

/**
 * 
 *
 * @author 19781971@qq.com
 */
public class Args {

    public static String notNullEmpty(String value, String field) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException(String.format("\"%s\" cannot be null/empty.", field));
        
        return value;
    }

    public static <T> T  notNull( T value, String field) {
        if (value == null)
            throw new IllegalArgumentException(String.format("\"%s\" cannot be null.", field));
        
        return value;
    }
}
