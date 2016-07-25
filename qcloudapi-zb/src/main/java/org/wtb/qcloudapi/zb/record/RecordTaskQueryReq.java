package org.wtb.qcloudapi.zb.record;

import org.wtb.qcloudapi.base.BasePageReq;

public class RecordTaskQueryReq extends BasePageReq<RecordTaskQueryReq> {

    @SuppressWarnings("unused")
    private String channelId;
    
    public static RecordTaskQueryReq ins(String channelId){
        RecordTaskQueryReq ret = new RecordTaskQueryReq();
        
        ret.channelId = channelId;
        
        return ret;
    }
}
