package voting.system.VotingManagementSystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.Program;
import voting.system.VotingManagementSystem.entity.Season;
import voting.system.VotingManagementSystem.entity.StudentStatus;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto {
    long id;
    String name;
    String sapId;
    String email;
    StudentStatus studentStatus;
    Season season;
    Program program;
    Year year;
}