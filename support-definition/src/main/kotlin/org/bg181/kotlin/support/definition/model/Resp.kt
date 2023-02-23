package org.bg181.kotlin.support.definition.model

import java.io.Serializable

/**
 * 通用返回
 *
 * @author lxc
 * @date 2023/02/19
 */
data class Resp<T>(

    /**
     * 返回编码
     */
    var code: Int? = 200,

    /**
     * 返回消息
     */
    var message: String? = "ok",

    /**
     * 返回数据
     */
    var data: T? = null

) : Serializable {

    fun toJsonString(): String {
        return when (data) {
            null -> "{\"code\": $code, \"message\": \"$message\"}"
            else -> "{\"code\": $code, \"message\": \"$message\", \"data\": \"$data\"}"
        }
    }

}
