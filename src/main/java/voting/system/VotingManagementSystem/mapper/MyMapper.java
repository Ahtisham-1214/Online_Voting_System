package voting.system.VotingManagementSystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import voting.system.VotingManagementSystem.dto.ElectionRequestDto;
import voting.system.VotingManagementSystem.dto.ElectionResponseDto;
import voting.system.VotingManagementSystem.dto.StudentDto;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.Student;

@Mapper(componentModel = "spring")
public interface MyMapper {

    ElectionResponseDto toElectionResponseDto(Election election);
    Election toElection(ElectionRequestDto electionRequestDto);

    StudentDto toStudentDto(Student student);
    Student toStudent(StudentDto studentDto);
}
