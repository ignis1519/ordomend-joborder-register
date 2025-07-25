package cl.ignis.ordomend.joborder.register.controller;

import cl.ignis.ordomend.joborder.register.service.MessagePublisher;
import cl.ignis.ordomend.joborder.register.vo.JobOrderVO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/joborder")
public class JobOrderController {

    private final MessagePublisher messagePublisher;

    public JobOrderController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/register")
    public ResponseEntity<String> recibirOrden(@Valid @RequestBody JobOrderVO ordenTrabajo) {
        messagePublisher.publish(ordenTrabajo);
        return ResponseEntity.accepted().body("Orden recibida y enviada a RabbitMQ");
    }

    // Handle validation errors and return readable messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
