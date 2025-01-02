package gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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

    // k - sized max element(not optimized) --->

    static ArrayList<Integer> maxSub(int[] arr,int k){
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i<=arr.length-k; i++) {
            int max = arr[i];
            for (int j = i; j < i+k; j++) {
                
                if(max<arr[j]) max =arr[j];
            }
            res.add(max);
        }

        return res;
    }

    // k - sized max element( optimized) --->
    public ArrayList<Integer> max_of_subarrays(int arr[], int k) {
        Deque<Integer> deque = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(!deque.isEmpty() && deque.getFirst() <= i - k){
                deque.removeFirst();
            }

            while(!deque.isEmpty() && arr[deque.getLast()] <= arr[i]){
                deque.removeLast();
            }

            deque.addLast(i);

            if(i >= k - 1){
                list.add(arr[deque.getFirst()]);
            }
        }
        
        return list;
    }

    public static void main(String[] args) {
        
        // Array search 

        int arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        // int x = 3;
        // System.out.println(search(arr, x));

        //----------------------------------------------------

        // Find min and max (solved on gfg)-->

        // -------------------------------------------------------

        // missing element in the array->

        // System.out.println(missingNumber(arr));

        //----------------------------------------------------------

        // wave the array---
        // print(arr);
        // System.out.println();
        // waveSort(arr);
        // print(arr);

        //-------------------------------------------------------------

        // K sized max element---->
        int k = 3;
        ArrayList<Integer> res = maxSub(arr,k);
        for (Integer integer : res) {
            System.out.print(integer+" ");
        }

    }
}
