package org.bg181.kotlin.support.definition.model

import java.io.Serializable

/**
 * 分页请求
 *
 * @author lxc
 * @date 2023/02/19
 */
data class PageReq(

    /**
     * 分页页码
     */
    var pageNo: Int = 1,

    /**
     * 分页大小
     */
    var pageSize: Int = 10

) : Serializable
