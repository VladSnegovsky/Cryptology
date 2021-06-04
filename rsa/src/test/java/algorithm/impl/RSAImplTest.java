package algorithm.impl;

import algorithm.RSA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.*;
import static java.math.BigInteger.ONE;
import static org.junit.jupiter.api.Assertions.*;

class RSAImplTest {
    private static RSA rsa;

    public static boolean checkPrime(BigInteger num, int iter) {
        if (num.compareTo(BigInteger.valueOf(3)) < 0)
            return true;

        int s = 0;
        // d % 2 = 1
        BigInteger d = num.subtract(ONE);

        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }

        for (int i = 0; i < iter; i++) {
            BigInteger n1 = myRandom(num.subtract(ONE));
            BigInteger n2 = n1.modPow(d, num);

            int n3 = 0;
            if (n2.equals(ONE) || n2.equals(num.subtract(ONE)))
                continue;

            for (; n3 < s; n3++) {
                n2 = n2.modPow(TWO, num);

                if (n2.equals(ONE))
                    return false;

                if (n2.equals(num.subtract(ONE)))
                    break;
            }

            if (n3 == s)
                return false;
        }
        return true;
    }

    private static BigInteger myRandom(BigInteger top) {
        Random rnd = new Random();

        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(BigInteger.TWO) < 0 || res.compareTo(top) > 0);
        return res;
    }

    public static BigInteger getPrime() {
        BigInteger num = new BigInteger(2048, 1, new Random());
        while (!checkPrime(num, 5)){
            num = new BigInteger(2048, 1, new Random());
        }
        return num;
    }

    @BeforeAll
    static void before() {
        rsa = new RSAImpl(getPrime(), getPrime(), getPrime());
    }

    @Test
    void encrypt() {
        BigInteger number1 = new BigInteger("10");
        BigInteger number2 = new BigInteger("15");
        System.out.println(rsa.encrypt(number1));
        System.out.println(rsa.encrypt(number2));
        assertNotEquals(new BigInteger("0"), rsa.encrypt(number1));
        assertNotEquals(new BigInteger("0"), rsa.encrypt(number2));

        assertEquals(1, rsa.decrypt(number1).compareTo(new BigInteger("10")));
        assertEquals(1, rsa.decrypt(number2).compareTo(new BigInteger("15")));
    }
}