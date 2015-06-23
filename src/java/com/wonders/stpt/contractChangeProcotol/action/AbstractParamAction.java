package com.wonders.stpt.contractChangeProcotol.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/4/2.
 */
public class AbstractParamAction extends ActionSupport {

    public ActionContext actionContext = ActionContext.getContext();
    public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
    public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);

    //public ServletContext  application = (ServletContext)actionContext.getApplication();
    public HttpSession session = request.getSession();

}
