package voting.system.VotingManagementSystem.mapper;

import org.mapstruct.Mapper;
import voting.system.VotingManagementSystem.dto.*;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.Party;
import voting.system.VotingManagementSystem.entity.Student;

@Mapper(componentModel = "spring")
public interface MyMapper {

    ElectionResponseDto toElectionResponseDto(Election election);
    Election toElection(ElectionRequestDto electionRequestDto);

    StudentResponseDto toStudentResponseDto(Student student);
    Student toStudent(StudentRequestDto studentRequestDto);

    Party toParty(PartyRequestDto partyRequestDto);
    PartyResponseDto toPartyResponseDto(Party party);
}
