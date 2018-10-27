/**
 * 
 */
package org.simon.pascal.controller;

import java.security.Principal;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.UsernamePasswordCredentials;

/**
 * @author simon.pascal.ngos
 *
 */
@Secured("isAuthenticated()")
@Controller("/")
public class HomeController {
	@Get("/")
	String index(Principal principal) { 
		return principal.getName();
	}
}
