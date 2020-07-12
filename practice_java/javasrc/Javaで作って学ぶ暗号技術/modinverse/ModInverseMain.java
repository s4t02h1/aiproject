package modinverse;

import java.math.BigInteger;

public class ModInverseMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        ModInverse gcdFunc = new ModInverse();
        // ---- check args ----
        if (argv.length != 2) return;
        BigInteger e = new BigInteger(argv[0]);
        BigInteger l = new BigInteger(argv[1]);
        if (e.signum() != 1 || l.signum() != 1) return;
        // ---- calc. gcd ----
        BigInteger d = gcdFunc.modInverse(e, l);
        System.out.println(d);
    }
}
