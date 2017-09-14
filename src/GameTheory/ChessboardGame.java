
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int [][] map = new int[15][15];
        map[0][0] = 2;
        map[0][1] = 2;
        map[1][0] = 2;
        map[1][1] = 2;
        map[0][2] = 1;
        map[2][0] = 1;
        boolean isSecond;
        for (int i = 3; i <= 28; i++) {
            for (int j = 0; j <= i && j<=14; j++){
                if(i-j>14) continue;
                isSecond = false;
                if(j -2 >=0 & i-j+1<=14){
                    if (map[j-2][i-j+1]==2){
                        isSecond = true;
                    }
                }
                if(j -2 >=0 & i-j-1 >=0){
                    if (map[j-2][i-j-1]==2){
                        isSecond = true;
                    }
                }
                if(j +1 <=14 & i-j-2 >=0){
                    if (map[j+1][i-j-2]==2){
                        isSecond = true;
                    }
                }
                if(j -1 >=0 & i-j-2 >=0){
                    if (map[j-1][i-j-2]==2){
                        isSecond = true;
                    }
                }

                if(isSecond==true) {
                    map[j][i-j]=1;
                } else{
                    map[j][i-j]=2;
                }
            }
        }
        for (int i = 0; i< 14; i++){
            for(int j =0; j < 14; j++){
                System.err.print(map[i][j] + " ");
            }
            System.err.println(" ");
        }
        
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int x =0;
        int y =0;
        for (int i =0; i < size; i++){
            x = scan.nextInt();
            y = scan.nextInt();
            if (map[x-1][y-1]==1){
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
}
