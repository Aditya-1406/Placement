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
    public static int CountFirstandLast(int[] arr, int target,boolean istrue){
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
        return ans;

    }

    public static void Findthefloor(int[] arr,int target){
        int s = 0;
        int ans=-1;
        int e = arr.length-1;
        while (s<=e) {
            int mid = s+(e-s)/2;
            if (arr[mid]==target) {
                ans = arr[mid];
                break;
            }
            else if(arr[mid]<target){
                s = mid+1;
                ans= arr[mid];
            }
            else e = mid-1;
        }
        System.out.println(ans);
    }

    public static void Findtheciel(int[] arr,int target){
        int s = 0;
        int ans=-1;
        int e = arr.length-1;
        while (s<=e) {
            int mid = s+(e-s)/2;
            if (arr[mid]==target) {
                ans = arr[mid];
                break;
            }
            else if(arr[mid]<target){
                s = mid+1;
                
            }
            else{ 
                e = mid-1;
                ans= arr[mid];
            }    
        }
        System.out.println(ans);
    }
    


    public static void main(String[] args) {
        
        int[] arr ={1,2,3,5,6};
        int target = 4;
        
        // A simple binary search -->
        // BinarySearch(arr,target);

        // count the first and last occurence  (beats 100% on leetcode/34) -->
        // int first =  CountFirstandLast(arr,target,true); // for first occurence        
        // int last =  CountFirstandLast(arr,target,false); // for last occurence
        // System.out.println(first + " " + last);

        // Count the number of element (Asked In google) --->
        //  int first =  CountFirstandLast(arr,target,true); // for first occurence        
        //  int last =  CountFirstandLast(arr,target,false); // for last occurence
        //  if(first == -1) System.out.println(0);
        //  else System.out.println(last-first+1); // formula to find occurence in sorted array(upper limit -lower limit + 1)


        // Find the floor of the targeted element --->
        Findthefloor(arr,target);

        // Find the ciel of the targeted element ---> leetcode(744)
        Findtheciel(arr,target);
        



    }
}
