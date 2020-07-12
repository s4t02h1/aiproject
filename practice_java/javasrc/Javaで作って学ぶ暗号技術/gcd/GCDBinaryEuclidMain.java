package gcd;

import java.math.BigInteger;

public class GCDBinaryEuclidMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        GCDBinaryEuclid gcdFunc = new GCDBinaryEuclid();
        // ---- check args ----
        if (argv.length != 2) return;
        BigInteger a = new BigInteger(argv[0]);
        BigInteger b = new BigInteger(argv[1]);
        if (a.signum() != 1 || b.signum() != 1) return;
        // ---- calc. ----
        BigInteger gcd = gcdFunc.gcd(a, b);
        System.out.println(gcd);
    }
}