package com.wonders.xss;

import com.google.gson.GsonBuilder;
import com.wonders.stpt.todoItem.model.vo.TodoInfo;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/11/19
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class GenericTest extends GenericParent<String, Integer> implements GenericInterface<Double> {
    private static Class getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            System.out.println("1111111");
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            System.out.println("--------" + ((Class) getClass(((TypeVariable) type).getBounds()[0], 0)).getName());
            return (Class) getClass(((TypeVariable) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {// class本身也是type，强制转型
            return (Class) type;
        }
    }

    private static Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            System.out.println("111111");
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            System.out.println("33333333");
            return (Class) getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            System.out.println("444444");
            return (Class) genericClass;
        }
    }

    public String[] getPayments(String[] payments,Integer[] sss, List<TodoInfo> products){
        return payments;
    }

    public static void main(String[] args) {
        GenericTest obj = new GenericTest();
        System.out.println(obj.getClass());
        Type[] a = obj.getClass().getGenericInterfaces();
        Type b = obj.getClass().getGenericSuperclass();
        System.out.println("//zhoushun  ");
        //interface
        for (Type t : a) {
            Type[] temp = ((ParameterizedType) t).getActualTypeArguments();
            for (Type c : temp) {
                System.out.println();
                System.out.println(((Class) c).getName());
            }
        }

        TypeVariable[] v = obj.getClass().getSuperclass().getTypeParameters();
        for (TypeVariable vv : v) {
            System.out.println(vv.getName());
            Type[] tt = vv.getBounds();
            for (Type c : tt) {
                System.out.println(((Class) c).getName());
            }
        }


        //super
        Type[] temp = ((ParameterizedType) b).getActualTypeArguments();
        for (Type c : temp) {
            System.out.println(((Class) c).getName());
        }

        Map map = new HashMap();
        map.put("a",null);
        map.put(1,"");
        map.put("das","asd");
        map.put("dasdsa","<a href='dasdsa'>");
        System.out.println(new GsonBuilder().serializeNulls().disableHtmlEscaping().create().toJson(map));

//        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
//        Object o = lsa;
//        Object[] oa = (Object[]) o;
//        List<Integer> li = new ArrayList<Integer>();
//        li.add(new Integer(3));
//        oa[1] = li; // Correct.
//        String s = (String) lsa[1].get(0); // Run time error, but cast is explicit.
//        System.out.println(s);

        Method getPayments = null;
        try {
            getPayments = GenericTest.class.getMethod("getPayments", new Class[]{String[].class,Integer[].class, List.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Type[] types = getPayments.getGenericParameterTypes();
        System.out.println("The first parameter of this method is GenericArrayType."+ types[0].getClass());
        ParameterizedType gType = (ParameterizedType)types[2];
        System.out.println("The GenericArrayType's component is String." +((Class)gType.getActualTypeArguments()[0]).getName());


    }
}
