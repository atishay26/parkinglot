package com.parkinglot.example.service;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.Floor;
import com.parkinglot.example.models.Slot;
import com.parkinglot.example.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public void printFreeSlotsByVehicleType(List<Floor> floors, VehicleType vehicleType) {
        floors.forEach(floor -> {
            List<Slot> slots = slotRepository.findByFloorIdAndVehicleType(floor.getFloorId(), vehicleType);
            List<Integer> slotNums = getAvailableSlotNums(slots);
            printSlotsForCurrentFloor(vehicleType, floor, slotNums);
        });

    }

    private List<Integer> getAvailableSlotNums(List<Slot> slots) {
        List<Integer> slotNums = new ArrayList<>();
        slots.forEach(slot -> {
            if(slot.isAvailable()) {
               slotNums.add(slot.getSlotNum());
            }
        });
        return slotNums;
    }

    private void printSlotsForCurrentFloor(VehicleType vehicleType, Floor floor, List<Integer> slotNums) {
        System.out.print(String.format("Free slots for %s on Floor %s:", vehicleType.name, floor.getFloorNum()));
        StringBuilder sb = new StringBuilder();
        for(int i:slotNums) {
            sb.append(i).append(",");
        }
        System.out.println(sb.substring(0,sb.length()-1));
    }

    public void createSlots(Integer floorId, Integer slotsPerFloor) {
        slotRepository.createSlots(floorId, slotsPerFloor);
    }

    public Slot findByFloorIdAndVehicleType(int floorId, VehicleType type) {
        List<Slot> slots = slotRepository.findByFloorIdAndVehicleType(floorId,type);
        return slots.stream()
                .filter(Slot::isAvailable)
                .findFirst()
                .get();
    }

    public void parkVehicle(Integer slotId) {
        slotRepository.parkVehicle(slotId);
    }

    public void unParkVehicle(Integer slotId) {
        slotRepository.unParkVehicle(slotId);
    }
}
