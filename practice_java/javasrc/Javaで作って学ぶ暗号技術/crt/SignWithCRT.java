package crt;

import java.math.BigInteger;

import modpow.ModPowBinary;

public class SignWithCRT {
    public BigInteger sign(BigInteger c, BigInteger p, BigInteger q, BigInteger dp, BigInteger dq, BigInteger v) {
        ModPowBinary modpow = new ModPowBinary();
        // STEP 1
        BigInteger cp = c.mod(p);
        BigInteger cq = c.mod(q);
        // STEP 2
        BigInteger mp = modpow.modPow(cp, dp, p);
        BigInteger mq = modpow.modPow(cq, dq, q);
        // STEP 3
        BigInteger vv = v.multiply(mq.subtract(mp)).mod(q);
        // STEP 4
        return vv.multiply(p).add(mp);
    }
}
