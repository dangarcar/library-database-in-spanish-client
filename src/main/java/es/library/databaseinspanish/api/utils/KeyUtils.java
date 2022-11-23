package es.library.databaseinspanish.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyUtils {

	private Logger logger = LogManager.getLogger();
	
	private static final String ALGORITMO = "DES";	
	private static final String ALIAS = "Llavesecreta";	
	private static final String FILE = "keys.ks";
	
	@Value("${crypto.password}") 
	private String password;
	
	private KeyStore keyStore;
	
	public synchronized static KeyUtils getInstance() {
		return StaticApplicationContext.getContext().getBean(KeyUtils.class);
	}
	
	private KeyUtils() {}
	
	public void loadKeyStore() throws KeyStoreException {
		keyStore = KeyStore.getInstance("JCEKS");
		try (InputStream fis = new FileInputStream(FILE);){
			keyStore.load(fis, password.toCharArray());
		} catch (Exception e) {
			logger.info("Se ha creado nuevo keys.ks");
			try {
				keyStore.load(null,password.toCharArray());
			} catch (NoSuchAlgorithmException | CertificateException | IOException e1) {
				logger.error("Error en la creación del guardado de llaves");
			}
		}
	}
	
	public void storeKey(SecretKey key) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		try {
			loadKeyStore();
		} catch (Exception e) {
			logger.error(e);
		}

		KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password.toCharArray());

		KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key);
		keyStore.deleteEntry(ALIAS);
		keyStore.setEntry(ALIAS, secretKeyEntry, protectionParameter);

		try (FileOutputStream fos= new FileOutputStream(FILE)){
			keyStore.store(fos, password.toCharArray());
		}
	}
	
	public SecretKey readKey() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		try {
			loadKeyStore();
		} catch (Exception e) {
			logger.error(e);
		}
		
		SecretKey key = null;
		
		try {
			key = (SecretKey) keyStore.getKey(ALIAS, password.toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			logger.error("Secret key error",e);
		}
		
		if(key == null) {
			key = generateKey();
			storeKey(key);
		}
		
		return key;
	}
	
	private static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO); 
		keyGen.init(new SecureRandom());
		return keyGen.generateKey();			
	}
}
