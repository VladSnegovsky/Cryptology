package test;

import main.Montgomery;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MontgomeryTest {

    private final BigInteger R = new BigInteger("128");
    private final int pow = 6;

    @Test
    public void multiply() {
        assertEquals(Montgomery.multiply(new BigInteger("48"), new BigInteger("37"),
                R, new BigInteger("87"), pow),
                new BigInteger("29"));
    }

    @Test
    public void pow() {
        assertEquals(Montgomery.pow(new BigInteger("48"), new BigInteger("37"),
                R, new BigInteger("87"), pow),
                new BigInteger("29"));
    }
}