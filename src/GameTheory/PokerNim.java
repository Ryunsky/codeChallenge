package GameTheory;
import java.io.*;
import java.util.*;

public class PokerNim {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int games = scan.nextInt();
        while(games-->= 0){
            int size = scan.nextInt();
            int k = scan.nextInt();
            int result = 0;
            for(int i = 0 ; i < size; i++){
                int tmp = scan.nextInt();
                result ^= tmp;
            }
            if (result == 0){
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
        scan.close();
    }
}