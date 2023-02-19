package org.bg181.kotlin.support.definition.exception

/**
 * 服务异常
 *
 * @author lxc
 * @date 2023/02/19
 */
class ServerException : BaseException {

    constructor(errorCode: IErrorCode) : super(errorCode)

    constructor(errorCode: IErrorCode, cause: Throwable) : super(errorCode, cause)

    constructor(errorCode: IErrorCode, msg: String) : super(errorCode, msg)

    constructor(errorCode: IErrorCode, msg: String, cause: Throwable) : super(errorCode, msg, cause)

    constructor(code: Int, msg: String) : super(code, msg)

    constructor(code: Int, msg: String, cause: Throwable) : super(code, msg, cause)

}