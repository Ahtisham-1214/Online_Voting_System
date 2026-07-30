package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.Position;

@Getter
@Setter
@NoArgsConstructor
public class PartyMemberRequestDto {

    @NotBlank(message = "Student SAP ID is required")
    private String sapId;

    @NotNull(message = "Party ID is required")
    private Long partyId;
    @NotNull(message = "Position is required")
    private Position position;
}
