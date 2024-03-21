package repository;

import models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {

    private Map<Long,Gate> gates= new TreeMap<>(); // this is going to acts as gate table

    public Optional<Gate> findGateById(Long gateId){
        //db.execute(select * from gates where id=11)
        if(gates.containsValue(gateId)){
            return Optional.of(gates.get(gateId));
        }
        return Optional.empty();

    }
}

//In-memory
//List
//Hashmap<id,Gate>
