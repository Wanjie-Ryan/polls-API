package com.voting.votingapp.model;


import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
// mark a class that does not have its own table but is embedded inside another entity
public class OptionVote {

    private String voteoption;
    private Long voteCount = 0L;

    public String getVoteoption() {
        return voteoption;
    }

    public void setVoteoption(String voteoption) {
        this.voteoption = voteoption;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
