package voting.system.VotingManagementSystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import voting.system.VotingManagementSystem.dto.*;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.Party;
import voting.system.VotingManagementSystem.entity.PartyMember;
import voting.system.VotingManagementSystem.entity.Student;

@Mapper(componentModel = "spring")
public interface MyMapper {

    ElectionResponseDto toElectionResponseDto(Election election);
    Election toElection(ElectionRequestDto electionRequestDto);

    StudentResponseDto toStudentResponseDto(Student student);
    Student toStudent(StudentRequestDto studentRequestDto);

    @Mapping(source = "electionId", target = "election.id")
    Party toParty(PartyRequestDto partyRequestDto);
    @Mapping(source = "election.id", target = "electionId")
    PartyResponseDto toPartyResponseDto(Party party);

    @Mapping(source = "party.id", target = "partyId")
    @Mapping(source = "party.name", target = "partyName")
    @Mapping(source = "student.sapId", target = "sapId")
    @Mapping(source = "student.name", target = "studentName")
    PartyMemberResponseDto toPartyMemberResponseDto(PartyMember partyMember);
}
