package tn.esprit.R2S.resource.util;

import io.jsonwebtoken.*;
import tn.esprit.R2S.model.*;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Cookie;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evilkid on 10/20/2016.
 */
public class TokenUtil {

    public static String getToken(Users user, Key key) {
        Map<String, Object> claims = new HashMap<String, Object>();

        claims.put("cin", user.getCin().toString());

        if (user instanceof Employee) {
            claims.put("role", Roles.EMPLOYEE.toString());
        } else if (user instanceof Candidate) {
            claims.put("role", Roles.CANDIDATE.toString());
        } else if (user instanceof RecruitmentManager) {
            claims.put("role", Roles.RECRUITMENT_MANAGER.toString());
        } else if (user instanceof ChiefHumanResourcesOfficer) {
            claims.put("role", Roles.CHIEF_HUMAN_RESOURCES_OFFICER.toString());
        }

        claims.put("firstname", user.getFirstname());
        claims.put("lastname", user.getLastname());
        claims.put("exp", new Date(new Date().getTime() + 3600));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static Jws<Claims> getClaims(Cookie cookie, Key key) {
        if (cookie == null) {
            throw new NotAuthorizedException("Not token found");
        }

        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(cookie.getValue());
        } catch (JwtException je) {
            throw new NotAuthorizedException("Token error");
        }
    }

}
