package modinverse;

import gcd.GCDBinaryEuclid;

import java.math.BigInteger;

public class ModInverse {
    public BigInteger modInverse(BigInteger e, BigInteger l) {
        if (e.signum() != 1 || l.signum() != 1) {
            throw new IllegalArgumentException();
        }
        // STEP 1 
        BigInteger dPrev = BigInteger.ONE, d = BigInteger.ZERO;
        BigInteger rPrev = e, r = l;
        // STEP 2
        while (!r.equals(BigInteger.ZERO)) {
            // STEP 3
            BigInteger dNext = rPrev.divide(r);
            BigInteger rNext = rPrev.remainder(r);
            // STEP 4
            BigInteger xNext = dPrev.subtract(dNext.multiply(d));
            // SETP 5
            dPrev = d; d = xNext;
            rPrev = r; r = rNext;
        }
        return dPrev.mod(l);
    }
}
