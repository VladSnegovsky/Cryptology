package algorithm.impl;

import algorithm.RSA;
import algorithm.utils.RSAUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSAImpl implements RSA {

    private final static BigInteger ONE = BigInteger.ONE;
    private BigInteger privateKey;
    private BigInteger e;
    private BigInteger modulus;
    private BigInteger p;
    private BigInteger q;
    private final BigInteger phi;

    public RSAImpl(BigInteger p, BigInteger q, BigInteger e) {
        //phi = (p-1)*(q-1)
        phi = (p.subtract(ONE)).multiply(q.subtract(ONE));
        this.e = e;
        this.p = p;
        this.q = q;
        modulus = p.multiply(q);
        privateKey = e.modInverse(phi);
    }

    // Encrypt
    @Override
    public BigInteger encrypt(BigInteger bigInteger) {
        if (isModulusSmallerThanMessage(bigInteger))
            throw new IllegalArgumentException("Cannot cypher");
        // this^e mod modulus
        return bigInteger.modPow(e, modulus);
    }

    public List<BigInteger> encryptMessage(final String message) {
        List<BigInteger> toEncrypt = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toEncrypt = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() {
                {
                    add(message);
                }
            }));
        } else {
            toEncrypt.add((messageBytes));
        }

        List<BigInteger> encrypted = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : toEncrypt) {
            encrypted.add(encrypt(bigInteger));
        }
        return encrypted;
    }

    // Decrypt
    @Override
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    @Override
    public List<BigInteger> decryptMessages(List<BigInteger> encryption) {
        List<BigInteger> decryption = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : encryption) {
            decryption.add(decrypt(bigInteger));
        }
        return decryption;
    }

    public List<BigInteger> messageToDecimal(final String message) {
        List<BigInteger> toDecimal = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toDecimal = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() {
                {
                    add(message);
                }
            }));
        } else toDecimal.add((messageBytes));

        return new ArrayList<BigInteger>(toDecimal);
    }





    @Override
    public BigInteger sign(BigInteger bigInteger) {
        return bigInteger.modPow(privateKey, modulus);
    }

    public List<BigInteger> signMessage(final String message) {
        List<BigInteger> toSign = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toSign = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() {
                {
                    add(message);
                }
            }));
        } else {
            toSign.add((messageBytes));
        }
        List<BigInteger> signed = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : toSign) {
            signed.add(sign(bigInteger));
        }
        return signed;
    }

    @Override
    public BigInteger verifySignedMessage(BigInteger signedMessage) {
        return signedMessage.modPow(e, modulus);
    }

    public List<BigInteger> verify(List<BigInteger> signedMessages) {
        List<BigInteger> verification = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : signedMessages) {
            verification.add(verifySignedMessage(bigInteger));
        }
        return verification;
    }

    @Override
    public boolean isVerified(BigInteger signedMessage, BigInteger message) {
        return verifySignedMessage(signedMessage).equals(message);
    }

    private List<BigInteger> getValidEncryptionBlocks(List<String> messages) {
        List<BigInteger> validBlocks = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(messages.get(0).getBytes());
        if (!isModulusSmallerThanMessage(messageBytes)) {
            for (String msg : messages) {
                validBlocks.add(new BigInteger(msg.getBytes()));
            }
            return validBlocks;
        } else
            return getValidEncryptionBlocks(RSAUtils.splitMessages(messages));
    }

    private boolean isModulusSmallerThanMessage(BigInteger messageBytes) {
        return modulus.compareTo(messageBytes) < 0;
    }

    @Override
    public String toString() {
        String s = "";
        s += "p                     = " + p + "\n";
        s += "q                     = " + q + "\n";
        s += "e                     = " + e + "\n";
        s += "privateKey            = " + privateKey + "\n";
        s += "modulus               = " + modulus;
        return s;
    }
}
