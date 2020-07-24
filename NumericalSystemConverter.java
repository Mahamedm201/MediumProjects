package converter;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int source;
    static String num;
    static int target;
    static int integer;
    static double decimal;
    static StringBuilder finalAnswer = new StringBuilder();
    static public void isDigit() {
        if (sc.hasNextInt()) {
            source = sc.nextInt();
            if (source > 36 || source <= 0) {
                System.out.println("error");
                System.exit(1);
            }
        } else {
            System.out.println("error");
            System.exit(1);
        }
        if (sc.hasNext()) {
            num = sc.next();
            try {
               int k = num.indexOf(".");
               if (k == -1 && source != 1) {
                   Integer.parseInt(num, source);
               } else if (source != 1) {
                   String f = num.substring(0, k);
                   Integer.parseInt(f, source);
               }
            } catch (NumberFormatException e) {
                System.out.println("error");
                System.exit(1);
            }
        } else {
            System.out.println("error");
            System.exit(1);
        }
        if (sc.hasNextInt()) {
            target = sc.nextInt();
            if (target > 36 || target <= 0) {
                System.out.println("error");
                System.exit(1);
            }
        } else {
            System.out.println("error");
            System.exit(1);
        }
        int period = num.indexOf(".");
        if (period == -1 && source != 1) {
            integer = Integer.parseInt(num, source);
            decimal = period;
        } else if (source == 1 && period == -1) {
            integer = num.length();
            decimal = period;
        } else if (period > 0) {
            String front = num.substring(0, period);
            String back = num.substring(period + 1);
            integer = Integer.parseInt(front, source);
            if (!Character.isDigit(back.charAt(0))) {
                double result = 0;
                double base = source;
                double[] ans = new double[back.length()];
                for (int i = 0; i < back.length(); i++) {
                    ans[i] = Character.getNumericValue(back.charAt(i));
                    result += ans[i] / Math.pow(base, i + 1);
                    decimal = result;
                }
            } else {
                decimal = Double.parseDouble(back) / Math.pow(10, back.length());
            }
        }
    }
    static public void isInteger () {
        if (decimal <= 0 && source != 1 && target != 1) {
            finalAnswer.append(Integer.toString(integer, target));
        } else if (decimal <= 0 && source == 1) {
            finalAnswer.append(Integer.toString(integer, target));
        } else if (decimal <= 0 && target == 1) {
            finalAnswer.append("1".repeat(Math.max(0, integer)));
        } else if (integer == 0) {
            finalAnswer.append(0);
        } else {
            finalAnswer.append(Integer.toString(integer, target));
        }
    }
    static public void isDecimal() {
        isDigit();
        isInteger();
        StringBuilder n = new StringBuilder();
        int digit;
         if (decimal >= 0) {
            finalAnswer.append(".");
             if (decimal == 0) {
                 String zeroes = "0".repeat(Math.max(0, 5));
                 finalAnswer.append(zeroes);
             } else {
                 for (int i = 0; i < 5; i++) {
                     decimal = decimal * target;
                     digit = (int) decimal;
                     decimal = decimal % 1;
                     if (digit > 9) {
                         n.append(Character.forDigit(digit, target));
                     } else {
                         n.append(digit);
                     }
                 }
             }
            finalAnswer.append(n);
         }
        System.out.println(finalAnswer);
    }
    public static void main(String[] args) {
        isDecimal();
    }
}