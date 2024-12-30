public class Arrays {

    // insertion int the array -->

    static void insert(int[] arr,int i, int data){
        int n = arr.length;
        for (int j = n-2; j >=i; j--) {
            arr[j+1] = arr[j];
        }
        arr[i] = data;
    }

    // deletion in the array-->

    static void delete(int[] arr,int i){
        int n = arr.length;
        for(int j = i;j<n-1;j++){
            arr[j] = arr[j+1];

        }
        arr[n-1] = 0 ;

    }

    // printing the array -->
    static void print(int arr[]){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {

        // insertion in the array

        // int[] arr = {1,2,3,4,5,6,0};
        // int i = 3;
        // int data = 10;
        // print(arr);

        // // insertion --> 

        // insert(arr, i, data);
        // System.out.println();
        // print(arr);
        

        // // Deletion -->

        // delete(arr, i);
        // System.out.println();
        // print(arr);

        //-----------------------------------------------------------------------------------

        // Linear search-->

        // int[] arr = {11,2,32,4,55,6,89};
        // int target = 55;
        // int res = -1;
        // for (int i = 0; i < arr.length; i++) {
        //     if(arr[i]==target){
        //         res = i;
        //         break;
        //     }
        // }
        // System.out.println(target+" found at index : "+res);

        //-------------------------------------------------------------------------------------

        // first occurence of element in the array -->

        // int[] arr = {1,2,3,4,5,6,4,85,4,7};
        // int target = 4;
        // int res = -1;
        // for (int i = 0; i < arr.length; i++) {
        //     if(arr[i]==target){
        //         res = i;
        //         break;
        //     }
        // }
        // System.out.println("First occurence at: " +res);

        //-------------------------------------------------------------------------------------

        // last occurence of element in the array -->

        // int[] arr = {1,2,3,4,5,6,4,85,4,7};
        // int target = 4;
        // int res = -1;
        // for (int i = 0; i < arr.length; i++) {
        //     if(arr[i]==target){
        //         res = i;
        //     }
        // }
        // System.out.println("last occurence at: " +res);

        //-------------------------------------------------------------------------------------

        // multiple occurence of element in the array -->

        // int[] arr = {1,2,3,4,5,6,4,85,4,7};
        // int[] res = new int[arr.length];
        // int k = 0;

        // int target = 4;
        // for (int i = 0; i < arr.length; i++) {
        //     if(arr[i]==target){
        //         res[k] = i;
        //         k++;
        //     }
        // }
        // for (int i = 0 ;i<k;i++) {
        //     System.out.println("Target found at : "+ res[i]);
        // }
        
    


    }
}
