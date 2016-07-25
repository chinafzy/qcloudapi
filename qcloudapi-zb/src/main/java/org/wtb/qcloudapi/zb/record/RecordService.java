package org.wtb.qcloudapi.zb.record;

import org.wtb.qcloudapi.base.BaseReq;
import org.wtb.qcloudapi.base.BaseService;
import org.wtb.qcloudapi.base.QcloudApiConf;
import org.wtb.qcloudapi.base.tr.BusinessException;

import com.fasterxml.jackson.databind.JsonNode;

public class RecordService extends BaseService {

    public RecordService(QcloudApiConf conf) {
        super(conf);
    }

    /**
     * 创建录制任务 
     * 
     * @param req
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4723">https://www.qcloud.com/doc/api/258/4723</a>
     */
    public String createRecord(RecordCreateReq req) throws BusinessException {
        return req("CreateRecord", req).path("task_id").asText();
    }

    /**
     * 删除录制任务 
     * 
     * @param channelId
     * @param taskId
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4729">https://www.qcloud.com/doc/api/258/4729</a>
     */
    public void deleteRecord(String channelId, String taskId) throws BusinessException {
        req("DeleteRecord", taskReq(channelId, taskId));
    }

    /**
     * 查询已录制分片列表 
     * 
     * @param req 
     * @return
     * @throws BusinessException
     * @see {@link RecordQueryReq}
     * @see <a href="https://www.qcloud.com/doc/api/258/4725">https://www.qcloud.com/doc/api/258/4725</a>
     */
    public RecordQueryResp queryRecord(RecordQueryReq req) throws BusinessException {
        JsonNode node = req("DescribeRecord", req);
        return parseBean(node, RecordQueryResp.class);
    }

    /**
     * 终止录制任务 
     * 
     * @param channelId
     * @param taskId
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4724">https://www.qcloud.com/doc/api/258/4724</a>
     */
    public void stopRecord(String channelId, String taskId) throws BusinessException {
        req("StopRecord", taskReq(channelId, taskId));
    }

    /**
     * 查询录制任务列表 
     * 
     * @param req
     * @return
     * @throws BusinessException
     * @see <a href="https://www.qcloud.com/doc/api/258/4731">https://www.qcloud.com/doc/api/258/4731</a>
     */
    public RecordTaskQueryResp listRecordTask(RecordTaskQueryReq req)
            throws BusinessException {
        JsonNode node = req("DescribeRecordList", req);
        return parseBean(node, RecordTaskQueryResp.class);
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
