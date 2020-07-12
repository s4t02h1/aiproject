package modpow;

import java.math.BigInteger;

public class ModPowSlidingWindow {
    public BigInteger modPow(BigInteger a, BigInteger m, BigInteger n, int w) {
        if (m.signum() < 0 || n.signum() != 1 || w <= 0 || w > Integer.SIZE)
            throw new IllegalArgumentException();

        BigInteger[] at = this.newTable(a, n, w);
        // STEP 1
        BigInteger s = BigInteger.ONE;
        for (int j = m.bitLength() - 1; // STEP 1
             j >= 0; // STEP 2
             ) {
            // STEP 3
            if (!m.testBit(j)) {
                s = s.multiply(s).mod(n);
                j--;
            } else {
                int l;
                for (l = Math.max(j - w + 1, 0); j > l; l++)
                    if (m.testBit(l)) break;
                int mjl = 0;
                for (int i = j; i >= l; i--) {
                    mjl <<= 1;
                    if (m.testBit(i)) mjl |= 1;
                    s = s.multiply(s).mod(n);
                }
                s = s.multiply(at[mjl >> 1]).mod(n);
                j = l - 1;
            }
        }
        return s; // STEP 2;
    }

    private BigInteger[] newTable(BigInteger a, BigInteger n, int w) {
        // STEP 1
        BigInteger[] at = new BigInteger[1 << (w - 1)];
        at[0] = a.mod(n);
        BigInteger at2 = a.multiply(a).mod(n);
        for (int j = 1; // STEP 1
             j < 1 << (w - 1); // STEP 2
             j++) { // STEP 3
            // STEP 3
            at[j] = at[j - 1].multiply(at2).mod(n);
        }
        return at; // STEP 2
    }
}
