package modpow;

import java.math.BigInteger;

public class ModPow2wary {
    public BigInteger modPow(BigInteger a, BigInteger m, BigInteger n, int w) {
        if (m.signum() < 0 || n.signum() != 1 || w <= 0 || w > Integer.SIZE)
            throw new IllegalArgumentException();

        // STEP 1
        final BigInteger[] as = new BigInteger[1 << w];
        as[0] = Biginteger.ONE;
        for (int k = 1; k < 1 << w; k++)
            as[k] = as[k - 1].multiply(a).mod(n);
        BigInteger s = BigInteger.ONE;
        for (int j = (m.bitLength() + w - 1) / w - 1; // STEP 1
             j >= 0; // STEP 2
             j--) { // STEP 5
            // STEP 3
            for (int i = 0; i < w; i++)
                s = s.multiply(s).mod(n);
            // STEP 4
            int mjw = 0;
            for (int i = w - 1; i >= 0; i--) {
                mjw <<= 1;
                if (m.testBit(j * w + i)) mjw |= 1;
            }
            s = s.multiply(as[mjw]).mod(n);
        }
        // STEP 2
        return s;
    }
}
