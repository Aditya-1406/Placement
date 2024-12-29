import java.net.Socket;

public class Bits2 {

    static void print(int n ){
        Bits.printb(n);
    }

    public static void main(String[] args) {
        

        // Clear LSB --->

        // int n = 53;
        // int i = 4;
        // Bits.printb(n);
        // int b = n & ~((1<<i+1)-1);
        // Bits.printb(b);

        //----------------------------------------------------------------------------------------------

        // Clear MSB --> 

        // int n = 53;
        // int i = 5;
        // print(n);
        // int b = n & ((1<<i)-1);
        // print(b);

        //----------------------------------------------------------------------------------------------

        // Clear MSB Exclusive --> 

        // int n = 53;
        // int i = 4;
        // print(n);
        // int b = n & ((1<<i+1)-1);
        // print(b);

        //---------------------------------------------------------------------------------------------

        //Character conversion to upper to  lower -->

        // int n = 'A';
        // System.out.println(n);
        // print(n);
        // int b = n | (1<<5);
        // System.out.println((char)b);
        // print(b);

        // trick -->

        // int b = n | ' ';
        // System.out.println((char)b);

        //-------------------------------------------------------------------------------------------------

        //Character conversion to lower to  upper -->

        // int n = 'a';
        // System.out.println(n);
        // print(n);
        // int b = n & ~(1<<5);
        // System.out.println((char)b);
        // print(b);

        // trick -->

        // int b = n & '_';
        // System.out.println((char)b);

        //------------------------------------------------------------------------------------------------------------

        // Swap  two numbers using xor 

        // int n = 64;
        // int m = 54;
        // System.out.println(n+" "+m);

        // n = n^m;
        // m = n^m;
        // n = n^m;

        // System.out.println(n+" "+m);

        //---------------------------------------------------------------------------------------------------------------

        // find the unique element in the array -->

        // int[] arr = {1,1,3,3,4,5,5,6,6};
        // int res =0;
        // for (int i = 0; i < arr.length; i++) {
        //     res = res ^ arr[i];
        // }
        // System.out.println(res);

        //--------------------------------------------------------------------------------------------------------------

        // find the unique element in the array  where all other elements are even time-->

        // int[] arr = {1,1,3,3,4,5,5,6,6,6,6};
        // int res =0;
        // for (int i = 0; i < arr.length; i++) {
        //     res = res ^ arr[i];
        // }
        // System.out.println(res);

        //-----------------------------------------------------------------------------------------------------

    }
}
