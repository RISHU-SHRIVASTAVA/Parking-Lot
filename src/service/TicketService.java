package service;

import factory.SlotAssignmentStrategyFactory;
import models.*;
import repository.GateRepository;
import Exceptions.GateNotFoundException;
import repository.ParkingLotRepository;
import repository.TicketRepository;
import repository.VehicleRepository;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,VehicleRepository vehicleRepository,ParkingLotRepository parkingLotRepository,TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository=ticketRepository;
    }

    public Ticket issueTicket(
            String vehicleNumber,
            String vehicleOwnerName,
            VehicleTypes vehicleType,
            Long gateId) throws GateNotFoundException {

        //1. Create a Ticket
        //2. Assign a spot
        // 3.return the ticket

        Ticket ticket=new Ticket();
        ticket.setEntryTime(new Date());

        //Setting the gate
        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);

        if(gateOptional.isEmpty()){
            try {
                throw new GateNotFoundException();
            } catch (GateNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        Gate gate = gateOptional.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getCurrentOperator());

        Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByNumber(vehicleNumber);
        Vehicle savedVehicle;
        if(vehicleOptional.isEmpty()){
            Vehicle vehicle=new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            vehicle.setVehicleType(vehicleType);

            savedVehicle=vehicleRepository.saveVehicle(vehicle);

        }
        else{
            savedVehicle=vehicleOptional.get();
        }
        ticket.setVehicle(savedVehicle);

        //Assign the slot
        SlotAssignmentStrategyType slotAssignmentStrategyType = parkingLotRepository
                .getParkingLotByGate(gate)
                .getSlotAssignmentStrategyType();

        ParkingSlot parkingSlot = SlotAssignmentStrategyFactory
                .getSlotForType(slotAssignmentStrategyType)
                .getSlot(gate, vehicleType);

        ticket.setParkingSlot(parkingSlot);

        ticket = ticketRepository.saveTicket(ticket);
        ticket.setNumber("Ticket - "+ticket.getId());
        return ticket;



    }
}
