package org.wtb.qcloudapi.zb.record;

import java.util.Date;

import org.wtb.qcloudapi.base.BaseReq;

public class RecordCreateReq extends BaseReq {

    @SuppressWarnings("unused")
    private String channelId;

    @SuppressWarnings("unused")
    private Date startTime, endTime;

    public RecordCreateReq endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public RecordCreateReq startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public static RecordCreateReq ins(String channelId) {
        RecordCreateReq ret = new RecordCreateReq();
        ret.channelId = channelId;

        return ret;
    }

}
