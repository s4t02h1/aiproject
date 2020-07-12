package gcd;

import java.math.BigInteger;

public class GCDTrialDivision {
    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.signum() != 1 || b.signum() != 1)
            throw new IllegalArgumentException();

        // [STEP 1]
        BigInteger g = BigInteger.ONE;
        if (a.compareTo(b) < 0) {
            BigInteger t = a; a = b; b = t;
        }
        // [STEP 2]
        for (BigInteger n = BigInteger.valueOf(2);
             n.compareTo(b) <= 0;
             n = n.add(BigInteger.ONE)) {
            // [STEP 2-1]
            while (a.remainder(n).equals(BigInteger.ZERO)
                && b.remainder(n).equals(BigInteger.ZERO)) {
                // [STEP 2-1-1]
                g = n.multiply(g);
                a = a.divide(n); b = b.divide(n);
            }
        }
        // [STEP 3]
        return g;
    }
}