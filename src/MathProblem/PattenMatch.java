package MathProblem;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PattenMatch {

    static int patternCount(String s) {
        // Complete this function
        boolean isOne = false;
        boolean isZero = false;
        int count = 0;
        
        for(int i =0; i < s.length(); i++){
            if(s.charAt(i) == '1' && isZero == true){
                isOne = true;
                count++;
            } 
            if (s.charAt(i) == '1') {
                isOne = true;
                isZero = false;
            } 
            if (s.charAt(i) == '0' && isOne == true){
                isZero = true;
            } 
            if (s.charAt(i) == '1' && s.charAt(i) == '0'){
                isOne = false;
                isZero = false;
            }
            
        }
        
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = patternCount(s);
            System.out.println(result);
        }
    }
}