package org.bg181.kotlin.support.definition.exception

/**
 * 错误码接口
 *
 * @author lxc
 * @date 2023/02/19
 */
interface IErrorCode {

    /**
     * 错误编码
     */
    val code: Int

    /**
     * 默认消息
     */
    val defaultMsg: String

}