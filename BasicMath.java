public class BasicMath {


    public static void main(String[] args) {


        // Digit Extractions-->

        int  n = 10234;
        while (n>0) {
            int temp = n%10;
            System.out.println(temp);
            n = n/10;
        }

        // Forming a number--->

        int[] arr = {1,0,2,3,4};
        int  res = 0;
        for(int i =0;i<arr.length;i++){
            res = res *10 + arr[i]; 
        }
        System.out.println(res);

        // reverse of a number -->

        
        
    }
}
