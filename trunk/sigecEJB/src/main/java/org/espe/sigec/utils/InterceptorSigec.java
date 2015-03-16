package org.espe.sigec.utils;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorSigec {
	@AroundInvoke
	public Object interceptorLog(InvocationContext invocationContext) throws Exception{
		Object objetoInterceptado = invocationContext.getTarget();
		Method methodInterceptado = invocationContext.getMethod();
		
		System.out.println("Entrando");
		System.out.println(objetoInterceptado.getClass().getName());
		System.out.println(methodInterceptado.getName());
		
		Object o = invocationContext.proceed();
		System.out.println("Saliendo");
		System.out.println(objetoInterceptado.getClass().getName());
		System.out.println(methodInterceptado.getName());
		return o;
	}
}
