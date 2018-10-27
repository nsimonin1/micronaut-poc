/**
 * 
 */
package org.simon.pascal.security;

import java.util.ArrayList;

import javax.inject.Singleton;

import org.reactivestreams.Publisher;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;

/**
 * @author simon.pascal.ngos
 *
 */
@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

	 
	@Override
	public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
		if(authenticationRequest.getIdentity().equals("simonpascal")
				&& authenticationRequest.getSecret().equals("hello")) {
			return Flowable.just(new UserDetails((String)authenticationRequest.getIdentity()
					, new ArrayList<>()));
		}
		return Flowable.just(new AuthenticationFailed());
	}

}
