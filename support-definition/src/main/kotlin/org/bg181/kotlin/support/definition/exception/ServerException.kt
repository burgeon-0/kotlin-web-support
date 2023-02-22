package org.bg181.kotlin.support.definition.exception

/**
 * 服务异常
 *
 * @author lxc
 * @date 2023/02/19
 */
class ServerException : RuntimeException {

    var code: Int = 0
    override var message: String = ""

    constructor(errorCode: IErrorCode) : super(errorCode.defaultMsg) {
        this.code = errorCode.code
        this.message = errorCode.defaultMsg
    }

    constructor(errorCode: IErrorCode, cause: Throwable) : super(errorCode.defaultMsg, cause) {
        this.code = errorCode.code
        this.message = errorCode.defaultMsg
    }

    constructor(errorCode: IErrorCode, message: String) : super(message) {
        this.code = errorCode.code
        this.message = message
    }

    constructor(errorCode: IErrorCode, message: String, cause: Throwable) : super(message, cause) {
        this.code = errorCode.code
        this.message = message
    }

    constructor(code: Int, message: String) : super(message) {
        this.code = code
        this.message = message
    }

    constructor(code: Int, message: String, cause: Throwable) : super(message, cause) {
        this.code = code
        this.message = message
    }

}