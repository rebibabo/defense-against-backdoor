public class CoinJam {
    public static void main(String[] args) {
        int cnt = 0;
        for (int i = 1; i < 1 << 16; i += 2) {
            if ((i & (1 << 15)) > 0) {
                int[] div = new int[11];
                for (int base = 2; base < 11; base++) {
                    long x = 0;
                    long p = 1;
                    for (int j = 0; j < 16; j++) {
                        if ((i & (1 << j)) > 0) {
                            x += p;
                        }
                        p *= base;
                    }
                    for (int k = 3; k < 10000; k += 2) {
                        if (x % k == 0) {
                            div[base] = k;
                            break;
                        }
                    }
                }
                boolean can = true;
                for (int j = 2; j < div.length; j++) {
                    if (div[j] == 0) {
                        can = false;
                    }
                }
                if (can) {
                    System.out.print(Integer.toBinaryString(i));
                    for (int j = 2; j < div.length; j++) {
                        System.out.print(" " + div[j]);
                    }
                    System.out.println();
                    cnt++;
                    if (cnt == 50)
                        return;
                }
            }
        }
    }
 }
