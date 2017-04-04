package com.blasco991.simpleelections.model;

import com.blasco991.annotations.UiThread;
import com.blasco991.simpleelections.MVC;
import com.blasco991.simpleelections.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.jcip.annotations.ThreadSafe;

/**
 * This class is thread-safe because of thread confinement.
 */

@ThreadSafe
public class Model {
    private MVC mvc;
    private final Map<String, Integer> votes = new HashMap<>();

    public void setMVC(MVC mvc) {
        this.mvc = mvc;
    }

    // 5: I need your state information
    @UiThread
    public Iterable<String> getParties() {
        // in alphabetical order
        return new TreeSet<>(votes.keySet());
    }

    @UiThread
    public int getVotesFor(String party) {
        return votes.get(party);
    }

    // 2: change your state
    @UiThread
    public void addParty(String party) {
        votes.put(party, 0);
        mvc.forEachView(View::onModelChanged);
    }

    @UiThread
    public void removeParty(String party) {
        votes.remove(party);
        mvc.forEachView(View::onModelChanged);
    }

    @UiThread
    public void addVotesTo(String party, int howMany) {
        if (votes.containsKey(party)) {
            votes.put(party, votes.get(party) + howMany);
            mvc.forEachView(View::onModelChanged);
        }
    }
}