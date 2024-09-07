package com.itheima.algorithm.sort;

import com.itheima.algorithm.sort.InsertSort.InsertionSort;
import com.itheima.datastructure.array.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h3>桶排序(改进)</h3>
 */
public class BucketSortGeneric {
    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7};
        System.out.println(Arrays.toString(ages));
        bucketSort(ages,3);
        System.out.println(Arrays.toString(ages));

    }

    /*
        0   0,1,2
        1   3,4,5
        2   6,7,8
        3   9
     */
    /**
     * 详细教学版
     * @param a
     * @param range 分几个桶呀？
     */
    public static void bucketSorts(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            max = Math.max(a[i],max);
            min = Math.min(a[i],min);
        }
        // 1. 准备桶
        List<Integer>[] buckets = new ArrayList[(max - min) / range + 1];
        //[重点]切记,创建一个每个元素都是集合list的数组时,数组的每个元素一开始都是null,需要遍历的new
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 2. 放入数据
        for (int age : a) {
            buckets[(age - min) / range].add(age);
        }
        int k = 0;

        for (List<Integer> bucket : buckets) {
            // 3. 排序桶内元素
            //3.1.在集合中排序
            Collections.sort(bucket);
            //bucket.sort((o1, o2) -> o1-o2);
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (int v : bucket) {
                a[k++] = v;
            }

            // 3.2排序桶内元素 在数组中排序 List转数组int[]
            // 法一,stream
            int[] array2 = bucket.stream().mapToInt(Integer::intValue).toArray();
            //法二,.toArray(new Integer[0]);
            Integer[] array = bucket.toArray(new Integer[0]);
            //[注意]:👇是不行的 ,使用.toArray()法, List<Integer>只能转Integer[]
            //int[] array2 = bucket.toArray(new Integer[0]);
            //int[] array3 = bucket.toArray(new int[0]);
            Arrays.sort(array);
            // 把每个桶排序好的内容，依次放入原始数组
            for (int v : array) {
                a[k++] = v;
            }
        }
    }

    /**
     * 精简版
     * @param a
     * @param range
     */
    public static void bucketSort(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            max = Math.max(a[i],max);
            min = Math.min(a[i],min);
        }
        // 1. 准备桶
        List<Integer>[] buckets = new ArrayList[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 2. 放入数据
        for (int age : a) {
            buckets[(age - min) / range].add(age);
        }
        int k = 0;
        for (List<Integer> bucket : buckets) {
            // 3. 排序桶内元素
            Collections.sort(bucket);
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (int v : bucket) {
                a[k++] = v;
            }
        }
    }

    public static void sort(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        // 1. 准备桶
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        // 2. 放入数据
        for (int age : a) {
            buckets[(age - min) / range].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket : buckets) {
            // 3. 排序桶内元素
            int[] array = bucket.array();
            InsertionSort.sort(array);
//            System.out.println(Arrays.toString(array));
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (int v : array) {
                a[k++] = v;
            }
        }
    }
}
