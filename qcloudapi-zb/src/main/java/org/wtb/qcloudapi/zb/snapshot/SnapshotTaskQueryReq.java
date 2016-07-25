package org.wtb.qcloudapi.zb.snapshot;

import org.wtb.qcloudapi.base.BasePageReq;

public class SnapshotTaskQueryReq extends BasePageReq<SnapshotTaskQueryReq> {
    @SuppressWarnings("unused")
    private String channelId;

    public static SnapshotTaskQueryReq ins(String channelId) {
        SnapshotTaskQueryReq ret = new SnapshotTaskQueryReq();

        ret.channelId = channelId;

        return ret;
    }
}
