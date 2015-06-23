package com.wonders.stpt.metroCover.util;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/12
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class YouXi {
    public static int removeNM(int n, int m) {
        LinkedList ll = new LinkedList();
        for (int i = 0; i < n; i++)
            ll.add(new Integer(i + 1));
        int removed = -1;
        while (ll.size() > 1) {
            removed = (removed + m) % ll.size();
            ll.remove(removed--);
        }
        return ((Integer) ll.get(0)).intValue();
    }

    public static void main(String[] args) {
        System.out.println(removeNM(50, 3));
    }
}
