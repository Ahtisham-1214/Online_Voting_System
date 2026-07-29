package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import voting.system.VotingManagementSystem.dto.PartyMemberRequestDto;
import voting.system.VotingManagementSystem.entity.*;
import voting.system.VotingManagementSystem.exception.ResourceNotFoundException;
import voting.system.VotingManagementSystem.repository.PartyMemberRepository;
import voting.system.VotingManagementSystem.repository.PartyRepository;
import voting.system.VotingManagementSystem.repository.StudentRepository;


import java.util.List;

@Service
public class PartyMemberService {
    private final StudentRepository studentRepository;
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;

    @Autowired
    public PartyMemberService(StudentRepository studentRepository,
                              PartyMemberRepository partyMemberRepository,
                              PartyRepository partyRepository) {
        this.studentRepository = studentRepository;
        this.partyMemberRepository = partyMemberRepository;
        this.partyRepository = partyRepository;
    }

    /*
     * There are few things to check before creating a PartyMember:
     * 1) There must exist a Party with the given ID
     * 2) The student must exist and be enrolled
     * 3) The student must neither be already a member of a party nor a member of a different election meaning that it can only participate in one election
     * 4) A party can't have more than one member of same designation like only 1 president, 1 GS, 1 VP, but they can have multiple EX positions
     */
    public PartyMember addPartyMember(PartyMemberRequestDto partyMemberRequestDto) {



        Party party = partyRepository.findById(partyMemberRequestDto.getPartyId()).orElseThrow(
                ()-> new ResourceNotFoundException("Party doesn't exists with party id: " + partyMemberRequestDto.getPartyId(),
                        PartyMemberService.class.getSimpleName() )
        );

        Student student = studentRepository.findBySapId(partyMemberRequestDto.getSapId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + partyMemberRequestDto.getSapId(),
                        PartyMemberService.class.getSimpleName()));


        if (student.getStudentStatus() != StudentStatus.ENROLLED) {
            throw new IllegalStateException("Student is not Enrolled and cannot participate.");
        }

        if(partyMemberRepository.existsByStudentId(student.getId()))
            throw new IllegalStateException("Student with Sap ID: "+ student.getSapId() +" is already a member of " + partyMemberRepository.findPartyByStudentId(student.getId())); // making sure one student can participate in an election only once


        if (partyMemberRequestDto.getPosition() != Position.EX_PRESIDENT
                && partyMemberRequestDto.getPosition() != Position.EX_VICE_PRESIDENT
                && partyMemberRequestDto.getPosition() != Position.EX_GENERAL_SECRETARY
                && partyMemberRequestDto.getPosition() != Position.EX_PUBLIC_RELATION_OFFICER
                && partyMemberRequestDto.getPosition() != Position.EX_TREASURER) {
            if (partyMemberRepository.existsByPartyIdAndPosition(party.getId(), partyMemberRequestDto.getPosition())) {
                throw new IllegalArgumentException(party.getName() + " already has a " + partyMemberRequestDto.getPosition()
                );
            }
        }


        // Create and save the PartyMember
        PartyMember member = new PartyMember();
        member.setStudent(student);
        member.setParty(party);
        member.setPosition(partyMemberRequestDto.getPosition());


        return partyMemberRepository.save(member);
    }

    public List<PartyMember> findPartyMembersById(Long partyId, Position position, Pageable pageable){
        if (!partyRepository.existsById(partyId)) {
            throw new ResourceNotFoundException(
                    "Party not found with ID: " + partyId,
                    PartyMemberService.class.getSimpleName()
            );
        }


        return partyMemberRepository.findPartyMembersById(partyId, position, pageable);
    }

}
