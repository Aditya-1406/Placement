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

    // wave array -->
    

    static void waveSort(int[] arr){
        int n = arr.length;
        if(n==1) return;
        if(n%2!=0){
            for(int i = 0;i<arr.length-2;i+=2){
                int temp = arr[i];
                arr[i] = arr[i+1] ;
                arr[i+1] = temp;
            }
        }
        else{
            for(int i = 0;i<arr.length-1;i+=2){
                int temp = arr[i];
                arr[i] = arr[i+1] ;
                arr[i+1] = temp;
            }
        }


    }
    static void print(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }


    public static void main(String[] args) {
        
        // Array search 

        int arr[] = {2,4,7,8,9,10};
        // int x = 3;
        // System.out.println(search(arr, x));

        //----------------------------------------------------

        // Find min and max (solved on gfg)-->

        // -------------------------------------------------------

        // missing element in the array->

        // System.out.println(missingNumber(arr));

        //----------------------------------------------------------

        // wave the array---
        print(arr);
        System.out.println();
        waveSort(arr);
        print(arr);
    }
}
