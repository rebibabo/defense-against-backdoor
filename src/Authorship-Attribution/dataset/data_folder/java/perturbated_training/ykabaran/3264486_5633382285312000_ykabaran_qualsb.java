package googlecodejam2017;
 
 import googlecodejam2017.GoogleCodeJam2017.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.math.BigInteger;
 
 public class QualsB implements Problem {
 
    private String number;
 
    @Override
    public void setup(BufferedReader input) throws IOException {
        number = input.readLine();
    }
 
    private int getDigitAt(int i) {
        return Integer.parseInt(Character.toString(number.charAt(i)));
    }
 
    private String repeatingString(int n, char c) {
        if (n == 0) {
            return "";
        }
 
        char[] chars = new char[n];
        for (int i = 0; i < n; ++i) {
            chars[i] = c;
        }
        return new String(chars);
    }
 
    private String fixNumberAt(int i) {
        int l = number.length();
        BigInteger firstHalf = new BigInteger(number.substring(0, i));
        firstHalf = firstHalf.subtract(BigInteger.ONE);
 
        String firstHalfStr = firstHalf.toString();
        return repeatingString(i - firstHalfStr.length(), '0') + firstHalfStr + repeatingString(l - i, '9');
    }
 
    @Override
    public void solve() {
        if (number.length() == 1) {
            return;
        }
 
        for (int i = number.length() - 1; i > 0; --i) {
            int currDigit = getDigitAt(i);
            int prevDigit = getDigitAt(i - 1);
            if (currDigit >= prevDigit) {
                continue;
            }
            number = fixNumberAt(i);
        }
 
        number = new BigInteger(number).toString();
    }
 
    @Override
    public String getSolution() {
        return number;
    }
 
 }
