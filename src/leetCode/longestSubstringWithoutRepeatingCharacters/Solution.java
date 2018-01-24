package leetCode.longestSubstringWithoutRepeatingCharacters;

/*
 * Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */


import java.util.HashMap;

class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (len <= numRows) return s;
        String out = "";
        for (int i = 0; i < numRows; i ++){
            int j = 0;
            if (i == 0 || i == numRows-1) {
                while ( (i + (2 * numRows-2) * j) < len) {
                    out += s.charAt(i + (2 * numRows-2) * j);
                    j++;
                }
            } else {
                while(true) {
                    if ((i + (2 * numRows-2) * j) < len) {
                        out += s.charAt(i + (2 * numRows-2) * j);
                    } else {
                        break;
                    }
                    if (((2 * numRows-2) * (j + 1) -i) < len) {
                        out += s.charAt((2 * numRows-2) * (j + 1) -i);
                    } else {
                        break;
                    }
                    j++;
                }
            }
        }
        return out;
    }
}