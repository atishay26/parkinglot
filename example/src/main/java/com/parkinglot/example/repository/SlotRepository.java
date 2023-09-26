package com.parkinglot.example.repository;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.Slot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.parkinglot.example.util.SlotUtil.getSlotType;

@Repository
public class SlotRepository {

    private static Map<Integer, Slot> slotMap = new HashMap<>();

    public void createSlots(Integer floorId, Integer slotsPerFloor) {
        for(int slotNum = 1; slotNum<slotsPerFloor; slotNum++)
        {
            Slot slot = Slot.builder()
                    .slotId(slotMap.size()+1)
                    .floorId(floorId)
                    .isAvailable(true)
                    .slotNum(slotNum)
                    .type(getSlotType(slotNum))
                    .build();
            slotMap.put(slot.getSlotId(),slot);
        }
    }


    public List<Slot> findByFloorId(Integer floorId) {
        return slotMap.keySet()
                .stream()
                .map(key -> slotMap.get(key))
                .filter(slot -> floorId == slot.getFloorId())
                .collect(Collectors.toList());
    }

    public List<Slot> findByFloorIdAndVehicleType(Integer floorId, VehicleType vehicleType) {
        return slotMap.keySet()
                .stream()
                .map(key -> slotMap.get(key))
                .filter(slot -> floorId == slot.getFloorId())
                .filter(slot -> slot.getType().equals(vehicleType))
                .collect(Collectors.toList());
    }

    public void parkVehicle(Integer slotId) {
        Slot slot = slotMap.get(slotId);
        slot.setAvailable(false);
        slotMap.put(slotId, slot);
    }

    public void unParkVehicle(Integer slotId) {
        Slot slot = slotMap.get(slotId);
        slot.setAvailable(true);
        slotMap.put(slotId, slot);
    }
}
