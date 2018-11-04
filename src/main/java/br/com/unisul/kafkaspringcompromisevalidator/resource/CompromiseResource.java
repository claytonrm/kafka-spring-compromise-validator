package br.com.unisul.kafkaspringcompromisevalidator.resource;

import br.com.unisul.kafkaspringcompromisevalidator.model.Compromise;
import br.com.unisul.kafkaspringcompromisevalidator.service.CompromiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("compromise")
public class CompromiseResource {

    @Autowired
    CompromiseService compromiseService;

    private static final String TOPIC = "importing";

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<List<Compromise>> post(@RequestBody final List<Compromise> rawCompromises) throws Exception {

        for(Compromise compromise: rawCompromises) {

            compromiseService.validateCompromise(compromise);
            compromiseService.parallelize(compromise, TOPIC);
        }

        return new ResponseEntity<>(rawCompromises, HttpStatus.OK);
    }
}
