package leetCode.zigZagConversion;

class Solution2 {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || len <= numRows) return s;
        StringBuilder[] build =new StringBuilder[numRows];
        //initialize string builder
        for (int i = 0; i < numRows; i++){
            build[i] = new StringBuilder("");
        }
        int row = 0;
        int index = 0;
        int increment = 1;
        while (index < len) {
            build[row].append(s.charAt(index));
            if (row == 0) increment =1;
            if (row == numRows-1) increment = -1;
            row += increment;
            index++;
        }
        for (int idx = 1; idx < build.length; idx++){
            build[0].append(build[idx]);
        }
        return build[0].toString();
    }
}