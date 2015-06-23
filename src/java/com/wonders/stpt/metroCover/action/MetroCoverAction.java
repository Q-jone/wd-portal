package com.wonders.stpt.metroCover.action;

import com.wonders.stpt.metroCover.service.MetroCoverService;
import com.wonders.stpt.metroCover.util.MetroCoverUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.validFile.action.AbstractParamAction;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/22
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("struts-default")
@Namespace(value="/metroCover")
@Controller("metroCoverAction")
@Scope("prototype")
public class MetroCoverAction extends AbstractParamAction{

    private ActionWriter aw = new ActionWriter(response);

    @Autowired
    private MetroCoverService service;

    public MetroCoverService getService() {
        return service;
    }

    public void setService(MetroCoverService service) {
        this.service = service;
    }

    /**
     * 获取所有运营公司（包括磁浮）
     * @return
     */
    @Action("getMetroCompany")
    public String getMetroCompany(){
        aw.writeJson(service.getMetroCompany());
        return null;
    }

    /**
     * 获取所有线路
     * @return
     */
    @Action("getMetroLine")
    public String getMetroLine(){
        aw.writeJson(service.getMetroLine());
        return null;
    }

    /**
     * 获取数据的所有时间
     * @return
     */
    @Action("getValidDate")
    public String getValidDate(){
        aw.writeAjax(service.getValidDate());
        return null;
    }

    /**
     * 获取信息
     * @return
     */
    @Action("getMetroQualityInfo")
    public String getMetroQualityInfo(){
        Map<String,String> map = MetroCoverUtil.prepareParam(this.request,service.getValidDate());
        aw.writeJson(service.getMetroQualityCache(map.get("lineId"),
                map.get("companyId"),
                map.get("date"),
                map.get("span"),
                MetroCoverUtil.getMetroType(map.get("type"))));
        return null;
    }

    /**
     * 获取信息
     * @return
     */
    @Action("getMetroScaleInfo")
    public String getMetroScaleInfo(){
        Map<String,String> map = MetroCoverUtil.prepareParam(this.request,service.getValidDate());
        aw.writeJson(service.getMetroScaleCache(map.get("lineId"),
                map.get("companyId"),
                map.get("date"),
                map.get("span"),
                MetroCoverUtil.getMetroType(map.get("type"))));
        return null;
    }

    /**
     * 获取信息
     * @return
     */
    @Action("getMetroProductionInfo")
    public String getMetroProductionInfo(){
        Map<String,String> map = MetroCoverUtil.prepareParam(this.request,service.getValidDate());
        aw.writeJson(service.getMetroProductionCache(map.get("lineId"),
                map.get("companyId"),
                map.get("date"),
                map.get("yearSpan"),
                map.get("span"),
                MetroCoverUtil.getMetroType(map.get("type"))));
        return null;
    }

}
