/**
 * Created by ahmetcanbulut on 3/18/17.
 */
import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Solution {
    // Minimum number of cuts which makes all substrings palindrome

    public static boolean isPalindrome(String s){
        int length = s.length();

        for(int i=0; i<(length/2)+1; i++){
            if(s.charAt(i) != s.charAt(length-1-i)){
                return false;
            }
        }
        return true;
    }

    public static int minNumOfCutsForAllPalindromesRecursive(String s){
        if(s.equals("") || isPalindrome(s)){
            return 0;
        }
        int length = s.length();
        int result = Integer.MAX_VALUE;
        for(int i=1;i<length;i++){
            String left = s.substring(0,i);
            String right = s.substring(i,length);
            result = Math.min((minNumOfCutsForAllPalindromesRecursive(left) + minNumOfCutsForAllPalindromesRecursive(right) + 1),result);
        }
        return result;
    }


    public static int minNumOfCutsForAllPalindromesDP(String s){
        Map<String,Integer> map = new HashMap<>();
        return minNumOfCutsForAllPalindromesDPInternal(s,map);
    }

    private static int minNumOfCutsForAllPalindromesDPInternal(String s, Map<String,Integer> map){
        if(s.equals("") || isPalindrome(s)){
            return 0;
        }
        if(map.containsKey(s)){
            return map.get(s);
        }

        int length = s.length();
        int result = Integer.MAX_VALUE;
        for(int i=1;i<length;i++){
            String left = s.substring(0,i);
            String right = s.substring(i,length);
            int leftResult = 0;
            int rightResult = 0;
            if(map.containsKey(left)){
                leftResult = map.get(left);
            }else{
                leftResult = minNumOfCutsForAllPalindromesDPInternal(left,map);
                map.put(left,leftResult);
            }

            if(map.containsKey(right)){
                rightResult = map.get(right);
            }else{
                rightResult = minNumOfCutsForAllPalindromesDPInternal(right,map);
                map.put(right,rightResult);
            }

            result = Math.min((leftResult + rightResult + 1),result);
        }
        return result;
    }


    public static void main(String[] args){
        // Minimum number of cuts which makes all substrings palindrome
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        int resDP = minNumOfCutsForAllPalindromesDP(word);
        end = System.currentTimeMillis();
        System.out.println("Min number of cuts for string " + word + " with DP is " + resDP + " in "+ (end-start) + " ms");

        start = System.currentTimeMillis();
        int resRecursive = minNumOfCutsForAllPalindromesRecursive(word);
        end = System.currentTimeMillis();
        System.out.println("Min number of cuts for string " + word + " with Recursion is " + resRecursive + " in "+ (end-start) + " ms");
    }
}
