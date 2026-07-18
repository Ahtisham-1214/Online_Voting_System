package voting.system.VotingManagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartyRequestDto {

    @NotBlank( message = "Party Name cannot be empty")
    private String name;
    private String slogan;
}
