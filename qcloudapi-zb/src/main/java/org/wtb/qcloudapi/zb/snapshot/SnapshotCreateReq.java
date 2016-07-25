package org.wtb.qcloudapi.zb.snapshot;

import java.util.Date;

import org.wtb.qcloudapi.base.BaseReq;

public class SnapshotCreateReq extends BaseReq {

    @SuppressWarnings("unused")
    private String channelId;

    @SuppressWarnings("unused")
    private Date startTime, endTime;

    public static SnapshotCreateReq ins(String channelId) {
        SnapshotCreateReq ret = new SnapshotCreateReq();
        ret.channelId = channelId;

        return ret;
    }

    public SnapshotCreateReq startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public SnapshotCreateReq endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }
}
