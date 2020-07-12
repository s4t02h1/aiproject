package crt;

import java.math.BigInteger;

import modinverse.ModInverse;

public class SignWithCRTMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        SignWithCRT signFunc = new SignWithCRT();
        ModInverse modInverseFunc = new ModInverse();
        // ---- check args ----
        if (argv.length != 4) return;
        BigInteger c = new BigInteger(argv[0]);
        BigInteger p = new BigInteger(argv[1]);
        BigInteger q = new BigInteger(argv[2]);
        BigInteger d = new BigInteger(argv[3]);
        if (c.signum() == -1 || p.signum() != 1 || q.signum() != 1
                || d.signum() != 1)
            return;
        // ---- calc. ----
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));

        BigInteger v = modInverseFunc.modInverse(p, q);
        BigInteger m = signFunc.sign(c, p, q, dp, dq, v);
        System.out.println(m);
    }
}
