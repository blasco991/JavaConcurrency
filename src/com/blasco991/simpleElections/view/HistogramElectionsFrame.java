package com.blasco991.simpleElections.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.blasco991.annotations.UiThread;
import com.blasco991.simpleElections.MVC;
import com.blasco991.simpleElections.model.Model;
import net.jcip.annotations.ThreadSafe;

@SuppressWarnings("serial")
@ThreadSafe
public class HistogramElectionsFrame extends JFrame implements View {
	private final MVC mvc;
	private final JPanel scores;
	private JLabel saved;
	private JButton save;

	@UiThread
	public HistogramElectionsFrame(MVC mvc) {
		this.mvc = mvc;
		mvc.register(this);
		setLocationByPlatform(true);
		setPreferredSize(new Dimension(450, 450));
		setTitle("Histogram Elections");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		JPanel south = new JPanel();
		save = new JButton("save");
		save.addActionListener(e -> {
			save.setEnabled(false);
			mvc.controller.saveVotes(this);
		});
		south.add(save);
		panel.add(south, BorderLayout.SOUTH);
		saved = new JLabel();
		south.add(saved);

		return scores;
	}

	@Override @UiThread
	public void onModelChanged() {
		Model model = mvc.model;

		int totalVotes = 0;
		for (String party: model.getParties())
			totalVotes += model.getVotesFor(party);

		scores.removeAll();
		for (String party: model.getParties()) {
			JButton label = new JButton(party);
			label.addActionListener(e -> mvc.controller.registerVoteFor(party));
			scores.add(label);
			scores.add(new Histogram((float) model.getVotesFor(party) / totalVotes));
		}
		
		pack();
	}

	@Override
	public void reportLoaded() {

	}

	@Override @UiThread
	public void askForNewParty() {
	}
	
	@Override @UiThread
	public void reportSaved() {
		saved.setText("Saved!");
		Runnable cleanUp = () -> {
			EventQueue.invokeLater(() -> {
				saved.setText("");
				save.setEnabled(true);
			});
		};
		exec.schedule(cleanUp, 2, TimeUnit.SECONDS);
	}
}







