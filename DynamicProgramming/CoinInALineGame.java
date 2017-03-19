/**
 * Created by ahmetcanbulut on 3/19/17.
 */
public class Solution {
    // Coin In a Line Game Problem
    // Two play­ers, who we will call Alice and Bob, take turns remov­ing one of the coins from either end of the remain­ing line of coins.
    // Alice takes turn first. Then Bob plays

    public static int solveForAlice(int[] array){
        int size = array.length;
        int[][] dp = new int[size][size];

        for(int interval = 0;interval<size; interval++){
            for(int i=0,j=interval; j<size; i++,j++){
                // ALICE CHOOSES i (FROM LEFT)
                // alice chooses i, bob chooses j then the rest is a subproblem with for the remaining subarray from i+1 to j-1
                // a = dp[i+1][j-1]
                int a = 0;
                if(i+1 <= j-1){  // If there is a subproblem between i+1 and j-1, then get it from the table dp
                    a = dp[i+1][j-1];
                }

                // alice chooses i, bob chooses i+1 then the rest is a subproblem for the remaining subarray from i+2 to j
                // b = dp[i+2][j]

                int b = 0;
                if(i+2 <= j){  // If there is a subproblem between i+2 and j, then get it from the table dp
                    b = dp[i+2][j];
                }

                // ALICE CHOOSES j (FROM RIGHT)
                // alice chooses j, bob chooses i the same case above, (b)

                // alice chooses j, bob chooses j-1 then the rest is a subproblem for the remaining subarray from i to j-2
                // c = dp[i][j-2]

                int c = 0;
                if(i <= j-2){  // If there is a subproblem between i and j-2, then get it from the table dp
                    c = dp[i][j-2];
                }

                int caseOfI = array[i] + Math.min(a,b);
                int caseOfJ = array[j] + Math.min(b,c);
                dp[i][j] = Math.max(caseOfI,caseOfJ);
            }
        }
        return dp[0][size-1];
    }

    public static int calcRemainingForBob(int[] array, int alicesScore){
        int length = array.length;
        int total = 0;
        for(int i=0;i<length;i++){
            total += array[i];
        }

        return total - alicesScore;
    }

    public static void main(String[] args){
        int[] a = {6,9,1,2,16,8};
        int[] b = a;

        int alicesScore = solveForAlice(b);
        int bobsScore = calcRemainingForBob(b,alicesScore);
        boolean isATie = alicesScore == bobsScore;
        boolean aliceWins = alicesScore > bobsScore;

        if(alicesScore == bobsScore){
            System.out.print("Tie :");
        }else if(alicesScore > bobsScore){
            System.out.print("Alice wins :");
        }else{
            System.out.print("Bob wins :");
        }
        System.out.println("Alice: " + alicesScore + " Bob: " + bobsScore);
    }
}
