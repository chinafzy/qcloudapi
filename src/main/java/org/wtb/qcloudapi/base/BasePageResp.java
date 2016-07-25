package org.wtb.qcloudapi.base;

public class BasePageResp extends BaseResp {

    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
