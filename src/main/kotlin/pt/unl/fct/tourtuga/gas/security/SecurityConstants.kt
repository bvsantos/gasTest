package pt.unl.fct.tourtuga.gas.security

import java.lang.IllegalStateException

class SecurityConstants {
    companion object {

        const val AUTH_LOGIN_URL = "/login";

        // Signing key for HS512 algorithm
        const val JWT_SECRET = "fL_eb3LiNeErf5Xnbwtly6md4nIK5Jo6xckynt8_7nlTQzw6ypkep-sDTYBAwHyKA9_qAeuBynrwxtmTnsrBYw";

        // JWT token defaults
        const val TOKEN_HEADER ="Authorization";
        const val TOKEN_PREFIX = "Bearer";
        const val TOKEN_TYPE = "JWT";
        const val TOKEN_ISSUER = "secure-api";
        const val TOKEN_AUDIENCE = "secure-app";
    }
    constructor() {
        throw IllegalStateException("Cannot create instance of static util class") ;
    }
}