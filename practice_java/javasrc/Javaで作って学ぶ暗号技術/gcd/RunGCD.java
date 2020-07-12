package gcd;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RunGCD {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        GCDBinaryEuclid gcdFunc = new GCDBinaryEuclid();
        // ---- check args ----
        if (argv.length != 2) return;
        int bitcount = Integer.valueOf(argv[0]);
        int datacount = Integer.valueOf(argv[1]);
        if (bitcount <= 0 || datacount <= 0) return;
        // ---- generate random numbers ----
        BigInteger[] randoms = new BigInteger[datacount + 1];
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < randoms.length;) {
            BigInteger v = new BigInteger(bitcount, r);
            if (v.signum() != 0) randoms[i++] = v;
        }
        // ---- measure execution time -----
        long t = Long.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < datacount; i++)
                gcdFunc.gcd(randoms[i], randoms[i + 1]);
            long t2 = System.currentTimeMillis();
            long d = t2 - t1; if (d < t) t = d;
            System.out.print(" " + d);
        }
        System.out.println(" : " + t);
    }
}
