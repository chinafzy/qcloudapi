#qcloudapi-base

##针对腾讯云API的调用客户端代码。
请参考子项目：qcloudapi-zb 
使用maven导入项目
```java
public class SimpleTest {
	ChannelService service = new ChannelService(
            new QcloudApiConfZb("AKIDSyhz7zt49iT8poEdJ8OXMsMq93UQIr7r", "kQxjYS30qvVw6TfP7GxGsraBSG4Zu61Q", "bj"));

    @Test
    public void test() throws BusinessException {
        // create 
        String id = service.createChannel( //
                ChannelCreateReq
                        .ins("t" + System.currentTimeMillis(), 3,
                                new ChannelCreateReq.Source("s1", ChannelCreateReq.Source.TYPE_RTMP)) //
                        .playerPassword("password123") //
        );

        // describe 
        {
            ChannelInfo channel = service.describeChannel(id);

            Assert.assertNotNull(channel);
            Assert.assertEquals(id, channel.getChannelId());
            Assert.assertEquals(ChannelInfo.STATUS_READY, channel.getChannelStatus());
        }

        // stop
        {
            service.stopChannels(id);

            ChannelInfo channel = service.describeChannel(id);

            Assert.assertEquals(ChannelInfo.STATUS_CLOSED, channel.getChannelStatus());
        }

        // start 
        {
            service.startChannels(id);

            ChannelInfo channel = service.describeChannel(id);

            Assert.assertEquals(ChannelInfo.STATUS_READY, channel.getChannelStatus());
        }

        // list 
        {
            ChannelListResp resp = service.listChannel(ChannelListReq.ins() //
                    .channelStatus(ChannelListReq.STATUS_READY));

            System.out.printf("All %s \n", resp.getAllCount());
            for (ChannelListResp.Channel channel : resp.getChannelSet()) {
                System.out.println(channel);
            }
        }

        // view online users.
        {
            Map<String, Integer> ret = service.viewOnlineUsers();
            System.out.println("online users:" + ret);
        }

        // delete
        {
            service.deleteChannels(id);
            try {
                service.describeChannel(id);

                Assert.fail("Channle shoud have be removed before: " + id);
            } catch (BusinessException ex) {
                Assert.assertEquals(4000, ex.getCode());
            }
        }
    }

}

```
