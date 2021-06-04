package test;

import main.TestFerma;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestFermaTest {

    @Test
    public void testCheckPrime() {
        Assert.assertTrue(TestFerma.checkPrime(new BigInteger("17"), 20));
        Assert.assertFalse(TestFerma.checkPrime(new BigInteger("18"), 20));
    }
}