package probableprime;

import java.math.BigInteger;

public class FermatTestMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        FermatTest primeTestFunc = new FermatTest();
        // ---- check args ----
        if (argv.length != 2) return;
        BigInteger r = new BigInteger(argv[0]);
        int t = Integer.valueOf(argv[1]);
        if (r.compareTo(BigInteger.valueOf(3)) < 0 || !r.testBit(0) || t < 1) return;
        // ---- prime test ----
        boolean p = primeTestFunc.isProbablePrime(r, t);
        System.out.println(p);
    }
}
