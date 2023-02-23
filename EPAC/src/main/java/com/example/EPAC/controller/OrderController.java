package com.example.EPAC.controller;

import com.example.EPAC.entity.Order;
import com.example.EPAC.service.OrderService;
import com.example.EPAC.service.SequenceGeneratorOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.EPAC.entity.Roll.SEQUENCE_NAME;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private SequenceGeneratorOrderService sequence;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order) {
        order.setOrderId(sequence.getSequenceNumber(SEQUENCE_NAME));
        return service.addOrder(order);
    }

    @GetMapping
    public List<Order> getRolls() {
        return service.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order getTask(@PathVariable Integer id) {
        return service.getOrderById(id);

    }

    //under test fct

    @PostMapping("/gets/{idRoll}")
    public Order attributeRoll(@PathVariable int idRoll , @RequestBody Order order ) {
        return service.attributeRoll(idRoll,order);
    }
    @GetMapping("/get/printing")
    public List<Order> getPrinting() {
        return service.getPrinting();
    }



}
