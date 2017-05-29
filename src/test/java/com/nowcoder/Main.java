package com.nowcoder;

/**
 * Created by Administrator on 2017/3/23.
 */

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[][] = new int[n][];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < 2; j++) {
                a[i][j] = sc.nextInt();
            }

        }


    }








    /**
     * 第二题  60
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int a[] = new int[3*n];
//
//        for(int i = 0; i < 3*n; i++){
//            a[i] = sc.nextInt();
//        }
//
//        for(int i = 1; i < 3*n; i++) {
//            for(int j = 0; j < 3*n - i; j++ ){
//                if(a[j] > a[j+1]){
//                    int tmp = a[j+1];
//                    a[j+1] = a[j];
//                    a[j] = tmp;
//                }
//            }
//        }
//        int len = 3*n;
//        int result = 0;
//        int d = 0;
//        for(int i = 0; i < n; i++) {
//            result = result + a[len-2 - d];
//            d = d + 2;
//        }
//
//        System.out.println(result);
//    }



    /**
     * 第一题  20
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int a[] = new int[n];
//
//        for(int i = 0; i < n; i++){
//            a[i] = sc.nextInt();
//        }
//        int sum = 0;
//        for(int i = 1; i < n-1; i++) {
//            if((a[i-1] < a[i] && a[i] > a[i+1]) || (a[i-1] > a[i] && a[i] < a[i+1]))
//                sum++;
//        }
//        int result = sum + 1;
//        System.out.println(result);
//
//    }





//    public static void main(String[] args) {
//        int a[] = {2,1,3,4,6,7,3,3,1,3,5,7,2,342,4,234,1,132,43,3,5,36,25,324,1422,1341,1};
//
//        Utils.selectSort(a);
//
//        for (int i = 0; i<a.length;i++) {
//            System.out.println(a[i]);
//        }
//
//
//    }




}
class Value{
    private int i;
    private String s;
    public Value() {};
    public Value(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Value value = (Value) o;

        if (i != value.i) return false;
        return s.equals(value.s);

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + s.hashCode();
        return result;
    }
}