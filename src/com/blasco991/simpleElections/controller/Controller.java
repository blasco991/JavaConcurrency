package com.blasco991.simpleElections.controller;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;

import com.blasco991.annotations.UiThread;
import com.blasco991.annotations.WorkerThread;
import com.blasco991.simpleElections.MVC;
import com.blasco991.simpleElections.view.View;
import net.jcip.annotations.ThreadSafe;

/**
 * This class is thread-sfae because of thread-confinement
 */

@ThreadSafe
public class Controller {
    private MVC mvc;

    public void setMVC(MVC mvc) {
        this.mvc = mvc;
    }

    // 1: the user did something
    @UiThread
    public void askForNewParty(View view) {
        view.askForNewParty();
    }

    @UiThread
    public void addParty(String party) {
        mvc.model.addParty(party);
    }

    @UiThread
    public void removeParty(String party) {
        mvc.model.removeParty(party);
    }

    @UiThread
    public void registerVoteFor(String party) {
        mvc.model.addVotesTo(party, 1);
    }

    @UiThread
    public void saveVotes(View view) {
        Map<String, Integer> votes = new HashMap<>();
        for (String party : mvc.model.getParties())
            votes.put(party, mvc.model.getVotesFor(party));

        new VoteSaver(votes, view).start();
    }

    @UiThread
    public void loadVotes(View view) {
        List<String> parties = new ArrayList<>();
        mvc.model.getParties().forEach(parties::add);
        VoteLoader voteLoader = new VoteLoader(parties, mvc.model);
        Executors.newSingleThreadExecutor().execute(voteLoader);
        EventQueue.invokeLater(view::onModelChanged);
    }
}









