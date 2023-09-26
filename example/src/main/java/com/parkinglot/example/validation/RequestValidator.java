package com.parkinglot.example.validation;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.exceptions.ValidationException;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

public class RequestValidator {

    public static void validateParkingLot(ParkingLotRequest request) throws ValidationException {
        validateNonNullField(request, "request");
        validateNonNullField(request.getNumFloors(), "numFloors");
        validateNonNullField(request.getSlotsPerFloor(), "slotsPerFloor");
        validateIntegerPositive(request.getNumFloors(), "numFloors");
        validateIntegerPositive(request.getSlotsPerFloor(), "slotsPerFloor");
    }

    private static void validateIntegerPositive(Integer i, String field) throws ValidationException {
        Optional.ofNullable(i)
                .filter( value -> value > 0)
                .orElseThrow(() -> new ValidationException(String.format("%s should be positive.", field)));
    }

    private static void validateNonEmptyString(String string, String field) throws ValidationException {
        Optional.ofNullable(string)
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new ValidationException(String.format("%s cannot be blank.", field)));

    }

    private static void validateNonNullField(Object body, String field) throws ValidationException {
        Optional.ofNullable(body)
                .map(Objects::nonNull)
                .orElseThrow(() -> new ValidationException(String.format("%s cannot be null.", field)));
    }

}
