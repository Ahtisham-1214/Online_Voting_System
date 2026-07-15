package voting.system.VotingManagementSystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voting.system.VotingManagementSystem.dto.ElectionRequestDto;
import voting.system.VotingManagementSystem.dto.ElectionResponseDto;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.service.ElectionService;

@RestController
@RequestMapping("/admin")
public class ElectionController {
    private final ElectionService electionService;
    private final ModelMapper modelMapper;

    @Autowired
    public ElectionController(ElectionService electionService, ModelMapper modelMapper) {
        this.electionService = electionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/elections")
    public ResponseEntity<ElectionResponseDto> createElection(@RequestBody ElectionRequestDto electionRequestDto) {
        Election election = modelMapper.map(electionRequestDto, Election.class);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(modelMapper.map(electionService.createElection(election), ElectionResponseDto.class));

    }

    @PutMapping("/elections/{id}")
    public ResponseEntity<ElectionResponseDto> updateStatus(@PathVariable Long id){

    }
}
