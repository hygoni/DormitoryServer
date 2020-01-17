package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.advice.exception.DuplicateObjectException;
import com.github.hygoni.dormitory.advice.exception.ProtectedObjectException;
import com.github.hygoni.dormitory.advice.exception.WasherNotFoundException;
import com.github.hygoni.dormitory.model.Washer;
import com.github.hygoni.dormitory.repository.WasherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WasherService {
    private final WasherRepository washerRepository;

    public List<Washer> findAll() {
        return washerRepository.findAll();
    }

    public void save(Washer washer) {
        Optional<Washer> opitonal = washerRepository.findByBuildingNumberAndSubId(washer.getBuildingNumber(), washer.getSubId());
        if(opitonal.isPresent()){
            throw new DuplicateObjectException();
        }
        washerRepository.save(washer);
    }




    public void start(@Param("buildingNumber") int buildingNumber, @Param("subId") int subId){
        Optional<Washer> optional = washerRepository.findByBuildingNumberAndSubId(buildingNumber, subId);
        if(optional.isPresent() == false){
            throw new WasherNotFoundException();
        } else {
            Washer washer = optional.get();
            if(washer.isWorking()){
                throw new ProtectedObjectException();
            }
            washer.setLastStarted(System.currentTimeMillis() / 1000);
            washerRepository.save(washer);
        }
    }

}
