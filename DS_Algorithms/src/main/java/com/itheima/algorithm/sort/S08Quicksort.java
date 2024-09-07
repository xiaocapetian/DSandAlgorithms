package com.itheima.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

//我自己的思考版
public class S08Quicksort {

    public int[] sort(int[] nums){
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    public static void quickSort(int[] arr,int first,int j){
        System.out.println("现在first是"+first+" ,j是"+j);
        if(first>j){//结束必须是> 为什么不能是 >=
            System.out.println("first>j结束");
            return;
        }
        int start = partition(arr, first, j);
        quickSort(arr,first,start-1);//基准数左边的范围
        quickSort(arr,start+1,j);//基准数右边的范围

    }

    private static int partition(int[] arr, int first, int last) {
        //以第一位first为基准数
        //int baseNumber = arr[first];
        Random random = new Random();
        int baseNumberItem = random.nextInt(last - first + 1) + first;
        swap(arr, first,baseNumberItem);
        int baseNumber = arr[first];
        System.out.println("选到了随机基准点baseNumberItem="+baseNumberItem+"值是"+baseNumber);


        int start = first;
        //int start  = first+1;
        /*[重难] 👆*///问题出在这里!!!
        //为什么不能这样写？假如要排的数是3,4,5,start和end停在4直接就把3和4换了!!!
        //假如要排的数是3,4 ,start和end根本不进循环,出来停在4直接就把3和4换了!!!

        int end = last;


//end从后往前找,找到比基准数小的就停下来
        //start从后往前找,找到比基准数大的就停下来
        //除非end,start有谁在没找到目标就相遇了
        while (start<end){
            /*[重难!!!]*/
            //为什么必须先移动end?直接想想最后start和end相遇的情况,如果先移动start,start碰到比基准数大的停下了,然后end移动,还没找到比基准小的就直接撞start了
            // 那么,相遇的时候 会相遇点会比基准数大
            //那么把这个相遇点移到前面去了//那么就不能符合一轮过后,所有比基准数小的在左边,比基准数大的在右边了
            while (end > start && arr[end] >= baseNumber) {
                end--;
            }
            System.out.println("现在end停在了arr["+end+"] = "+ arr[end]);
            while (end > start && arr[start] <= baseNumber) {
                start++;
            }
            System.out.println("现在start停在了arr["+start+"] = "+ arr[start]);
            //start和end停下来后,就交换
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;

            System.out.println("startend交换后"+ Arrays.toString(arr));
            //交换完之后怎么办?继续left和right继续向前,循环,直到相遇
        }
        //baseNumber
        //left和right相遇了.那么这就是基准数first应该在的位置,
        //first和left交换
        System.out.println("现在arr[first]为arr["+ first +"] = "+ arr[first]);
        int temp = arr[end];
        arr[end] = arr[first];
        arr[first] = temp;
        System.out.println("firstend交换后"+Arrays.toString(arr)+",现在start = "+start);
        return start;
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
