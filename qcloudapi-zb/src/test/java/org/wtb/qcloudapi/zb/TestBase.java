package org.wtb.qcloudapi.zb;

import org.wtb.qcloudapi.zb.channel.ChannelService;

public class TestBase {
    protected ChannelService service = new ChannelService(
            new QcloudApiConfZb("AKIDSyhz7zt49iT8poEdJ8OXMsMq93UQIr7r", "kQxjYS30qvVw6TfP7GxGsraBSG4Zu61Q", "bj"));
}
