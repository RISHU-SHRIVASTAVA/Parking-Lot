package repository;

import models.Gate;
import models.ParkingLot;

import java.util.Map;
import java.util.TreeMap;

public class ParkingLotRepository {

    private Map<Long, ParkingLot> parkingLots = new TreeMap<>();

    public ParkingLot getParkingLotByGate(Gate gate) {
        for (ParkingLot parkingLot : parkingLots.values()) {
            if (parkingLot.getGates().contains(gate)) {
                return parkingLot;
            }
        }
        return null;
    }
}
