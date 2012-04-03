package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.Session;
import common.StatusUpdate;
import common.User;

public class UI extends JFrame implements StatusListener, StatusUpdate,
KeyListener, FocusListener {

	private static final long serialVersionUID = 1L;

	private Session session = null;

	private JPanel uiPanel = new JPanel();
	private JTextArea statusText = new JTextArea(3, 80);
	private JScrollPane statusScroll = new JScrollPane(this.statusText);
	private Vector<String> statusMsgs = new Vector<String>();
	private JTextArea mainText = new JTextArea(22, 80);
	private JScrollPane mainScroll = new JScrollPane(this.mainText);
	private StringBuffer mainBuffer = new StringBuffer();
	private StringBuffer keyBuffer = new StringBuffer();
	private String readResult;
	private boolean isEchoObfuscated = false;

	public void clear() {
		this.mainBuffer.setLength(0);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		this.requestFocus();

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		this.requestFocus();

	}

	public Session getSession() {
		return this.session;
	}

	public void init() {
		this.setSize(640, 480);

		this.statusText.setEditable(false);
		this.statusText.addFocusListener(this);
		this.mainText.addFocusListener(this);

		this.add(this.uiPanel);
		this.uiPanel.setLayout(new BorderLayout());
		this.uiPanel.add(this.statusScroll, BorderLayout.SOUTH);
		this.uiPanel.add(this.mainScroll);
		// uiPanel.add(loginPanel, BorderLayout.NORTH);
		this.uiPanel.addFocusListener(this);

		StatusPublisher.getInstance().addListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		this.statusText.setWrapStyleWord(true);
		this.mainText.setWrapStyleWord(true);
		this.mainText.setEditable(false);

		addKeyListener(this);
		statusUpdate("Begin");
		this.requestFocus();


		AdministratorDialogue adminDialogue= new AdministratorDialogue(this);
		InstructorDialogue instructorDialogue = new InstructorDialogue(this);

		while (true) {
			LoginDialogue.login(this);
			statusUpdate("Login "+session.getUser().getUserId()+" ok.");


			switch (SelectRoleDialogue.selectRole(this)) {
			case User.ROLE_ADMINISTRATOR:
				adminDialogue.showMainDialogue();
				break;
			case User.ROLE_INSTRUCTOR:
				instructorDialogue.showMainDialogue();
				break;
			case User.ROLE_STUDENT:
				StudentDialogue.showDialogue(this);
				break;
			case User.ROLE_TA:
				TADialogue.showDialogue(this);
				break;
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

		if (Character.isISOControl(c)) {
			write(c);
			if (c == '\n') {
				this.readResult = this.keyBuffer.toString();
				isEchoObfuscated=false;
				synchronized (this) {
					this.notify();
				}
			}
		} else {
			this.keyBuffer.append(c);
			if (this.isEchoObfuscated) {
				write('*');
			} else {
				write(c);
			}
		}

	}

	public String readLine() {
		this.keyBuffer.setLength(0);

		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}

		return this.readResult;
	}

	public void setObfuscated(boolean tf) {

		this.isEchoObfuscated = tf;

	}

	public void setSession(Session s) {
		this.session = s;
	}

	@Override
	public void setTitle(String t) {
		this.setTitle("Assessment: " + t);
	}

	public void stateChange(Component oldView, Component newView) {
		statusUpdate("Role change from "
				+ (null == oldView ? "<null>" : oldView.getClass().getName())
				+ " to " + newView.getClass().getName());
		oldView.setVisible(false);
		this.uiPanel.remove(oldView);
		this.uiPanel.add(newView, BorderLayout.NORTH);
		newView.setVisible(true);

	}

	@Override
	public void statusUpdate(String msg) {
		this.statusMsgs.add(msg);

		if (this.statusMsgs.size() > 30) {
			this.statusMsgs.remove(0);
		}

		StringBuffer sb = new StringBuffer();

		for (String s : this.statusMsgs) {
			sb.append(s);
			sb.append("\n");
		}
		this.statusText.setText(sb.toString());

	}

	private void updateMainText() {
		this.mainText.setText(this.mainBuffer.toString());
	}

	public void write(char c) {
		this.mainBuffer.append(c);
		updateMainText();
	}

	public void write(String s) {
		this.mainBuffer.append(s);
		updateMainText();
	}

}
