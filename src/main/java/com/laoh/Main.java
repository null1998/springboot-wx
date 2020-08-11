package com.laoh;

import java.util.ArrayList;

/**
 * @author hyd
 * @date 2020/8/1 0:19
 */
public class Main {
    public static void main(String[] args) throws Exception{
        int[] num = {2,3,4,2,6,2,5,1};
        Solution solution = new Solution();
        ArrayList<Integer> list =  solution.maxInWindows(num,3);
        System.out.println(list);

    }

}
