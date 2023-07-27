package com.service.lestplanit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    /**
     * Generate an access token (JWT) for the specified username.
     *
     * @param username The username for whom the token will be generated.
     * @return The generated access token (JWT).
     */
    public String generateAcessToken(String username){
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, keyBytes)
                .compact();

        return token;
    }

    /**
     * Check if the given token is valid (not expired and correctly signed).
     *
     * @param token The token to be validated.
     * @return True if the token is valid, otherwise false.
     */
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            System.out.println("There was an error related to validate the token");
            return false;
        }
    }

    /**
     * Extract the username from the given token.
     *
     * @param token The token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Extract a claim from the given token using a claims function.
     *
     * @param token The token from which to extract the claim.
     * @param claimsTFunction The function to extract the desired claim from the token's claims.
     * @param <T> The type of the claim to be extracted.
     * @return The extracted claim.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * Extract all claims from the given token.
     *
     * @param token The token from which to extract the claims.
     * @return The claims extracted from the token.
     */
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    /**
     * Get the signature key used to verify the token's signature.
     *
     * @return The signature key.
     */
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
