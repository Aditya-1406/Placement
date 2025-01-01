package gfg;

import java.util.Arrays;

public class Beginner {

    // Array Search
    static int search(int arr[], int x) {

        int res = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==x){
                res = i;
                break;
            }
        }

        return res;
    }

    // missing in the array -->

    static int missingNumber(int arr[]) {
        Arrays.sort(arr);
        int res = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != i+1){
                res = i+1;
                break;
            }
        }
        if(res == -1) {
            res = arr.length+1;
        }
        return res;
    }


    public static void main(String[] args) {
        
        // Array search 

        int arr[] = {1};
        // int x = 3;
        // System.out.println(search(arr, x));

        //----------------------------------------------------

        // Find min and max (solved on gfg)-->

        // -------------------------------------------------------

        // missing element in the array->
        System.out.println(missingNumber(arr));
    }
}
