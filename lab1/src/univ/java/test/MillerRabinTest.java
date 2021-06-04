package test;

import main.MillerRabin;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class MillerRabinTest {

    @Test
    public void isProbablePrime() {
        Assert.assertTrue(MillerRabin.isProbablePrime(new BigInteger("7")));
        Assert.assertTrue(MillerRabin.isProbablePrime(new BigInteger("11")));
        Assert.assertFalse(MillerRabin.isProbablePrime(new BigInteger("12")));
        Assert.assertFalse(MillerRabin.isProbablePrime(new BigInteger("14")));
    }
}