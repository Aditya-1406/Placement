// (*) means important or an algo

public class BasicMath {

    // Power function -->

    static int power(int n , int pow){
        int res = 1;
        if(pow ==0) return 1;
        if(pow<0) return -1;
        for (int i = 0; i < pow; i++) {
            res  =  res * n;
        }
        return res;
    }

    // Decimal to anybse-->

    static void Dtob(int n,int base){
        int res = 0;
        int p = 0;

        while (n>0) {  
            int temp = n%base;
            n /=base;
            res = res + temp * power(10, p);
            p++;
        }
        System.out.println(res);


    }

    // anybse to decimal-->

    static void Btod(int n , int base){
        int res = 0;
        int p = 0;
        while (n>0) {
            int temp = n%10;
            n /= 10;
            res = res + temp * power(base, p);
            p++;
        }
        System.out.println(res);
    }

    // Fast expo -->

    static void FastExpo(int n,int p ){ // 2 , 7
        int res = 1;   
        int num = n;
        int pow = p;
        while (pow>0) {   
            if(pow%2!=0){    //7|3|1
                res *= num;  // 1*2 = 2| 2*4=8|128
            }
            pow /= 2; //3|1|0
 
            num = num * num; // 2*2= 4|4*4=16| 
        }
        System.out.println(res);
    }

    public static void main(String[] args) {


        // Digit Extractions-->

        /* 
        int  n = 10234;
        while (n>0) {
            int temp = n%10;
            System.out.println(temp);
            n = n/10;
        }
        */

        //-------------------------------------------------------------------------------------------

        // Forming a number--->

        // int[] arr = {1,0,2,3,4};
        // int  res = 0;
        // for(int i =0;i<arr.length;i++){
        //     res = res *10 + arr[i]; 
        // }
        // System.out.println(res);

        //-------------------------------------------------------------------------------------------

        // reverse of a number -->

        // int n = 10990064;
        // int res = 0;
        // while (n>0) {
        //     int temp =  n%10;
        //     res = res *10 + temp;
        //     n /=10;
        // }
        // System.out.println(res);

        //-----------------------------------------------------------------------------------------------

        // Find even or odd --->

        // int n = 1523;
        // if(n%2==0){
        //     System.out.println("even");
        // }
        // else{
        //     System.out.println("Odd");
        // }

        //----------------------------------------------------------------------------------------------------

        // Find exponents

        // int n = 10;
        // int pow  = 6;
        // System.out.println(power(n,pow));

        //--------------------------------------------------------------------------------------------

        // Decimal to any base --->

        // int n = 3 ;
        // int base = 2;
        // Dtob(n,base);

        //-------------------------------------------------------------------------------------------

        // any base to decimal -->

        // int n = 101;
        // int base = 2;
        // Btod( n, base);

        //-------------------------------------------------------------------------------------------

        // *** Fast exponentiation Algorithm --> 

        int n = 2;
        int p = 7;

        FastExpo(n,p);

        
    }
}
