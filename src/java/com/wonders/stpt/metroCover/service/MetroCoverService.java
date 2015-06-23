package com.wonders.stpt.metroCover.service;


import com.wonders.stpt.metroCover.model.vo.common.MetroCompanyVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroLineVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;
import com.wonders.stpt.metroCover.model.vo.production.MetroProductionVo;
import com.wonders.stpt.metroCover.model.vo.quality.MetroQualityVo;
import com.wonders.stpt.metroCover.model.vo.scale.MetroScaleVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public interface MetroCoverService {
    /**
     * 公共配置-获取线路集合
     *
     * @return
     */
    public List<MetroLineVo> getMetroLine();

    /**
     * 公共配置-获取运营公司集合
     *
     * @return
     */
    public List<MetroCompanyVo> getMetroCompany();

    /**
     * 公共配置-根据运营公司ID获取线路集合
     *
     * @param companyId
     * @return
     */
    public List<MetroLineVo> getMetroLineByCompany(String companyId);

    public MetroQualityVo getMetroQualityCache(String lineId, String companyId, String date, String span, MetroType type);

    public MetroScaleVo getMetroScaleCache(String lineId, String companyId, String date, String span, MetroType type);

    public MetroProductionVo getMetroProductionCache(String lineId, String companyId, String date, String yearSpan, String span, MetroType type);

    public String getValidDate();
}