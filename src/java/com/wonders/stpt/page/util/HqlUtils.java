/**
 * 
 */
package com.wonders.stpt.page.util;

import java.util.List;
import java.util.Collection;
import org.hibernate.Query;

import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;

/**
 * @ClassName: HqlUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhoushun
 * @date 2013-1-24 下午02:37:29
 * 
 */
public class HqlUtils {
	@SuppressWarnings("unchecked")
	public static void setQueryParameters(Query query, List args) {
		if (args != null) {
			for (int i = 0; i < args.size(); i++) {
				HqlParameter arg = (HqlParameter) args.get(i);
				String argName = arg.getName();
				Object argValue = arg.getValue();
				org.hibernate.type.Type argType = arg.getType();
				if (argName == null) {
					if (argType == null)
						query.setParameter(i, argValue);
					else
						query.setParameter(i, argValue, argType);
					continue;
				}
				if (argType == null) {
					if (Collection.class.isInstance(argValue))
						query.setParameterList(argName, (Collection) argValue);
					else
						query.setParameter(argName, argValue);
					continue;
				}
				if (Collection.class.isInstance(argValue))
					query.setParameterList(argName, (Collection) argValue,
							argType);
				else
					query.setParameter(argName, argValue, argType);
			}

		}
	}

}
