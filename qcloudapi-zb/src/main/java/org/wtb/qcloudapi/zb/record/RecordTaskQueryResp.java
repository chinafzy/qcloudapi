package org.wtb.qcloudapi.zb.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wtb.qcloudapi.base.BasePageResp;

public class RecordTaskQueryResp extends BasePageResp {

    public static class Task {
        
        public static final int STATUS_NOTSTARTED = 0;
        public static final int STATUS_PROCESSING = 1;
        public static final int STATUS_DONE = 2;
        public static final int STATUS_ERROR = 3;
        
        private String taskId;
        private Date startTime, endTime;
        private int status;
        private int count;

        public int getCount() {
            return count;
        }

        public Date getEndTime() {
            return endTime;
        }

        public Date getStartTime() {
            return startTime;
        }

        public int getStatus() {
            return status;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
    }

    private List<Task> taskSet = new ArrayList<Task>();

    public List<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(List<Task> taskSet) {
        this.taskSet = taskSet;
    }

}
