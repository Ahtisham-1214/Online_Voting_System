package voting.system.VotingManagementSystem.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import voting.system.VotingManagementSystem.entity.PartyMember;
import voting.system.VotingManagementSystem.entity.Position;

import java.util.List;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {
    Boolean existsByStudentId(Long studentId);

    boolean existsByPartyIdAndPosition(long id, @NotNull(message = "Position is required") Position position);

    @Query("SELECT p.name FROM PartyMember pm JOIN pm.party p WHERE pm.student.id = :id ")
    String findPartyByStudentId(@Param("id") Long id);

//    @Query("SELECT pm FROM PartyMember pm " +
//            "JOIN FETCH pm.student " +
//            "JOIN FETCH pm.party " +
//            "WHERE pm.party.id = :partyId")
//    List<PartyMember> findPartyMembersById(@Param("partyId") Long partyId, Pageable pageable);

    @Query("SELECT pm FROM PartyMember pm " +
            "JOIN FETCH pm.student " +
            "JOIN FETCH pm.party " +
            "WHERE pm.party.id = :partyId AND (:position IS NULL OR pm.position = :position)")
    List<PartyMember> findPartyMembersById(@Param("partyId") Long partyId,@Param("position") Position position, Pageable pageable);



    @Query("""
    SELECT COUNT(pm)
    FROM PartyMember pm
    WHERE pm.party.id = :partyId
      AND pm.position NOT IN (
          voting.system.VotingManagementSystem.entity.Position.EX_GENERAL_SECRETARY,
          voting.system.VotingManagementSystem.entity.Position.EX_PRESIDENT,
          voting.system.VotingManagementSystem.entity.Position.EX_PUBLIC_RELATION_OFFICER,
          voting.system.VotingManagementSystem.entity.Position.EX_TREASURER,
          voting.system.VotingManagementSystem.entity.Position.EX_VICE_PRESIDENT
      )
    """)
    int countPartyMemberById(@Param("partyId") Long partyId);
}
