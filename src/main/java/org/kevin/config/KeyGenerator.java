package org.kevin.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Membuat objek KeyPairGenerator dengan algoritma RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // Menentukan panjang kunci (2048 bit)
        keyPairGenerator.initialize(2048);

        // Menghasilkan pasangan kunci (public key dan private key)
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Mengonversi kunci ke format Base64 untuk mudah disimpan dan dibaca
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // Menampilkan kunci publik dan privat dalam format Base64
        System.out.println("Public Key (Base64): " + publicKeyBase64);
        System.out.println("Private Key (Base64): " + privateKeyBase64);
    }
}

