import controller.TicketController;
import repository.GateRepository;
import repository.ParkingLotRepository;
import repository.TicketRepository;
import repository.VehicleRepository;
import service.TicketService;

import javax.swing.text.GapContent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();

        TicketService ticketService = new TicketService(gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController=new TicketController(ticketService);
    }
    }


// controller -> service -> repo ---this is called topological sorting