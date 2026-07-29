package voting.system.VotingManagementSystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voting.system.VotingManagementSystem.dto.*;
import voting.system.VotingManagementSystem.entity.Party;
import voting.system.VotingManagementSystem.entity.Position;
import voting.system.VotingManagementSystem.mapper.MyMapper;
import voting.system.VotingManagementSystem.service.PartyMemberService;
import voting.system.VotingManagementSystem.service.PartyService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class PartyController {

    private final MyMapper myMapper;
    private final PartyService partyService;
    private final PartyMemberService partyMemberService;

    @Autowired
    public PartyController(PartyService partyService, MyMapper myMapper, PartyMemberService partyMemberService) {
        this.partyService = partyService;
        this.myMapper = myMapper;
        this.partyMemberService = partyMemberService;
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
    public ResponseEntity<PartyResponseDto> updatePartyById(@PathVariable Long id, @RequestBody @Valid PartyUpdateDto partyUpdateDto){
        return ResponseEntity.ok(myMapper.toPartyResponseDto(partyService.updatePartyById(id, partyUpdateDto)));
    }

    @DeleteMapping("/parties/{id}")
    public ResponseEntity<?> deletePartyById(@PathVariable Long id){
        partyService.deletePartyById(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/parties/members")
    public ResponseEntity<PartyMemberResponseDto> addPartyMember(@Valid @RequestBody PartyMemberRequestDto requestDto){
        PartyMemberResponseDto responseDto = myMapper.toPartyMemberResponseDto(partyMemberService.addPartyMember(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }



    @GetMapping("/parties/{id}/members")
    public ResponseEntity<List<PartyMemberResponseDto>> findPartyMembersByIdWithPagination(
            @PathVariable(name = "id") Long partyId,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) @Min(value = 1, message = "Page Number must be greater or equal to 1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder,
            @RequestParam(value = "position", required = false) Position position

    ) {
        sortBy = switch (sortBy) {
            case "studentName" -> "student.name";
            case "sapId"       -> "student.sapId";
            case "partyName"   -> "party.name";
            default            -> sortBy;
        };


        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        List<PartyMemberResponseDto> partyMembers = partyMemberService
                .findPartyMembersById(partyId, position, PageRequest.of(pageNumber - 1, pageSize, sort))
                .stream()
                .map(myMapper::toPartyMemberResponseDto)
                .toList();

        return ResponseEntity.ok(partyMembers);
    }

}
