package com.itheima.algorithm.sort;

import java.util.Arrays;

public class quick {
    static int res;
    public static void quick(int[] nums,int k,int shou,int wei){
        if(shou>wei){return;}
        //
        int left = shou;
        int right = wei;
        while (right>left){
            while (right>left&&nums[right]>=nums[shou]){
                right--;
            }
            while (right>left&&nums[left]<=nums[shou]){
                left++;
            }
            int temp = nums[right];
            nums[right] =nums[left];
            nums[left] =temp;

        }
        int temp = nums[right];
        nums[right] =nums[shou];
        nums[shou] =temp;
        System.out.println("现在"+ Arrays.toString(nums));
        //nums[right]现在是找到位置的索引数
        //现在索引为nums.length-k的就是我要找的,第k大的元素
        if(k == right){res = nums[right];}
        else if (k < right)  {quick(nums,k ,shou, right);}
        else  {quick(nums,k, right + 1, wei-1);}
        //System.out.println("nums.length-k我要求的这个值在第:"+(nums.length-k)+"位");


    }
}
