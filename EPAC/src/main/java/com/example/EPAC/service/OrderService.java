package com.example.EPAC.service;

import com.example.EPAC.entity.Order;
import com.example.EPAC.entity.Part;
import com.example.EPAC.entity.Roll;
import com.example.EPAC.repository.OrderRepo;
import com.example.EPAC.repository.PartRepo;
import com.example.EPAC.repository.RollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PartRepo partRepo;
    @Autowired
    private RollRepo rollRepo;



    public Order addOrder(Order o){
        Part test = partRepo.findById(o.getIsbn()).get();
        test.setPrinted(true);

        partRepo.save(test);
        return orderRepo.save(o);
    }
    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }
    public Order getOrderById(Integer id){
        return orderRepo.findById(id).get();
    }


    public Order attributeRoll(int idRoll, Order order) {
        Order order1 = orderRepo.findById(order.getOrderId()).get();
        order1.setRollId((idRoll));
        return orderRepo.save(order1);

    }

    public List<Order> getPrinting() {
        List <Integer>ids = new ArrayList<>();
        List <Integer>idsPrinted = new ArrayList<>();
        List<Order> orders = new ArrayList<>(orderRepo.findAll());
        for (Order order:orders) {
            ids.add(order.getIsbn());
        }
        //orders.clear();
        for (Integer id : ids){
            Part p = partRepo.findById(id).get();
            if (p.isPrinted()) {
                idsPrinted.add(p.getIsbn());

            }
        }
        for(Integer id : idsPrinted) {
            orders.add(orderRepo.findOrdersByIsbn(id));
        }
        return orders;
    }
    public void getBestCombination(String type){
       List<Order> orders=orderRepo.findAll();
       List<Roll> rolls=rollRepo.findAllByType(type);
       for( Order o:orders){

       }



    }
}