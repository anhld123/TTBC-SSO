/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.encrypt;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author LION
 */
public class RSA {

    private static boolean String(byte[] toByteArray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private BigInteger modulus;
    private BigInteger privateKey;
    private BigInteger publicKey;
    private int bitlen = 1024;

    /**
     * Create an instance that can encrypt using someone elses public key.
     */
    public RSA(BigInteger modulus, BigInteger publicKey) {
        this.modulus = modulus;
        this.publicKey = publicKey;
    }

    /**
     * Create an instance that can both encrypt and decrypt.
     */
    public RSA(int bits) {
        bitlen = bits;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        modulus = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        publicKey = new BigInteger("3");
        while (m.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(new BigInteger("2"));
        }
        privateKey = publicKey.modInverse(m);
    }

    /**
     * Encrypt the given plaintext message.
     */
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
    }

    /**
     * Encrypt the given plaintext message.
     */
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    /**
     * Decrypt the given ciphertext message.
     */
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(privateKey, modulus).toByteArray());
    }

    /**
     * Decrypt the given ciphertext message.
     */
    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(privateKey, modulus);
    }

    /**
     * Generate a new public and private key set.
     */
    public synchronized void generateKeys() {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        modulus = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        publicKey = new BigInteger("3");
        while (m.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(new BigInteger("2"));
        }
        System.err.println("public key: " + publicKey.toString());
        privateKey = publicKey.modInverse(m);
        System.err.println("pivate key: " + privateKey.toString());
    }

    /**
     * Return the modulus.
     */
    public synchronized BigInteger getN() {
        return modulus;
    }

    /**
     * Return the public key.
     */
    public synchronized BigInteger getE() {
        return publicKey;
    }

    /**
     * Trivial test program.
     */
    public static void main(String[] args) {
        RSA rsa = new RSA(1024);

        String text1 = "intellect";
        System.out.println("Plaintext intellect binh thuong : " + text1);
        BigInteger plaintext = new BigInteger(text1.getBytes());

        BigInteger ciphertext = rsa.encrypt(plaintext);
        System.out.println("Ciphertext duoi dang ma hoa BigInteger: " + ciphertext);
        plaintext = rsa.decrypt(ciphertext);

        String text2 = new String(plaintext.toByteArray());
        System.out.println("Plaintext: " + text2);
        
        String mkmahoa="105251841855197465438356729560861564900395508910840516500323828706501528463890914750433447771768046605985557995559996575701023126965609230291771408384";
        BigInteger decode = new BigInteger(mkmahoa.getBytes());
        BigInteger matkhau = rsa.decrypt(decode);
       // System.err.println(String(matkhau.toByteArray()));
    }
}
