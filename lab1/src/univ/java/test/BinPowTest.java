package test;

import main.BinPow;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static org.junit.Assert.assertEquals;

public class BinPowTest {

    @Test
    public void binPow() {
        BigInteger test1 = BinPow.binPow(new BigInteger("100"), new BigInteger("5"), new BigInteger("7"));
        BigInteger test2 = BinPow.binPow(new BigInteger("72657635"), ONE, new BigInteger("7"));

        Assert.assertEquals(test1, new BigInteger("4"));
        Assert.assertEquals(test2, ONE);
    }
}