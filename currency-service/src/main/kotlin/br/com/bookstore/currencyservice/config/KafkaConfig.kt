package br.com.bookstore.currencyservice.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig (
    @Value("\${kafka.bootstrapAddress}")
    private val servers: String,
    @Value("\${kafka.topics.currency-update}")
    private val currencyUpdateTopic: String){

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = mutableMapOf<String, Any>(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to servers)
        return KafkaAdmin(configs)
    }

    @Bean
    fun currencyUpdateTopic(): NewTopic
        = NewTopic(currencyUpdateTopic, 1, 1.toShort())

}