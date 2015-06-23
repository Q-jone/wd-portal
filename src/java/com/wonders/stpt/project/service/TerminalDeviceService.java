package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.TerminalDevice;

/**
 * Created by Administrator on 2014/6/27.
 */
public interface TerminalDeviceService {
    TerminalDevice getTerminalDevice(String id) throws Exception;
    TerminalDevice save(TerminalDevice terminalDevice) throws Exception;
    PageResultSet<TerminalDevice> getTerminalDevices(TerminalDevice terminalDevice,Integer year,int page,int pageSize) throws Exception;
}
