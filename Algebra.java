// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  
    public static int plus(int a, int b) {
        // מוסיפים 1 ל-a בדיוק b פעמים
        while (b > 0) {
            a++;
            b--;
        }
        while (b < 0) { // אם b שלילי
            a--;
            b++;
        }
        return a;
    }

    public static int minus(int a, int b) {
        // a - b = a + (-b)
        while (b > 0) {
            a--;
            b--;
        }
        while (b < 0) {
            a++;
            b++;
        }
        return a;
    }

    public static int times(int a, int b) {
        int result = 0;
        boolean negative = false;

        // טיפול בסימן
        if (a < 0) {
            a = minus(0, a); // a = -a
            negative = !negative;
        }
        if (b < 0) {
            b = minus(0, b); // b = -b
            negative = !negative;
        }

        while (b > 0) {
            result = plus(result, a);
            b--;
        }

        if (negative)
            result = minus(0, result);

        return result;
    }

    public static int pow(int a, int b) {
        int result = 1;
        while (b > 0) {
            result = times(result, a);
            b--;
        }
        return result;
    }

    public static int div(int a, int b) {
        if (b == 0) throw new RuntimeException("Division by zero!");

        int count = 0;
        boolean negative = false;

        if (a < 0) {
            a = minus(0, a);
            negative = !negative;
        }
        if (b < 0) {
            b = minus(0, b);
            negative = !negative;
        }

        while (a >= b) {
            a = minus(a, b);
            count++;
        }

        if (negative)
            count = minus(0, count);

        return count;
    }

    public static int mod(int a, int b) {
        int d = div(a, b);
        int product = times(d, b);
        return minus(a, product);
    }

    public static int sqrt(int x) {
        int i = 0;
        while (times(i, i) <= x) {
            i++;
        }
        return minus(i, 1);
    }
}