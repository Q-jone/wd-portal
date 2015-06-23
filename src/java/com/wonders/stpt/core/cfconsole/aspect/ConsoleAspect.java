package com.wonders.stpt.core.cfconsole.aspect;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

import com.wonders.stpt.core.cfconsole.action.ConsoleAction;
import com.wonders.stpt.core.cfconsole.entity.vo.UserVo;
import com.wonders.stpt.core.cfconsole.util.ConsoleCheckUtil;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;

@Aspect
@Component("consoleAspect")
public class ConsoleAspect {
	
	private ConsoleCheckUtil consoleCheckUtil = new ConsoleCheckUtil();
	
	//Pointcut表示式
	//@Pointcut("execution(* com.savage.aop.MessageSender.*(..))")
	//@Pointcut("logSender() || logReceiver()")
	//@Before("com.sagage.aop.Pointcuts.logMessage()")
	//Point签名
//	private void log(){}  
	//@Before("og()")
	@Before(value="execution(public * com.wonders.stpt.core.cfconsole.action.ConsoleAction.addUser*(..))")
	public void beforeActionStep(JoinPoint joinPoint){
		ConsoleAction target = (ConsoleAction) joinPoint.getTarget();
		String stepCode = joinPoint.getSignature().getName();
		executePrepareMethod(stepCode,target);
	}
	
	//@After(value="execution(public * com.wondersgroup.workflow.deptContact.service.DeptContactService.flowStepForward(..))")
	//public void afterServiceForward(JoinPoint joinPoint){
		//checkService.checkFlowIsInProcess();
	//}
	
	@SuppressWarnings("unused")
	private void executePrepareMethod(String stepCode,ConsoleAction target){
		try {
			Method prepareMethod = consoleCheckUtil.getClass()
				.getMethod("userCheck",new Class[]{UserVo.class});
			List<TuserRelation> list = (List<TuserRelation>)prepareMethod.invoke(consoleCheckUtil,new Object[]{target.getVo()});
			target.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
