package com.parkinglot.example.util;

public class TicketUtil {

    public static String getTicketId(String parkingLotId, int floorNum, int slotNum) {
        return new StringBuilder()
                .append(parkingLotId)
                .append("_")
                .append(floorNum)
                .append("_")
                .append(slotNum).toString();
    }

}
