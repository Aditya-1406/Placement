import java.util.*;


public class BSearch {

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


    public static void main(String[] args) {
        
        int[] arr ={1,2,3,4,5,6};
        int target = 2;

        BinarySearch(arr,target);

    }
}
