package voting.system.VotingManagementSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voting.system.VotingManagementSystem.dto.PartyRequestDto;
import voting.system.VotingManagementSystem.dto.PartyResponseDto;
import voting.system.VotingManagementSystem.dto.PartyUpdateDto;
import voting.system.VotingManagementSystem.entity.Party;
import voting.system.VotingManagementSystem.mapper.MyMapper;
import voting.system.VotingManagementSystem.service.PartyService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class PartyController {

    private final MyMapper myMapper;
    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService, MyMapper myMapper) {
        this.partyService = partyService;
        this.myMapper = myMapper;
    }

    @GetMapping("/parties")
    public ResponseEntity<List<PartyResponseDto>> getAllParties(){
        List<Party> parties = partyService.getAllParties();
        if (parties.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(parties.stream()
                .map(myMapper::toPartyResponseDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/parties/{id}")
    public ResponseEntity<PartyResponseDto> getPartyById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(myMapper.toPartyResponseDto(partyService.getPartyById(id)));
    }

    @PostMapping("/parties")
    public ResponseEntity<PartyResponseDto> addParty(@Valid @RequestBody PartyRequestDto partyRequestDto){
        return new ResponseEntity<>(
                myMapper.toPartyResponseDto(partyService.addParty(myMapper.toParty(partyRequestDto)))
                ,HttpStatus.CREATED);
    }

    @PatchMapping("/parties/{id}")
    public ResponseEntity<PartyResponseDto> updatePartyById(@PathVariable Long id, @RequestBody PartyUpdateDto partyUpdateDto){
        return ResponseEntity.ok(myMapper.toPartyResponseDto(partyService.updatePartyById(id, partyUpdateDto)));
    }

    @DeleteMapping("/parties/{id}")
    public ResponseEntity<?> deletePartyById(@PathVariable Long id){
        partyService.deletePartyById(id);
        return ResponseEntity.noContent().build();

    }

}
