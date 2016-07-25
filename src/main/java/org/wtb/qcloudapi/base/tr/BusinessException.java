package org.wtb.qcloudapi.base.tr;

/**
 * 业务异常，直接从QCloud 的Response中code, message字段获取而来 
 *
 * @author 19781971@qq.com
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    

}
