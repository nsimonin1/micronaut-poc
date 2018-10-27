/**
 * 
 */
package org.simon.pascal.security;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;

import io.micronaut.context.annotation.Value;
import io.micronaut.security.token.jwt.encryption.rsa.RSAEncryptionConfiguration;

/**
 * @author simon.pascal.ngos
 *
 */
@Named("generator")
@Singleton 
public class RSAOAEPEncryptionConfiguration implements RSAEncryptionConfiguration{
	private final Logger log=LoggerFactory.getLogger(getClass());
    private RSAPrivateKey rsaPrivateKey;
    private RSAPublicKey rsaPublicKey;
    private final JWEAlgorithm jweAlgorithm = JWEAlgorithm.RSA_OAEP_256;
    private final EncryptionMethod encryptionMethod = EncryptionMethod.A128GCM;

    public RSAOAEPEncryptionConfiguration(@Value("${pem.path}") String pemPath) {
    	log.debug("Contructeur de RSA");
    	
        final Optional<KeyPair> keyPair = KeyPairProvider.keyPair(pemPath);
        if (keyPair.isPresent()) {
        	log.debug("keyPair est present");
            rsaPublicKey = (RSAPublicKey) keyPair.get().getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.get().getPrivate();
        }
    }

    @Override
    public RSAPublicKey getPublicKey() {
        return rsaPublicKey;
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return rsaPrivateKey;
    }

    @Override
    public JWEAlgorithm getJweAlgorithm() {
        return jweAlgorithm;
    }

    @Override
    public EncryptionMethod getEncryptionMethod() {
        return encryptionMethod;
    }

}
