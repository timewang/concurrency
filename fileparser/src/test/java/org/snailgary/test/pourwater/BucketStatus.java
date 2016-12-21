/**
 *
 */
package org.snailgary.test.pourwater;

import java.util.Arrays;

/**
 * ***********************************************************
 *
 * @类名 ：BucketStatus.java
 * @DESCRIPTION : 桶状态
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/12/21
 * ***********************************************************
 */
public class BucketStatus {

    private int buckets[] = {8,5,3};

    private int status[] = {8,0,0};

    private TagAvtion avtion = new TagAvtion(-1,1,8);



    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public TagAvtion getAvtion() {
        return avtion;
    }

    public void setAvtion(TagAvtion avtion) {
        this.avtion = avtion;
    }

    public boolean isFinalState(){
       return this.status[0] == 4 && this.status[1] == 4 && this.status[2] == 0;
    }

    public boolean canTakeDumpAction(int from,int to){
        assert from >= 0 && from <= 3;
        assert to >= 0 && to <= 3;
        if (from == to || this.isBucketEmpty(from) || this.isBucketFull(to)){
            return false;
        }
        return true;
    }

    private boolean isBucketEmpty(int bucketNo){
        return this.status[bucketNo] == 0;
    }

    private boolean isBucketFull(int bucketNo){
        return this.status[bucketNo] == this.buckets[bucketNo];
    }

    @Override
    public String toString() {
        return "BucketStatus{" + "status=" + Arrays.toString(status) + ", avtion=" + avtion + '}';
    }
}
