package org.wtb.qcloudapi.zb.channel;

import org.wtb.qcloudapi.base.BaseReq;

public class WithIdsReq extends BaseReq {

    @SuppressWarnings("unused")
    private String[] channelIds;

    public static WithIdsReq ins(String... ids) {
        WithIdsReq ret = new WithIdsReq();

        ret.channelIds = ids;

        return ret;
    }
}
