package test;

import main.MultKarazuba;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class MultKarazubaTest {

    @Test
    public void karazubaMult() {
        BigInteger num = new BigInteger("722876385");
        assertEquals(MultKarazuba.multiplication(num, BigInteger.ONE), num);
        assertEquals(MultKarazuba.multiplication(new BigInteger("846348765834658363523376721"), new BigInteger("53423434348768")),
                new BigInteger("45214857727728692358861251840800966229728"));
    }
}