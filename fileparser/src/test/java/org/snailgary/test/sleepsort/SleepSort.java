/**
 *
 */
package org.snailgary.test.sleepsort;

import java.util.concurrent.CountDownLatch;

/**
 * ***********************************************************
 *
 * @类名 ：SleepSort.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/12/2
 * ***********************************************************
 */
public class SleepSort {

    public static void sleepSortAndPrint(int[] nums) {
        final CountDownLatch doneSignal = new CountDownLatch(nums.length);
        for (final int num : nums) {
            new Thread(() -> {
                doneSignal.countDown();
                try {
                    doneSignal.await();

                    //using straight milliseconds produces unpredictable
                    //results with small numbers
                    //using 1000 here gives a nifty demonstration
                    Thread.sleep(num * 1000);
                    System.out.println(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[args.length];
        for (int i = 0; i < args.length; i++)
            nums[i] = Integer.parseInt(args[i]);
        sleepSortAndPrint(nums);
    }

}
