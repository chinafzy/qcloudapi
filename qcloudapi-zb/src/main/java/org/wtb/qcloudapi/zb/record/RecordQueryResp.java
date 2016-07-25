package org.wtb.qcloudapi.zb.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wtb.qcloudapi.base.BasePageResp;

public class RecordQueryResp extends BasePageResp {

    public static class Record {
        private String field, fileName;
        private Date startTime, endTime;

        public Date getEndTime() {
            return endTime;
        }

        public String getField() {
            return field;
        }

        public String getFileName() {
            return fileName;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public void setField(String field) {
            this.field = field;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
    }

    private List<Record> fileSet = new ArrayList<Record>();

    public List<Record> getFileSet() {
        return fileSet;
    }

    public void setFileSet(List<Record> fileSet) {
        this.fileSet = fileSet;
    }

}
