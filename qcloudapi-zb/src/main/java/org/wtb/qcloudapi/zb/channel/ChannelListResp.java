package org.wtb.qcloudapi.zb.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.wtb.qcloudapi.base.BaseBean;
import org.wtb.qcloudapi.base.BaseResp;

public class ChannelListResp extends BaseResp {

    public static class Channel extends BaseBean {
        
        public static final int STATUS_READY = 0;
        public static final int STATUS_WORKING = 1;
        public static final int STATUS_ERROR = 2;
        public static final int STATUS_CLOSED = 3;

       @XmlElement(name="channel_id")
//        @JsonProperty("channel_id")
        private String channelId;
       @XmlElement(name="channel_name")
//        @JsonProperty("channel_name")
        private String channelName;
       @XmlElement(name="channel_status")
//        @JsonProperty("channel_status")
        private int channelStatus;
       @XmlElement(name="create_time")
//        @JsonProperty("create_time")
        private Date createTime;

        public String getChannelId() {
            return channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public int getChannelStatus() {
            return channelStatus;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public void setChannelStatus(int channelStatus) {
            this.channelStatus = channelStatus;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

    }

    @XmlElement(name="all_count")
//    @JsonProperty("all_count")
    private int allCount;
    private List<Channel> channelSet = new ArrayList<Channel>();

    public int getAllCount() {
        return allCount;
    }

    public List<Channel> getChannelSet() {
        return channelSet;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public void setChannelSet(List<Channel> channelSet) {
        this.channelSet = channelSet;
    }

}
