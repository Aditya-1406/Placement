package gfg;

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


    public static void main(String[] args) {
        
        // Array search 

        int arr[] = {1, 2, 3, 4};
        int x = 3;
        System.out.println(search(arr, x));
    }
}
