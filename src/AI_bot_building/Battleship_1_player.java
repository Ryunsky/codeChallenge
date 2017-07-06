package AI_bot_building;

import java.io.*;
import java.util.*;

public class Battleship_1_player {
    static BufferedReader reader;
    static BufferedWriter writer;
    
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        String[] boardS = new String[size];
        char[][] boardC = new char[size][size];
        String[] state = new String[size];
        for(int i = 0; i<size; i++){
            boardS[i] = scan.next();
        }
        scan.close();
//        for(int i =0;i<size;i++){
//            String next = scan.next();
//            for(int j =0;j<size;j++){
//                boardC[i][j] = next.charAt(j);
//            }
//        }
        File f = new File("state.txt");
        if(f.exists() && !f.isDirectory()){
            reader = new BufferedReader(new FileReader("state.txt"));
            String line;
            int index = 0;
            while((line = reader.readLine()) != null) {
                state[index] = line;
                index++;
            }
            reader.close();
            
        }
    }
}