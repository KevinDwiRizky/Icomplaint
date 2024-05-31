package org.kevin.utils;


import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@RequestScoped
public class JwtUtils {

    @Inject
    JsonWebToken jwt;

    @Context
    SecurityContext securityContext;

    public Long getUserIdFromToken() {
        if (jwt == null) {
            return null;
        }
        return Long.parseLong(jwt.getSubject());
    }

    public String getEmailFromToken() {
        if (jwt == null) {
            return null;
        }
        return jwt.getClaim("email");
    }

    public boolean isAdmin() {
        if (securityContext == null || !securityContext.isUserInRole("ADMIN")) {
            return false;
        }
        return true;
    }
}

