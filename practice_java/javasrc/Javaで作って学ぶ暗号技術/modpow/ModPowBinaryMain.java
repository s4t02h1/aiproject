package modpow;

import java.math.BigInteger;

public class ModPowBinaryMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        ModPowBinary modPowFunc = new ModPowBinary();
        // ---- check args ----
        if (argv.length != 3) return;
        BigInteger a = new BigInteger(argv[0]);
        BigInteger m = new BigInteger(argv[1]);
        BigInteger n = new BigInteger(argv[2]);
        if (m.signum() < 0 || n.signum() != 1) return;
        // ---- calc. ----
        BigInteger pow = modPowFunc.modPow(a, m, n);
        System.out.println(pow);
    }
}
