package com.blasco991.simpleelections.controller;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

import com.blasco991.simpleelections.view.View;

public class VoteSaver extends Thread {
    private final Map<String, Integer> votes;
    private final static String NAME = "votes.txt";
    private final View view;

    VoteSaver(Map<String, Integer> votes, View view) {
        this.votes = votes;
        this.view = view;
    }

    @Override
    public void run() {
        try (PrintStream ps = new PrintStream(new FileOutputStream(NAME))) {
            for (String party : votes.keySet())
                ps.println(party + ": " + votes.get(party));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> view.reportSaved());
    }
}





