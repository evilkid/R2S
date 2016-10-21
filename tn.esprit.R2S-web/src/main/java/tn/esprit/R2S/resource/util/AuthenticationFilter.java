package tn.esprit.R2S.resource.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import tn.esprit.R2S.interfaces.ITokenService;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by evilkid on 10/21/2016.
 */
@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    ITokenService tokenService;
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        requiredRoles();

        System.out.println("filtered");
        if (containerRequestContext.getCookies().containsKey("access_token")) {
            String token = containerRequestContext.getCookies().get("access_token").getValue();
            System.out.println(token);

            try {
                Jws<Claims> jws = Jwts.parser().setSigningKey(tokenService.getKey()).parseClaimsJws(token);

                System.out.println(jws);
            } catch (JwtException je) {
            }
        }

    }

    private boolean requiredRoles() {
        Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        if (secured == null) {
            secured = resourceInfo.getResourceClass().getAnnotation(Secured.class);
        }
        System.out.println(Arrays.toString(secured.value()));
        return true;
    }
}
