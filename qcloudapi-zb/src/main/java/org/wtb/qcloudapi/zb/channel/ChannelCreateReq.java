package org.wtb.qcloudapi.zb.channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.wtb.qcloudapi.base.BaseReq;
import org.wtb.qcloudapi.base.util.PlainMapper;

public class ChannelCreateReq extends BaseReq {

    public static class Source {
        public static final int TYPE_RTMP = 1;

        @SuppressWarnings("unused")
        private String name;
        @SuppressWarnings("unused")
        private int type = TYPE_RTMP;

        public Source(String name, int type) {
            super();
            this.name = name;
            this.type = type;
        }

    }

    @SuppressWarnings("unused")
    private String channelName;
    @SuppressWarnings("unused")
    private String channelDescribe;
    @SuppressWarnings("unused")
    private Integer outputSourceType;
    @SuppressWarnings("unused")
    private String playerPassword;
    @SuppressWarnings("unused")
    private List<Source> sourceList;
    @SuppressWarnings("unused")
    private Integer watermarkId;
    @SuppressWarnings("unused")
    private int[] outputRate;

    public ChannelCreateReq channelDescribe(String channelDescribe) {
        this.channelDescribe = channelDescribe;
        return this;
    }

    public ChannelCreateReq outputRate(int[] outputRate) {
        this.outputRate = outputRate;
        return this;
    }

    public ChannelCreateReq playerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
        return this;
    }

    public ChannelCreateReq watermarkId(Integer watermarkId) {
        this.watermarkId = watermarkId;
        return this;
    }

    public static ChannelCreateReq ins(String channelName, int outputSourceType, List<Source> sourceList) {
        ChannelCreateReq ret = new ChannelCreateReq();

        ret.channelName = channelName;
        ret.outputSourceType = outputSourceType;
        ret.sourceList = sourceList;

        return ret;
    }

    public static ChannelCreateReq ins(String channelName, int outputSourceType, Source source) {
        return ins(channelName, outputSourceType, Arrays.asList(source));
    }

    public static void main(String[] args) throws Exception {
        List<Source> sourceList = new ArrayList<Source>();
        sourceList.add(new Source("source.1", 1));
        ChannelCreateReq me = ChannelCreateReq.ins("channel1", 1, sourceList) //
                .channelDescribe("just for test") //
        ;

        Map<String, Object> m = PlainMapper.asPlainMap(me);
        System.out.println(m);

    }

}
