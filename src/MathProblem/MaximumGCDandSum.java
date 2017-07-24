package MathProblem;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaximumGCDandSum {
    
    static final int N = 1000001;
    
    static int countA[] = new int[N];
    static int countB[] = new int[N];
    static int lmulA[] = new int[N];
    static int lmulB[] = new int[N];

    static int maximumGcdAndSum(int[] A, int[] B, int n) {
        // Complete this function
        Arrays.fill(countA, 0);
        Arrays.fill(countB, 0);
        Arrays.fill(lmulA, 0);
        Arrays.fill(lmulB, 0);
        int max =0;
        
        //calculate max multiple in array a
        
        for(int i = 1; i <= n; i++) {
            countA[A[i-1]]++;
        }
        for(int i = 1; i < N; i++) {
            for (int j = i; j <= N; j += i){
                if(countA[j] != 0){
                    lmulA[i] = j;
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            countB[B[i-1]]++;
        }
        for(int i = 1; i < N; i++) {
            for (int j = i; j <= N; j += i){
                if(countB[j] != 0){
                    lmulB[i] = j;
                }
            }
        }
        
        for(int i =1; i< N; i++){
            if(lmulA[i] >0 && lmulB[i] >0){
                max = lmulA[i] + lmulB[i];
            }
        }
        
        return max;
    }
    

    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] A = new int[n];
    for(int A_i = 0; A_i < n; A_i++){
        A[A_i] = in.nextInt();
    }
    int[] B = new int[n];
    for(int B_i = 0; B_i < n; B_i++){
        B[B_i] = in.nextInt();
    }
    int res = maximumGcdAndSum(A, B, n);
    System.out.println(res);
    }
}
