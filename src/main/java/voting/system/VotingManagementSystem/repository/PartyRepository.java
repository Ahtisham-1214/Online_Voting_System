package voting.system.VotingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.system.VotingManagementSystem.entity.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
}
