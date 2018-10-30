package br.com.unisul.kafkaspringcompromisevalidator.resource;

import br.com.unisul.kafkaspringcompromisevalidator.model.Compromise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class CompromiseResource {

    private static final String TOPIC = "importing";

    @Autowired
    private KafkaTemplate<String, Compromise> kafkaTemplate;

    @GetMapping("/publish/{compromise}")
    public String post(@PathVariable("compromise") final String rawCompromise) {

        final Compromise compromise = new Compromise();
        compromise.setPaymentDescription(rawCompromise);

        kafkaTemplate.send(TOPIC, compromise);

        return "Compromise published sucessfully!";
    }
}
