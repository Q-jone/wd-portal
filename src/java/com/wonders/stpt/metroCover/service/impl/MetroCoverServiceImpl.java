package com.wonders.stpt.metroCover.service.impl;

import com.wonders.stpt.metroCover.model.vo.common.MetroCompanyVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroLineVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;
import com.wonders.stpt.metroCover.model.vo.production.MetroProductionVo;
import com.wonders.stpt.metroCover.model.vo.quality.MetroQualityVo;
import com.wonders.stpt.metroCover.model.vo.scale.MetroScaleVo;
import com.wonders.stpt.metroCover.service.MetroCoverService;
import com.wonders.stpt.metroCover.util.MetroCoverUtil;
import com.wonders.stpt.util.ConfigDbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
@Service("metroCoverService")
@Transactional(value="txManager2",propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class MetroCoverServiceImpl implements MetroCoverService{

    //缓存map
    private static Map<String,MetroQualityVo> qualityCacheMap = new ConcurrentHashMap<String, MetroQualityVo>();
    private static Map<String,MetroScaleVo> scaleCacheMap = new ConcurrentHashMap<String, MetroScaleVo>();
    private static Map<String,MetroProductionVo> productionCacheMap = new ConcurrentHashMap<String, MetroProductionVo>();

    private static String cacheTime = "";

    @Override
    public MetroQualityVo getMetroQualityCache(String lineId, String companyId, String date, String span, MetroType type){
        String key = cacheTime+"_"+lineId+"_"+companyId+"_"+date+"_"+span+"_"+type.getId();
        //synchronized (qualityCacheMap) {
            if (qualityCacheMap.containsKey(key)) {
                return qualityCacheMap.get(key);
            } else {
                MetroQualityVo vo = this.getMetroQualityInfo(lineId, companyId, date, span, type);
                qualityCacheMap.put(key, vo);
                return vo;
            }
        //}

    }

    @Override
    public MetroScaleVo getMetroScaleCache(String lineId, String companyId, String date, String span, MetroType type){
        String key = cacheTime+"_"+lineId+"_"+companyId+"_"+date+"_"+span+"_"+type.getId();
        //synchronized (scaleCacheMap) {
            if (scaleCacheMap.containsKey(key)) {
                return scaleCacheMap.get(key);
            } else {
                MetroScaleVo vo = this.getMetroScaleInfo(lineId, companyId, date, span, type);
                scaleCacheMap.put(key, vo);
                return vo;
            }
        //}
    }

    @Override
    public MetroProductionVo getMetroProductionCache(String lineId, String companyId, String date, String yearSpan, String span, MetroType type){
        String key = cacheTime+"_"+lineId+"_"+companyId+"_"+date+"_"+yearSpan+"_"+span+"_"+type.getId();
        //synchronized (productionCacheMap) {
            if (productionCacheMap.containsKey(key)) {
                return productionCacheMap.get(key);
            } else {
                MetroProductionVo vo = this.getMetroProductionInfo(lineId, companyId, date, yearSpan, span, type);
                productionCacheMap.put(key, vo);
                return vo;
            }
        //}
    }

    /**
     * 获取时间戳
     * @return
     */
    private void setMaxTime(){
        String sql = "select  max(to_char(d.operate_time,'yyyy-mm-dd hh24:mi:ss')) from dw_yygl_log d\n" +
                "where d.return_value=1000 and d.operate_time is not null";
        String maxTime = ConfigDbUtil.getJdbcTemplate("").queryForObject(sql,String.class);
        synchronized (cacheTime) {
            if (cacheTime.compareTo(maxTime) < 0) {
                qualityCacheMap.clear();
                scaleCacheMap.clear();
                productionCacheMap.clear();
                cacheTime = maxTime;
            } else {

            }
        }
    }


    /**
     * 获得有效时间的最大选项
     */
    public String getValidDate(){
        setMaxTime();
        String sql = "select max(t.indicator_date) from dw_metro_quality t where t.removed=0";
        return ConfigDbUtil.getJdbcTemplate("").
                queryForObject(sql, String.class);
    }
    /**
     * 公共配置-获取线路集合
     * @return
     */
    @Override
    public List<MetroLineVo> getMetroLine(){
        String sql = "select distinct line_id lineId,line_name " +
                "lineName from t_metro_line m order by m.sorting_order";
        return ConfigDbUtil.getJdbcTemplate("").
                query(sql, new BeanPropertyRowMapper<MetroLineVo>(MetroLineVo.class));
    }

    /**
     * 公共配置-获取运营公司集合
     * @return
     */
    @Override
    public List<MetroCompanyVo> getMetroCompany(){
        String sql = "select companyId,companyName from (\n" +
                " select distinct company_id companyId,company_name companyName,\n" +
                "  m.company_order orders from t_metro_company m \n" +
                " where m.removed = 0)\n" +
                " order by orders";
        return ConfigDbUtil.getJdbcTemplate("").
                query(sql,new BeanPropertyRowMapper<MetroCompanyVo>(MetroCompanyVo.class));
    }

    /**
     * 公共配置-根据运营公司ID获取线路集合
     * @param companyId
     * @return
     */
    @Override
    public List<MetroLineVo> getMetroLineByCompany(String companyId){
        String sql = "select l.line_id,l.line_name from t_metro_company m,t_metro_line l\n" +
                "where m.company_id = ? and m.line_id=l.line_id and m.removed =0\n" +
                "order by l.sorting_order";
        return ConfigDbUtil.getJdbcTemplate("").
                query(sql,new Object[]{companyId},new BeanPropertyRowMapper<MetroLineVo>(MetroLineVo.class));
    }

    /**
     * 质量安全-获取相关信息
     * @param lineId
     * @param companyId
     * @param date
     * @param span
     * @param type
     * @return
     */
    private MetroQualityVo getMetroQualityInfo(String lineId, String companyId, String date, String span, MetroType type){
        MetroQualityVo vo = new MetroQualityVo();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(ConfigDbUtil.getJdbcTemplate(""));
        String sql = null;
        try {
        switch (type){
            case LINE:
                //今年累计正点率 : 日年累计 今日 日正点率
                sql = "select distinct\n" +
                        "t.metro_ontime_daily onTimeDaily,\n" +
                        "t.metro_ontime_year onTimeYear,\n" +
                        "t.metro_onwork_daily onWorkDaily,\n" +
                        "t.metro_onwork_year onWorkYear\n" +
                        "from dw_metro_quality t where\n" +
                        "t.indicator_line = ? and t.indicator_date=?\n";

                    vo = ConfigDbUtil.getJdbcTemplate("").
                            queryForObject(sql, new Object[]{lineId, date}, new BeanPropertyRowMapper<MetroQualityVo>(MetroQualityVo.class));

                //去年累计正点率 ：去年最后一天年累计
                sql = "select distinct\n" +
                        "t.metro_ontime_year onTimeLastYear,\n" +
                        "t.metro_onwork_year onWorkLastYear\n" +
                        "from dw_metro_quality t where\n" +
                        "t.indicator_line = ? and t.indicator_date=?\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId, MetroCoverUtil.getLastDateLastYear(date)}),vo);
                //管控值 读取年管控值
                sql = "select distinct t.metro_ontime onTimeControl,\n" +
                        "t.metro_onwork onWorkControl from dw_metro_info_predict\n" +
                        "t where t.removed='0' and t.indicator_line = ? \n" +
                        "and t.indicator_year=? and t.indicator_month =0 ";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId, MetroCoverUtil.getYear(date)}),vo);
                //近15日的所有数据
                sql = "select \n" +
                        "t.metro_ontime_daily onTimeList,\n" +
                        "t.metro_onwork_daily onWorkList \n" +
                        "from dw_metro_quality t where t.indicator_line = :indicator_line\n" +
                        "and t.indicator_date in (:indicator_date) \n" +
                        "order by t.indicator_date";
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line",lineId);
                paramMap.put("indicator_date",MetroCoverUtil.getLastDateByDaySpan(date,span));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForList(sql, paramMap),vo);
                break;
            case COMPANY:
                List<MetroLineVo> lines = getMetroLineByCompany(companyId);
                for(MetroLineVo lineVo : lines){
                    MetroQualityVo resultVo = this.getMetroQualityInfo(lineVo.getLineId(),companyId,date,span,MetroType.LINE);
                    resultVo.setLineName(lineVo.getLineName());
                    vo.getList().add(resultVo);
                }


                break;
            default:break;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //刷新数据
        vo.refresh(lineId,companyId,date,type);
        return vo;
    }

    /**
     * 规模指标-获取相关信息
     * @param lineId
     * @param companyId
     * @param date
     * @param span
     * @param type
     * @return
     */
    private MetroScaleVo getMetroScaleInfo(String lineId, String companyId, String date, String span, MetroType type){
        MetroScaleVo vo = new MetroScaleVo();
        String sql = null;
        try {
        switch(type) {
            case LINE:
                //配属数、线路长度、车站数
                sql = "select distinct t.line_distance lineDistance,\n" +
                        "t.station_count stationCount,\n" +
                        "t.allocate_eight_section eightSections,\n" +
                        "t.allocate_seven_section sevenSections,\n" +
                        "t.allocate_six_section sixSections,\n" +
                        "t.allocate_four_section fourSections,\n" +
                        "t.allocate_three_section threeSections  from dw_metro_scale t where t.removed = '0'\n" +
                        "and t.indicator_line = ? and t.indicator_date = ?";

                    vo = ConfigDbUtil.getJdbcTemplate("").
                            queryForObject(sql, new Object[]{lineId, date}, new BeanPropertyRowMapper<MetroScaleVo>(MetroScaleVo.class));

                break;
            case COMPANY:
                List<MetroLineVo> lines = getMetroLineByCompany(companyId);
                for(MetroLineVo lineVo : lines){
                    vo.getList().add(this.getMetroScaleInfo(lineVo.getLineId(),companyId,date,span,MetroType.LINE));
                }
//                sql = "select t.line_distance lineDistance,\n" +
//                        "t.station_count stationCount,\n" +
//                        "t.allocate_eight_section eightSections,\n" +
//                        "t.allocate_seven_section sevenSections,\n" +
//                        "t.allocate_six_section sixSections,\n" +
//                        "t.allocate_four_section fourSections,\n" +
//                        "t.allocate_three_section threeSections  from dw_metro_scale t,\n" +
//                        "t_metro_line l , t_metro_company c\n" +
//                        " where t.removed = '0' and c.removed = '0' and c.line_id= l.line_id\n" +
//                        " and c.company_id = ? and t.indicator_line=l.line_id\n" +
//                        "and  t.indicator_date = ? \n" +
//                        "order by c.line_order\n";
//                vo.setList(ConfigDbUtil.getJdbcTemplate("").
//                        query(sql, new Object[]{companyId, date}, new BeanPropertyRowMapper<MetroScaleVo>(MetroScaleVo.class)));
                break;
            default:break;


        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //刷新数据
        vo.refresh(lineId, companyId, date, type);
        return vo;
    }

    /**
     * 运营生产-获取相关信息
     * @param lineId
     * @param companyId
     * @param date
     * @param span
     * @param type
     * @return
     */
    private MetroProductionVo getMetroProductionInfo(String lineId, String companyId, String date,
                                                    String yearSpan, String span, MetroType type){
        MetroProductionVo vo = new MetroProductionVo();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(ConfigDbUtil.getJdbcTemplate(""));
        String sql = null;
        try {
        switch(type){
            case LINE:
                //运用列车数 开行列次 运营里程
                sql = "select distinct \n" +
                        "t.online_eight_section+\n" +
                        "t.online_seven_section+\n" +
                        "t.online_six_section+\n" +
                        "t.online_four_section+\n" +
                        "t.online_three_section+\n" +
                        "t.backup_eight_section+\n" +
                        "t.backup_seven_section+\n" +
                        "t.backup_six_section+\n" +
                        "t.backup_four_section+\n" +
                        "t.backup_three_section useMetroDaily,\n" +
                        "t.metro_column_daily runMetroDaily,\n" +
                        "t.metro_column_month runMetroMonth,\n" +
                        "t.passenger_capacity_daily passCapDaily,\n" +
                        "t.passenger_capacity_year passCapYear,\n" +
                        "t.ticket_income_daily incomeDaily,\n" +
                        "t.ticket_income_year incomeYear,\n" +
                        "t.metro_distance_daily metroDistanceDaily,\n" +
                        "t.metro_distance_year metroDistanceYear \n" +
                        " from dw_metro_production t \n" +
                        "where t.removed=0 and t.indicator_line=? and t.indicator_date=?";

                    vo = ConfigDbUtil.getJdbcTemplate("").
                            queryForObject(sql, new Object[]{lineId, date}, new BeanPropertyRowMapper<MetroProductionVo>(MetroProductionVo.class));

                //获取当前日管控值

                Map<String,Object> paramMap = new HashMap<String, Object>();

                BigDecimal a = new BigDecimal("0.0");
                BigDecimal b = new BigDecimal("0.0");
                BigDecimal c = new BigDecimal("0.0");
                double percent = (double)MetroCoverUtil.getCurDayByDate(date)/MetroCoverUtil.getMaxDaysByDate(date);
                //过往月度管控值
                sql = "select sum(t.passenger_capacity) a,\n" +
                        "sum(t.ticket_income) b,\n" +
                        "sum(t.metro_distance) c from dw_metro_info_predict t\n" +
                        "where t.removed=0 and t.indicator_line= :indicator_line \n" +
                        "and t.indicator_year=:indicator_year\n" +
                        "and t.indicator_month in (:indicator_month)";
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_year",MetroCoverUtil.getYear(date));
                paramMap.put("indicator_month",MetroCoverUtil.getCurrentBeforeMonth(date));
                Map<String,Object> aa = namedParameterJdbcTemplate.queryForMap(sql, paramMap);
                BigDecimal bb = (BigDecimal)aa.get("a");
                a = a.add((BigDecimal)aa.get("a"));
                b = b.add((BigDecimal)aa.get("b"));
                c = c.add((BigDecimal)aa.get("c"));

                //当月累计管控值 精确到日
                sql = "select t.passenger_capacity a ,\n" +
                        " t.ticket_income  b,\n" +
                        " t.metro_distance  c from dw_metro_info_predict t\n" +
                        " where t.removed=0 and t.indicator_line= :indicator_line\n" +
                        " and t.indicator_year= :indicator_year \n" +
                        " and t.indicator_month = :indicator_month";
                paramMap = new HashMap<String,Object>();
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_year",MetroCoverUtil.getYear(date));
                paramMap.put("indicator_month", MetroCoverUtil.getCurrentMonth(date));
                aa = namedParameterJdbcTemplate.queryForMap(sql, paramMap);

                a = a.add(((BigDecimal)aa.get("a")).multiply(new BigDecimal(percent)));
                b = b.add(((BigDecimal)aa.get("b")).multiply(new BigDecimal(percent)));
                c = c.add(((BigDecimal)aa.get("c")).multiply(new BigDecimal(percent)));

                vo.setPassCapDailyControl(a.doubleValue());
                vo.setIncomeDailyControl(b.doubleValue());
                vo.setMetroDistanceDailyControl(c.doubleValue());

                //运用车总数
                sql = "select distinct t.all_section allSection from dw_metro_section_predict t \n" +
                        "where t.removed=0 and t.indicator_line =?\n" +
                        "and t.indicator_day=?";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql,new Object[]{lineId,MetroCoverUtil.getDay(date)}),vo);
                //近15日 运用列车数 开行列车
                sql = "select t.online_eight_section+t.online_seven_section+t.online_six_section+t.online_four_section\n" +
                        "+t.online_three_section+t.backup_eight_section+t.backup_seven_section+t.backup_six_section\n" +
                        "+t.backup_four_section+t.backup_three_section useMetroList, \n" +
                        " t.metro_column_daily runMetroList from dw_metro_production t \n" +
                        "where t.removed =0 and t.indicator_line = :indicator_line\n" +
                        "and t.indicator_date in (:indicator_date) order by t.indicator_date \n";
                paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line",lineId);
                paramMap.put("indicator_date",MetroCoverUtil.getLastDateByDaySpan(date,span));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForList(sql, paramMap),vo);
                //去年总额
                sql = "select distinct t.passenger_capacity_year passCapLastYear,\n" +
                        "t.ticket_income_year incomeLastYear,t.metro_distance_year metroDistanceLastYear\n" +
                        "from dw_metro_production t where t.indicator_line = ? and t.indicator_date=?\n" +
                        "and t.removed=0";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql,new Object[]{lineId,MetroCoverUtil.getLastDateLastYear(date)}),vo);
                //年管控值
                sql = "select distinct t.passenger_capacity passCapControlYear,\n" +
                        "t.ticket_income incomeControlYear,\n" +
                        "t.metro_distance metroDistanceControlYear from dw_metro_info_predict t\n" +
                        "where t.removed=0 and t.indicator_line=? and t.indicator_year=?\n" +
                        "and t.indicator_month =0 ";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql,new Object[]{lineId,MetroCoverUtil.getYear(date)}),vo);
                //月计划累计值
                sql = "select sum(t.passenger_capacity) passCapPlanMonth,\n" +
                        "sum(t.ticket_income) incomePlanMonth,\n" +
                        "sum(t.metro_distance) metroDistancePlanMonth from dw_metro_info_predict t\n" +
                        "where t.removed=0 and t.indicator_line= :indicator_line \n" +
                        "and t.indicator_year=:indicator_year\n" +
                        "and t.indicator_month in (:indicator_month)";
                paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_year",MetroCoverUtil.getYear(date));
                paramMap.put("indicator_month",MetroCoverUtil.getTotalMonth(date));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForMap(sql, paramMap),vo);
                //近12个月平均值
                sql = "select avg(t.passenger_capacity_month) passCapAvgMonth,\n" +
                        "avg(t.ticket_income_month) incomeAvgMonth, \n" +
                        "avg(t.metro_distance_month) metroDistanceAvgMonth from dw_metro_production t\n" +
                        "where t.removed=0 and t.indicator_line= :indicator_line and t.indicator_date in (:indicator_date)\n";
                paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_date",MetroCoverUtil.getLastDateByMonthSpan(date, yearSpan));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForMap(sql,paramMap),vo);
                //近12个月值
                sql = "select t.passenger_capacity_month passCapMonthList,\n" +
                        "t.ticket_income_month incomeMonthList,\n" +
                        "t.metro_distance_month metroDistanceMonthList from dw_metro_production t\n" +
                        "where t.removed=0 and t.indicator_line= :indicator_line and t.indicator_date in (:indicator_date)\n" +
                        "order by t.indicator_date\n";
                paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_date",MetroCoverUtil.getLastDateByMonthSpan(date,yearSpan));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForList(sql, paramMap),vo);
                //开始日期结束日期 vo内进行
                sql = "select min(t.indicator_date) passCapStartMonth,\n" +
                        "min(t.indicator_date)incomeStartMonth,\n" +
                        "min(t.indicator_date) metroDistanceStartMonth,\n" +
                        "max(t.indicator_date) passCapEndMonth,\n" +
                        "max(t.indicator_date) incomeEndMonth,\n" +
                        "max(t.indicator_date) metroDistanceEndMonth from dw_metro_production t\n" +
                        " where t.removed=0 and t.indicator_line= :indicator_line and t.indicator_date in (:indicator_date)";
                paramMap = new HashMap<String, Object>();
                paramMap.put("indicator_line", lineId);
                paramMap.put("indicator_date",MetroCoverUtil.getLastDateByMonthSpan(date,yearSpan));
                MetroCoverUtil.copyProperties(namedParameterJdbcTemplate.
                        queryForList(sql, paramMap),vo);

                //去年日均 今年日均 日均管控
                sql = "select avg(t.passenger_capacity_daily) passCapAvgLastYear,\n" +
                        " avg(t.ticket_income_daily) incomeAvgLastYear,\n" +
                        " avg(t.metro_distance_daily) metroDistanceAvgLastYear from dw_metro_production t \n" +
                        "where t.indicator_line = ? and t.indicator_date >= ? and t.indicator_date <= ?\n" +
                        "and t.removed =0 ";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId, MetroCoverUtil.getFirstDateLastYear(date),
                                MetroCoverUtil.getLastDateLastYear(date)}),vo);
                sql = "select avg(t.passenger_capacity_daily) passCapAvgYear,\n" +
                        " avg(t.ticket_income_daily) incomeAvgYear,\n" +
                        " avg(t.metro_distance_daily) metroDistanceAvgYear from dw_metro_production t \n" +
                        "where t.indicator_line = ? and t.indicator_date >= ? and t.indicator_date <= ?\n" +
                        "and t.removed =0 ";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId, MetroCoverUtil.getFirstDateYear(date),
                                date}),vo);

                //今年峰值 峰值日期
                sql = "select passCapMaxYearDate,passCapMaxYear from (\n" +
                        "select t.indicator_date passCapMaxYearDate,\n" +
                        "t.passenger_capacity_daily passCapMaxYear, row_number ()\n" +
                        " over \n" +
                        "( order by t.passenger_capacity_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=? and  t.indicator_date >=?\n" +
                        "and t.indicator_date < =?\n" +
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId,MetroCoverUtil.getFirstDateYear(date),date}),vo);

                //今年峰值 峰值日期
                sql = "select incomeMaxYearDate,incomeMaxYear from (\n" +
                        "select t.indicator_date incomeMaxYearDate,\n" +
                        "t.ticket_income_daily incomeMaxYear, row_number ()\n" +
                        " over \n" +
                        "( order by t.ticket_income_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=? and t.indicator_date >=?\n" +
                        "and t.indicator_date < =?\n" +
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId,MetroCoverUtil.getFirstDateYear(date),date}),vo);

                //今年峰值 峰值日期
                sql = "select metroDistanceMaxYearDate,metroDistanceMaxYear from (\n" +
                        "select t.indicator_date metroDistanceMaxYearDate,\n" +
                        "t.metro_distance_daily metroDistanceMaxYear, row_number ()\n" +
                        " over \n" +
                        "( order by t.metro_distance_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=? and t.indicator_date >=?\n" +
                        "and t.indicator_date < =?\n" +
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId,MetroCoverUtil.getFirstDateYear(date),date}),vo);


                //历史峰值 峰值日期
                sql = "select passCapMaxLastDate,passCapMaxLast from (\n" +
                        "select t.indicator_date passCapMaxLastDate,\n" +
                        "t.passenger_capacity_daily passCapMaxLast, row_number ()\n" +
                        " over \n" +
                        "( order by t.passenger_capacity_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=?\n"+
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId}),vo);

                //历史峰值 峰值日期
                sql = "select incomeMaxLastDate,incomeMaxLast from (\n" +
                        "select t.indicator_date incomeMaxLastDate,\n" +
                        "t.ticket_income_daily incomeMaxLast, row_number ()\n" +
                        " over \n" +
                        "( order by t.ticket_income_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=? \n" +
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId}),vo);

                //历史峰值 峰值日期
                sql = "select metroDistanceMaxLastDate,metroDistanceMaxLast from (\n" +
                        "select t.indicator_date metroDistanceMaxLastDate,\n" +
                        "t.metro_distance_daily metroDistanceMaxLast, row_number ()\n" +
                        " over \n" +
                        "( order by t.metro_distance_daily desc)  rn\n" +
                        "from dw_metro_production t \n" +
                        "where t.removed='0' and t.indicator_line=?\n" +
                        ") where rn=1\n";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId}),vo);

                //昨日数据
                sql = "select t.passenger_capacity_daily passCapLast,\n" +
                        "t.ticket_income_daily incomeLast,\n" +
                        "t.metro_distance_daily metroDistanceLast from dw_metro_production t where t.removed=0 \n" +
                        "and t.indicator_line = ? and t.indicator_date = ?";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{lineId, MetroCoverUtil.getPreDay(date)}),vo);

                break;
            case COMPANY:
                //今年峰值 历史峰值
                sql = "select d passCapMaxYearDate  ,a passCapMaxYear from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.passenger_capacity_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 and d.indicator_date >=?\n" +
                        "and d.indicator_date < =?\n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId,MetroCoverUtil.getFirstDateYear(date),date}),vo);

                sql = "select d incomeMaxYearDate ,a incomeMaxYear from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.ticket_income_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 and d.indicator_date >=?\n" +
                        "and d.indicator_date < =?\n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId,MetroCoverUtil.getFirstDateYear(date),date}),vo);

                sql = "select d metroDistanceMaxYearDate ,a metroDistanceMaxYear from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.metro_distance_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 and d.indicator_date >=?\n" +
                        "and d.indicator_date < =?\n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId,MetroCoverUtil.getFirstDateYear(date),date}),vo);

                //历史峰值
                sql = "select d passCapMaxLastDate  ,a passCapMaxLast from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.passenger_capacity_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 \n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId}),vo);

                sql = "select d incomeMaxLastDate ,a incomeMaxLast from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.ticket_income_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 \n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId}),vo);

                sql = "select d metroDistanceMaxLastDate ,a metroDistanceMaxLast from (\n" +
                        "select d,a,row_number() over(order by a desc) rn from (\n" +
                        "select sum(d.metro_distance_daily) a,indicator_date d\n" +
                        " from \n" +
                        "t_metro_company c , t_metro_line l,dw_metro_production d\n" +
                        "where c.line_id=l.line_id and c.company_id= ?\n" +
                        " and c.removed=0 \n"+
                        "and d.indicator_line=c.line_id \n" +
                        "group by c.company_id,d.indicator_date\n" +
                        ") p ) where rn=1";
                MetroCoverUtil.copyProperties(ConfigDbUtil.getJdbcTemplate("").
                        queryForMap(sql, new Object[]{companyId}),vo);

                List<MetroLineVo> lines = getMetroLineByCompany(companyId);
                for(MetroLineVo lineVo : lines){
                    vo.getList().add(this.getMetroProductionInfo(lineVo.getLineId(), companyId, date, yearSpan,span, MetroType.LINE));
                }
                break;
            default:break;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //刷新数据
        vo.refresh(lineId,companyId,date,type);
        return vo;
    }

}
