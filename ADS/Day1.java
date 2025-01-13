package ADS;

public class Day1 {
    public static void main(String[] args) {
        
        int[] arr = {1,2,3,4,5,6,7};
        int k = 1;
        int size = k%arr.length;
        int start = arr.length - size;

        for(int i = start;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        for(int i = 0;i<start;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
