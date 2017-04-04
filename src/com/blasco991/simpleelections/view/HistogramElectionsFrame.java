package com.blasco991.simpleelections.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import com.blasco991.annotations.UiThread;
import com.blasco991.simpleelections.MVC;
import com.blasco991.simpleelections.model.Model;
import net.jcip.annotations.ThreadSafe;

@SuppressWarnings("serial")
@ThreadSafe
public class HistogramElectionsFrame extends JFrame implements View {
    private final MVC mvc;
    private final JPanel scores;

    @UiThread
    public HistogramElectionsFrame(MVC mvc) {
        this.mvc = mvc;
        mvc.register(this);

        setPreferredSize(new Dimension(450, 300));
        setTitle("Histogram Elections");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.scores = buildWidgets();

        onModelChanged();
    }

    private JPanel buildWidgets() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel scores = new JPanel();
        scores.setLayout(new GridLayout(0, 2));
        panel.add(scores, BorderLayout.NORTH);

        add(new JScrollPane(panel));

        return scores;
    }

    @Override
    @UiThread
    public void onModelChanged() {
        Model model = mvc.model;

        int totalVotes = 0;
        for (String party : model.getParties())
            totalVotes += model.getVotesFor(party);

        scores.removeAll();
        for (String party : model.getParties()) {
            JButton label = new JButton(party);
            label.addActionListener(e -> mvc.controller.registerVoteFor(party));
            scores.add(label);
            scores.add(new Histogram((float) model.getVotesFor(party) / totalVotes));
        }

        pack();
    }

    @Override
    @UiThread
    public void askForNewParty() {
    }
}