package org.wtb.qcloudapi.zb.snapshot;

import java.util.LinkedHashMap;

import org.wtb.qcloudapi.base.BaseReq;
import org.wtb.qcloudapi.base.BaseService;
import org.wtb.qcloudapi.base.QcloudApiConf;
import org.wtb.qcloudapi.base.tr.BusinessException;

import com.fasterxml.jackson.databind.JsonNode;

public class SnapshotService extends BaseService {

    public SnapshotService(QcloudApiConf conf) {
        super(conf);
    }

    /**
     * 创建截图任务 
     * 
     * @param req
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4726">https://www.qcloud.com/doc/api/258/4726</a>
     */
    public String createSnapshot(SnapshotCreateReq req) throws BusinessException {
        return req("CreateLVBShot", req).path("task_id").asText();
    }

    /**
     * 终止截图任务 
     * 
     * @param channelId
     * @param taskId
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4727">https://www.qcloud.com/doc/api/258/4727</a>
     */
    public void stopSnapshot(String channelId, String taskId) throws BusinessException {
        req("StopLVBShot", taskReq(channelId, taskId));
    }

    /**
     * 查看队列消息（实际上是通过消息机制来获取完成的抓图） 
     * 
     * @return
     * @throws BusinessException
     * @see {@link #listSnapshotsBySms(Integer)}
     */
    public LinkedHashMap<String, String> listSnapshotsBySms() throws BusinessException {
        return listSnapshotsBySms(1);
    }

    /**
     * 查看队列消息（实际上是通过消息机制来获取完成的抓图）  
     * 
     * @param count2
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4728">https://www.qcloud.com/doc/api/258/4728</a>
     */
    public LinkedHashMap<String, String> listSnapshotsBySms(final Integer count2) throws BusinessException {

        JsonNode node = req("DescribeQueueLog", new BaseReq() {
            @SuppressWarnings("unused")
            int bid = 100;
            @SuppressWarnings("unused")
            Integer count = count2;
        }).get("data");

        if (node == null) {
            throw new IllegalStateException("Missing json node \"data\".");
        }

        LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>(100);

        for (int i = 0;; i++) {
            JsonNode data = node.get(i);
            if (data == null)
                break;

            ret.put(data.path("stream_id").asText(), data.path("pic_url").asText());
        }

        return ret;
    }

    /**
     * 删除截图任务 
     * 
     * @param channelId
     * @param taskId
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4730">https://www.qcloud.com/doc/api/258/4730</a>
     */
    public void deleteSnapshot(String channelId, String taskId) throws BusinessException {
        req("DeleteLVBShot", taskReq(channelId, taskId));
    }

    private static BaseReq taskReq(final String channelId2, final String taskId2) {
        return new BaseReq() {
            @SuppressWarnings("unused")
            String channelId = channelId2;
            @SuppressWarnings("unused")
            String taskId = taskId2;
        };
    }
}
