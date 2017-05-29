package com.nowcoder;

/**
 * Created by Administrator on 2017/4/27.
 */
public class Utils {

    /**
     * 二分查找  返回target索引
     *
     * @param a
     * @param target
     * @return
     */
    public static int binarySearch(int a[], int target) {
        if (a == null || a.length == 0)
            return -1;
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == target)
                return mid;
            else if (a[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }


    public static void selectSort(int a[]) {
        int flag = 0;
        int len = a.length;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++) {
            flag = i;
            min = a[i];
            for(int j = i + 1; j < len; j++) {
                if(a[j] < min){
                    min = a[j];
                    flag = j;
                }
            }
            if(flag != i) {
                int tmp = a[i];
                a[i] = a[flag];
                a[flag] = tmp;
            }
        }
    }




    /**
     *  冒泡排序
     * @param a
     */


    public static void bubbleSort(int a[]) {
        if(a == null || a.length == 0)
            return;
        boolean exchange;
        int len = a.length;
        int temp;
        //最多n-1趟冒泡排序(若发现某趟排序没有交换操作,则停止冒泡)
        for(int i = 1; i < len; i++) {
            exchange = false;
            for(int j = 0; j < len - i; j++) {
                if(a[j] > a[j+1]) {
                    swap(a,j,j+1);
                    exchange = true;
                }
            }
            if (exchange == false)
                break;
        }
    }


    public static void bubbleSort2(int a[]) {
        if(a==null || a.length == 0)
            return;
        int temp;
        boolean exchange;
        for(int i = 1; i < a.length; i++){
            exchange = false;
            for(int j = 0; j < a.length - i; j++){
                if(a[j] > a[j+1]){
                    temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    exchange=true;
                }
            }
            if(!exchange)
                break;
        }
    }

    public static void swap(int a[], int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }


    /**
     * 快速排序
     * @param a
     */
    public static void quickSort(int[] a) {
        if (a == null || a.length == 0)
            return;
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int i = left, j = right;
            int key = a[left];
            while (i < j) {
                while (i < j && a[j] >= key) {
                    j--;
                }
                if (i < j) {
                    swap(a, i, j);
                    i++;
                }
                while (i < j && a[i] <= key) {
                    i++;
                }
                if (i < j){
                    swap(a, i, j);
                    j--;
                }
            }
            a[i] = key;
            quickSort(a,left,i-1);
            quickSort(a,i+1,right);
        }
    }





}
