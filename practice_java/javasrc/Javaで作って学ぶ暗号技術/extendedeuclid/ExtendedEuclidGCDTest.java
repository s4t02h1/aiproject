package extendedeuclid;

import java.math.BigInteger;
import java.security.SecureRandom;

import junit.framework.Assert;

import org.junit.Test;

import gcd.GCDBigInteger;

public class GCDExtendedEuclidTest {

    @Test
    public void testGCDExtendedEuclid() {
        // ---- generate random numbers ----
        BigInteger[] randoms = new BigInteger[DATACOUNT + 1];
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < randoms.length;) {
            BigInteger v = new BigInteger(BITCOUNT, r);
            if (v.signum() != 0)
                randoms[i++] = v;
        }

        final GCDExtendedEuclid func = new GCDExtendedEuclid();
        final GCDBigInteger func2 = new GCDBigInteger();
        for (int i = 0; i < DATACOUNT; i++) {
            func.gcd(randoms[i], randoms[i + 1]);
            BigInteger x = func.getX();
            BigInteger y = func.getY();
            BigInteger gcd = func.getGCD();

            BigInteger gcd2 = func2.gcd(randoms[i], randoms[i + 1]);

            Assert.assertEquals(gcd, gcd2);

            BigInteger gcd3 = randoms[i].multiply(x).add(
                    randoms[i + 1].multiply(y));

            Assert.assertEquals(gcd, gcd3);
        }
    }

    public static int DATACOUNT = 100;

    public static int BITCOUNT  = 4096;
}
