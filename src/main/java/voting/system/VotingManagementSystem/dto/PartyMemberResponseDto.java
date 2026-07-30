package voting.system.VotingManagementSystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.Position;

@Getter
@Setter
@NoArgsConstructor
public class PartyMemberResponseDto {
    private Long id;
    private Long partyId;
    private String partyName;
    private String sapId;
    private String studentName;
    private Position position;
}
