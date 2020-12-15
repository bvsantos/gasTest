package pt.unl.fct.tourtuga.gas.security

import io.jsonwebtoken.Jwts

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.json.JSONObject
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager): UsernamePasswordAuthenticationFilter() {
    val authM: AuthenticationManager = authenticationManager;

    init {
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }


    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        var jsonString: String? = null;
        var json : JSONObject? = null;
        try {
            if (request != null) {
                jsonString = request.getReader().lines().collect(Collectors.joining())
                json = JSONObject(jsonString);
            };
        }catch (e: IOException){
            e.printStackTrace();
        }
        if (json != null) {
            if (json.has("username") && json.has("password")) {
                val authenticationToken:UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(json.getString("username"), json.get("password"));
                return this.authM.authenticate(authenticationToken);
            }
        }

        return this.authM.authenticate(UsernamePasswordAuthenticationToken(null, null));
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        var user:MyUserDetails = authResult?.getPrincipal() as MyUserDetails;
        var roles: List<String>? = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        var signingKey:ByteArray = SecurityConstants.JWT_SECRET.toByteArray();

        val token:String = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(signingKey))
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact();
        response?.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token)
    }

}


