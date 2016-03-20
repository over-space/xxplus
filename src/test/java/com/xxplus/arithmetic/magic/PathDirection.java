package com.xxplus.arithmetic.magic;

/**
 * Created by 栾海鹏 on 2016/1/28.
 */
public class PathDirection {
    private int x;
    private int y;
    private boolean isR;
    private String name;
    public PathDirection(int x, int y,boolean isR,String name) {
        this.x = x;
        this.y = y;
        this.isR = isR;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int [][] go(int mm[][]){
        if (isR){
            return this.getNextNodeStatus_R(mm);
        }
        return this.getNextNodeStatus_r(mm);
    }

    private void arrayCopy(int s[][],int t[][]){
        for (int i = 0 ;i<3;i++){
            for (int j=0;j<3;j++){
                t[i][j] = s[i][j];
            }
        }
    }


    /**
     * 逆时针
     * @param mm
     * @return
     */
    private int[][] getNextNodeStatus_R(int mm[][]) {
        int[][] d = new int[3][3];

        arrayCopy(mm,d);

        int temp = d[x][y];
        d[x][y] = d[x][y + 1];
        d[x][y + 1] = d[x + 1][y + 1];
        d[x + 1][y + 1] = d[x + 1][y + 0];
        d[x + 1][y + 0] = temp;
        return d;
    }


    /**
     * 顺时针
     * @param mm
     * @return
     */
    private int[][] getNextNodeStatus_r(int mm[][]) {
        int[][] d = new int[3][3];
        arrayCopy(mm,d);

        int temp = d[x][y];
        d[x][y] = d[x+1][y+0];
        d[x+1][y+0] = d[x+1][y+1];
        d[x+1][y+1] = d[x+0][y+1];
        d[x+0][y+1] = temp;

        return d;
    }


}
