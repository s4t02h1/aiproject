package modpow;

import java.math.BigInteger;

public class ModPowBinary {
    public BigInteger modPow(BigInteger a, BigInteger m, BigInteger n) {
        if (m.signum() < 0 || n.signum() != 1)
            throw new IllegalArgumentException();

        // [STEP 1]
        BigInteger s = BigInteger.ONE;
        // [STEP 2]
        for (int j = m.bitLength() - 1; j >= 0; j--) {
            // [STEP 2-1]
            s = s.multiply(s).mod(n);
            // [STEP 2-2]
            if (m.testBit(j)) s = s.multiply(a).mod(n);
        }
        // [STEP 3]
        return s;
    }
}