package org.wtb.qcloudapi.base.tr;

/**
 * Json格式异常。
 *  基于RuntimeException，不要求在每个调用中来catch
 *
 * @author 19781971@qq.com
 */
public class InvalidJsonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidJsonException() {
    }

    public InvalidJsonException(String message) {
        super(message);
    }

    public InvalidJsonException(Throwable cause) {
        super(cause);
    }

    public InvalidJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
