package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Montgomery {
    private static BigInteger MR(BigInteger T, BigInteger N, BigInteger R, int pow) {
        BigInteger[] array = ExtendedEuclid.extendedAlgorithm(N, R);
        BigInteger d = array[0];

        if (!d.equals(BigInteger.ONE)) {
            throw new IllegalArgumentException("GCD(N,R) != 1");
        }

        BigInteger invN = array[1];
        BigInteger minusInvN = invN.negate();
        BigInteger m = (T.multiply(minusInvN)).mod(R);
        BigInteger t = (T.add(m.multiply(N))).shiftRight(pow);

        while (N.compareTo(t) < 0) {
            t = t.subtract(N);
        }
        return t;
    }

    public static BigInteger multiply(BigInteger a, BigInteger b, BigInteger R, BigInteger N, int pow) {
        BigInteger a1 = a.shiftLeft(pow).mod(N);
        BigInteger b1 = b.shiftLeft(pow).mod(N);
        BigInteger c1 = MR(a1.multiply(b1), N, R, pow);
        return MR(c1, N, R, pow);
    }

    public static BigInteger pow(BigInteger a, BigInteger e, BigInteger R, BigInteger N, int pow) {
        BigInteger a1 = a.shiftLeft(pow).mod(N);
        BigInteger x1 = BigInteger.ONE;
        while (e.compareTo(BigInteger.ZERO) > 0) {
            x1 = MR(x1.multiply(a1), N, R, pow);
            e = e.subtract(BigInteger.ONE);
        }
        return x1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.US_ASCII));
        System.out.print("x= ");
        BigInteger x = new BigInteger(in.readLine());
        System.out.print("* or ^: ");
        String oper = in.readLine();
        System.out.print("b= ");
        BigInteger y = new BigInteger(in.readLine());
        System.out.print("mod= ");
        BigInteger N = new BigInteger(in.readLine());
        System.out.print("r= ");
        BigInteger R = new BigInteger(in.readLine());
        System.out.println();

        BigInteger R1 = R;
        int pow = -1;
        while (R1.compareTo(BigInteger.ZERO) > 0) {
            R1 = R1.divide(BigInteger.TWO);
            pow++;
        }

        BigInteger res;

        if (oper.equals("times")) {
            res = multiply(x, y, R, N, pow);
        } else if (oper.equals("pow")) {
            res = pow(x, y, R, N, pow);
        } else {
            throw new IllegalArgumentException("Invalid operation: " + oper);
        }
        System.out.println(res);
    }
}
