/**
 * Çlass that will code and decode the tokens for the users on the site.
 *
 * @author Jasper Fernhout
 */
package app.jwt;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


public class JWToken {
    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_USERID_CLAIM = "id";
    private static final String JWT_ROLE_CLAIM = "role";
    public static final String JWT_ATTRIBUTE_NAME = "JWTokenInfo";
    private String callName;
    private Long userId;
    private String role;

    /**
     * Constructs a JWToken object with user information.
     *
     * @param callName - The username or call name associated with the token.
     * @param userId   - The unique identifier for the user.
     * @param role     - The role or permissions assigned to the user.
     * @author Jasper Fernhout
     */
    public JWToken(String callName, Long userId, String role) {
        this.callName = callName;
        this.userId = userId;
        this.role = role;
    }

    /**
     * Encodes the token with provided issuer, passphrase, and expiration time.
     *
     * @param issuer     - The issuer of the token.
     * @param passphrase - The passphrase used for encoding.
     * @param expiration - The expiration time of the token in seconds.
     * @return String - The encoded token as a string.
     * @author Jasper Fernhout
     */
    public String encode(String issuer, String passphrase, int expiration) {
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.callName)
                .claim(JWT_USERID_CLAIM, this.userId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Decodes the provided token using the passphrase.
     *
     * @param token      - The token to be decoded.
     * @param passphrase - The passphrase used for decoding.
     * @return JWToken - The JWToken object extracted from the token.
     * @throws ExpiredJwtException   - If the token has expired.
     * @throws MalformedJwtException - If the token is malformed.
     * @author Jasper Fernhout
     */
    public static JWToken decode(String token, String passphrase)
            throws ExpiredJwtException, MalformedJwtException {
        Key key = getKey(passphrase);
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
        Claims claims = jws.getBody();

        return new JWToken(
                claims.get(JWT_CALLNAME_CLAIM).toString(),
                Long.valueOf(claims.get(JWT_USERID_CLAIM).toString()),
                claims.get(JWT_ROLE_CLAIM).toString()
        );
    }

    /**
     * Retrieves the cryptographic key based on the provided passphrase.
     *
     * @param passphrase - The passphrase used to generate the cryptographic key.
     * @return Key - The cryptographic key for encoding and decoding tokens.
     * @author Jasper Fernhout
     */
    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCallName() {
        return callName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }
}
