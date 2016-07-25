package org.wtb.qcloudapi.zb.channel;

import org.wtb.qcloudapi.base.BaseReq;

public class ChannelModifyReq extends BaseReq {
    
    @SuppressWarnings("unused")
    private String channelId, channelName, channelDescribe;
    
    public static ChannelModifyReq ins(String channelId, String channelName){
        ChannelModifyReq ret = new ChannelModifyReq();
        ret.channelId = channelId;
        ret.channelName = channelName;
        return ret;
    }
    
    public ChannelModifyReq channelDescribe(String channelDescribe){
        this.channelDescribe = channelDescribe;
        return this;
    }

}
