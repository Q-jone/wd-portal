package com.wonders.stpt.operation.util;

import com.wonders.stpt.todoItem.util.ConfigUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FlowWorkUtils {
	public static final String ATT_URL = ConfigUtil.getValueByKey("config.properties","attachpath");

    public static String evaluateEL(String expression, Map<String, Object> properties)
    {
        String regex = "\\$\\{(\\w+)\\.(\\w+[\\.|\\w]*)\\}";
        List<String> matches = new LinkedList<String>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find())
        {
            String key = matcher.group(1);
            if (properties.containsKey(key))
            {
                String fieldName = matcher.group(2);
                Object obj = properties.get(key);

                try
                {
                    String value = BeanUtils.getProperty(obj, fieldName);
                    matches.add(value);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            while (!matches.isEmpty())
            {
                String match = matches.remove(0);
                expression = expression.replaceFirst(regex, match);
            }

        }
        
        return expression;
    }
}
