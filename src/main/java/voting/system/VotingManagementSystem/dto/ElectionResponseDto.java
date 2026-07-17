package voting.system.VotingManagementSystem.dto;

import lombok.*;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.ElectionStatus;

import java.time.LocalDateTime;

/**
 * DTO for {@link Election}
 */
@NoArgsConstructor
@Getter
@Setter
public class ElectionResponseDto {
   private Long id;
   private String title;
   private ElectionStatus electionStatus;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private int maxPartyMembers;
}