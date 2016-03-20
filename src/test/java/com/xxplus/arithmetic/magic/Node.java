package com.xxplus.arithmetic.magic;

/**
 * Created by 栾海鹏 on 2016/1/28.
 */
public class Node {
    private int[][] status;
    private boolean hasVisited = false;
    private String fromPath;
    private int mmHashCode;


    public Node(int[][] status) {
        this.setStatus(status);
    }

    public Node(String fromPath, boolean hasVisited, int mmHashCode, int[][] status) {
        this.fromPath = fromPath;
        this.hasVisited = hasVisited;
        this.mmHashCode = mmHashCode;
        this.status = status;
    }

    public int getMmHashCode() {
        return mmHashCode;
    }

    public void setMmHashCode(int mmHashCode) {
        this.mmHashCode = mmHashCode;
    }

    public boolean isHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public int[][] getStatus() {
        return status;
    }

    public static int calculateHashCode(int[][] status){
        int m = 1;
        int mmHashCode = 0;
        for (int[] s : status) {
            for (int x : s) {
                mmHashCode = mmHashCode + x * m;
                m = m * 10;
            }
        }

        return mmHashCode;
    }

    public void setStatus(int[][] status) {
        this.mmHashCode = calculateHashCode(status);
        this.status = status;
    }

    public String getFromPath() {
        return fromPath;
    }

    public void setFromPath(String fromPath) {
        this.fromPath = fromPath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (mmHashCode != node.mmHashCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mmHashCode;
    }
}
