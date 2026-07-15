package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.repository.ElectionRepository;

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



}
