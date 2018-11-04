package br.com.unisul.kafkaspringcompromisevalidator.resource;

import br.com.unisul.kafkaspringcompromisevalidator.model.Compromise;
import br.com.unisul.kafkaspringcompromisevalidator.service.CompromiseService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    CompromiseService compromiseService;

    private static final String TOPIC = "importing";

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<Compromise> post(@RequestBody final Compromise rawCompromise) throws Exception {

        compromiseService.validateCompromise(rawCompromise);
        compromiseService.parallelize(rawCompromise, TOPIC);

        return new ResponseEntity<>(rawCompromise, HttpStatus.OK);
    }
}
