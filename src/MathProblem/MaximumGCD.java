package MathProblem;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaximumGCD {
    
    static int maxGcd = 0;
    static int sum = 0;

    static int maximumGcdAndSum(int[] A, int[] B) {
        // Complete this function
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i = A.length - 1; i >= 0; i--){
            if(i == A.length -1){
                
            } else if(A[i] == A[i+1] || maxGcd > A[i]){
                continue;
            }
            for(int j = B.length -1; j >= 0; j--){
                if(j == B.length -1){
                    
                } else if(B[j] == B[j+1] || maxGcd > B[j]){
                    continue;
                }
                int temp = gcd(A[i],B[j]);
                if(temp > maxGcd){
                    sum = A[i] + B[j];
                    System.err.println(A[i] + " " + B[j] + " is " + temp);
                    maxGcd = temp;
                }
            }
        }
        return sum;
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
    int res = maximumGcdAndSum(A, B);
    System.out.println(res);
    }
    
    public static List<Integer> sortedArray(int[] a){
        Set<Integer> set = new HashSet<Integer>();
        for (int i= 0; i < a.length; i++){
            set.add(a[i]);
        }
        
        List<Integer> list = new ArrayList<Integer>(set);
        Collections.sort(list, new Comparator<Integer>(){
            public int compare (Integer a, Integer b){
                return a.compareTo(b);
            }
        });
        return list;
        
    }
    public static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd (b, a%b);
    }
}