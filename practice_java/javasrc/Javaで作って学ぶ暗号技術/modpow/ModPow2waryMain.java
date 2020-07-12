package modpow;

import java.math.BigInteger;

public class ModPow2waryMain {
    public static void main(String[] argv) {
        // ---- select algorithm ----
        ModPow2wary modPowFunc = new ModPow2wary();
        // ---- check args ----
        if (argv.length != 4) return;
        BigInteger a = new BigInteger(argv[0]);
        BigInteger m = new BigInteger(argv[1]);
        BigInteger n = new BigInteger(argv[2]);
        int w = Integer.valueOf(argv[3]);
        if (m.signum() < 0 || n.signum() != 1 || w < 1) return;
        // ---- calc. ----
        BigInteger pow = modPowFunc.modPow(a, m, n, w);
        System.out.println(pow);
    }
}
