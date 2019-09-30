package org.yoogurt.other;

import org.yoogurt.util.CalculateUtil;

/**
 * User: yaocj
 * Date: 2019/9/29
 * Description: 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class CalculateRabbitMethod {

    private static final StringBuffer initRabbitSum = new StringBuffer("2");

    public static void main(String[] args) {
        StringBuffer rs = calculateRabbitSum(10, initRabbitSum);
        System.out.println(rs.toString());
    }

    /**
     * 计算某个月有多少只兔子
     * @param monthNum 表示第几个月
     * @param sum 计算前的兔子总数
     * @return
     */
    private static StringBuffer calculateRabbitSum(Integer monthNum, StringBuffer sum){
        if(monthNum < 3){
            return sum;
        }
        // 每次生兔子都会生一对，所以总数是上个月的两倍
        Integer times = monthNum / 3;
        double pow = Math.pow(2, times);
        String[] param = new String[(int) pow];
        for(int i = 0;i < (int) pow; i ++){
            param[i] = String.valueOf(sum);
        }
        return CalculateUtil.getCharValByStr(param);
    }

}
