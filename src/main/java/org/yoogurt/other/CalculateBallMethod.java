package org.yoogurt.other;

import org.yoogurt.util.CalculateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * User: yaocj
 * Date: 2019/9/23
 * Description:算法题：袋子里有 total 个球，每次从袋子里取1、2或3个球，用算法实现有多少种方法能取完
 */
public class CalculateBallMethod {
    private static final int total = 3000;

    private static final Map<Integer, String> countMap = new HashMap<Integer, String>();

    /**
     * 最后一次取的情况有1、2和3个球，剩下的球数量分别是n-1、n-2和n-3个球
     * 假设n个球的取法有f(n)种，那么剩下的球取法一共有f(n-1),f(n-2)和f(n-3)种，所以n个球的取法一共有f(1) + f(n-1) + f(2) + f(n-2) + f(3) + f(n-3)种
     * 其中经过口算f(1) = 1; f(2) = 2; f(3) = 4
     * @param n 球总数
     * @return
     */
    private static String calculate(int n){
        switch (n){
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "4";
            default:
                String l1 = countMap.get(n - 1);
                if (l1 == null || "".equals(l1)){
                    l1 = calculate(n - 1);
                    countMap.put(n - 1, l1);
                }
                String l2 = countMap.get(n - 2);
                if (l2 == null || "".equals(l2)){
                    l2 = calculate(n - 2);
                    countMap.put(n - 2, l2);
                }
                String l3 = countMap.get(n - 3);
                if (l3 == null || "".equals(l3)){
                    l3 = calculate(n - 3);
                    countMap.put(n - 3, l3);
                }
                return String.valueOf(CalculateUtil.getCharValByStr(l1, l2, l3));
        }
    }

    public static void main(String[] args) {
        Long l = System.currentTimeMillis();
        String count = calculate(total);
        System.out.println("计算结果：" + count);
        System.out.println("计算的时间毫秒数：" + (System.currentTimeMillis() - l));
//        System.out.println(countMap.toString());
    }

}
