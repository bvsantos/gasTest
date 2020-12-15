package pt.unl.fct.tourtuga.gas.security

import io.jsonwebtoken.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(authenticationManager: AuthenticationManager): BasicAuthenticationFilter(authenticationManager) {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authentication:UsernamePasswordAuthenticationToken? = getAuthentication(request as HttpServletRequest?);
        val header:String? = request?.getHeader(SecurityConstants.TOKEN_HEADER);

        if (header == null|| !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain?.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain?.doFilter(request, response);
    }


    private fun getAuthentication(request: HttpServletRequest?):UsernamePasswordAuthenticationToken?{
        var token:String? = request?.getHeader(SecurityConstants.TOKEN_HEADER);
        if(token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            try{
                var signingKey:ByteArray = SecurityConstants.JWT_SECRET.toByteArray();
                var parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));
                var claims: Claims = parsedToken.getBody();
                var username:String = claims.getSubject();
                var collection:Collection<*> = claims.get("rol") as Collection<*>;
                var authorities:List<SimpleGrantedAuthority> = collection.stream()
                        .map { authority -> SimpleGrantedAuthority(authority as String)}.collect(Collectors.toList())
                if(username.isNotEmpty())
                    return UsernamePasswordAuthenticationToken(username, null, authorities);
            }catch (exception: ExpiredJwtException){
                println("Expired Jwt Exception "+exception.message);
            }catch (exception: UnsupportedJwtException) {
                println("Request to parse unsupported JWT : "+token+" failed : "+exception.message);
            } catch (exception:MalformedJwtException) {
                println("Request to parse invalid JWT : "+token+" failed : "+exception.message);
            } catch (exception:SignatureException) {
                println("Request to parse JWT with invalid signature : "+token+" failed :"+exception.message);
            } catch (exception:IllegalArgumentException) {
                println("Request to parse empty or null JWT : "+token+" failed :"+exception.message);
            }
        }
        return null;
    }
}