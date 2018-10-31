package br.com.unisul.kafkaspringcompromisevalidator.resource;

import br.com.unisul.kafkaspringcompromisevalidator.model.Compromise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compromise")
public class CompromiseResource {


    @Autowired
    KafkaTemplate<String, Compromise> kafkaTemplate;

    private static final String TOPIC = "importing";

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<Compromise> post(@RequestBody final Compromise rawCompromise) {
        //final Compromise compromise = new Compromise();
        //compromise.setPaymentDescription(rawCompromise);

        kafkaTemplate.send(TOPIC, rawCompromise);

        return new ResponseEntity<Compromise>(rawCompromise, HttpStatus.OK);
    }
}
