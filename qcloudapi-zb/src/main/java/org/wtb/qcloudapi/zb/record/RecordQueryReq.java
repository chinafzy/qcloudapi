package org.wtb.qcloudapi.zb.record;

import org.wtb.qcloudapi.base.BasePageReq;

public class RecordQueryReq extends BasePageReq<RecordQueryReq> {
    
    @SuppressWarnings("unused")
    private String channelId, taskId;
    
    public static RecordQueryReq ins(String channelId, String taskId){
        RecordQueryReq ret = new RecordQueryReq();
        
        ret.channelId = channelId;
        ret.taskId = taskId;
        
        return ret;
    }

}
