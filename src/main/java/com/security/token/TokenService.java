package com.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.security.Constants;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class TokenService implements Constants {
    // ################################################################################################################################## // Nimbusds
    public String generateToken(String username) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(secretKey);
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
					//.issuer(issuer)
                    .subject(username)
                    .expirationTime(expiry).build();
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (Exception ex) {
            System.out.println("EXCEPTION OCCURED :: "+ex.getMessage());
        }
        return  token;
    }

    public boolean validateToken(String token) {
        boolean _isValid = false;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey);
            _isValid = signedJWT.verify(verifier);
        } catch (Exception ex) {
            System.out.println("EXCEPTION OCCURED :: "+ex.getMessage());
        }
        return _isValid;
    }
    // ################################################################################################################################## // Auth0
    public String generateAuthToken(String username) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(username)
                    .withAudience(trustedAud)
                    .withIssuedAt(issueDate)
                    .sign(algorithm);
        } catch (Exception ex) {
            System.out.println("EXCEPTION OCCURRED :: "+ex.getMessage());
        }
        return token;
    }

    public boolean validateAuthToken(String token) {
        boolean _isValid = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withAudience(trustedAud)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            _isValid = true;
        } catch (Exception ex) {
            System.out.println("EXCEPTION OCCURRED :: "+ex.getMessage());
        }
        return _isValid;
    }
}