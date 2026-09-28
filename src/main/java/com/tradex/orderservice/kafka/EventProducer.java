package com.tradex.orderservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tradex.orderservice.exception.KafkaEventPublishException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, Object> template;

    public void publishEvent(String topic, String key, Object event) {
        try {
            template.send(topic, key, event)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Failed to publish Kafka event. topic={}, key={}", topic, key, ex);
                            return;
                        }
                        log.info("Kafka event published successfully. topic={}, partition={}, offset={}, key={}",
                                result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset(), key);
                    });
        } catch (Exception ex) {
            log.error("Failed to initiate Kafka event publishing. topic={}, key={}", topic, key, ex);
            throw new KafkaEventPublishException(
                    "Failed to initiate Kafka event publishing. topic=" + topic + ", key=" + key, ex);
        }
    }
}
