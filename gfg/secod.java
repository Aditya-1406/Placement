package gfg;

public class secod {

    static boolean diff(int N){
        int prevDigit = -1; // To store the previous digit
        while (N > 0) {
            int currentDigit = N % 10; // Extract the last digit
            if (prevDigit != -1) { // Compare with the previous digit
                int diff = Math.abs(currentDigit - prevDigit);
                if (diff != 1) {
                    return false; // Return false if the difference is not 1
                }
            }
            prevDigit = currentDigit; // Update previous digit
            N /= 10; // Remove the last digit
        }
        return true;
    }

    static void getno(int[] arr,int k ){
        int n = arr.length;
        for(int i = 0;i<n;i++){
            if(diff(arr[i]) && arr[i]<k && arr[i]>9){
                System.out.print(arr[i]+" ");
            }
        }
    }

    public static void main(String[] args) {
        
        int[] arr = {7, 98, 56, 43, 45, 23, 12, 8};
        int k = 54;
        getno(arr,k);
    }
}