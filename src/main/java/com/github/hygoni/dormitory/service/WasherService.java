package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.model.Washer;
import com.github.hygoni.dormitory.repository.WasherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WasherService {
    private final WasherRepository washerRepository;

    public List<Washer> findAllByBuildingNumber(int buildingNumber) {
        return washerRepository.findAllByBuildingNumber(buildingNumber);
    }

    public Washer save(Washer washer) {
        if(washerRepository.findByBuildingNumberAndSubId(washer.getBuildingNumber(), washer.getSubId()) == null){
            return null;
        }
        return washerRepository.save(washer);
    }

}
