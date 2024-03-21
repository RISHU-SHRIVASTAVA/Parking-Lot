package controller;

import dto.IssueTicketRequestDTO;
import dto.IssueTicketResponseDTO;
import dto.ResponseType;
import models.Ticket;
import service.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO){
        IssueTicketResponseDTO response=new IssueTicketResponseDTO();

        try{
            Ticket ticket = ticketService.issueTicket(
                    requestDTO.getVehicleNumber(),
                    requestDTO.getVehicleOwnerName(),
                    requestDTO.getVehicleType(),
                    requestDTO.getGateId());
            response.setTicket(ticket);
            response.setResponseStatus(ResponseType.SUCCESS);

        } catch (Exception e){
            response.setResponseStatus(ResponseType.FAILURE);
            response.setFailureMessage(e.getMessage());

        }
        System.out.println();

        return response;
    }
}

//1. Need to interact with client
//2. Validation on the input
//3. Call the service and provide it with the inputs
//4. Receive the output from service and send it over the client in proper format