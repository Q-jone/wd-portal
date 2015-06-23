package com.wonders.xss;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/11/19
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class GenericParent<T,K> {
    public Class<T> entityClass;

    public GenericParent(){
//        TypeVariable[] tValue = GenericParent.class.getTypeParameters();
//        System.out.println(tValue[0].getName());
//        System.out.println(getClass().getName());
//        Type t = getClass().getGenericSuperclass();
//        System.out.println(t);
//        System.out.println(t.getClass().getName());
//        entityClass = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
//        System.out.println(entityClass.getName());
    }

    public static void main(String[] args){
        GenericParent<String,Integer> obj = new GenericParent<String,Integer>();
    }
}
