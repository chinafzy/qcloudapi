package org.wtb.qcloudapi.zb;

import org.wtb.qcloudapi.base.QcloudApiConf;

/**
 * 直播云api配置信息 
 *
 * @author chinafzy1978@gmail.com
 */
public class QcloudApiConfZb extends QcloudApiConf {

    public QcloudApiConfZb(String secretId, String secretKey, String region) {
        super("live.api.qcloud.com", "/v2/index.php", secretId, secretKey, region);
    }

}
