public class Solution {
    // Fibonacci with Recursion
    public static int fibRecursive(int n){
        if(n==0){
            return 0;  // base case
        }
        if(n==1){
            return 1;  // base case
        }
        return fibRecursive(n-1) + fibRecursive(n-2); // recurse
    }

    // Fibonacci with Dynamic Programming - Bottom Up Approach
    public static int fibDPBottomUp(int n){
        int[] dp = new int[n+1];
        // store base cases
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<n+1;i++){
            // calculate next using prev and store
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // Fibonacci with Dynamic Programming - Top Down Approach
    public static int fibDPTopDown(int n){
        int[] dp = new int[n+1];
        return fibDPTDRecursive(n,dp);
    }

    private static int fibDPTDRecursive(int n, int[] dp){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(dp[n]!=0){
            // if it is already calculated, return
            return dp[n];
        }else{
            // if it is not calculated, calculate, store and return
            dp[n] = fibDPTDRecursive(n-1,dp) + fibDPTDRecursive(n-2,dp);
            return dp[n];
        }
    }

    public static void main(String[] args){
        int n = 45;
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        long fibRec = fibRecursive(n);
        end = System.currentTimeMillis();
        System.out.println("Fib " + n + " Recursive: " + fibRec + " in " + (end - start) + " ms");

        start = System.currentTimeMillis();
        long fibDPBU = fibDPBottomUp(n);
        end = System.currentTimeMillis();
        System.out.println("Fib " + n + " DP Bottom Up: " + fibDPBU + " in " + (end - start) + " ms");

        start = System.currentTimeMillis();
        long fibDPTD = fibDPTopDown(n);
        end = System.currentTimeMillis();
        System.out.println("Fib " + n + " DP Top Down: " + fibDPTD + " in " + (end - start) + " ms");

    }
}
