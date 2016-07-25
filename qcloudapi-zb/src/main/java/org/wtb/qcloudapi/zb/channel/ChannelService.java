package org.wtb.qcloudapi.zb.channel;

import java.util.HashMap;
import java.util.Map;

import org.wtb.qcloudapi.base.BaseReq;
import org.wtb.qcloudapi.base.BaseService;
import org.wtb.qcloudapi.base.QcloudApiConf;
import org.wtb.qcloudapi.base.tr.BusinessException;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 *
 * @author 19781971@qq.com
 */
public class ChannelService extends BaseService {

    public ChannelService(QcloudApiConf conf) {
        super(conf);
    }

    /**
     * 创建直播频道 
     * 
     * @param req
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4715">https://www.qcloud.com/doc/api/258/4715</a>
     */
    public String createChannel(ChannelCreateReq req) throws BusinessException{
        return req("CreateLVBChannel", req) //
                .path("channel_id").asText();
    }

    /**
     * 删除直播频道 
     * 
     * @param ids
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4722">https://www.qcloud.com/doc/api/258/4722</a>
     */
    public void deleteChannels(String... ids) throws BusinessException {
        req("DeleteLVBChannel", WithIdsReq.ins(ids));
    }

    /**
     * 查询直播频道详情 
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4717">https://www.qcloud.com/doc/api/258/4717</a>
     */
    public ChannelInfo describeChannel(final String id) throws BusinessException {
        BaseReq req = new BaseReq() {
            @SuppressWarnings("unused")
            String channelId = id;
        };

        JsonNode node = req("DescribeLVBChannel", req);
        JsonNode node2 = node.path("channelInfo").path(0);
        if (node2.isMissingNode()) {
            throw new IllegalArgumentException();
        }

        return parseBean(node2, ChannelInfo.class);

    }

    /**
     * 查询直播频道列表 
     * 
     * @param req
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4716">https://www.qcloud.com/doc/api/258/4716</a>
     */
    public ChannelListResp listChannel(ChannelListReq req) throws BusinessException {
        JsonNode node = req("DescribeLVBChannelList", req);

        return parseBean(node, ChannelListResp.class);
    }

    /**
     * 修改直播频道 
     * 
     * @param req {@link ChannelModifyReq} 
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4718">https://www.qcloud.com/doc/api/258/4718</a>
     */
    public void modifyChannel(ChannelModifyReq req) throws BusinessException {
        req("ModifyLVBChannel", req);
    }

    /**
     * 批量启用直播频道 
     * 
     * @param ids
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4719">https://www.qcloud.com/doc/api/258/4719</a>
     */
    public void startChannels(String... ids) throws BusinessException {
        req("StartLVBChannel", WithIdsReq.ins(ids));
    }

    /**
     * 批量停止直播频道 
     *   
     * @param ids
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4720">https://www.qcloud.com/doc/api/258/4720</a>
     */
    public void stopChannels(String... ids) throws BusinessException {
        req("StopLVBChannel", WithIdsReq.ins(ids));
    }

    /**
     * 查询直播频道当前并发收看数 
     * 
     * @param ids
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4721">https://www.qcloud.com/doc/api/258/4721</a>
     */
    public Map<String, Integer> viewOnlineUsers(String... ids) throws BusinessException {
        JsonNode node = req("DescribeLVBOnlineUsers", WithIdsReq.ins(ids)) //
                .path("list");

        Map<String, Integer> ret = new HashMap<String, Integer>();
        for (int i = 0;; i++) {
            JsonNode node2 = node.get(i);
            if (node2 == null)
                break;

            ret.put(node2.path("channelId").asText(), node2.path("online").asInt());
        }

        return ret;

    }
}
