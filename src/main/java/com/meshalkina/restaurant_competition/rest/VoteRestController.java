package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.MealDTO;
import com.meshalkina.restaurant_competition.model.Meal;
import com.meshalkina.restaurant_competition.model.Vote;
import com.meshalkina.restaurant_competition.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@Api("Controller for votes")
public class VoteRestController {

    private final VoteService voteService;

    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @ApiOperation("Create new vote")
    @PostMapping("/{restaurant_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Vote> createVote(@PathVariable Long restaurant_id) {
        Vote newVote = voteService.createVote(restaurant_id);
        if (newVote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @ApiOperation("Get all votes")
    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> allVotes = voteService.getAllVotes();
        return new ResponseEntity<>(allVotes, HttpStatus.OK);
    }

    @ApiOperation("Get a vote by id")
    @GetMapping("/{vote_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long vote_id) {
        Vote vote = voteService.getVoteById(vote_id);
        if (vote != null) {
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Update the vote")
    @PutMapping("/{restaurant_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Vote> updateVote(@PathVariable Long restaurant_id) {
        Vote newVote = voteService.updateVote(restaurant_id);
        if (newVote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @ApiOperation("Delete the vote")
    @DeleteMapping("/{vote_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public void deleteVoteById(@PathVariable Long vote_id) {
        voteService.deleteVote(vote_id);
    }
}
