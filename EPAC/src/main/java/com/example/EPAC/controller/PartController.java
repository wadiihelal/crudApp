package com.example.EPAC.controller;

import com.example.EPAC.entity.Order;
import com.example.EPAC.entity.Part;
import com.example.EPAC.service.PartService;
import com.example.EPAC.service.SequenceGeneratorRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.EPAC.entity.Roll.SEQUENCE_NAME;

@RestController
@CrossOrigin
@RequestMapping("/parts")
public class PartController {
    @Autowired
    private PartService service;
    @Autowired
    private SequenceGeneratorRollService sequence;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part createPart(@RequestBody Part part) {
       // part.setId(sequence.getSequenceNumber(SEQUENCE_NAME));
        return service.addPart(part);
    }

    @GetMapping
    public List<Part> getParts() {
        return service.findAllParts();
    }

    @GetMapping("/{id}")
    public Part getTask(@PathVariable Integer id) {
        return service.getPartById(id);

    }
    @GetMapping("find/{isbn}")
    public Part findPart(@PathVariable int isbn){
        return service.findByIsbn(isbn);
    }

    @GetMapping("/isbn")
    public List<Integer> findIsbn () {
        return service.findIsbn();
    }
    @PostMapping("/affectBookToOrder/{isbn}/{idOrder}")
    public Order affectBook( @PathVariable int isbn ,@PathVariable int idOrder)
    {
        return service.assignBookToOrdre(isbn,idOrder);
    }

}
