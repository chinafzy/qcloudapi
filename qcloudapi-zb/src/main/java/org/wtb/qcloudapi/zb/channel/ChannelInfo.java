package org.wtb.qcloudapi.zb.channel;

import java.util.ArrayList;
import java.util.List;

import org.wtb.qcloudapi.base.BaseBean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelInfo extends ChannelListResp.Channel {

    public static class Upstream extends BaseBean {
        private String sourceName;
        private String sourceID;
        private String sourceType;
        private String sourceAddress;

        public String getSourceAddress() {
            return sourceAddress;
        }

        public String getSourceID() {
            return sourceID;
        }

        public String getSourceName() {
            return sourceName;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
        }

        public void setSourceID(String sourceID) {
            this.sourceID = sourceID;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }
    }

    private String channelDescribe;
    @JsonProperty("hls_downstream_address")
    private String hlsDownstreamAddress;
    @JsonProperty("rtmp_downstream_address")
    private String rtmpDownstreamAddress;
    @JsonProperty("player_id")
    private String playerId;
    private String resolution;
    private String password;
    @JsonProperty("upstream_list")
    private List<Upstream> upstreamList = new ArrayList<Upstream>();

    public String getChannelDescribe() {
        return channelDescribe;
    }

    public String getHlsDownstreamAddress() {
        return hlsDownstreamAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getResolution() {
        return resolution;
    }

    public String getRtmpDownstreamAddress() {
        return rtmpDownstreamAddress;
    }

    public List<Upstream> getUpstreamList() {
        return upstreamList;
    }

    public void setChannelDescribe(String channelDescribe) {
        this.channelDescribe = channelDescribe;
    }

    public void setHlsDownstreamAddress(String hlsDownstreamAddress) {
        this.hlsDownstreamAddress = hlsDownstreamAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setRtmpDownstreamAddress(String rtmpDownstreamAddress) {
        this.rtmpDownstreamAddress = rtmpDownstreamAddress;
    }

    public void setUpstreamList(List<Upstream> upstreamList) {
        this.upstreamList = upstreamList;
    }
}
