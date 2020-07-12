package gcd;

import java.math.BigInteger;

public class GCDEuclid {
    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.signum() != 1 || b.signum() != 1)
            throw new IllegalArgumentException();

        // [STEP 1]
        for (BigInteger r = a.remainder(b);
             !r.equals(BigInteger.ZERO);
			 r = a.remainder(b)) {
            // [STEP 1-1], [STEP 1-2]
            a = b; b = r;
        }
        // [STEP 2]
        return b;
    }
}
