package GameTheory;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SillyGame {

    private final static int size = 100000;
    public static void main(String[] args) {
        boolean[] prime = new boolean[size+1]; 
        Arrays.fill(prime, true); 
        for (int i = 2; i * i <= size; i++){
            if (prime[i]){
                for (int j = i * i; j<=size; j+=i){
                    prime[j] = false;
                }
            }
        }
        int[] primeNum = new int[size+1];
        for(int i = 1; i <= size; i++){
            if(i==1){
                primeNum[i]=0;
                continue;
            }
            if (prime[i]==true){
                primeNum[i] = primeNum[i-1]+1;
            }else {
                primeNum[i] = primeNum[i-1];
            }
        }
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            if(primeNum[n] % 2 ==1){
                System.out.println("Alice");
            } else {
                System.out.println("Bob");
            }
        }
        in.close();
    }
}
