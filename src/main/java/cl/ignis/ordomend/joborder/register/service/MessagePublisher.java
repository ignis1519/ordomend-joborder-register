package cl.ignis.ordomend.joborder.register.service;

import cl.ignis.ordomend.joborder.register.vo.JobOrderVO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public MessagePublisher(RabbitTemplate rabbitTemplate,
                            @Value("${spring.rabbitmq.template.default-receive-queue}") String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void publish(JobOrderVO ordenTrabajo) {
        MessageProperties props = new MessageProperties();
        props.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        props.setExpiration("60000"); // TTL: 1 minute in ms

        MessageConverter converter = rabbitTemplate.getMessageConverter();
        Message message = converter.toMessage(ordenTrabajo, props);

        rabbitTemplate.send(queueName, message);
    }
}
