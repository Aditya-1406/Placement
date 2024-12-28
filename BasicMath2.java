import java.util.Arrays;

public class BasicMath2 {

    public static void main(String[] args) {
        

        // count the number of the integer -->

        // int n = 12345;
        // int count = 0;
        // while (n>0) {
        //     count++;
        //     n /=10;
        // }
        // System.out.println(count);

        //---------------------------------------------------------------------------------------------------

        // count the number via log --> if there is like division by ten and we have to find the count then we use log10(n)+1;

        // int n = 10;
        // if(n==0) System.out.println(1);
        // int res = (int)(Math.log(n)/Math.log(10)); // this method use because we have log base e so to convert log base 10 we use this
        // int res = (int)Math.log10(n); // Else we can use this Math.log10
        // System.out.println(res + 1);

        //----------------------------------------------------------------------------------------------------------------------------------

        // Number in power of two

        // int n = 4096;
        // double res = Math.log(n)/Math.log(2);
        // int fin = (res == (int)res)?(int)res:-1;
        // System.out.println(fin);

        //---------------------------------------------------------------------------------------------------------------------------------
        

        // armstrong Number -->

        // int n = 123;
        // int copy = n;
        // int length = (int)( Math.log10(n))+1;
        // int res = 0;
        // while (n>0) {
        //     int temp = n%10;
        //     res += (int)Math.pow(temp, length);
        //     n/=10; 
        // }
        // boolean fin = res==copy;
        // System.out.println(fin);

        //-------------------------------------------------------------------------------------------------------------------------------

        // palindrome number --->

        // int n = 1531;
        // int copy = n;
        // int res = 0;
        // while (n>0) {
        //     int temp = n%10;
        //     res = res *10+temp;
        //     n/=10;
        // }
        // System.out.println(copy==res);

        //------------------------------------------------------------------------------------------------------------------------

        // print all divisor--->

        // int n = 36;
        
        // for(int i = 1;i<=Math.sqrt(n);i++){
        //     if(n%i==0){
        //         System.out.print(i+" ");
        //         System.out.println(n/i);
        //     }
        // }

        //---------------------------------------------------------------------------------------------------------------------------

        // number is prinme or not -->

        // int n = 13;
        // boolean flag = true;
        // for (int i = 2; i < Math.sqrt(n); i++) {
        //     if(n%i==0){
        //         flag = false;
        //         break;
        //     }
        // }
        // System.out.println(flag);


        // ---------------------------------------------------------------------------------------------------------------------

        // find the prime number in the range 1 to n; (optimised way) --> sieve of Erathontes

        // int n = 20;
        // boolean[] arr = new boolean[n+1];
        // Arrays.fill(arr, true);
        // for(int i = 2;i<n;i++){
        //     for(int j = i*2;j<n;j+=i){
        //         if(arr[j]==true){
        //             arr[j]=false;
        //         }
        //     }
        // }
        // for(int i = 2;i<n;i++){
        //     if(arr[i]==true){
        //         System.out.println(i);
        //     }
        // }

        //---------------------------------------------------------------------------------------------------------------------

        // find a sqrt of a number --> Newton Raphson method

        // int n = 4;
        // double tol = 0.001;
        // double X = n;
        // double root;
        // while (true) {
        //     root = 0.5 * (X+(n/X));
        //     double ans = X - root;
        //     if(ans<tol) break;
        //     X = root;
        // }
        // System.out.println(root);

        //------------------------------------------------------------------------------------------------------------------

        // find the gcd of two number optimised euclidian

        // int n = 24;
        // int m = 36;

        // while (n!=0 && m !=0) {
        //     if(n>m){
        //         n= n%m;
        //     }
        //     else{
        //         m%=n;
        //     }
        // }
        // int res = (n==0)?m:n;
        // System.out.println(res);

        //---------------------------------------------------------------------------------------------------------------------------

        // find the lcm of a num

        // taking the n and m and also the gcd value from the above question
        //     n = 24;
        //     m = 36;

        //     int lcm = (n*m)/res;
        //     System.out.println(lcm);
        

        //----------------------------------------------------------------------------------------------------------------------

        // find the factorial of the number 

        // int n = 5;
        // int res = 1;
        // for (int i = 2; i <=n; i++) {
        //     res *= i;
        // }
        // System.out.println(res);

        //------------------------------------------------------------------------------------------------------------------------

        // find the trailing zero in a number 

        // int n = 1090600;
        // int count =0;
        // while (n%10==0) {
        //     count++;
        //     n/=10;
        // }
        // System.out.println(count);

        // --------------------------------------------------------------------------------------------------------------

        // find the trailing zeros in a factorial number

        // int  n = 10;
        // int count = 0;
        // for (int i = 5; i <= n ; i = i*i) {
        //     count += n/i;
        // } 
        // System.out.println(count);

        //---------------------------------------------------------------------------------------------------------------------
    }    

}
