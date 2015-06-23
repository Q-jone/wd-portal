package com.wonders.stpt.contractChangeProcotol.model.vo;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ContractChangeProtocolVo {
    public int pageSize;
    public int page;

    public ContractChangeProtocolVo(){
        this.pageSize = 10;
        this.page = 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
