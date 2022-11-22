package es.library.databaseinspanish.api.security.token;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.KeyUtils;
import es.library.databaseinspanish.model.security.TokenPair;

public class TokenManager {

	private Logger logger = LogManager.getLogger();
	
	private static final String TOKENS_FILEPATH = "keys.dat";
	
	private TokenPair tokenPair;
	
	private SecretKey key;
	
	private AlgorithmParameterSpec ivParameter;
	
	public static TokenManager getInstance() {
		return Lazy.INSTANCE;
	}
	
	private TokenManager() {
		try {
			var keystore = KeyUtils.getInstance();
			key = keystore.readKey();
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			logger.error("Error en la creación de la clave",e);
		}
		ivParameter = new IvParameterSpec(new byte[] {23,65,75,123,53,76,88,90});
	}
	
	private static class Lazy {
		public static TokenManager INSTANCE = new TokenManager();
	}
	
	public TokenPair getUserTokens() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if(tokenPair != null) return tokenPair;
		
		try(ObjectInputStream ois = new ObjectInputStream(new CipherInputStream(new BufferedInputStream(new FileInputStream(TOKENS_FILEPATH)), getDecryptCipher()))){
			return (TokenPair) ois.readObject();
		}
	}
	
	public void setUserTokens(TokenPair tokenPair) throws InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidAlgorithmParameterException {
		this.tokenPair = tokenPair;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(TOKENS_FILEPATH)), getEncryptCipher())))
		{
			oos.writeObject(tokenPair);
		}
	}
	
	public void deleteUserTokens() {
		File tokens = new File(TOKENS_FILEPATH);
		tokens.delete();
	}
	
	private Cipher getEncryptCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key,ivParameter);
		return cipher;
	}
	
	private Cipher getDecryptCipher() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key,ivParameter);
		return cipher;
	}
}
