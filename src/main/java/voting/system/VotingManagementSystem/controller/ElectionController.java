package voting.system.VotingManagementSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voting.system.VotingManagementSystem.dto.ElectionRequestDto;
import voting.system.VotingManagementSystem.dto.ElectionResponseDto;
import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.mapper.MyMapper;
import voting.system.VotingManagementSystem.service.ElectionService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ElectionController {
    private final ElectionService electionService;

    @Autowired
    private  MyMapper myMapper;
    @Autowired
    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping("/elections")
    public ResponseEntity<ElectionResponseDto> createElection(@Valid @RequestBody ElectionRequestDto electionRequestDto) {
        Election election = myMapper.toElection(electionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(myMapper.toElectionResponseDto(electionService.createElection(election)));

    }

    @GetMapping("/elections")
    public ResponseEntity <List<ElectionResponseDto>> getAllElections(){
        List<Election> elections = electionService.getAllElections();

        if (elections.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(elections.stream()
                .map(myMapper::toElectionResponseDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/elections/{id}")
    public ResponseEntity<ElectionResponseDto> getElectionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).
                body(myMapper.toElectionResponseDto(electionService.getElectionById(id)));
    }
//    @PutMapping("/elections/{id}")
//    public ResponseEntity<ElectionResponseDto> updateStatus(@PathVariable Long id){
//
//    }
}
