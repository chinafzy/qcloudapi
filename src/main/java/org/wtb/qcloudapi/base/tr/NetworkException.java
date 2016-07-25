package org.wtb.qcloudapi.base.tr;

/**
 * 发生网络故障时候的异常。
 *  基于RuntimeException，不要求在每个调用中来catch
 *
 * @author chinafzy1978@gmail.com
 */
public class NetworkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NetworkException() {
        super();
    }

    public NetworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }

}
