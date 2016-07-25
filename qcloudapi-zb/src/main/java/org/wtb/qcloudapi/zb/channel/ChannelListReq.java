package org.wtb.qcloudapi.zb.channel;

import org.wtb.qcloudapi.base.BasePageReq;

public class ChannelListReq extends BasePageReq<ChannelListReq> {

    public static final int STATUS_READY = 0;
    public static final int STATUS_WORKING = 1;
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_CLOSED = 3;
    public static final int STATUS_INCOMPLETE = 4;

    public static final int SORT_ASC = 0;
    public static final int SORT_DESC = 1;

    @SuppressWarnings("unused")
    private Integer channelStatus;
    @SuppressWarnings("unused")
    private Integer ascDesc;
    @SuppressWarnings("unused")
    private String orderBy;

    public ChannelListReq channelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
        return this;
    }

    public ChannelListReq ascDesc(Integer ascDesc) {
        this.ascDesc = ascDesc;
        return this;
    }


    public ChannelListReq orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public static ChannelListReq ins() {
        return new ChannelListReq();
    }

}
