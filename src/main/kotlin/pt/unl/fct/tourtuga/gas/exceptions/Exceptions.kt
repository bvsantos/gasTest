package pt.unl.fct.tourtuga.gas.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


@ResponseStatus(HttpStatus.NOT_FOUND)
class HTTPNotFoundException(e: String) : RuntimeException(e)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class HTTPBadRequestException(e: String) : Exception(e)

@ResponseStatus(HttpStatus.FORBIDDEN)
class HTTPPermissionDenied(e: String) : Exception(e)

@ResponseStatus(HttpStatus.CONFLICT)
class HTTPConflictException(e: String) : Exception(e)




