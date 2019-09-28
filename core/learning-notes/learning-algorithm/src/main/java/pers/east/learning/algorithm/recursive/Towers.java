package pers.east.learning.algorithm.recursive;

/**
 * 递归练习：汉诺塔问题
 * @author eastFu
 */
public class Towers {

    /**
     * 递归解决汉诺塔问题
     * @param topN  汉诺塔总块数
     * @param src   源
     * @param temp  中间变量
     * @param desc  目的
     */
    public static void transfer(int topN,String src,String temp,String desc){
        if(topN==1){
            System.out.println("将"+topN+"从"+src+"移动到"+desc);
        } else {
            transfer(topN-1,src,desc,temp);
            System.out.println("将"+topN+"从"+src+"移动到"+desc);
            transfer(topN-1,temp,src,desc);
        }
    }

    public static void main(String[] args) {
        /**
         *      1
         *      2
         *      3
         *
         *      A  →→→→→ B →→→→→ C
         *
         *      将 1.2.3.4.5 从A移动到C
      */
        transfer(3,"A","B","C");
    }
}
