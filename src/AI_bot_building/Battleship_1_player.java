package AI_bot_building;

import java.io.*;
import java.util.*;

public class Battleship_1_player {
    static BufferedReader reader;
    static BufferedWriter writer;
    static ArrayList<LOC> unknown;
    static ArrayList<LOC> known;
    static LOC lastTarget;
    static LOC lastHit;
    static Random rand;
    static char[][] boardC;
    static boolean isFirstShoot;
    static int nextShootX;
    static int nextShootY;
    static int lastHitX = 10;
    static int lastHitY = 10;
    
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        String[] boardS = new String[size];
        boardC = new char[size][size];
        for(int i = 0; i<size; i++){
            boardS[i] = scan.next();
        }
        scan.close();
        unknown = new ArrayList<LOC>();
        for(int i =0;i<boardS.length;i++){
            for(int j =0;j<size;j++){
                boardC[i][j] = boardS[i].charAt(j);
                if (boardC[i][j] == '-') {
                    unknown.add(new LOC(i,j));
                } else {
                    known.add(new LOC(i,j));
                }
            }
        }
        File f = new File("state.txt");
        if(f.exists() && !f.isDirectory()){
            isFirstShoot = false;
            reader = new BufferedReader(new FileReader("state.txt"));
            String line =reader.readLine();
            String[] info = line.split(",");
            lastTarget = new LOC(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
            lastHit = new LOC(Integer.parseInt(info[2]), Integer.parseInt(info[3]));
            reader.close();
        } else {
            isFirstShoot = true;
        }
        next_move();
        
        writer = new BufferedWriter(new FileWriter("state.txt",false));
        writer.write(nextShootX + " " + nextShootY + " " + lastHitX + " " + lastHitY);
        writer.newLine();
        writer.close();
    }
    
    public static class LOC {
        public int x;
        public int y;
        // the board is sized by 10*10
        final int size = 10;
        
        public LOC(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int getX(){
            return this.x;
        }
        
        public int getY(){
            return this.y;
        }
        
        public char status(char[][] board){
            return board[this.x][this.y];
        }
        
        public boolean isValid () {
            if (this.x >= size || this.y >=size) {
                return false;
            }
            return true;
        }
        
        public int distance (LOC next){
            return Math.abs(this.x-next.x) + Math.abs(this.y-next.y);
        }
        
        public int horizontalDist (LOC next) {
            return Math.abs(this.y - next.y);
        }
        
        public int verticalDist (LOC next) {
            return Math.abs(this.x - next.x);
        }
        
    }
    
    public static void next_move(){
        rand = new Random();
        int index = rand.nextInt(unknown.size());
        // check if this is the first shoot.
        if (isFirstShoot){
            nextShootX = unknown.get(index).x;
            nextShootY = unknown.get(index).y;
            System.out.println(nextShootX + " " + nextShootY);
        }
        // haven't shot the target
        else if (boardC[lastTarget.x][lastTarget.y] == 'm' && !lastHit.isValid()){
            nextShootX = unknown.get(index).x;
            nextShootY = unknown.get(index).y;
            System.out.println(nextShootX + " " + nextShootY);
        } 
        // Destroy the target
        else if (boardC[lastTarget.x][lastTarget.y] == 'd') {
            boolean isHit = false;
            //check if there is any hitted location. 
            for (int i = 0; i < known.size();i++){
                if (boardC[known.get(i).getX()][known.get(i).getY()] == 'h'){
                    lastHitX = known.get(i).getX();
                    lastHitY = known.get(i).getY();
                    isHit = true;
                    break;
                }
            }
           if (isHit) {
               firstHit();
           } else {
               nextShootX = unknown.get(index).x;
               nextShootY = unknown.get(index).y;
               System.out.println(nextShootX + " " + nextShootY);
           }
        }
        
        else if (boardC[lastTarget.x][lastTarget.y] == 'h'){
            if (lastHit.isValid()) {
                alreadyHit();
            } else {
                firstHit();
            }
        }
    }
    
    //there is already has a hitted location
    public static void firstHit(){
        int minDist = 100;
        int tempDist;
        int nextShootIndex = 0;
        for (int i = 0; i < unknown.size(); i++){
            tempDist = unknown.get(i).distance(new LOC(lastTarget.x, lastTarget.y));
            if (tempDist < minDist){
                minDist = tempDist;
                nextShootIndex = i;
            }
        }
        lastHitX = lastTarget.x;
        lastHitY = lastTarget.y;
        nextShootX = unknown.get(nextShootIndex).x;
        nextShootY = unknown.get(nextShootIndex).y;
        System.out.println(unknown.get(nextShootIndex).x + " " + unknown.get(nextShootIndex).y);
        
    }
    
    public static void alreadyHit(){
        int minDist = 10;
        int tempDist;
        int nextShootIndex = 0;
        if (lastTarget.x == lastHit.x){
            for (int i = 0; i < unknown.size(); i++){
                tempDist = unknown.get(i).horizontalDist(new LOC(lastTarget.x, lastTarget.y));
                if (tempDist < minDist){
                    minDist = tempDist;
                    nextShootIndex = i;
                }
            }
        }
        else {
            for (int i = 0; i < unknown.size(); i++){
                tempDist = unknown.get(i).verticalDist(new LOC(lastTarget.x, lastTarget.y));
                if (tempDist < minDist){
                    minDist = tempDist;
                    nextShootIndex = i;
                }
            }
        }
        lastHitX = lastTarget.x;
        lastHitY = lastTarget.y;
        nextShootX = unknown.get(nextShootIndex).x;
        nextShootY = unknown.get(nextShootIndex).y;
        System.out.println(unknown.get(nextShootIndex).x + " " + unknown.get(nextShootIndex).y);
    }
}