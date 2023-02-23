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
public class PartService {

    @Autowired
    private PartRepo partRepo;
    @Autowired
    private OrderRepo orderRepo;

    public Part addPart(Part p){
        return partRepo.save(p);
    }
    public List<Part> findAllParts() {
        return partRepo.findAll();
    }
    public Part getPartById(Integer id){
        return partRepo.findById(id).get();
    }
    public Part findByIsbn(int isbn) {
        return partRepo.findPartsByIsbn(isbn);
    }

    public List<Integer> findIsbn() {
        List<Part> list = findAllParts();
        List<Integer> out = new ArrayList<>();
        for (Part part : list){
                out.add(part.getIsbn());
        }
        return out;
    }
    public Order assignBookToOrdre(int isbn,int OrderId){
        List<Part> partsList=new ArrayList<>();
        Part part=partRepo.findPartsByIsbn(isbn);
        Order order = orderRepo.findById(OrderId).get();
        if ( order.getListParts()==null){
            partsList.add(part);
            order.setListParts(partsList);
        }
         else    {
             partsList.addAll(order.getListParts());
             partsList.add(part);
             order.setListParts(partsList);
         }
            return orderRepo.save(order);
    }

}
