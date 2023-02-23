package com.example.EPAC.service;

import com.example.EPAC.entity.Order;
import com.example.EPAC.entity.Part;
import com.example.EPAC.entity.Roll;
import com.example.EPAC.repository.OrderRepo;
import com.example.EPAC.repository.PartRepo;
import com.example.EPAC.repository.RollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  RollService {

    @Autowired
    private RollRepo rollRepo;
    @Autowired
    private PartRepo partRepo;
    @Autowired
    private OrderRepo orderRepo;

    public Roll addRoll(Roll r){
       return rollRepo.save(r);
    }
    public List<Roll> findAllRolls() {
        return rollRepo.findAll();
    }
    public Roll getRollById(Integer id){
        return rollRepo.findById(id).get();
    }

    public List<Float> calculate(int idRoll, int idOrder) {
        Roll roll = rollRepo.findById(idRoll).get();
        Order order = orderRepo.findById(idOrder).get();
        Part part = partRepo.findById(order.getIsbn()).get();
        List<Float> list = new ArrayList<>();


        double rollHeight = roll.getHeight();
        double rollwidth = roll.getWidth();
        float rollSize = (float) (rollHeight*rollwidth);
        
        double partHeight = part.getHeight();
        double partwidth = part.getWidth();
        double partNbPage= part.getPageNb();
        //int facteur = order.getFacteur();
        //float size = (float) (partNbPage/(facteur)*(partHeight*partwidth));
//        if(order.isDoubleSided())
//            size=size/2;
//        float pourcentage = (size/rollSize)*100;
//        list.add(pourcentage);
//        list.add(roll.getPourcentage()+pourcentage);
        return list;
    }

    public List<Float> bestCalculate(int idOrder) {
        List<Roll> list = new ArrayList<>(rollRepo.findAll());
        List<Float> listOutput = new ArrayList<>();
        Float max = 0F;
        int id = 0 ;
        for (Roll roll : list) {
            List<Float> y = calculate( roll.getRollId() ,idOrder);
            if(y.get(0)>max && y.get(1)<100) {
                max=y.get(0);
                id=roll.getRollId();
            }
        }
        listOutput.add(max);
        listOutput.add((float) id);
        return listOutput;

    }

    public List<Float> cantUse(int idOrder) {
        List<Roll> list = new ArrayList<>(rollRepo.findAll());
        List<Float> listOutput = new ArrayList<>();
        int id = 0 ;
        for (Roll roll : list) {
            List<Float> y = calculate( roll.getRollId() ,idOrder);
            if( y.get(1)>100) {
                id=roll.getRollId();
                listOutput.add((float) id);
            }
        }
        return listOutput;
    }

    public Map<Integer, Float> stats() {
         Map<Integer, Float> out=new HashMap<>();
        List<Roll> list = new ArrayList<>(rollRepo.findAll());
        for (Roll roll :list) {
            out.put(roll.getRollId(), ( roll.getPourcentage()));
        }
        return out;

    }

    public Order update(int idOrder, int rollId) {
    List <Float> list1 = new ArrayList<>();
    list1= calculate(rollId,idOrder);
    float result = (float) list1.get(1);
    Roll roll = rollRepo.findById(rollId).get();
    roll.setPourcentage(result);
    rollRepo.save(roll);
    Order order = orderRepo.findById(idOrder).get();
    order.setRollId(roll.getRollId());
    orderRepo.save(order);
    return order;
    }

    public Set<String> getType() {
        List<Roll> list = new ArrayList<>(rollRepo.findAll());
        Set <String> types = new HashSet<>();
        for (Roll roll :list) {
            types.add(roll.getType());
        }
        return types;
    }

    public List<Roll> getRollsByType(String type) {
        return rollRepo.findAllByType(type);
    }
}
