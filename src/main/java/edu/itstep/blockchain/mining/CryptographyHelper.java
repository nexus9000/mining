package edu.itstep.blockchain.mining;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {
	/**
	 * generate public key and private key private key: 256 bits long random integer
	 * public key: point on the elliptic curve (x,y) - both values are 256 bits long
	 * 
	 * @return
	 */
	public static KeyPair ellipticCurveCrypto() {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
			ECGenParameterSpec params = new ECGenParameterSpec("prime256v1");
			keyPairGenerator.initialize(params);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final byte[] sign(PrivateKey privateKey, String input) {
		Signature signature;
		byte[] output = new byte[0];
		try {
			signature = Signature.getInstance("ECDSA", "BC");
			signature.initSign(privateKey);
			signature.update(input.getBytes());
			output = signature.sign();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}

	public final static boolean verify(PublicKey sender, String data, byte[] signature) {
		try {
			Signature ecdsaSignature = Signature.getInstance("ECDSA", "BC");
			ecdsaSignature.initVerify(sender);
			ecdsaSignature.update(data.getBytes());
			return ecdsaSignature.verify(signature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static final String generateHash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes());
			StringBuilder hexaString = new StringBuilder();
			for(int i = 0; i < hash.length; i++ ) {
				String hex = Integer.toHexString(hash[i] & 0xff);
				if(hex.length() == 1)hexaString.append('0');
				hexaString.append(hex);
			}
			return hexaString.toString();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
