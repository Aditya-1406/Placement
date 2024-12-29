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
    }
}
