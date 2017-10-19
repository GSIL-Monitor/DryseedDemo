package com.dryseed.dryseedapp.LeetCode.other;

/**
 * Created by caiminming on 2017/10/19.
 * <p>
 * n级阶梯，每次走一步或两步，问最多有多少种走法
 */

public class Step {
    public static int jumpFloor(int number) {
        if (number == 1)
            return 1;
        else if (number == 2)
            return 2;
        else
            return jumpFloor(number - 1) + jumpFloor(number - 2);
    }

    /**
     * 题目描述
     * 　　一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * 问题分析
     * 　　由于每次跳的阶数不确定，没有一个固定的规律，但是可以了解的是后一次跳是在前一次跳的结果上累加的，因此我们可以考虑使用递归的方法来解决问题。
     * 　　那么从递归的三个步骤开始寻找解决方案：
     * 　　1. 递归截止条件。
     * 　　由于每次可以跳1-n的任意阶数，因此无论有多少阶，都可以一次跳完，为了表示方便，我们将一次性跳完的情况设为F(0)，当n=1时，只能有一种情况，F(1) = 1。当n=2时，可以每次跳1阶，也可以一次跳两阶，则F(2) = 2。
     * 　　2. 递归的前后衔接。
     * 　　假设现在又n阶，可以跳完n阶的情况分别是：一次跳完F(0)；先跳一步F(1)，后面还有F(n-1)种跳法；或者先跳两步F(2)，后面还有F(n-2)种跳法。依次类推，第一次跳出n阶后，后面还有 F(n-n)中跳法。可以得出：
     * 　　F(n) = F(n-1)+F(n-2)+F(n-3)+..........+F(0)
     * 　　3. 递归节点数据的处理。
     * 　　根据题目，本题目中用到的递归只是统计前后计数，并没有数据处理。对于其他递归，可以具体情况具体对待。
     *
     * @param target
     * @return
     */
    public static int jumpFloorII(int target) {
        if (target == 0 || target == 1)
            return 1;
        if (target == 2)
            return 2;
        /*int sum = 0;
        for (int i = 0; i < target; i++) {
            sum += jumpFloorII(i);
        }*/

        /**
         * 假设跳上第n个台阶有f(n)种方法，则f(1)=1,f(2)=2,f(3)=4,f(4)=8,我们隐约感觉到f(n)=2^(n-1)。
         * 但是需要证明下，同样根据我们根据上篇文章中跳台阶的思路，可以得到f(n)=f(n-1)+f(n-2)+....+f(1)+1,而f(n-1)=f(n-2)+....+f(1)+1,两个式子相减，得到f(n) = 2f(n-1),
         * 很明显可以得到f(n)=2^(n-1)。
         */
        int sum = 1;
        for (int i = 1; i < target; i++) {
            sum *= 2;
        }
        return sum;
    }

    public static void main(String args[]) {
        System.out.println(jumpFloor(6));
        System.out.println(jumpFloorII(6));
        System.out.println(eatApple(6));
    }

    //x个苹果，一天只能吃一个、两个、或者三个，问有多少种吃法
    public static int eatApple(int number) {
        if (number == 1) return 1;
        else if (number == 2) return 2;
        else if (number == 3) return 4;
        else return eatApple(number - 3) + eatApple(number - 2) + eatApple(number - 1);
    }
}
