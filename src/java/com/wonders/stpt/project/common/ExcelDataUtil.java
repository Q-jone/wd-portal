package com.wonders.stpt.project.common;

import com.wonders.stpt.util.StringUtil;
import jxl.*;
import jxl.write.biff.DateRecord;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2014/8/20.
 */
public class ExcelDataUtil {
    public List<String> validExcelTemplate(Sheet sheet, int beginRowIndex, Integer[] cellIndexs) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = beginRowIndex; i < sheet.getRows(); i++) {
            Cell[] cells = sheet.getRow(i);
            for (Integer cellIndex : cellIndexs) {
                String r = validateDate(cells[cellIndex]);
                if (StringUtils.isNotEmpty(r))
                    result.add(r);
            }

        }
        return result;
    }

    public String validateDate(Cell cell) {
        int row = cell.getRow() + 1;
        int column = cell.getColumn() + 1;
        System.out.println(StringUtil.getNotNullValueString(cell.getContents()));
        if (cell.getType() == CellType.NUMBER) {
            if (!StringUtil.getNotNullValueString(cell.getContents()).matches("^[0-9]*[1-9][0-9]*$")) {
                return "第" + row + "行，第" + column + "列日期格式不正确!正确的格式为:yyyy/mm/dd";
            }
           return null;
        } else if (cell.getType() == CellType.DATE) {
                return null;
        } else {
            if (!validateDate(StringUtil.getNotNullValueString(cell.getContents()), "yyyy/MM/dd")) {
                return "第" + row + "行，第" + column + "列日期格式不正确!正确的格式为:yyyy/mm/dd";
            }
        }


        return null;
    }

    private boolean validateDate(String date, String pattern) {
        if (StringUtils.isBlank(date))
            return true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            String dates[] = date.split("/");
            if (dates.length == 2) {
                date += "/01";
            }
            format.parse(date);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
        return true;
    }

    public Date getDate(Cell cell) {
        String content = StringUtil.getNotNullValueString(cell.getContents());
        if (StringUtils.isBlank(content))
            return null;
        System.out.println(content);
        GregorianCalendar calendar = null;
        if (cell.getType() == CellType.NUMBER) {
            calendar = new GregorianCalendar(1900, 0, 1);
            NumberCell nc=(NumberCell)cell;
            calendar.add(Calendar.DATE, (int)nc.getValue() - 2);
            return calendar.getTime();
        } else if (cell.getType() == CellType.DATE) {
               return ((DateCell) cell).getDate();
        } else {
            String[] date = content.split("/");
            if (date.length == 3) {
                calendar = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
                return calendar.getTime();
            }
            if (date.length == 2) {
                calendar = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, 1);
                return calendar.getTime();
            }
        }
        return null;
    }
}
