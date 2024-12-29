public class Bits {

    // print the bits -->

    static void printb(int n){
        for(int i = 7;i>=0;i--){
            System.out.print((n>>i)&1);
        }
        System.out.println();
    }



    public static void main(String[] args) {
        
        // print  the number is even or odd --->
        // in bits, lsb is 0 for even and 1 for odd so we perform and operation 

        // int n = 7;
        // boolean flag = ((n &1)==0);  
        // System.out.println(flag);

        //-----------------------------------------------------------------------------------------------------------------------------------

        // print the bits of a number---> we are taking 8 bits;

        // int n = 32;
        // printb(n);

        //-------------------------------------------------------------------------------------------------------------------------------

        //  bit at the ith position -->

        // int n = 7;
        // int i = 3;
        // printb(n);
        // System.out.println((n>>i)&1);

        //------------------------------------------------------------------------------------------------------------------

        // set ith bit ---->

        // int n = 32;
        // int i = 4;
        // printb(n);
        // int b = n | 1<<i;
        // printb(b);

        //-----------------------------------------------------------------------------------------------------------------------

        // toggle ith bit ---->

        // int n = 53;
        // int i = 1;
        // printb(n);
        // int b = n ^ (1<<i);
        // printb(b);

        //------------------------------------------------------------------------------------------------------------------

        // unset the ith bit----->

        // int n = 53;
        // int i = 1;
        // printb(n);
        // int b = n &~(1<<i);
        // printb(b);

        //----------------------------------------------------------------------------------------------------------------

        // is power of two ---->

        // int n = 10;
        // boolean flag = (n&(n-1))==0;
        // System.out.println(flag);

        //---------------------------------------------------------------------------------------------------------------

        // unset the right most bit -->

        // int n = 17;
        // printb(n);
        // int b = n&n-1;
        // printb(b);

        //---------------------------------------------------------------------------------------------------------------

        // count the number of the set bits ---->

        // int  n = 80;
        // printb(n);
        // int count = 0;
        // while (n!=0) {
        //     count++;
        //     n = n & n-1;
            
        // }
        // System.out.println(count);

        //---------------------------------------------------------------------------------------------------------------

    }
}
