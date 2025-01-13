package ADS;

public class Day1 {
    static void rev(int[] arr,int i, int j){
        
        while (i<=j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {


        // Rotate the Array--->  brute force

        // int[] arr = {1,2,3,4,5,6,7};
        // int k = 3;

        // int size = k%arr.length;
        // int start = arr.length - size;

        // for(int i = start;i<arr.length;i++){
        //     System.out.print(arr[i]+" ");
        // }
        // for(int i = 0;i<start;i++){
        //     System.out.print(arr[i]+" ");
        // }

        //optmised way--->

        // k=k%arr.length;
        // rev(arr,0,arr.length-1);
        // rev(arr,0,k-1);
        // rev(arr,k,arr.length-1);
        
        // for (int i : arr) {
        //     System.out.print(i);
        // }

        
        
    }
    
}
