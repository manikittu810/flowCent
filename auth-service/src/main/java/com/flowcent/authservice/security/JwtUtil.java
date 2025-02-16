package com.flowcent.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "flowcentsupersecretkeyflowcentsupersecretkey";//change in prod
    private static final long EXPIRATION_TIME = 1000*60*60; //1 hour

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, Set<String> roles){
        return Jwts.builder()
                .setSubject(username) //this sets for whom the token belongs to example -> setSubject(mani); {"sub" : "mani" }
                .claim("roles",roles) // this stores users roles inside a token like {"sub": "mani","roles" : ["STUDENT","ADMIN"]}
                .setIssuedAt(new Date()) // this says when was the
                // token created, creates time stamp of a token,token looks like {"sub": "mani","roles" : ["STUDENT","ADMIN"],"iat": 1712323200,}
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)) // this gives us the expiration time ,
                /* that is current time in milli second + expiration time ,
                that is 2000ms +1 hour => it expires in 1 hour 20 seconds,
                token looks like
                {
                "sub": "mani",
                "roles" : ["STUDENT","ADMIN"],
                "iat": 1712323200,
                "exp": 1712326800
                  }
                  */
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //this signs the token with a secret key (getSigningKey)
                /*
                the above prevents the hacker from creating fake tokens,
                 signature is generated using the HS256 (HMAC SHA-256) algorithm.
                {
                  "signature": "jJojlskJL3fslsKJJsl213kl..."
                }
                 this is how Jwt token is generated as Jwt Signature
                if someone modifies the token , signature won't match and API rejects the token */
                .compact();
                /*
                compact creates the final JWT token as a compact string.
                final JWT token looks like  :
                eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKb2huRG9lIiwicm9sZXMiOlsiU1RVREVOVCIsIkFETUlOI
                l0sImlhdCI6MTcxMjMyMzIwMCwiZXhwIjoxNzEyMzI2ODAwfQ.jJojlskJL3fslsKJJsl213kl...
                Think of it as: The final printed library/student pass 🏷️ that the student
                will show at the entrance of college or library.

                * */
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()//
                // Jwts.parserBuilder() is Token reader ->Creates a parser that will decode the Jwt Token
                .setSigningKey(getSigningKey())//This ensures that the token was signed with the correct secret key.
                .build()//This finalizes the parser so that it is ready to decode the JWT token.
                .parseClaimsJws(token)
                //This parses (decodes) the token, if the token was changed or is expired, it will throw an error.
                .getBody();
        /* for .getBody()
        * This extracts the claims (information inside the token), This includes the username, roles, issued date, expiration date.
        *
        * {
              "sub": "JohnDoe",
              "roles": ["STUDENT", "ADMIN"],
              "iat": 1712323200,
              "exp": 1712326800
            }
        This is the decoded / decrypted token
        * */



                /*
                * This function takes a JWT token and extracts the information inside it (username, roles, expiration date).
                * It verifies the signature using the secret key (getSigningKey()).
                * If the token is valid, it returns the claims (decoded information).
                * */
    }

    public boolean validateToken(String token){
        try{
            extractClaims(token); //validate the token
            return true; // if successful, token is valid
        }catch(Exception e){
            return false;// If an error occurs, the token is in Valid
        }
    }
}
