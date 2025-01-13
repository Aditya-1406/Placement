package gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

        int arr[] = {10,22,3,4,1,6,7,8,5,33,4,99,100};
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
        // int k = 3;
        // ArrayList<Integer> res = maxSub(arr,k);
        // for (Integer integer : res) {
        //     System.out.print(integer+" ");
        // }

        // today  not work just solved 1 question on gfg
        

        //----------------------------------------------------------------------------

        //         Find SoP (sum of product). You were given with a integer n and you have to find sum of all products of i*j where 

        // i ranges from 1 to n, including both ranges
        // j is n/i  for every i
        // Find i*j for every iteration and sum them up to the final output

        // Example:

        // Input:
        // n=4

        // Output:
        // 15


        // int n = 4;
        // int  sum =0;

        // for(int i = 1;i<=n;i++){
        //     int j = n/i;
        //     sum += i*j;

        // }
        // System.out.println(sum);

        //------------------------------------------------------------------------------------------------------
        // Find the count of numbers in the array which has K digits in it.

        // Note: size of array and the value of K is always greater than zero.
        
        // Example:
        
        // Input:
        // arr=[10,22,3,4,1,6,7,8,5,33,4,99,100]
        // k=2
        
        // Output:
        // 4

        // int k = 3;
        // int[] great = {0,10,100,1000,10000,100000,1000000};
        // int start = great[k-1];
        // int end = great[k];
        // int count = 0;
        // for (int i = 0; i < arr.length; i++) {
        //     if(arr[i]>=start && arr[i]<end){
        //         count++;
        //     }
        // }
        // System.out.println(count);

        //--------------------------------------------------------------------------------------------------

        // Return the count of numbers whose unit digit is end with the integer ‘k’ which is one of the input given to the function.

        // You were given with three inputs starting range , ending  range and the integer k.
        
        // Example:
        
        // Input: 
        // start=10
        // end=54
        // k=2
        
        // Output:
        // 5

        // int s = 10;
        // int e = 54;
        // int k = 2;
        // int count =0;
        // for (int i = s; i <= e; i++) {
        //     if(i%10==k) count++;
        // }
        // System.out.println(count);

        //------------------------------------------------------------------------------------------------------------------
        // In English alphabet a,e,i,o,u are called as vowels. Write a function to return the most frequent vowel used in the given input string.

        // Note: All characters are lower case English alphabets
        
        // Example:
        
        // Input:
        // abeabutiedcia
        
        // Output:
        // a

        //     String str = "abeabutiedcia";
        //    HashMap<Character,Integer> mp = new LinkedHashMap<>();

        //     for (int i = 0; i < str.length(); i++) {
        //         char ch = str.charAt(i);
        //         mp.put(ch,mp.getOrDefault(ch, 0)+1 );

        //     }

        //     int max = 0;
        //     char res = ' ';
        //     for(HashMap.Entry<Character, Integer> entry : mp.entrySet()){
        //         if(entry.getValue()>max){
        //             max = entry.getValue();
        //             res = entry.getKey();
        //         }
        //     }
        //     System.out.println(res);

        //----------------------------------------------------------------------------------------------------------------------------

        // find the sum of the digits in a number until its sum is equal to single digit. Consider the below example for better understand

        // #testcase1:
        
        // Input:
        // 123
        
        // Output:
        // 6

        // Long n = 8448440710l;
        // while (n>10) {
        //     Long sum = 0l;
        //     while (n>0) {
        //         long temp = n%10;
        //         sum += temp;
        //         n/=10;
        //     }
        //     n = sum;
        // }
        // System.out.println(n);

        //-----------------------------------------------------------------------------------------------------------------------------

        // Write a function which accepts a string str, implement the function to find and return the minimum characters required to append at the end of str to make it a palindrome

        // Assumptions – 
        // The string will only contain lowercase English Alphabets
        
        // Note – 
        
        // If string is already a palindrome then return NULL
        // You have to find the minimum characters required to append at the end of the string to make it a palindrome
        // Example –
        
        // Input –
        // abcdc
        
        // Output –
        // ba

        // String str = "abcdc";

        // System.out.println(palin(str));

        //---------------------------------------------------------------------------------

        // Given a string consisting of only 0, 1, A, B, C where
        // A = AND
        // B = OR
        // C = XOR
        // Calculate the value of the string assuming no order of precedence and evaluation is done from left to right.
        
        // Constraints – The length of string will be odd. It will always be a valid string.
        // Example, 1AA0 will not be given as an input.
        
        // Examples:
        
        // Input: 1A0B1
        // Output : 1
        // 1 AND 0 OR 1 = 1
        
        // Input : 1C1B1B0A0
        // Output : 0

        // String str = "1C1B1B0A0";
        // int res = str.charAt(0)-'0';

        // for (int i = 1; i < str.length(); i+=2) {
        //     char ch = str.charAt(i);
        //     int operand = str.charAt(i+1)-'0';
        //     if(ch == 'A') res = res & operand;
        //     else if(ch=='B') res = res | operand;
        //     else res = res ^ operand;
        // }
        // System.out.println(res);

        //-----------------------------------------------------------------------------------------------------------------

        // Longest Prefix Suffix
        // Given a string of character, find the length of longest proper prefix which is also a proper suffix.
        // Example:
        // S = abab
        // lps is 2 because, ab.. is prefix and ..ab is also a suffix.
        
        // Input:
        // First line is T number of test cases. 1<=T<=100.
        // Each test case has one line denoting the string of length less than 100000.
        
        // Expected time compexity is O(N).
        
        // Output:
        // Print length of longest proper prefix which is also a proper suffix.
        
        // Example:
        
        // Input:
        // 2
        // abab
        // aaaa
        
        // Output:
        // 2
        // 2

        String str = "abab";
        System.out.println(lps(str));
        

    }

    static int lps(String str){
        int len = 0;
        int n = str.length();
        int[] lps = new int[n];
        int i = 0;
        while (i<n) {
            if(str.charAt(i)==str.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len!=0){
                    len = lps[len-1];
                }
                else{
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps[n-1];
    }



    static boolean isPal(String str){
        int s = 0;
        int e = str.length()-1;
        while (s<=e) {
            if(str.charAt(s)!=str.charAt(e)){
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
    static int palin(String str){
        if(isPal(str)) return 0;

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            res.insert(0,str.charAt(i));
            if(isPal(str+res)){
                return res.length();
            }
        }
        return res.length();

    }
}
