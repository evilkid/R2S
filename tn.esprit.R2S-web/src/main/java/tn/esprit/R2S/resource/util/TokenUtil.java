package tn.esprit.R2S.resource.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.R2S.model.*;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evilkid on 10/20/2016.
 */
public class TokenUtil {

    public static String getToken(Users user, Key key) {
        Map<String, Object> claims = new HashMap<String, Object>();


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

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
