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

        int[] arr = {1,2,3,4,5,6,0};
        int i = 3;
        int data = 10;
        print(arr);

        // insertion --> 

        insert(arr, i, data);
        System.out.println();
        print(arr);
        

        // Deletion -->

        delete(arr, i);
        System.out.println();
        print(arr);

        //-----------------------------------------------------------------------------------

        // Linear search
    }
}
