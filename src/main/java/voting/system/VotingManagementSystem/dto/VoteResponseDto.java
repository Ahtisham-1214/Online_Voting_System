package voting.system.VotingManagementSystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VoteResponseDto {
    private String partyName;
    private String electionTitle;
    private LocalDateTime votedAt;


}
