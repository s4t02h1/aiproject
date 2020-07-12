package probableprime;

import java.math.BigInteger;
import java.security.SecureRandom;

import modpow.ModPowBinary;

public class FermatTest {
    public boolean isProbablePrime(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0)
            throw new IllegalArgumentException();
        BigInteger rm1 = r.subtract(ONE);
        for (int j = t - 1; // STEP 1
             j >= 0; j--) { // STEP 5
            // STEP 2
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0
                  || a.compareTo(rm1) >= 0);
            // STEP 3
            BigInteger sr = MOD_POW_FUNC.modPow(a, rm1, r);
            // STEP 4
            if (!sr.equals(ONE)) return false;
        }
        // STEP 5
        return true;
    }

    private static final BigInteger ONE   = BigInteger.ONE;
    private static final BigInteger THREE = BigInteger.valueOf(3);

    private static final ModPowBinary MOD_POW_FUNC = new ModPowBinary();
}
