package com.wonders.stpt.metroCover.util;

import com.wonders.stpt.metroCover.constants.MetroCoverConstants;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;
import com.wonders.stpt.metroCover.model.vo.production.MetroProductionVo;
import com.wonders.stpt.metroCover.model.vo.quality.MetroQualityVo;
import com.wonders.stpt.metroCover.model.vo.scale.MetroScaleVo;
import com.wonders.stpt.util.StringUtil;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/24
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 */
public class MetroCoverUtil {
    private static Map<String, String> keyMap = new HashMap<String, String>();

    static {
        setKeyMap(keyMap, MetroScaleVo.class);
        setKeyMap(keyMap, MetroProductionVo.class);
        setKeyMap(keyMap, MetroQualityVo.class);

    }

    private static void setKeyMap(Map<String, String> map, Class<?> c) {
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            map.put(f.getName().toUpperCase(), f.getName());
        }
    }

    /**
     * //将字符串解析为日期
     *
     * @param date
     * @return
     */
    private static Date getDateFromString(String date) {
        //yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        Date d = new Date();
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * //获得每月最后1天
     *
     * @param span
     * @param now
     * @return
     */
    private static String getLastDateByMonth(int span, String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        calendar.add(Calendar.MONTH, span);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormate.format(calendar.getTime());
    }

    /**
     * //获得每年第一天
     *
     * @param span
     * @param now
     * @return
     */
    private static String getFirstDateByYear(int span, String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        calendar.add(Calendar.YEAR, span);
        calendar.set(Calendar.DAY_OF_YEAR, calendar
                .getActualMinimum(Calendar.DAY_OF_YEAR));
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormate.format(calendar.getTime());
    }

    /**
     * //获得每年最后一天
     *
     * @param span
     * @param now
     * @return
     */
    private static String getLastDateByYear(int span, String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        calendar.add(Calendar.YEAR, span);
        calendar.set(Calendar.DAY_OF_YEAR, calendar
                .getActualMaximum(Calendar.DAY_OF_YEAR));
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormate.format(calendar.getTime());
    }

    /**
     * //获得去年最后一天
     *
     * @param span
     * @param now
     * @return
     */
    public static String getLastDateLastYear(String now) {
        return getLastDateByYear(-1, now);
    }

    /**
     * //获得去年第一天
     *
     * @param span
     * @param now
     * @return
     */
    public static String getFirstDateLastYear(String now) {
        return getFirstDateByYear(-1, now);
    }

    /**
     * //获得今年最后一天
     *
     * @param span
     * @param now
     * @return
     */
    public static String getLastDateYear(String now) {
        return getLastDateByYear(0, now);
    }

    /**
     * //获得今年第一天
     *
     * @param span
     * @param now
     * @return
     */
    public static String getFirstDateYear(String now) {
        return getFirstDateByYear(0, now);
    }

    /**
     * //获得指定日期
     *
     * @param now
     * @param span
     * @return
     */
    public static String getDateBySpan(String now, int span) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));

        calendar.add(Calendar.DATE, span);
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormate.format(calendar.getTime());
    }

    /**
     * //获得一年第一天 到指定日期
     *
     * @param now
     * @return
     */
    public static List<String> getFirstAndSpecificDateByYear(String now) {
        List<String> result = new ArrayList<String>();
        result.add(getFirstDateByYear(0, now));
        result.add(now);
        return result;
    }

    /**
     * //获得一年第一天和最后一天 1.1 12.31
     *
     * @param now
     * @return
     */
    public static List<String> getFirstAndLastDateByYear(String now) {
        List<String> result = new ArrayList<String>();
        result.add(getFirstDateByYear(0, now));
        result.add(getLastDateByYear(0, now));
        return result;
    }

    /**
     * //获取指定区间前的月份 1 - 12 月
     *
     * @param now
     * @param span
     * @return
     */
    public static List<String> getLastDateByMonthSpan(String now, String span) {
        List<String> result = new ArrayList<String>();
        int daySpan = 0;
        try {
            daySpan = Integer.parseInt(span);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = daySpan; i >= 1; i--) {
            result.add(getLastDateByMonth(i * -1, now));
        }
        //这个月取当前时间
        //result.add(now);
        return result;
    }

    /**
     * //获取指定区间前的日期 1号-15号
     *
     * @param now
     * @param span
     * @return
     */
    public static List<String> getLastDateByDaySpan(String now, String span) {
        List<String> result = new ArrayList<String>();
        int daySpan = 0;
        try {
            daySpan = Integer.parseInt(span);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = daySpan - 1; i >= 0; i--) {
            result.add(getDateBySpan(now, i * -1));
        }
        return result;
    }

    /**
     * 获得昨天日期
     *
     * @return
     */
    public static String getPreDay(String now) {
        return getDateBySpan(now, -1);
    }

    /**
     * 获得累计月份1-8
     *
     * @param now
     * @return
     */
    public static List<String> getTotalMonth(String now) {
        List<String> result = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        int month = calendar.get(Calendar.MONTH) + 1;
        for (int i = 1; i <= month; i++) {
            result.add(i + "");
        }
        return result;
    }

    /**
     * //获得指定日期 年份
     *
     * @param now
     * @return
     */
    public static String getYear(String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        return calendar.get(Calendar.YEAR) + "";
    }

    /**
     * //获得当前日期 为周几
     *
     * @param now
     * @return
     */
    public static String getDay(String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return day == 0 ? "7" : day + "";
    }

    /**
     * //获得指定日期 月份
     *
     * @param now
     * @return
     */
    public static List<String> getMonth(String now) {
        List<String> result = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        for (int i = 1; i <= (calendar.get(Calendar.MONTH) + 1); i++) {
            result.add(i + "");
        }
        return result;
    }

    /**
     * 获得指定日期当前月份
     *
     * @param now
     * @return
     */
    public static String getCurrentMonth(String now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * //获得指定日期 前的月份 4-12 return 1 2 3
     *
     * @param now
     * @return
     */
    public static List<String> getCurrentBeforeMonth(String now) {
        List<String> result = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(now));
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month > 1) {
            for (int i = 1; i < month; i++) {
                result.add(i + "");
            }
        }
        return result;
    }

    /**
     * 获得每月天数
     * @param date
     * @return
     */
    public static long getMaxDaysByDate(String date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(date));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前 日
     * @param date
     * @return
     */
    public static long getCurDayByDate(String date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromString(date));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * //将single中的结果插入bo对象
     *
     * @param map
     * @param o
     */
    public static void copyProperties(String name, Object value, Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField(keyMap.get(name));
            boolean accessFlag = field.isAccessible();
            field.setAccessible(true);
            setField(obj, field, value);
            field.setAccessible(accessFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * //将map中的结果插入bo对象
     *
     * @param map
     * @param o
     */
    public static void copyProperties(Map<String, Object> map, Object obj) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                Field field = obj.getClass().getDeclaredField(keyMap.get(entry.getKey()));
                boolean accessFlag = field.isAccessible();
                field.setAccessible(true);
                setField(obj, field, entry.getValue());
                field.setAccessible(accessFlag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * //将map中的结果插入bo对象
     *
     * @param list
     * @param o
     */
    public static void copyProperties(List<Map<String, Object>> list, Object obj) {
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    Field field = obj.getClass().getDeclaredField(keyMap.get(entry.getKey()));
                    boolean accessFlag = field.isAccessible();
                    field.setAccessible(true);
                    setField(obj, field, entry.getValue());
                    field.setAccessible(accessFlag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getMethod(Object obj, String methodName, Object[] args) {
        Object result = args[0];
        Class<?> cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        String methodsName = "";
        for (Method method : methods) {
            methodsName += method.getName() + ",";
        }
        if (methodsName.indexOf(methodName) >= 0) {
            //返回方法名为“testMethod”的一个 Method 对象，后面跟的是该方法参数
            Method method;
            try {
                method = cls.getMethod(methodName, argsClass);
                result = method.invoke(null, args);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    private static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * list 相加
     *
     * @param result
     * @param list
     */
    public static void addDoubleList(List<Double> result, List<Double> list) {
        if (list.size() > 0) {
            List<Double> temp = new ArrayList<Double>();
            for (int i = 0; i < list.size(); i++) {
                temp.add((result.size() == 0 ? list.get(i) : add(result.get(i), list.get(i))));
            }
            result.clear();
            result.addAll(temp);
        }
    }

    public static void addIntegerList(List<Integer> result, List<Integer> list) {
        if (list.size() > 0) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < list.size(); i++) {
                temp.add((result.size() == 0 ? 0 : result.get(i)) + list.get(i));
            }
            result.clear();
            result.addAll(temp);
        }
    }

    /**
     * 获取泛型
     *
     * @param field
     * @return
     */
    private static Class<?> getGenericType(Field field) {
        Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
        Class genericClazz = null;
        if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
        {
            ParameterizedType pt = (ParameterizedType) fc;
            genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
        }
        return genericClazz;
    }

    /**
     * 设置泛型方法
     */
    private static void setField(Object object, Field field, Object value) {
        try {
            if (field.getType().equals(List.class)) {
                List temp = (List) field.get(object);
                if (getGenericType(field).equals(Double.class)) {
                    temp.add(((BigDecimal) value).doubleValue());
                } else if (getGenericType(field).equals(Integer.class)) {
                    temp.add(((BigDecimal) value).intValue());
                } else if (getGenericType(field).equals(String.class)) {
                    temp.add(((String) value));
                }

            } else {
                if (field.getType().equals(Double.class)) {
                    field.set(object, ((BigDecimal) value).doubleValue());
                } else if (field.getType().equals(Integer.class)) {
                    field.set(object, ((BigDecimal) value).intValue());
                } else if (field.getType().equals(String.class)) {
                    field.set(object, (String) value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 准备request
     *
     * @param request
     * @param validDate
     * @return
     */
    public static Map<String, String> prepareParam(HttpServletRequest request, String validDate) {
        Map<String, String> result = new HashMap<String, String>();
        MetroType type = getMetroType(StringUtil.getNotNullValueString(request.getParameter("type")));
        result.put("type", type.getId());
        switch (getMetroType(StringUtil.getNotNullValueString(request.getParameter("type")))) {
            case LINE:
                result.put("lineId", StringUtil.getNotNullValueString(request.getParameter("id")).length()
                        == 0 ? MetroCoverConstants.LINE_DEFAULT : StringUtil.getNotNullValueString(request.getParameter("id")));
                break;
            case COMPANY:
                result.put("companyId", StringUtil.getNotNullValueString(request.getParameter("id")).length()
                        == 0 ? MetroCoverConstants.COMPANY_DEFAULT : StringUtil.getNotNullValueString(request.getParameter("id")));
                break;
            default:
                result.put("lineId", StringUtil.getNotNullValueString(request.getParameter("id")).length()
                        == 0 ? MetroCoverConstants.LINE_DEFAULT : StringUtil.getNotNullValueString(request.getParameter("id")));
                break;
        }
        result.put("date", StringUtil.getNotNullValueString(request.getParameter("date")).length()
                == 0 ? validDate : StringUtil.getNotNullValueString(request.getParameter("date")));
        result.put("span", StringUtil.getNotNullValueString(request.getParameter("span")).length()
                == 0 ? MetroCoverConstants.SPAN_DEFAULT : StringUtil.getNotNullValueString(request.getParameter("span")));
        result.put("yearSpan", StringUtil.getNotNullValueString(request.getParameter("yearSpan")).length()
                == 0 ? MetroCoverConstants.YEAR_SPAN_DEFAULT : StringUtil.getNotNullValueString(request.getParameter("yearSpan")));
        return result;
    }

    /**
     * 获取type类型
     *
     * @param type
     * @return
     */
    public static MetroType getMetroType(String type) {
        MetroType result = MetroType.LINE;
        MetroType[] metroType = MetroType.values();
        for (MetroType t : metroType) {
            if (t.getId().equals(type)) {
                result = t;
                break;
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println(getMonth("2014-10-19"));
        //      System.out.println(getDay("2014-10-19"));
        //       System.out.println(getYear("2013-12-12"));
        // System.out.println(getDateBySpan("2014-12-23","-1"));
        List<String> list = null;
        list = getMonth("2014-12-03");
        //        list = getFirstAndLastDateByYear("2014-11-11");
        //      list = getFirstAndSpecificDateByYear("2014-10-30");
        //       list = getLastDateByDaySpan("2014-12-22","15");
        //      list = getLastDateByMonthSpan("2014-11-22","12");
//        System.out.println(getLastDateByYear("2014-12-21"));

        for (String s : list) {
            System.out.println(s);
        }

        Field f = MetroQualityVo.class.getDeclaredField("onTimeList");
        System.out.println(f.getType() == List.class);

        MetroScaleVo a = new MetroScaleVo();
        a.setLineName("1111");
        a.test();

        Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
        List<Double> result = new ArrayList<Double>();
        List<Double> list2 = new ArrayList<Double>();
        list2.add(11.0);
        list2.add(22.1);
        list2.add(33.1);
        List<Double> list3 = new ArrayList<Double>();
        list3.add(51.0);
        list3.add(12.1);
        list3.add(63.1);
        //MetroCoverUtil.addListByList(result,list2);
        //MetroCoverUtil.addListByList(result,list3);
        System.out.println(result);
        System.out.println(new DecimalFormat("0.00").format(36.477));
        Integer i = 1;
        System.out.println(i.getClass().equals(Integer.class));
        List<Double> dd = new ArrayList<Double>();
        Type[] ttt = dd.getClass().getGenericInterfaces();
        Type listType = ttt[0];
        if (listType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) listType;
            Type t = pt.getActualTypeArguments()[0];
            System.out.println(pt.getRawType());
        }


        Field field = MetroScaleVo.class.getDeclaredField("list");
        Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型


        if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
        {
            ParameterizedType pt = (ParameterizedType) fc;

            Class genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
            System.out.println(genericClazz);
        }

        BigDecimal d1 = new BigDecimal("213.31");
        BigDecimal d2 = new BigDecimal("71.28");
        System.out.println(d1.add(d2).doubleValue());

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(Double.valueOf(df.format(1.0)));

        System.out.println(getMaxDaysByDate("2015-04-15"));
    }
}
