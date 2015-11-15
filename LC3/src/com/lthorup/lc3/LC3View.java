package com.lthorup.lc3;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LC3View extends JPanel {

	LC3 machine;
	File currentDir = null;
	private JTextField r2;
	private JTextField r1;
	private JTextField r3;
	private JTextField r4;
	private JTextField r5;
	private JTextField r6;
	private JTextField r7;
	private JTextField r0;
	private JTextArea memView;
	private JTextField status;
	private JCheckBox cbNeg;
	private JCheckBox cbZero;
	private JCheckBox cbPos;
	private JTextField d0;
	private JTextField d1;
	private JTextField d2;
	private JTextField d3;
	private JTextField d4;
	private JTextField d5;
	private JTextField d6;
	private JTextField d7;
	private JButton btnStepOver;
	private JButton btnStop;
	private JButton btnStep;
	private JButton btnRun;
	private JTextArea console;
	
	/**
	 * Create the panel.
	 */
	public LC3View() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(31, 29, 192, 229);
		add(panel);
		panel.setLayout(null);
		
		r0 = new JTextField();
		r0.setHorizontalAlignment(SwingConstants.CENTER);
		r0.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r0.setEditable(false);
		r0.setFocusable(false);
		r0.setBounds(46, 10, 70, 26);
		panel.add(r0);
		r0.setColumns(10);
		
		JLabel lblR = new JLabel("R0");
		lblR.setFocusable(false);
		lblR.setBounds(21, 16, 25, 16);
		panel.add(lblR);
		
		r2 = new JTextField();
		r2.setHorizontalAlignment(SwingConstants.CENTER);
		r2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r2.setEditable(false);
		r2.setFocusable(false);
		r2.setColumns(10);
		r2.setBounds(46, 62, 70, 26);
		panel.add(r2);
		
		JLabel lblR_2 = new JLabel("R2");
		lblR_2.setFocusable(false);
		lblR_2.setBounds(21, 68, 25, 16);
		panel.add(lblR_2);
		
		r1 = new JTextField();
		r1.setHorizontalAlignment(SwingConstants.CENTER);
		r1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r1.setEditable(false);
		r1.setFocusable(false);
		r1.setColumns(10);
		r1.setBounds(46, 36, 70, 26);
		panel.add(r1);
		
		JLabel lblR_1 = new JLabel("R1");
		lblR_1.setFocusable(false);
		lblR_1.setBounds(21, 42, 25, 16);
		panel.add(lblR_1);
		
		r3 = new JTextField();
		r3.setHorizontalAlignment(SwingConstants.CENTER);
		r3.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r3.setEditable(false);
		r3.setFocusable(false);
		r3.setColumns(10);
		r3.setBounds(46, 88, 70, 26);
		panel.add(r3);
		
		JLabel lblR_3 = new JLabel("R3");
		lblR_3.setFocusable(false);
		lblR_3.setBounds(21, 94, 25, 16);
		panel.add(lblR_3);
		
		r4 = new JTextField();
		r4.setHorizontalAlignment(SwingConstants.CENTER);
		r4.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r4.setEditable(false);
		r4.setFocusable(false);
		r4.setColumns(10);
		r4.setBounds(46, 114, 70, 26);
		panel.add(r4);
		
		JLabel lblR_4 = new JLabel("R4");
		lblR_4.setFocusable(false);
		lblR_4.setBounds(21, 120, 25, 16);
		panel.add(lblR_4);
		
		r5 = new JTextField();
		r5.setHorizontalAlignment(SwingConstants.CENTER);
		r5.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r5.setEditable(false);
		r5.setFocusable(false);
		r5.setColumns(10);
		r5.setBounds(46, 140, 70, 26);
		panel.add(r5);
		
		JLabel lblR_5 = new JLabel("R5");
		lblR_5.setFocusable(false);
		lblR_5.setBounds(21, 146, 25, 16);
		panel.add(lblR_5);
		
		r6 = new JTextField();
		r6.setHorizontalAlignment(SwingConstants.CENTER);
		r6.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r6.setEditable(false);
		r6.setFocusable(false);
		r6.setColumns(10);
		r6.setBounds(46, 166, 70, 26);
		panel.add(r6);
		
		JLabel lblR_6 = new JLabel("R6");
		lblR_6.setFocusable(false);
		lblR_6.setBounds(21, 172, 25, 16);
		panel.add(lblR_6);
		
		r7 = new JTextField();
		r7.setHorizontalAlignment(SwingConstants.CENTER);
		r7.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		r7.setEditable(false);
		r7.setFocusable(false);
		r7.setColumns(10);
		r7.setBounds(46, 192, 70, 26);
		panel.add(r7);
		
		JLabel lblR_7 = new JLabel("R7");
		lblR_7.setFocusable(false);
		lblR_7.setBounds(21, 198, 25, 16);
		panel.add(lblR_7);
		
		d0 = new JTextField();
		d0.setText("0");
		d0.setHorizontalAlignment(SwingConstants.RIGHT);
		d0.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d0.setFocusable(false);
		d0.setEditable(false);
		d0.setColumns(10);
		d0.setBounds(118, 10, 62, 26);
		panel.add(d0);
		
		d1 = new JTextField();
		d1.setText("0");
		d1.setHorizontalAlignment(SwingConstants.RIGHT);
		d1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d1.setFocusable(false);
		d1.setEditable(false);
		d1.setColumns(10);
		d1.setBounds(118, 36, 62, 26);
		panel.add(d1);
		
		d2 = new JTextField();
		d2.setText("0");
		d2.setHorizontalAlignment(SwingConstants.RIGHT);
		d2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d2.setFocusable(false);
		d2.setEditable(false);
		d2.setColumns(10);
		d2.setBounds(118, 62, 62, 26);
		panel.add(d2);
		
		d3 = new JTextField();
		d3.setText("0");
		d3.setHorizontalAlignment(SwingConstants.RIGHT);
		d3.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d3.setFocusable(false);
		d3.setEditable(false);
		d3.setColumns(10);
		d3.setBounds(118, 88, 62, 26);
		panel.add(d3);
		
		d4 = new JTextField();
		d4.setText("0");
		d4.setHorizontalAlignment(SwingConstants.RIGHT);
		d4.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d4.setFocusable(false);
		d4.setEditable(false);
		d4.setColumns(10);
		d4.setBounds(118, 114, 62, 26);
		panel.add(d4);
		
		d5 = new JTextField();
		d5.setText("0");
		d5.setHorizontalAlignment(SwingConstants.RIGHT);
		d5.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d5.setFocusable(false);
		d5.setEditable(false);
		d5.setColumns(10);
		d5.setBounds(118, 140, 62, 26);
		panel.add(d5);
		
		d6 = new JTextField();
		d6.setText("0");
		d6.setHorizontalAlignment(SwingConstants.RIGHT);
		d6.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d6.setFocusable(false);
		d6.setEditable(false);
		d6.setColumns(10);
		d6.setBounds(118, 166, 62, 26);
		panel.add(d6);
		
		d7 = new JTextField();
		d7.setText("0");
		d7.setHorizontalAlignment(SwingConstants.RIGHT);
		d7.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		d7.setFocusable(false);
		d7.setEditable(false);
		d7.setColumns(10);
		d7.setBounds(118, 192, 62, 26);
		panel.add(d7);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusable(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.reset();
				console.setText("");
				update();
			}
		});
		btnReset.setBounds(55, 369, 99, 29);
		add(btnReset);
		
		btnStep = new JButton("Step");
		btnStep.setFocusable(false);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.step();
				update();
			}
		});
		btnStep.setBounds(9, 400, 99, 29);
		add(btnStep);
		
		btnStepOver = new JButton("Step Over");
		btnStepOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText("running...");
				memView.select(0, 0);	
				btnStep.setEnabled(false);
				btnStepOver.setEnabled(false);
				btnRun.setEnabled(false);
				btnStop.setEnabled(true);
				machine.stepOver();
			}
		});
		btnStepOver.setBounds(104, 400, 92, 29);
		add(btnStepOver);

		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText("running...");
				memView.select(0, 0);	
				btnStep.setEnabled(false);
				btnStepOver.setEnabled(false);
				btnRun.setEnabled(false);
				btnStop.setEnabled(true);
				machine.run();
			}
		});
		btnRun.setBounds(9, 432, 99, 29);
		add(btnRun);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.stop();
			}
		});
		btnStop.setBounds(104, 432, 99, 29);
		add(btnStop);

		JScrollPane memScroll = new JScrollPane();
		memScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		memScroll.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		memScroll.setBounds(235, 29, 421, 485);
		add(memScroll);
		
		memView = new JTextArea();
		memView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int line = getLine(memView.getText(), memView.getCaretPosition());
					machine.toggleBreakPnt(line);
				}
				update();
			}
		});
		memView.setEditable(false);
		memView.setFont(new Font("Courier New", Font.PLAIN, 13));
		memScroll.setViewportView(memView);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(17, 541, 50, 16);
		add(lblStatus);
		
		status = new JTextField();
		status.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		status.setBounds(67, 536, 589, 26);
		add(status);
		status.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(31, 270, 123, 71);
		add(panel_1);
		panel_1.setLayout(null);
		
		cbNeg = new JCheckBox("Neg");
		cbNeg.setFocusable(false);
		cbNeg.setBounds(28, 6, 57, 23);
		panel_1.add(cbNeg);
		
		cbZero = new JCheckBox("Zero");
		cbZero.setFocusable(false);
		cbZero.setSelected(true);
		cbZero.setBounds(28, 25, 60, 23);
		panel_1.add(cbZero);
		
		cbPos = new JCheckBox("Pos");
		cbPos.setFocusable(false);
		cbPos.setBounds(28, 43, 54, 23);
		panel_1.add(cbPos);
		
		JButton btnLoadProgram = new JButton("Load Program");
		btnLoadProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				if (currentDir != null)
					fileChooser.setCurrentDirectory(currentDir);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Prolog Files", new String[] {"lc3"}));
				if (fileChooser.showOpenDialog(memView) == JFileChooser.APPROVE_OPTION) {
				  File file = fileChooser.getSelectedFile();
				  try {
					  machine.loadProgram(file.getAbsolutePath());
					  update();
					  JFrame frame = (JFrame)memView.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
					  frame.setTitle("LC3 Simulator    " + machine.programName());
					  currentDir = file.getParentFile();
				  }
				  catch(Exception error) {}
				}
				
			}
		});
		btnLoadProgram.setBounds(48, 473, 117, 29);
		add(btnLoadProgram);		
		
		console = new JTextArea();
		console.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				memView.setCaretPosition(console.getText().length());
			}
		});
		console.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				e.consume();
				machine.consoleNewChar(c);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					e.consume();
			}
		});
		console.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		console.setBounds(69, 574, 589, 145);
		add(console);
		
		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setBounds(9, 574, 58, 16);
		add(lblConsole);
								
		machine = new LC3(this);
		update();
	}
	
	public void update() {
		memView.setText(machine.memToString());
		
		r0.setText(String.format("%04X", machine.readReg(0) & 0xFFFF));
		r1.setText(String.format("%04X", machine.readReg(1) & 0xFFFF));
		r2.setText(String.format("%04X", machine.readReg(2) & 0xFFFF));
		r3.setText(String.format("%04X", machine.readReg(3) & 0xFFFF));
		r4.setText(String.format("%04X", machine.readReg(4) & 0xFFFF));
		r5.setText(String.format("%04X", machine.readReg(5) & 0xFFFF));
		r6.setText(String.format("%04X", machine.readReg(6) & 0xFFFF));
		r7.setText(String.format("%04X", machine.readReg(7) & 0xFFFF));
		
		d0.setText(String.format("%d", machine.readReg(0)));
		d1.setText(String.format("%d", machine.readReg(1)));
		d2.setText(String.format("%d", machine.readReg(2)));
		d3.setText(String.format("%d", machine.readReg(3)));
		d4.setText(String.format("%d", machine.readReg(4)));
		d5.setText(String.format("%d", machine.readReg(5)));
		d6.setText(String.format("%d", machine.readReg(6)));
		d7.setText(String.format("%d", machine.readReg(7)));		
		
		cbNeg.setSelected(machine.neg());
		cbZero.setSelected(machine.zero());
		cbPos.setSelected(machine.pos());
		
		status.setText(machine.status());
		
		int line = machine.pcLine();
		try {
			int start = memView.getLineStartOffset(line);
			int end = memView.getLineEndOffset(line);
			memView.setCaretPosition(start);
			memView.select(start, end);	
			
            Rectangle viewRect = memView.modelToView(start);
            // Scroll to make the rectangle visible
            memView.scrollRectToVisible(viewRect);
		}
		catch (Exception e) {}
		
		btnStep.setEnabled(true);
		btnStepOver.setEnabled(true);
		btnRun.setEnabled(true);
		btnStop.setEnabled(false);
	}
	
	private int getLine(String text, int index) {
		int line = 0;
		int i = 0;
		while (i != index) {
			if (text.charAt(i) == '\n')
				line++;
			i++;
		}
		return line;
	}
	
	public void writeConsole(String s) {
		console.setText(console.getText() + s);
		int end = console.getText().length();
		console.select(end, end);	
		console.setCaretPosition(end);
	}
}
