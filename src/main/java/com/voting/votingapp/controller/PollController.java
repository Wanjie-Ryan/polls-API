package com.voting.votingapp.controller;

import com.voting.votingapp.dto.Vote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.service.PollService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {

        Poll newPoll = pollService.createPoll(poll);
        return new ResponseEntity<>(newPoll, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Poll>> getAllPolls() {

        List<Poll> getPolls = pollService.getAllPolls();
        return new ResponseEntity<>(getPolls, HttpStatus.OK);
    }

    @GetMapping("/{id}/poll")

    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {

        Poll getSinglePoll = pollService.getPollById(id).orElseThrow(() -> new EntityNotFoundException("Poll with id: " + id + " not found"));
        return new ResponseEntity<>(getSinglePoll, HttpStatus.OK);
    }

    //voting functionality
    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote) {
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }

}
