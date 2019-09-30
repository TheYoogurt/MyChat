package org.yoogurt.util;

/**
 * User: yaocj
 * Date: 2019/9/26
 * Description: 计算工具类
 */
public class CalculateUtil {

    /**
     * 求任意数量字符串对应数字的和
     * @param v 任意数字对应的字符串
     * @return
     */
    public static StringBuffer getCharValByStr(String... v){
        if(v.length == 0){
            return new StringBuffer("0");
        }
        if(v.length == 1){
            return new StringBuffer(v[0]);
        }
        char[][] chars = new char[v.length][];
        int[] index = new int[v.length];
        for(int i = 0; i < v.length; i++){
            chars[i] = v[i].toCharArray();
            index[i] = v[i].length() - 1;
        }
        // 第一遍从个位开始算，所以下标传每个数字的长度-1
        return cal3Chars(chars, 0, new StringBuffer(""), index);
    }

    /**
     * 每一位进行加法计算（个位与个位相加，十位与十位相加...），结果大于10向前进位，递归计算得到最终结果
     * @param chars 二维数组（每一行表示一个数字，从右往左分别是个位、十位...）
     * @param index 每个数字的下标
     * @param a 进位
     * @param val 当前已经得到的结果
     * @return
     */
    private static StringBuffer cal3Chars(char[][] chars, int a, StringBuffer val, int... index){
//        System.out.println("=======================我是一条分割线========================");
        // 传给下一轮的数字下标
        int[] indexNext = new int[index.length];
        // 判断当前运算的结果长度是否大于三个数字中最大的字符串长度，因为是加法运算，所以前面的条件不成立则运算一定未结束
        boolean lengthFlag = true;
        int count = 0;
        // 创建一个数组，每个元素表示需要计算的那一位数字
        char[] ch  = new char[index.length];
        // 如果f==0，则说明需要计算的数字已经计算完了，除了进位以外
        int f = 0;
        for(int i=0; i < index.length; i++){
            ch[i] = '0';
            if(index[i] >= 0){
                ch[i] = chars[i][index[i]];
            }
            if(ch[i] != '0'){
                f ++;
            }
//            System.out.println("当前位数字：" + ch[i]);
            count += Integer.parseInt(String.valueOf(ch[i]));
            // 赋值
            indexNext[i] = index[i] - 1;
            if(chars[i].length > val.length()){
                lengthFlag = false;
            }
        }
        if(f == 0 && a == 0 && lengthFlag){
            return val;
        }
        count += a;
        StringBuffer rs = new StringBuffer(String.valueOf(count%10)).append(val);
//        System.out.println("当前得到的结果：" + rs);
        return cal3Chars(chars, count/10, rs, indexNext);
    }

}
