package org.bg181.kotlin.support.rest.exception

import jakarta.servlet.http.HttpServletRequest
import org.bg181.kotlin.support.definition.exception.BaseErrorCode
import org.bg181.kotlin.support.definition.exception.BaseException
import org.bg181.kotlin.support.definition.exception.BusinessException
import org.bg181.kotlin.support.definition.exception.ServerException
import org.bg181.kotlin.support.definition.model.Resp
import org.slf4j.LoggerFactory
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException

/**
 * 统一异常处理
 *
 * @author lxc
 * @date 2023/02/19
 */
@RestControllerAdvice
class ExceptionControllerAdvice {

    private val logger = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    @Value("\${kotlin.web.support.rest.exception.logDetails:true}")
    private var logDetails = true

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        request: HttpServletRequest,
        ex: HttpRequestMethodNotSupportedException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.METHOD_NOT_ALLOWED.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleHttpMediaTypeNotSupportedException(
        request: HttpServletRequest,
        ex: HttpMediaTypeNotSupportedException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.UNSUPPORTED_MEDIA_TYPE.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    fun handleHttpMediaTypeNotAcceptableException(
        request: HttpServletRequest,
        ex: HttpMediaTypeNotAcceptableException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.NOT_ACCEPTABLE.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MissingPathVariableException::class)
    fun handleMissingPathVariableException(
        request: HttpServletRequest,
        ex: MissingPathVariableException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.INTERNAL_SERVER_ERROR.code, ex.message)
        logServerError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(
        request: HttpServletRequest,
        ex: MissingServletRequestParameterException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletRequestBindingException::class)
    fun handleServletRequestBindingException(
        request: HttpServletRequest,
        ex: ServletRequestBindingException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConversionNotSupportedException::class)
    fun handleConversionNotSupportedException(
        request: HttpServletRequest,
        ex: ConversionNotSupportedException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.INTERNAL_SERVER_ERROR.code, ex.message)
        logServerError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException::class)
    fun handleTypeMismatchException(
        request: HttpServletRequest,
        ex: TypeMismatchException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        request: HttpServletRequest,
        ex: HttpMessageNotReadableException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotWritableException::class)
    fun handleHttpMessageNotWritableException(
        request: HttpServletRequest,
        ex: HttpMessageNotWritableException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.INTERNAL_SERVER_ERROR.code, ex.message)
        logServerError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        request: HttpServletRequest,
        ex: MethodArgumentNotValidException
    ): Resp<Unit> {
        val msg = ex.fieldErrors.map { "${it.field} ${it.defaultMessage}" }.joinToString(", ")
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, msg)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException::class)
    fun handleMissingServletRequestPartException(
        request: HttpServletRequest,
        ex: MissingServletRequestPartException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun handleBindException(
        request: HttpServletRequest,
        ex: BindException
    ): Resp<Unit> {
        val msg = ex.fieldErrors.map { "${it.field} ${it.defaultMessage}" }.joinToString(", ")
        val resp = Resp<Unit>(BaseErrorCode.BAD_REQUEST.code, msg)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(
        request: HttpServletRequest,
        ex: NoHandlerFoundException
    ): Resp<Unit> {
        val resp = Resp<Unit>(BaseErrorCode.NOT_FOUND.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerException::class)
    fun handleServerException(
        request: HttpServletRequest,
        ex: ServerException
    ): Resp<Unit> {
        val resp = Resp<Unit>(ex.code, ex.message)
        logServerError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        request: HttpServletRequest,
        ex: BaseException
    ): Resp<Unit> {
        val resp = Resp<Unit>(ex.code, ex.message)
        logBusinessError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(
        request: HttpServletRequest,
        ex: BaseException
    ): Resp<Unit> {
        val resp = Resp<Unit>(ex.code, ex.message)
        logClientError(request, resp, ex)
        return resp
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleUnkonwnException(
        request: HttpServletRequest,
        ex: Throwable
    ): Resp<Unit> {
        val resp = Resp<Unit>(
            BaseErrorCode.INTERNAL_SERVER_ERROR.code,
            BaseErrorCode.INTERNAL_SERVER_ERROR.defaultMsg
        )
        logServerError(request, resp, ex)
        return resp
    }

    private fun logClientError(request: HttpServletRequest, resp: Resp<Unit>, ex: Throwable) {
        when (logDetails) {
            true -> logger.warn(
                "An client error occurred => uri: $request.requestURI, response: ${resp.toJsonString()}",
                ex
            )
            else -> logger.warn(
                "An client error occurred => uri: $request.requestURI, response: ${resp.toJsonString()}"
            )
        }
    }

    private fun logBusinessError(request: HttpServletRequest, resp: Resp<Unit>, ex: Throwable) {
        when (logDetails) {
            true -> logger.warn(
                "An business error occurred => uri: $request.requestURI, response: ${resp.toJsonString()}",
                ex
            )
            else -> logger.warn(
                "An business error occurred => uri: $request.requestURI, response: ${resp.toJsonString()}"
            )
        }
    }

    private fun logServerError(request: HttpServletRequest, resp: Resp<Unit>, ex: Throwable) {
        logger.error(
            "An server error occurred => uri: $request.requestURI, response: ${resp.toJsonString()}", ex
        )
    }

}