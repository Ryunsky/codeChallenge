package leetCode.zigZagConversion;

public class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1) return s;
        if (len <= numRows) return s;
        String out = "";
        for (int i = 0; i < numRows; i ++){
            int index = i;
            int index_2;
            if (i == 0 || i == numRows-1) {
                while (index < len) {
                    out += s.charAt(index);
                    index += (2 * numRows -2);
                }
            } else {
                while(index < len) {
                    out += s.charAt(index);
                    index_2 = index + 2 * (numRows - i - 1);
                    if (index_2 < len) {
                        out += s.charAt(index_2);
                    } else {
                        break;
                    }
                    index += (2 * numRows -2);
                }
            }
        }
        return out;
    }
}