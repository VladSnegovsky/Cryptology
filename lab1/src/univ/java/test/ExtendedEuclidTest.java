package test;

import main.ExtendedEuclid;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ExtendedEuclidTest {

    @Test
    public void extendedAlgorithm() {
        BigInteger[] gcd1 = ExtendedEuclid.extendedAlgorithm(new BigInteger(String.valueOf(84)), new BigInteger(String.valueOf(275)));
        Assert.assertEquals(gcd1[0], BigInteger.ONE);
        Assert.assertEquals(gcd1[1],  new BigInteger("-36"));
    }
}