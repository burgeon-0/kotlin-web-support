package org.bg181.kotlin.support.xxl.job.config

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * XXL Job 配置
 *
 * @author lxc
 * @date 2023/03/08
 */
@Configuration
class XxlJobConfig {

    private val logger = LoggerFactory.getLogger(XxlJobConfig::class.java)

    @Value("\${xxl.job.enable:true}")
    private val enable: Boolean = true

    @Value("\${xxl.job.admin.addresses}")
    private val adminAddresses: String? = null

    @Value("\${xxl.job.executor.appname}")
    private val appname: String? = null

    @Value("\${xxl.job.executor.ip:}")
    private val ip: String? = null

    @Value("\${xxl.job.executor.port:9999}")
    private val port = 9999

    @Value("\${xxl.job.accessToken:}")
    private val accessToken: String? = null

    @Value("\${xxl.job.executor.logpath}")
    private val logPath: String? = null

    @Value("\${xxl.job.executor.logretentiondays:30}")
    private val logRetentionDays = 30

    @Bean
    fun xxlJobExecutor(): XxlJobSpringExecutor? {
        if (!enable) {
            logger.info(">>>>>>>>>>> xxl-job disabled.")
            return null
        }

        logger.info(">>>>>>>>>>> xxl-job config init.")
        logger.info(">>>>>>>>>>> adminAddresses: $adminAddresses")
        logger.info(">>>>>>>>>>> appname: $appname")
        logger.info(">>>>>>>>>>> ip: $ip")
        logger.info(">>>>>>>>>>> port: $port")
        logger.info(">>>>>>>>>>> accessToken: $accessToken")
        logger.info(">>>>>>>>>>> logPath: $logPath")
        logger.info(">>>>>>>>>>> logRetentionDays: $logRetentionDays")

        val xxlJobSpringExecutor = XxlJobSpringExecutor()
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses)
        xxlJobSpringExecutor.setAppname(appname)
        xxlJobSpringExecutor.setIp(ip)
        xxlJobSpringExecutor.setPort(port)
        xxlJobSpringExecutor.setAccessToken(accessToken)
        xxlJobSpringExecutor.setLogPath(logPath)
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays)

        logger.info(">>>>>>>>>>> xxl-job config init completed.")
        return xxlJobSpringExecutor
    }

}