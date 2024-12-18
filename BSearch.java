import java.util.*;


public class BSearch {


    // Simple binary Search
    public static void BinarySearch(int[] arr, int target){
        int s = 0;
        int e = arr.length-1;
        int ans = -1;
       
        while (s<=e) {
            int mid = s+(e-s)/2;

            if(arr[mid]== target){
                ans = mid;
                break;
            }
            else if(arr[mid]<target) s = mid+1;
            else e = mid-1;
        }
        System.out.println(ans);

    }

    // Simple binary Search for counting the first and last occurence
    public static void CountFirstandLast(int[] arr, int target,boolean istrue){
        int s = 0;
        int e = arr.length-1;
        int ans = -1;
       
        while (s<=e) {
            int mid = s+(e-s)/2;

            if(arr[mid]== target){
                ans = mid;
                if (istrue) {
                    e = mid-1;
                }
                else s=mid+1;
            }
            else if(arr[mid]<target) s = mid+1;
            else e = mid-1;
        }
        System.out.println(ans);

    }


    public static void main(String[] args) {
        
        int[] arr ={1,2,3,4,4,4,5,6};
        int target = 4;
        

        // BinarySearch(arr,target);

        // count the first and last occurence 
        CountFirstandLast(arr,target,false);



    }
}
