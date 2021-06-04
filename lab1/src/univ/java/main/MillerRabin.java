package main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.*;

public class MillerRabin {
    private static final BigInteger THREE = BigInteger.valueOf(3);

    public static boolean isProbablePrime(BigInteger n) {
        if (n.compareTo(THREE) < 0)
            return true;

        int s = 0;
        BigInteger d = n.subtract(ONE);

        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }

        for (int i = 0; i < 20; i++) {
            BigInteger a = myMRRandom(n.subtract(ONE));
            BigInteger x = a.modPow(d, n);

            if (x.equals(ONE) || x.equals(n.subtract(ONE)))
                continue;

            int r = 0;
            for (; r < s; r++) {
                x = x.modPow(TWO, n);

                if (x.equals(ONE))
                    return false;

                if (x.equals(n.subtract(ONE)))
                    break;
            }

            if (r == s)
                return false;
        }
        return true;
    }

    private static BigInteger myMRRandom(BigInteger top) {
        Random rnd = new Random();

        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(BigInteger.TWO) < 0 || res.compareTo(top) > 0);
        return res;
    }


    public static void main(String[] args) {
        String[] primes = {"2", "193"};
        String[] nonPrimes = {"21"};

        for (String p : primes)
            System.out.println(isProbablePrime(new BigDecimal(p).toBigInteger()));
        System.out.println();
        for (String n : nonPrimes)
            System.out.println(isProbablePrime(new BigDecimal(n).toBigInteger()));
    }
}
