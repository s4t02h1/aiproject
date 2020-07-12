package gcd;

import java.math.BigInteger;

public class GCDBinaryEuclid {
    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.signum() != 1 || b.signum() != 1)
            throw new IllegalArgumentException();

        // [STEP 1]
        int g = 0;
        // [STEP 2]
        while (a.signum() > 0) {
            boolean aIsOdd = a.testBit(0);
            boolean bIsOdd = b.testBit(0);
            // [STEP 2-1]
            if (!aIsOdd && !bIsOdd) {
                a = a.shiftRight(1);
                b = b.shiftRight(1);
                g++;
            // [STEP 2-2]
            } else if (!aIsOdd && bIsOdd) {
                a = a.shiftRight(1);
            // [STEP 2-3]
            } else if (aIsOdd && !bIsOdd) {
                b = b.shiftRight(1);
            // [STEP 2-4]
            } else { // aIsOdd && bIsOdd
                if (a.compareTo(b) < 0)
                    b = b.subtract(a).shiftRight(1);
                else
                    a = a.subtract(b).shiftRight(1);
            }
        }
        // [[STEP 3]
        return b.shiftLeft(g);
    }
}
