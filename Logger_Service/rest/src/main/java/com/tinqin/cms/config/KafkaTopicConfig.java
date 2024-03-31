package com.tinqin.cms.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic apiGatewayServiceTopic() {
        return TopicBuilder.name("API-GATEWAY-SERVICE").build();
    }

    @Bean
    public NewTopic assetServiceTopic() {
        return TopicBuilder.name("ASSET-SERVICE").build();
    }

    @Bean
    public NewTopic attendanceServiceTopic() {
        return TopicBuilder.name("ATTENDANCE-SERVICE").build();
    }

    @Bean
    public NewTopic chatServiceTopic() {
        return TopicBuilder.name("CHAT-SERVICE").build();
    }

    @Bean
    public NewTopic configurationServerTopic() {
        return TopicBuilder.name("CONFIGURATION-SERVICE").build();
    }

    @Bean
    public NewTopic departmentServiceTopic() {
        return TopicBuilder.name("DEPARTMENT-SERVICE").build();
    }

    @Bean
    public NewTopic employeeServiceTopic() {
        return TopicBuilder.name("EMPLOYEE-SERVICE").build();
    }

    @Bean
    public NewTopic leaveServiceTopic() {
        return TopicBuilder.name("LEAVE-SERVICE").build();
    }

    @Bean
    public NewTopic notificationServiceTopic() {
        return TopicBuilder.name("NOTIFICATION-SERVICE").build();
    }

    @Bean
    public NewTopic paymentServiceTopic() {
        return TopicBuilder.name("PAYMENT-SERVICE").build();
    }

    @Bean
    public NewTopic serviceRegistryTopic() {
        return TopicBuilder.name("SERVICE-REGISTRY").build();
    }

    @Bean
    public NewTopic trainingAndDevelopmentServiceTopic() {
        return TopicBuilder.name("TRAINING-AND-DEVELOPMENT-SERVICE").build();
    }

    @Bean
    public NewTopic userAuthenticationServiceTopic() {
        return TopicBuilder.name("USER-AUTHENTICATION-SERVICE").build();
    }
}
