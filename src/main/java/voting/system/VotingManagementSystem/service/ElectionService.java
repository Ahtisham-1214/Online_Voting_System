package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.system.VotingManagementSystem.dto.ElectionUpdateDto;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.exception.ResourceNotFoundException;
import voting.system.VotingManagementSystem.repository.ElectionRepository;

import java.util.List;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    @Autowired
    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    public Election getElectionById(Long id) {
        return electionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Election with id " + id + " not found", Election.class.getSimpleName()));
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    @Transactional
    public Election updateElectionById(Long id, ElectionUpdateDto dto) {
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Election not found", Election.class.getSimpleName()));

        if (dto.getTitle() != null) {
            if (dto.getTitle().isBlank()) throw new IllegalArgumentException("Election Title cannot be empty");
            election.setTitle(dto.getTitle());
        }

        if (dto.getMaxPartyMembers() != 0 && dto.getMaxPartyMembers() < 0) {
            throw new IllegalArgumentException("Max Party Members cannot be negative");
        }



        election.setMaxPartyMembers(dto.getMaxPartyMembers());
        if (dto.getElectionStatus() != null) election.setElectionStatus(dto.getElectionStatus());
        if (dto.getStartTime() != null) election.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) election.setEndTime(dto.getEndTime());
        return electionRepository.save(election);
    }


}
