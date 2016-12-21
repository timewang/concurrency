/**
 *
 */
package org.snailgary.test.pourwater;

import java.util.concurrent.BlockingQueue;

/**
 * ***********************************************************
 *
 * @类名 ：EqualsPartWater.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/12/21
 * ***********************************************************
 */
public class EqualsPartWater {


    public void searchState(BlockingQueue<BucketStatus> blockingQueu) throws InterruptedException {
        BucketStatus current = blockingQueu.take();

        if(current.isFinalState()){
            System.out.println(blockingQueu);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.sraechStatusOnaction(blockingQueu,current,i,j);
            }
        }

    }

    public void sraechStatusOnaction(BlockingQueue<BucketStatus> blockingQueu,BucketStatus current,int from,int to){
        if(current.canTakeDumpAction(from,to)){

        }
    }


}
