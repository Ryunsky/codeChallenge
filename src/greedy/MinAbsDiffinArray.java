package greedy;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MinAbsDiffinArray {

    static int minimumAbsoluteDifference(int n, int[] arr) {
        // Complete this function
        Set<Integer> set = new HashSet<Integer>();
        for(int i =0; i< arr.length;i++) {
            set.add(arr[i]);
        }
        if(arr.length != set.size()) {
            return 0;
        }
        Arrays.sort(arr);
        int min =(int)2e9;
        for(int i=0; i<arr.length-1;i++) {
            int tmp = Math.abs(arr[i]-arr[i+1]);
            if(tmp < min) {
                min = tmp;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        int result = minimumAbsoluteDifference(n, arr);
        System.out.println(result);
        in.close();
    }
}