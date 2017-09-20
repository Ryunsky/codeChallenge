package GameTheory;

import java.io.*;
import java.util.*;

public class TowerBreakerAgain {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. 
         * Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int games = scan.nextInt();
        while(games-->0){
            int tower = scan.nextInt();
            int result = 0;
            for (int i = 0; i < tower; i++){
                int level = scan.nextInt();
                boolean isTwo = true;
                result ^= totalOddPrimes(level, isTwo);
            }
            if(result ==0){
                System.out.println(2);
            } else {
                System.out.println(1);
            }
        }
        scan.close();
    }
    
    private static int totalOddPrimes (int i, boolean isTwo){
        if(i == 0 || i == 1){
            return 0;
        }
        if(i%2 == 0){
            if(isTwo == true){
                return (1+ totalOddPrimes(i/2, false));
            } else{
                return totalOddPrimes(i/2, false);
            }
        } else {
            for(int j = 3; j*j <= i; j+=2){
                if(i%j==0){
                    return (1 + totalOddPrimes(i/j,false));
                }
            }
        }
        return 1;
    }
}