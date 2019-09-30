package org.yoogurt.other;

import java.util.List;

/**
 * User: yaocj
 * Date: 2019/9/29
 * Description: 统计 total 以内的所有水仙花数，如153 = 1^3 + 5^3 + 3^3
 */
public class NarcissusMethod {

    private static final Integer total = 1000;

    public static void main(String[] args) {
        for(int i=0; i<total; i++){
            if(checkNarcissusNum(String.valueOf(i))){
                System.out.println(i);
            }
        }
    }

    /**
     * 判断当前数字是否为水仙花数
     * @param num 被判断数字转换成的字符串
     * @return true=是； false=否
     */
    private static Boolean checkNarcissusNum(String num){
        int thisNum = 0;
        try {
            thisNum = Integer.parseInt(num);
        } catch (Exception e){
            return false;
        }
        int sum = 0;
        for(int i=0; i<num.length(); i++){
            int arr = Integer.parseInt(num.substring(i, i+1));
            sum += arr * arr * arr;
        }
        if(thisNum == sum){
            return true;
        }
        return false;
    }
}
