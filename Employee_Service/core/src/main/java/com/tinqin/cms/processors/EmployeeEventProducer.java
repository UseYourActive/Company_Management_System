package com.tinqin.cms.processors;

import com.tinqin.cms.entities.EmployeeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeEventProducer {
    private KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public void sendEmployeeEvent(EmployeeEvent event) {
        kafkaTemplate.send("employee-events-topic", event);
    }
}
