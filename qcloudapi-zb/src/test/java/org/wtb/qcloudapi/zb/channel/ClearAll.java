package org.wtb.qcloudapi.zb.channel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wtb.qcloudapi.base.tr.BusinessException;
import org.wtb.qcloudapi.zb.TestBase;

public class ClearAll extends TestBase {

    @Test
    public void test() throws BusinessException {

        ChannelListResp resp = service.listChannel(ChannelListReq.ins());
        List<ChannelListResp.Channel> channels = resp.getChannelSet();

        if (channels == null || channels.isEmpty()) {
            System.out.println("No channels found.");
            return;
        }

        List<String> ids = new ArrayList<String>();
        for (ChannelListResp.Channel channel : resp.getChannelSet()) {
            if (channel.getChannelStatus() == ChannelListResp.Channel.STATUS_READY
                    || channel.getChannelStatus() == ChannelListResp.Channel.STATUS_WORKING)
                service.stopChannels(channel.getChannelId());
                
                ids.add(channel.getChannelId());
        }

        String[] idarr = ids.toArray(new String[ids.size()]);
        service.deleteChannels(idarr);
    }

}
