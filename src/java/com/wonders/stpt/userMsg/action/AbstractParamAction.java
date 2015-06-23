package com.wonders.stpt.userMsg.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AbstractParamAction extends ActionSupport implements 
SessionAware, ServletRequestAware, ServletResponseAware{
	private static final long serialVersionUID = 7620009925942346125L;   
	
	protected Map session;
    protected HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;

	 public Map getSession()
    {
        return session;
    }

    public void setSession(Map session)
    {
        this.session = session;
    }


    public HttpServletRequest getServletRequest()
    {
        return servletRequest;
    }

    public HttpServletResponse getServletResponse()
    {
        return servletResponse;
    }

    public void setServletRequest(HttpServletRequest servletRequest)
    {
        this.servletRequest = servletRequest;
    }

    public void setServletResponse(HttpServletResponse servletResponse)
    {
        this.servletResponse = servletResponse;
    }

}
