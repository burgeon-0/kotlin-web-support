package org.bg181.kotlin.support.definition.exception

/**
 * 错误编码定义
 *
 * @author lxc
 * @date 2023/02/19
 */
enum class BaseErrorCode(code: Int, defaultMsg: String) : IErrorCode {

    OK(200, "ok"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    NOT_ACCEPTABLE(406, "not acceptable"),
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported media type"),
    INTERNAL_SERVER_ERROR(500, "internal server error");

    override val code: Int = code
    override val defaultMsg: String = defaultMsg

}