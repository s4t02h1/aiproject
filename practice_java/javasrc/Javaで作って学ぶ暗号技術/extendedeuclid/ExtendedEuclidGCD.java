package extendedeuclid;

import java.math.BigInteger;

public class ExtendedEuclidGCD {

    public void gcd(BigInteger a, BigInteger b) {
        if (a.signum() != 1 || b.signum() != 1)
            throw new IllegalArgumentException();

        // STEP1
        BigInteger xPrev = BigInteger.ONE, x = BigInteger.ZERO;
        BigInteger yPrev = BigInteger.ZERO, y = BigInteger.ONE;
        BigInteger rPrev = a, r = b;
        // STEP2
        while (!r.equals(BigInteger.ZERO)) {
            // STEP3
            BigInteger qNext = rPrev.divide(r);
            BigInteger rNext = rPrev.remainder(r);
            // STEP4
            BigInteger xNext = xPrev.subtract(qNext.multiply(x));
            BigInteger yNext = yPrev.subtract(qNext.multiply(y));
            // SETP5
            xPrev = x; x = xNext;
            yPrev = y; y = yNext;
            rPrev = r; r = rNext;
        }
        this.gcd_ = rPrev;
        this.x_ = xPrev;
        this.y_ = yPrev;
    }

    public BigInteger getX() { return this.x_; }
    public BigInteger getY() { return this.y_; }
    public BigInteger getGCD() { return this.gcd_; }

    private BigInteger x_, y_, gcd_;
}
