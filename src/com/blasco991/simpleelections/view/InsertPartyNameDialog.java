package com.blasco991.simpleelections.view;

import it.univr.concurrency.simpleelections.controller.Controller;

import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.jcip.annotations.UiThread;

@SuppressWarnings("serial")
class InsertPartyNameDialog extends JDialog {

	@UiThread
	public InsertPartyNameDialog(Controller controller) {
		super((Dialog) null);

		setTitle("Insert Party Name");
		setLayout(new FlowLayout());
		add(new JLabel("Insert new party name: "));

		JTextField textField = new JTextField("nome partito");
		textField.addActionListener(e -> {
			String party = textField.getText();
			if (!party.isEmpty())
				controller.addParty(party);

			setVisible(false);
			dispose();
		});
		add(textField);

		pack();
		setVisible(true);
	}
}