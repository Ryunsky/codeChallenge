package greedy;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Cakewalk {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] calories = new int[n];
        for(int calories_i=0; calories_i < n; calories_i++){
            calories[calories_i] = in.nextInt();
        }
        // your code goes here
        Arrays.sort(calories);
        long miles = 0;
        for(int i=n-1; i >= 0;i--) {
            miles += calories[i]*Math.pow(2.0, (double)(n-1-i));
        }
        System.out.println(miles);
    }
}
