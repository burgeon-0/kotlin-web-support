package org.bg181.kotlin.support.definition.exception

/**
 * 业务异常
 *
 * @author lxc
 * @date 2023/02/19
 */
class BusinessException : BaseException {

    constructor(errorCode: IErrorCode) : super(errorCode)

    constructor(errorCode: IErrorCode, msg: String) : super(errorCode, msg)

    constructor(code: Int, msg: String) : super(code, msg)

}