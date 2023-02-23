package com.example.EPAC.controller;

import com.example.EPAC.entity.Order;
import com.example.EPAC.entity.Roll;
import com.example.EPAC.service.RollService;
import com.example.EPAC.service.SequenceGeneratorRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.EPAC.entity.Roll.SEQUENCE_NAME;

@RestController
@CrossOrigin
@RequestMapping("/rolls")
public class RollController {
    @Autowired
    private RollService service;
    @Autowired
    private SequenceGeneratorRollService sequence;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Roll createRoll(@RequestBody Roll roll) {
        roll.setRollId(sequence.getSequenceNumber(SEQUENCE_NAME));
        return service.addRoll(roll);
    }

    @GetMapping
    public List<Roll> getRolls() {
        return service.findAllRolls();
    }

    @GetMapping("/{id}")
    public Roll getTask(@PathVariable Integer id) {
        return service.getRollById(id);
    }
    @GetMapping("/calculate")
    public List<Float> calculate(@RequestParam int idRoll ,@RequestParam int idOrder) {
        return service.calculate (idRoll,idOrder) ;
    }
    @GetMapping("/best")
    public List<Float> bestCalculate(@RequestParam int idOrder) {
        return  service.bestCalculate(idOrder);
    }
    @GetMapping("/cantUse")
    public List<Float> cantUse(@RequestParam int idOrder) {
        return  service.cantUse(idOrder);
    }

    @GetMapping("/stats")
    public Map<Integer, Float> stats() {
        return service.stats();
    }
    @PostMapping("/update")
    public Order update(@RequestParam int idOrder, @RequestParam int rollId) {
     return service.update(idOrder,rollId);
    }
    @GetMapping("/get")
    public Set<String> getType() {
        return service.getType();
    }
    @GetMapping("/get/{type}")
    public  List<Roll> getRollsByType(@PathVariable String type) {
        return service.getRollsByType(type);
    }
}
