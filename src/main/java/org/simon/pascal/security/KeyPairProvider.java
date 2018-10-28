/**
 * 
 */
package org.simon.pascal.security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.Security;
import java.util.Optional;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author simon.pascal.ngos
 *
 */

public class KeyPairProvider {
	private static final Logger LOGGER=LoggerFactory.getLogger(KeyPairProvider.class);
	
	 /**
	 * 
	 */
	private KeyPairProvider() {
		
	}

	/**
    *
    * @param pemPath Full path to PEM file.
    * @return returns KeyPair if successfully for PEM files.
    */
   public static Optional<KeyPair> keyPair(String pemPath) {
       // Load BouncyCastle as JCA provider
       Security.addProvider(new BouncyCastleProvider());

       // Parse the EC key pair 
       try { 
    	   LOGGER.info(pemPath);
    	   final InputStream is=KeyPairProvider.class.getResourceAsStream(pemPath);
    	   
    	   final PEMParser pemParser = new PEMParser(new InputStreamReader(is)); 
           final PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject(); 
           // Convert to Java (JCA) format
           final JcaPEMKeyConverter converter = new JcaPEMKeyConverter(); 
           final KeyPair keyPair = converter.getKeyPair(pemKeyPair); 
           pemParser.close(); 
           return Optional.of(keyPair);

       } catch (final FileNotFoundException e) {
    	   LOGGER.warn("file not found: {}", pemPath);

       } catch (final PEMException e) {
    	   LOGGER.warn("PEMException {}", e.getMessage());

       } catch (final IOException e) {
    	   LOGGER.warn("IOException {}", e.getMessage());
       }
       return Optional.empty();
   }
}
