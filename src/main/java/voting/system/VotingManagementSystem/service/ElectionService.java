package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.system.VotingManagementSystem.dto.ElectionUpdateDto;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.ElectionStatus;
import voting.system.VotingManagementSystem.exception.ResourceNotFoundException;
import voting.system.VotingManagementSystem.repository.ElectionRepository;
import voting.system.VotingManagementSystem.util.HelperClass;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    @Autowired
    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public Election createElection(Election election) {
        if (election.getStartTime() != null && election.getEndTime() != null) {
            HelperClass.validateTimes(election.getStartTime(), election.getEndTime());
        }

        if (election.getElectionStatus() != null) {
            // Explicit status change requested
            ElectionStatus targetStatus = election.getElectionStatus();

            if (targetStatus == ElectionStatus.VOTING) {
                if (election.getStartTime() == null || election.getEndTime() == null) {
                    throw new IllegalStateException("Cannot set status to VOTING without configuring start and end times.");
                }
                if (LocalDateTime.now().isAfter(election.getEndTime())) {
                    throw new IllegalStateException("Cannot set status to VOTING because the scheduled end time has passed.");
                }
            }

            election.setElectionStatus(targetStatus);
        }
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
                .orElseThrow(() -> new ResourceNotFoundException("Election not found with id: " + id, Election.class.getSimpleName()));

        if (dto.getTitle() != null) {
            if (dto.getTitle().isBlank()) throw new IllegalArgumentException("Election Title cannot be empty");
            election.setTitle(dto.getTitle());
        }

        if (dto.getMaxPartyMembers() != null) {
            if (dto.getMaxPartyMembers() < 1) {
                throw new IllegalArgumentException("Max Party Members cannot be negative");
            }
            election.setMaxPartyMembers(dto.getMaxPartyMembers());
        }


        if (dto.getStartTime() != null) election.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) election.setEndTime(dto.getEndTime());

        if (election.getStartTime() != null && election.getEndTime() != null) {
            HelperClass.validateTimes(election.getStartTime(), election.getEndTime());
        }

        if (dto.getElectionStatus() != null) {
            // Explicit status change requested
            ElectionStatus targetStatus = dto.getElectionStatus();

            if (targetStatus == ElectionStatus.VOTING) {
                if (election.getStartTime() == null || election.getEndTime() == null) {
                    throw new IllegalStateException("Cannot set status to VOTING without configuring start and end times.");
                }
                if (LocalDateTime.now().isAfter(election.getEndTime())) {
                    throw new IllegalStateException("Cannot set status to VOTING because the scheduled end time has passed.");
                }
            }

            election.setElectionStatus(targetStatus);
        }



        return election;
    }


}
