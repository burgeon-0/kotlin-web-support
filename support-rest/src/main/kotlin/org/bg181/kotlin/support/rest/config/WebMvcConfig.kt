package org.bg181.kotlin.support.rest.config

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

/**
 * Spring MVC 配置
 *
 * @author lxc
 * @date 2023/02/19
 */
@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        val jackson2HttpMessageConverter = MappingJackson2HttpMessageConverter()
        converters.add(0, jackson2HttpMessageConverter)

        val objectMapper = jackson2HttpMessageConverter.objectMapper
        // 返回值排除 null 值
        // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        // 返回值：Long/long 转 String，Date 转 long
        // 为啥要 Long/long 转 String，原因是 long 类型如果以数字形式返回给前端，前端会发生溢出，导致精度丢失
        // 为啥要 Date 转 long，如果以字符串形式把日期返回给前端，那么前端需要跟后端对齐日期格式，返回时间戳则避免了这些麻烦
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(Long::class.java, ToStringSerializer.instance)
        simpleModule.addSerializer(java.lang.Long.TYPE, ToStringSerializer.instance)
        simpleModule.addSerializer(Date::class.java, DateSerializer.instance)
        objectMapper.registerModule(simpleModule)
    }

}