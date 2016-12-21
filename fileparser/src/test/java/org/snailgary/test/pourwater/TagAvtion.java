/**
 *
 */
package org.snailgary.test.pourwater;

/**
 * ***********************************************************
 * @类名 ：TagAvtion.java
 *
 * @DESCRIPTION : 倒水动作
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/12/21
 * ***********************************************************
 */
public class TagAvtion {
    /**
     * 从哪个桶中倒水
     */
    private int from;
    /**
     * 水倒向那个桶
     */
    private int to;
    /**
     * 本次倒水动作所倒的水量
     */
    private int water;

    public TagAvtion(int from, int to, int water) {
        this.from = from;
        this.to = to;
        this.water = water;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    @Override
    public String toString() {
        return "TagAvtion{" + "from=" + from + ", to=" + to + ", water=" + water + '}';
    }
}
