package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.TerminalDeviceDao;
import com.wonders.stpt.project.model.TerminalDevice;
import com.wonders.stpt.project.service.TerminalDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2014/6/27.
 */

@Repository("terminalDeviceService")
@Transactional(value="txManager2",propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class TerminalDeviceServiceImpl implements TerminalDeviceService {


    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    @Override
    public TerminalDevice getTerminalDevice(String id) throws Exception {
        return terminalDeviceDao.load(id);
    }

    @Override
    public TerminalDevice save(TerminalDevice terminalDevice) throws Exception {
        return terminalDeviceDao.save(terminalDevice);
    }

    @Override
    public PageResultSet<TerminalDevice> getTerminalDevices(TerminalDevice terminalDevice, Integer year, int page, int pageSize) throws Exception {
        return terminalDeviceDao.find(terminalDevice,page,pageSize);
    }
}
