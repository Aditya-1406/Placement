// assuming that basic i/o of 2D array u know-->
// proceeding to the linear search

import java.util.*;
 class  Pair {
    int i;
    int j;
    Pair(int i, int j ){
        this.i = i;
        this.j = j;
    }
    
}

public class Arrays2 {


    public static void main(String[] args) {

        int[][] arr = {
            {7,84,100,4},
            {10,4,52,46},
            {4,79,22,72},
            {9,62,4,24}
        };


        int target = 4;

        // first occurence --->   
        
        // int firsti = -1;
        // int seci = -1;

        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         if (arr[i][j] == target) {
        //             firsti = i;
        //             seci = j;
        //             break;
        //         }
        //     }
        //     break;
        // }

        // System.out.println(firsti+" "+seci);

        //-------------------------------------------------------------------------

        // last occurence -->
        
        // int firsti = -1;
        // int seci = -1;

        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         if (arr[i][j] == target) {
        //             firsti = i;
        //             seci = j;
        //         }
        //     }
        // }

        // System.out.println(firsti +" "+ seci);

        //-------------------------------------------------------------------------
        
        // multiple occurencee -->
          
        
        // ArrayList<Pair> res = new ArrayList<>(); // pair is the class above declared

        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         if(arr[i][j]==target){
        //             res.add(new Pair(i, j));
        //         }
        //     }
        // }

        // for (Pair pair : res) {
        //     Pair p = pair;
        //     System.out.println(p.i+" "+ p.j);

        // }

        //-----------------------------------------------------------------------------

        // find min and max in the 2D array -->

        // int min = Integer.MAX_VALUE;
        // int max = Integer.MIN_VALUE;

        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         if (arr[i][j] < min) {
        //           min =arr[i][j];
        //         }
        //         if (arr[i][j] > max) {
        //             max =arr[i][j];
        //           }
        //     }
        // }

        // System.out.println(min +" "+ max);

        //--------------------------------------------------------------------------------------
    }
}
