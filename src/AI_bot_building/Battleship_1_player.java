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
    static int size;
    
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        size = scan.nextInt();
        String[] boardS = new String[size];
        boardC = new char[size][size];
        for(int i = 0; i<size; i++){
            boardS[i] = scan.next();
        }
        scan.close();
        unknown = new ArrayList<LOC>();
        known = new ArrayList<LOC>();
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
        
        System.out.println(nextShootX + " " + nextShootY);
        
        writer = new BufferedWriter(new FileWriter("state.txt",false));
        writer.write(nextShootX + "," + nextShootY + "," + lastHit.getX() + "," + lastHit.getY());
        System.err.println(nextShootX + "," + nextShootY + "," + lastHit.getX() + "," + lastHit.getY());
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
        
        public void setX(int x) {
            this.x = x;
        }
        
        public void setY(int y) {
            this.y = y;
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
        
        //only consider the same row
        public int horizontalDist (LOC next) {
            if (this.x == next.x){
                return Math.abs(this.y - next.y);
            } else {
                return 10;
            }
        }
        
        public int verticalDist (LOC next) {
            if (this.y == next.y){
                return Math.abs(this.x - next.x);
            } else {
                return 10;
            }
        }
        
    }
    
    public static void next_move(){
        rand = new Random();
        int index = rand.nextInt(unknown.size());
        // check if this is the first shoot.
        if (isFirstShoot){
            nextShootX = unknown.get(index).x;
            nextShootY = unknown.get(index).y;
            lastHit = new LOC(10,10);
            System.err.println("first shoot");
        }
        // haven't shot the target
        else if (boardC[lastTarget.x][lastTarget.y] == 'm'){
            System.err.println("miss");
            if (!lastHit.isValid()) {
                nextShootX = unknown.get(index).x;
                nextShootY = unknown.get(index).y;
            } else {
              //same row
              if (lastTarget.x == lastHit.x){
                  boolean isFound = false;
                  //miss is on the left of the last hit
                  if(lastTarget.y < lastHit.y) {
                      for (int i = lastHit.y+1; i < size-1; i++){
                          if(boardC[lastHit.x][i] =='h'){
                              continue;
                          }
                          if (boardC[lastHit.x][i]!='-') {
                              break;
                          }
                          if(boardC[lastHit.x][i] =='-') {
                              nextShootX = lastHit.x;
                              nextShootY = i;
                              isFound = true;
                              break;
                          }
                      }
                  }
                  //miss is on the right of the last hit
                  else if (lastTarget.y > lastHit.y) {
                      for (int i = lastHit.y-1; i > 0; i--){
                          if(boardC[lastHit.x][i] =='h'){
                              continue;
                          }
                          if (boardC[lastHit.x][i]!='-') {
                              break;
                          }
                          if(boardC[lastHit.x][i] =='-') {
                              nextShootX = lastHit.x;
                              nextShootY = i;
                              isFound = true;
                              break;
                          }
                      }
                  }
                  if (!isFound){
                      if (lastHit.x == 0){
                          nextShootX = lastHit.x +1;
                          nextShootY = lastHit.y;
                      } else if (lastHit.x ==9){
                          nextShootX = lastHit.x - 1;
                          nextShootY = lastHit.y;
                      } else {
                          if (boardC[lastHit.x-1][lastHit.y] == '-'){
                              nextShootX = lastHit.x - 1;
                              nextShootY = lastHit.y;
                          } else {
                              nextShootX = lastHit.x +1;
                              nextShootY = lastHit.y;
                          }
                      }
                  }
              }
              //same column
              else if (lastTarget.y == lastHit.y){
                  boolean isFound = false;
                  //miss is on the left of the last hit
                  if(lastTarget.x < lastHit.x) {
                      for (int i = lastHit.x+1; i < size-1; i++){
                          if(boardC[i][lastHit.y] =='h'){
                              continue;
                          }
                          if(boardC[i][lastHit.y] !='-') {
                              break;
                          }
                          if(boardC[i][lastHit.y] =='-') {
                              nextShootX = i;
                              nextShootY = lastHit.y;
                              isFound = true;
                              break;
                          }
                      }
                  }
                  //miss is on the right of the last hit
                  else if (lastTarget.x > lastHit.x) {
                      for (int i = lastHit.x-1; i > 0; i--){
                          if(boardC[i][lastHit.y] =='h'){
                              continue;
                          }
                          if(boardC[i][lastHit.y] !='-') {
                              break;
                          }
                          if(boardC[i][lastHit.y] =='-') {
                              nextShootX = i;
                              nextShootY = lastHit.y;
                              isFound = true;
                              break;
                          }
                      }
                  }
                  if (!isFound){
                      if (lastHit.y == 0){
                          nextShootX = lastHit.x;
                          nextShootY = lastHit.y +1;
                      } else if (lastHit.y == 9){
                          nextShootX = lastHit.x;
                          nextShootY = lastHit.y - 1;
                      } else {
                          if (boardC[lastHit.x][lastHit.y - 1] == '-'){
                              nextShootX = lastHit.x;
                              nextShootY = lastHit.y - 1;
                          } else {
                              nextShootX = lastHit.x;
                              nextShootY = lastHit.y +1;
                          }
                      }
                  }
              }

            }
        } 
        // Destroy the target
        else if (boardC[lastTarget.x][lastTarget.y] == 'd') {
            System.err.println("destory");
            boolean isHit = false;
            lastHit.setX(10);
            lastHit.setY(10);;
            //check if there is any hitted location. 
            for (int i = 0; i < known.size();i++){
                if (boardC[known.get(i).getX()][known.get(i).getY()] == 'h'){
                    lastTarget.setX(known.get(i).getX());
                    lastTarget.setY(known.get(i).getY());
                    isHit = true;
                    break;
                }
            }
           if (isHit) {
               firstHit();
           } else {
               nextShootX = unknown.get(index).x;
               nextShootY = unknown.get(index).y;
           }
        }
        
        else if (boardC[lastTarget.x][lastTarget.y] == 'h'){
            if (lastHit.isValid()) {
                System.err.println("already hit");
                alreadyHit();
            } else {
                firstHit();
                System.err.println("first hit");
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
        lastHit.setX(lastTarget.x);
        lastHit.setY(lastTarget.y);
        nextShootX = unknown.get(nextShootIndex).x;
        nextShootY = unknown.get(nextShootIndex).y;
        
    }
    
    public static void alreadyHit(){
        //same row
        if (lastTarget.x == lastHit.x){
            boolean isFound = false;
            //last target is on the left of the last hit
            if(lastTarget.y < lastHit.y) {
                if (lastTarget.y != 0 && boardC[lastTarget.x][lastTarget.y-1] == '-'){
                    nextShootX = lastHit.x;
                    nextShootY = lastTarget.y -1;
                    isFound = true;
                } else {
                    for (int i = lastHit.y+1; i < size-1; i++){
                        if(boardC[lastTarget.x][i] =='h'){
                            continue;
                        }
                        if(boardC[lastHit.x][i] !='-') {
                            break;
                        }
                        if(boardC[lastHit.x][i] =='-') {
                            nextShootX = lastHit.x;
                            nextShootY = i;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
            //miss is on the right of the last hit
            else if (lastTarget.y > lastHit.y) {
                if (lastTarget.y != 9 && boardC[lastTarget.x][lastTarget.y+1] == '-'){
                    nextShootX = lastHit.x;
                    nextShootY = lastTarget.y + 1;
                    isFound = true;
                } else {
                    for (int i = lastHit.y-1; i > 0; i--){
                        if(boardC[lastHit.x][i] =='h'){
                            continue;
                        }
                        if(boardC[lastHit.x][i] !='-'){
                            break;
                        }
                        if(boardC[lastHit.x][i] =='-') {
                            nextShootX = lastHit.x;
                            nextShootY = i;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
            if (!isFound){
                if (lastHit.x == 0){
                    nextShootX = lastHit.x +1;
                    nextShootY = lastHit.y;
                } else if (lastHit.x == 9){
                    nextShootX = lastHit.x - 1;
                    nextShootY = lastHit.y;
                } else {
                    if (boardC[lastHit.x-1][lastHit.y] == '-'){
                        nextShootX = lastHit.x - 1;
                        nextShootY = lastHit.y;
                    } else {
                        nextShootX = lastHit.x +1;
                        nextShootY = lastHit.y;
                    }
                }
            }
        }
        //same column
        else if (lastTarget.y == lastHit.y){
            boolean isFound = false;
            //miss is on the left of the last hit
            if(lastTarget.x < lastHit.x) {
                if (lastTarget.x != 0 && boardC[lastTarget.x -1][lastTarget.y] == '-'){
                    System.err.println("more option");
                    nextShootX = lastTarget.x -1;
                    nextShootY = lastTarget.y;
                    isFound = true;
                } else {
                    for (int i = lastHit.x+1; i < size-1; i++){
                        if(boardC[i][lastHit.y] =='h'){
                            continue;
                        }
                        if(boardC[i][lastHit.y] !='-') {
                            break;
                        }
                        if(boardC[i][lastHit.y] =='-') {
                            nextShootX = i;
                            nextShootY = lastHit.y;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
            //miss is on the right of the last hit
            else if (lastTarget.x > lastHit.x) {
                if (lastTarget.x != 9 && boardC[lastTarget.x + 1][lastTarget.y] == '-'){
                    nextShootX = lastTarget.x + 1;
                    nextShootY = lastTarget.y;
                    isFound = true;
                } else {
                    for (int i = lastHit.x-1; i > 0; i--){
                        if(boardC[i][lastHit.y] =='h'){
                            continue;
                        }
                        if(boardC[i][lastHit.y] =='-') {
                            nextShootX = i;
                            nextShootY = lastHit.y;
                            isFound = true;
                            break;
                        }
                    }
                }
            }
            if (!isFound){
                if (lastHit.y == 0){
                    nextShootX = lastHit.x;
                    nextShootY = lastHit.y +1;
                } else if (lastHit.y == 9){
                    nextShootX = lastHit.x;
                    nextShootY = lastHit.y - 1;
                } else {
                    if (boardC[lastHit.x][lastHit.y - 1] == '-'){
                        nextShootX = lastHit.x;
                        nextShootY = lastHit.y - 1;
                    } else {
                        nextShootX = lastHit.x;
                        nextShootY = lastHit.y +1;
                    }
                }
            }
        }
        lastHit.setX(lastTarget.x);
        lastHit.setY(lastTarget.y);
      }
    }


