// Incomplete

 import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    private static ArrayList<ArrayList<Integer>> mergeSolutions(ArrayList<ArrayList<Integer>> source, ArrayList<ArrayList<Integer>> toAdd){
        for(int i=0;i<toAdd.size();i++){
            source.add(toAdd.get(i));
        }
        return source;
    }

    public static ArrayList<ArrayList<Integer>> findChangeCombinations(int amount, int[] coins) {
        int coinCount = coins.length;

        ArrayList<ArrayList<Integer>> curSolutions = new ArrayList<>();
        for(int i=0;i<coinCount;i++){
            int cur = coins[i];
            ArrayList<ArrayList<Integer>> subSolutions = new ArrayList<>();
            if(amount - cur == 0){
                ArrayList<Integer> curSolution = new ArrayList<>();
                curSolution.add(cur);
                subSolutions.add(curSolution);
            } else if(amount-cur > 0){
                subSolutions = findChangeCombinations(amount-cur,coins);
                for(int j=0;j<subSolutions.size();j++){
                    subSolutions.get(j).add(cur);
                }
            }
            curSolutions = mergeSolutions(curSolutions,subSolutions);
        }
        return curSolutions;
    }

    private static ArrayList<Map<Integer,Integer>> mergeUniqueSolutions(ArrayList<Map<Integer,Integer>> source, ArrayList<Map<Integer,Integer>> toAdd){
        for(int i=0;i<toAdd.size();i++){
            source.add(toAdd.get(i));
        }
        return source;
    }

    public static ArrayList<Map<Integer,Integer>> findChangeUniqueChangeCombinations(int amount, int[] coins){
        int coinCount = coins.length;
        ArrayList<Map<Integer,Integer>> curSolutions = new ArrayList<>();
        for(int i=0;i<coinCount;i++){
            int cur = coins[i];
            ArrayList<Map<Integer,Integer>> subSolutions = new ArrayList<>();
            if(amount-cur == 0){
                Map<Integer,Integer> curSolution = new HashMap<>();
                for(int k=0;k<coinCount;k++){
                    curSolution.put(coins[k],0);
                }
                curSolution.put(cur,1);
                subSolutions.add(curSolution);
            } else if(amount-cur>0){
                subSolutions = findChangeUniqueChangeCombinations(amount-cur,coins);
                for(int j=0;j<subSolutions.size();j++){
                    subSolutions.get(j).put(cur,(subSolutions.get(j).get(cur)+1));
                }
            }
            curSolutions = mergeUniqueSolutions(curSolutions,subSolutions);
        }
        return curSolutions;
    }

    private static Set<Map<Integer,Integer>> mergeUniqueSolutions2(Set<Map<Integer,Integer>> source, Set<Map<Integer,Integer>> toAdd){
        Iterator<Map<Integer,Integer>> it = toAdd.iterator();
        while(it.hasNext()){
            source.add(it.next());
        }
        return source;
    }


    // map -> array  , set -> array
    public static Set<Map<Integer,Integer>> findChangeUniqueChangeCombinations2(int amount, int[] coins){
        int coinCount = coins.length;
        Set<Map<Integer,Integer>> curSolutions = new HashSet<>();
        for(int i=0;i<coinCount;i++){
            int cur = coins[i];
            Set<Map<Integer,Integer>> subSolutions = new HashSet<>();
            if(amount-cur == 0){
                Map<Integer,Integer> curSolution = new HashMap<>();
                for(int k=0;k<coinCount;k++){
                    curSolution.put(coins[k],0);
                }
                curSolution.put(cur,1);
                subSolutions.add(curSolution);
            } else if(amount-cur>0){
                subSolutions = findChangeUniqueChangeCombinations2(amount-cur,coins);
                Iterator<Map<Integer,Integer>> it = subSolutions.iterator();
                while(it.hasNext()){
                    Map<Integer,Integer> curMap = it.next();
                    curMap.put(cur,curMap.get(cur)+1);
                }
            }
            curSolutions = mergeUniqueSolutions2(curSolutions,subSolutions);
        }
        return curSolutions;
    }

    public static int minCoins(int amount, int[] coins){
        int coinCount = coins.length;

        if(amount == 0){
            return 0;
        }

        int solution = Integer.MAX_VALUE;  // no solution

        for(int i=0;i<coinCount;i++){
            int curCoin = coins[i];

            if(amount-curCoin>=0){
                int subSolution = minCoins(amount-curCoin,coins);
                if((subSolution!= Integer.MAX_VALUE) && (subSolution+1<solution)){
                    solution = subSolution +1;
                }
            }
        }
        return solution;
    }

    public static int minCoinsDP(int amount, int[] coins){
        int coinCount = coins.length;
        int[] dp = new int[amount];

        // fill the dp table for every amount
        for(int rows=0;rows<amount;rows++){
            int curAmount = rows+1;
            int amountMin = Integer.MAX_VALUE;
            for(int cols=0;cols<coinCount;cols++){
                int curCoin = coins[cols];
                if(curAmount%curCoin==0){
                    amountMin = Math.min(amountMin, curAmount/curCoin); // subsolution for amont "curAmont", using only coin "curCoin"
                }
            }
            dp[rows] = amountMin;
        }

        // look for solution amount
        // look for solution of 1 and solution amount-1
        // look for solution of 2 and solution of amount-2
        // ....
        // compare all above, and

        int minSolution = dp[amount-1];
        for(int top=amount-1,bottom=1;top>=bottom;top--,bottom++){
            int topIdx = top-1;
            int bottomIdx = bottom-1;
            minSolution = Math.min(minSolution,(dp[bottomIdx]+dp[topIdx]));
        }
        if(minSolution!= Integer.MAX_VALUE){
            return minSolution;
        }
        return -1;
    }

    public static void main(String[] args){

        int amount = 5;
        int[] coins = {1,2,3};

        System.out.println("Min coin count: " + minCoinsDP(amount,coins));

        ArrayList<ArrayList<Integer>> solution = findChangeCombinations(amount,coins);
        for(int i=0;i<solution.size();i++){
            ArrayList<Integer> curList = solution.get(i);
            for(int j=0;j<curList.size();j++){
                System.out.print(curList.get(j)+" ");
            }
            System.out.println("");
        }

        System.out.println("*************");
        for(int i=0;i<coins.length;i++){
            System.out.print(coins[i] + " ");
        }
        System.out.println("");
        System.out.println("_____");

        Set<Map<Integer,Integer>> solutionUnique = findChangeUniqueChangeCombinations2(amount,coins);

        Iterator<Map<Integer,Integer>> it = solutionUnique.iterator();
        while(it.hasNext()){
            Map<Integer,Integer> curMap = it.next();
            for(int j=0;j<coins.length;j++){
                System.out.print(curMap.get(j+1)+" ");
            }
            System.out.println("");
        }

    }
}
