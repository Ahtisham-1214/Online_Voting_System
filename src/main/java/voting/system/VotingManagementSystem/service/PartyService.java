package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.system.VotingManagementSystem.dto.PartyUpdateDto;
import voting.system.VotingManagementSystem.entity.Party;
import voting.system.VotingManagementSystem.exception.ResourceNotFoundException;
import voting.system.VotingManagementSystem.repository.PartyRepository;

import java.util.List;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Party getPartyById(Long id) {
        return partyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Party with id " + id + " not found", Party.class.getSimpleName()));
    }

    public Party addParty(Party party) {
        return partyRepository.save(party);
    }

    public Party updatePartyById(Long id, PartyUpdateDto partyUpdateDto) {
        Party party = partyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Party with id " + id + " not found", Party.class.getSimpleName()));

        if (partyUpdateDto.getName() != null) {
            if (partyUpdateDto.getName().isBlank()) throw new IllegalArgumentException("Party name cannot be empty");
            party.setName(partyUpdateDto.getName());
        }
        party.setSlogan(partyUpdateDto.getSlogan());
        return partyRepository.save(party);

    }

    public void deletePartyById(Long id) {
        if (!partyRepository.existsById(id))
            throw new ResourceNotFoundException("Party with id " + id + " does not Exist", Party.class.getSimpleName());

        partyRepository.deleteById(id);
    }
}
