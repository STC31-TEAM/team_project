package ru.innopolis.stc31.appeal.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.stc31.appeal.converters.TicketDTOToTicket;
import ru.innopolis.stc31.appeal.converters.TicketToTicketDTO;
import ru.innopolis.stc31.appeal.model.dto.TicketDTO;
import ru.innopolis.stc31.appeal.model.entity.Ticket;
import ru.innopolis.stc31.appeal.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final TicketDTOToTicket ticketDTOToTicket;
    private final TicketToTicketDTO ticketToTicketDTO;

    @Override
    public List<TicketDTO> getTicketList() {
        List <Ticket> ticketList=ticketRepository.findAll();
        List <TicketDTO> ticketDTOList=new ArrayList<>();
        for (Ticket ticket:ticketList) {
            ticketDTOList.add(ticketToTicketDTO.convert(ticket));
        }
        return ticketDTOList;
    }

    @Override
    public boolean createTicket(TicketDTO ticketDTO) {
        ticketRepository.save(ticketDTOToTicket.convert(ticketDTO));
        return true;
    }

    @Override
    public boolean deleteTicket(TicketDTO ticketDTO) {
        ticketRepository.deleteById(ticketDTO.getId());
        return false;
    }

}