package com.wonders.stpt.metroCover.model.vo.common;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public enum MetroType {
    LINE("0","线路"),COMPANY("1","公司");

    private MetroType(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){  //覆盖了父类Enum的toString()
        return super.toString()+"("+id+","+name+")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    public static void main(String[] args){
        MetroType type = MetroType.LINE;
        System.out.println(type.getName());
        MetroType[] allLight = MetroType.values();

        for (MetroType aLight : allLight) {

            System.out.println("当前灯name：" + aLight.name());

            System.out.println("当前灯ordinal：" + aLight.ordinal());

            System.out.println("当前灯：" + aLight);

        }
    }
}
