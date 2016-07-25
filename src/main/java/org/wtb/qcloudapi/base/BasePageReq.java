package org.wtb.qcloudapi.base;

public abstract class BasePageReq<E extends BasePageReq<E>> extends BaseReq {

    @SuppressWarnings("unused")
    private Integer pageNo, pageSize;

    @SuppressWarnings("unchecked")
    public E pageNo(Integer pageNo) {
        this.pageNo = pageNo;

        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E pageSize(Integer pageSize) {
        this.pageSize = pageSize;

        return (E) this;
    }
}
