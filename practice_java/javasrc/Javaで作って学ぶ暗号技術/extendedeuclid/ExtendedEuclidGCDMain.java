package extendedeuclid;

import java.math.BigInteger;

public class ExtendedEuclidGCDMain {
    public static void main(final String[] argv) {
        // ---- select algorithm ----
        ExtendedEuclidGCD gcdFunc = new ExtendedEuclidGCD();
        // ---- check args ----
        if (argv.length != 2) return;
        BigInteger a = new BigInteger(argv[0]);
        BigInteger b = new BigInteger(argv[1]);
        if (a.signum() != 1 || b.signum() != 1) return;
        // ---- calc. gcd ----
        gcdFunc.gcd(a, b);
        System.out.println("gcd = " + gcdFunc.getGCD());
        System.out.println("x = " + gcdFunc.getX());
        System.out.println("y = " + gcdFunc.getY());
    }
}
