import java.util.*;
public class CreditCardValidation {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a credit card number");
        String creditCardNumberString = sc.nextLine();
        creditCardNumberString = creditCardNumberString.trim();
        long creditCardNumber;
        try {
            creditCardNumber = Long.parseLong(creditCardNumberString);
            System.out.println(checkValidity(creditCardNumberString));
        } catch(Exception e) {
            System.out.println("Invalid");
        }
    }

    public static String checkValidity(String creditCardNumberString) {
        if(creditCardNumberString.length() != 16)
            return "Invalid";
        if((oddSum(creditCardNumberString) + evenSum(creditCardNumberString)) % 10 == 0)
            return "Valid";
        else
            return "Invalid";
    }

    public static int oddSum(String creditCardNumberString) {
        int sum = 0;
        for(int i=1;i<creditCardNumberString.length();i+=2) {
            sum += Character.digit(creditCardNumberString.charAt(i), 10);
        }
        return sum;
    }

    public static int evenSum(String creditCardNumberString) {
        int sum = 0;
        for(int i=0;i<creditCardNumberString.length();i+=2) {
            int doubledNumber = 2 * Character.digit(creditCardNumberString.charAt(i), 10);
            sum += convertDoubledNumber(doubledNumber);
        }
        return sum;
    }

    public static int convertDoubledNumber(int doubledNumber) {
        if(doubledNumber >= 10)
            return doubledNumber%10 + doubledNumber/10;
        else
            return doubledNumber;
    }
}