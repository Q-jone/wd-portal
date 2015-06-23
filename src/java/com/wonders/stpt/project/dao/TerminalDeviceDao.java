package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.TerminalDevice;

import java.util.List;

/**
 * Created by Administrator on 2014/6/27.
 */
public interface TerminalDeviceDao {

    TerminalDevice load(String id) throws Exception;

    TerminalDevice save(TerminalDevice terminalDevice) throws Exception;
    PageResultSet<TerminalDevice> find(TerminalDevice terminalDevice,int page,int pageSize) throws Exception;
}
