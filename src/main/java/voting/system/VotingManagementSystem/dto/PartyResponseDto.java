package voting.system.VotingManagementSystem.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartyResponseDto {
    private Long id;
    private String name;
    private String slogan;
    private Long electionId;

}
