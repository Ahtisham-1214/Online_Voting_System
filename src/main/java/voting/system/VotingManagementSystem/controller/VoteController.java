package voting.system.VotingManagementSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.system.VotingManagementSystem.dto.VoteRequestDto;
import voting.system.VotingManagementSystem.dto.VoteResponseDto;
import voting.system.VotingManagementSystem.mapper.MyMapper;
import voting.system.VotingManagementSystem.service.VoteService;


@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;
    private final MyMapper myMapper;

    @Autowired
    public VoteController(VoteService voteService, MyMapper myMapper) {
        this.myMapper = myMapper;
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteResponseDto> castVote(@Valid @RequestBody VoteRequestDto voteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(myMapper.toVoteResponseDto(voteService.castVote(voteRequestDto)));
    }
}
