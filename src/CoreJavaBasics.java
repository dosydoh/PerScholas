
public class CoreJavaBasics {
    public static void main(String[] args) {

        //Write a program that declares two integer variables, assigns an integer to each,
        // and adds them together. Assign the sum to a variable. Print out the result.
        int a = 6;
        int b = 10;
        int sum = a + b;
        System.out.println(sum);

        //Write a program that declares two double variables, assigns a number to each, and adds them together.
        // Assign the sum to a variable. Print out the result.
        double c = 42.6;
        double d = 64.3;
        double sum2 = c + d;
        System.out.println(sum2);

        //Write a program that declares an integer variable and a double variable, assigns numbers to each,
        // and adds them together. Assign the sum to a variable. Print out the result. What variable type must the sum be?
        int e = 15;
        double f = 23.6;
        double sum3 = e + f;
        System.out.println(sum3);

        //Write a program that declares two integer variables, assigns an integer to each, and divides the larger number by
        // the smaller number. Assign the result to a variable. Print out the result. Now change the larger number to a decimal.
        // What happens? What corrections are needed?
        //Syntax error happens because larger number is an int class which doesnt allow decimals so needs to be converted to a double
        //variable type
        int g = 637;
        int h = 204;
        int i = g / h;
        System.out.println(i);

        //Write a program that declares two double variables, assigns a number to each, and divides the larger by the smaller number.
        // Assign the result to a variable. Print out the result. Now, cast the result to an integer. Print out the result again.
        //Truncates the decimal
        double j = 28;
        double k = 38;
        double l = k / j;
        System.out.println((int)l);

        //****Write a program that declares two integer variables, x and y, and assigns the number 5 to x and the number 6 to y. Declare a
        // variable q and assign y/x to it and print q. Now, cast y to a double and assign it to q. Print q again.
        int x = 5;
        int y = 6;
        double q = (double)y / x;
        System.out.println(q);

        //Write a program that declares a named constant and uses it in a calculation. Print the result.
        final double constant = 5.14;
        double notconstant = 10;
        double toconsornot = constant * notconstant;
        System.out.println(toconsornot);

        //Write a program where you create three variables that represent products at a cafe. The products could be beverages
        // like coffee, cappuccino, espresso, green tea, etc. Assign prices to each product. Create two more variables called
        // subtotal and totalSale and complete an “order” for three items of the first product, four items of the second product,
        // and two items of the third product. Add them all together to calculate the subtotal. Create a constant called SALES_TAX and
        // add sales tax to the subtotal to obtain the totalSale amount. Be sure to format the results to two decimal places.
        double dripCoffee = 3.75;
        double latte = 4.75;
        double tea = 4.00;
        double order1 = dripCoffee * 3;
        double order2 = latte  * 4;
        double order3 = tea * 2;
        double subtotal = order1 + order2 + order3;
        final double SALES_TAX = .0987;
        double totalSale = (subtotal * SALES_TAX) + subtotal;
        System.out.printf("$%.2f", totalSale);


    }
}
