package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Chronology;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadMessagesService {
    private final ChronologyService chronologyService;

    @KafkaListener(topics = "API-GATEWAY-SERVICE", groupId = "API-GATEWAY-SERVICE")
    public void listenApiGatewayService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "ASSET-SERVICE", groupId = "ASSET-SERVICE")
    public void listenAssetService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "ATTENDANCE-SERVICE", groupId = "ATTENDANCE-SERVICE")
    public void listenAttendanceService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "CHAT-SERVICE", groupId = "CHAT-SERVICE")
    public void listenChatService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "CONFIGURATION-SERVICE", groupId = "CONFIGURATION-SERVICE")
    public void listenConfigurationService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "DEPARTMENT-SERVICE", groupId = "DEPARTMENT-SERVICE")
    public void listenDepartmentService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "EMPLOYEE-SERVICE", groupId = "EMPLOYEE-SERVICE")
    public void listenEmployeeService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "LEAVE-SERVICE", groupId = "LEAVE-SERVICE")
    public void listenLeaveService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "NOTIFICATION-SERVICE", groupId = "NOTIFICATION-SERVICE")
    public void listenNotificationService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "PAYMENT-SERVICE", groupId = "PAYMENT-SERVICE")
    public void listenPaymentService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "SERVICE-REGISTRY", groupId = "SERVICE-REGISTRY")
    public void listenServiceRegistry(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "TRAINING-AND-DEVELOPMENT-SERVICE", groupId = "TRAINING-AND-DEVELOPMENT-SERVICE")
    public void listenTrainingAndDevelopmentService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    @KafkaListener(topics = "USER-AUTHENTICATION-SERVICE", groupId = "USER-AUTHENTICATION-SERVICE")
    public void listenUserAuthenticationServiceService(ConsumerRecord<String, String> record) {
        createChronology(record);
    }

    private void createChronology(ConsumerRecord<String, String> record) {
        Chronology chronology = new Chronology();
        chronology.setLog(record.value());
        chronology.setCreatedOn(Instant.ofEpochMilli(record.timestamp()).atZone(ZoneId.systemDefault()).toOffsetDateTime());

        chronologyService.saveChronology(chronology);

        log.info(chronology.getLog() + " " + chronology.getCreatedOn());
    }
}
