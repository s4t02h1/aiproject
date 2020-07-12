package probableprime;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import modpow.ModPowBinary;

public class MillerRabinTest {
    public boolean isProbablePrime0a(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0)
            throw new IllegalArgumentException();

        // STEP3
        for (int i = 1; // STEP2
        i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);
            if (!this.checkProbablePrime(r, a))
                return false;
        }
        return true;
    }

    private boolean checkProbablePrime(BigInteger r, BigInteger a) {
        BigInteger rm1 = r.subtract(ONE);

        // STEP1
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP4
        BigInteger b = MOD_POW_FUNC.modPow(a, k, r);
        if (b.equals(ONE))
            return true;

        // STEP6
        for (int j = 0; // STEP5
        j < s; j++) { // STEP6
            if (b.equals(ONE))
                return false;
            if (b.equals(rm1))
                return true;
            // STEP6
            b = b.multiply(b).mod(r);
        }
        return false;
    }

    public boolean isProbablePrime0(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0)
            throw new IllegalArgumentException();

        // STEP1
        BigInteger rm1 = r.subtract(ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        L: for (int i = 1; // STEP2
        i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);
            if (b.equals(ONE))
                continue;

            // STEP6
            int j;
            for (j = 0; // STEP5
            j < s; j++) { // STEP6
                if (b.equals(ONE))
                    return false;
                if (b.equals(rm1))
                    continue L;
                // STEP6
                b = b.multiply(b).mod(r);
            }
            return false;
        }
        return true;
    }

    public boolean isProbablePrime_original(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0) {
            throw new IllegalArgumentException();
        }

        // STEP1
        BigInteger rm1 = r.subtract(ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        for (int i = 1; // STEP2
        i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);

            // STEP6
            int j;
            for (j = 0; // STEP5
            j < s && !b.equals(ONE) && !b.equals(rm1); j++) { // STEP6
                // STEP6
                b = b.multiply(b).mod(r);
            }

            // STEP7
            if (j == s || (j > 0 && b.equals(ONE)))
                return false;
        }
        return true;
    }

    public boolean isProbablePrime_final(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0) {
            throw new IllegalArgumentException();
        }

        // STEP1
        BigInteger rm1 = r.subtract(ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        L: for (int i = 1; i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);

            int j;
            for (j = 0; j < s; j++) {
                if (b.equals(rm1))
                    continue L;

                if (b.equals(ONE)) {
                    if (j == 0)
                        continue L;
                    else
                        return false;
                }

                b = b.multiply(b).mod(r);
            }
            return false;
        }
        return true;
    }

    public boolean isProbablePrime_1a(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0) {
            throw new IllegalArgumentException();
        }

        // STEP1
        BigInteger rm1 = r.subtract(ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        for (int i = 1; i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);

            boolean isComposite;
            L: {
                for (int j = 0; j < s; j++) {
                    if (b.equals(ONE)) {
                        isComposite = j != 0;
                        break L;
                    }

                    if (b.equals(rm1)) {
                        isComposite = false;
                        break L;
                    }

                    b = b.multiply(b).mod(r);
                }
                isComposite = true;
                break L;
            }

            if (isComposite)
                return false;
        }
        return true;
    }

    public boolean isProbablePrime_tmp(BigInteger r, int t) {
        if (r.compareTo(THREE) < 0 || !r.testBit(0) || t <= 0) {
            throw new IllegalArgumentException();
        }

        // STEP1
        BigInteger rm1 = r.subtract(ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        for (int i = 1; i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);

            L: {
                for (int j = 0; j < s; j++) {
                    if (b.equals(rm1))
                        break L;

                    if (b.equals(ONE)) {
                        if (j == 0)
                            break L;
                        return false;
                    }

                    b = b.multiply(b).mod(r);
                }
                return false;
            }
        }
        return true;
    }

    public boolean isProbablePrime(BigInteger r, int t) {
        if (r.compareTo(BigInteger.valueOf(3)) < 0 || !r.testBit(0) || t <= 0) {
            throw new IllegalArgumentException();
        }

        // STEP1
        BigInteger rm1 = r.subtract(BigInteger.ONE);
        int s = rm1.getLowestSetBit();
        BigInteger k = rm1.shiftRight(s);

        // STEP3
        for (int i = 1; i < t; i++) { // STEP8
            SecureRandom secureRandom = new SecureRandom();
            BigInteger a;
            do {
                a = new BigInteger(r.bitLength(), secureRandom);
            } while (a.compareTo(BigInteger.ONE) <= 0 || a.compareTo(r) >= 0);

            // STEP4
            BigInteger b = MOD_POW_FUNC.modPow(a, k, r);
            if (isCompsite(b, s, r))
                return false;
        }
        return true;
    }

    private boolean isCompsite(BigInteger b, int s, BigInteger r) {
        BigInteger rm1 = r.subtract(BigInteger.ONE);
        for (int j = 0; j < s; j++) {
            if (b.equals(BigInteger.ONE)) return j != 0;
            if (b.equals(rm1)) return false;
            b = b.multiply(b).mod(r);
        }
        return true;
    }

    private static final BigInteger   ONE          = BigInteger.ONE;

    private static final BigInteger   THREE        = BigInteger.valueOf(3);

    private static final ModPowBinary MOD_POW_FUNC = new ModPowBinary();
}
