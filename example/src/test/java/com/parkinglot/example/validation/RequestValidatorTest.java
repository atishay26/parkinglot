package com.parkinglot.example.validation;

import com.parkinglot.example.exceptions.ValidationException;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.junit.jupiter.api.Test;


public class RequestValidatorTest {

    @Test
    public void validateParkingLotRequest() throws ValidationException {
        ParkingLotRequest pr1234 = ParkingLotRequest.builder()
                .numFloors(2)
                .slotsPerFloor(6)
                .build();
        RequestValidator.validateParkingLot(pr1234);
    }

    @Test
    public void validateParkingLotRequestException() throws ValidationException {
        ParkingLotRequest pr1234 = ParkingLotRequest.builder()
                .numFloors(0)
                .slotsPerFloor(6)
                .build();
        RequestValidator.validateParkingLot(pr1234);
    }

}
