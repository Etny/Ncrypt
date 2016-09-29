package Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main implements ActionListener{
	
	public static Dimension SIZE = new Dimension(400, 200);
	
	JTextField inputField;
	JTextField pwField;
	JTextField outputField;
	
	JLabel inputLabel;
	JLabel pwLabel;
	JLabel outputLabel;
	
	JButton enButton;
	JButton deButton;
	JButton copyButton;
	
	Encrypt encrypt = new Encrypt();
	Decrypt decrypt = new Decrypt();
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		JFrame frame = new JFrame("Ncrypt");
		
		frame.setSize(SIZE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(null);
		
		inputField = new JTextField();
		pwField = new JTextField();
		outputField = new JTextField();
		enButton = new JButton("Encrypt");
		deButton = new JButton("Decrypt");
		copyButton = new JButton("Copy");
		inputLabel = new JLabel("Input:");
		pwLabel = new JLabel("PW:");
		outputLabel = new JLabel("Output:");
		
		outputField.setEditable(false);
		
		frame.add(inputField);
		frame.add(pwField);
		frame.add(outputField);
		frame.add(enButton);
		frame.add(deButton);
		frame.add(copyButton);
		frame.add(inputLabel);
		frame.add(pwLabel);
		frame.add(outputLabel);
		
		inputField.setBounds(50, 25, 300, 25);
		pwField.setBounds(50, 50, 300, 25);
		outputField.setBounds(50, 75, 200, 25);
		enButton.setBounds(50, 110, 150, 30);
		deButton.setBounds(200, 110, 150, 30);
		copyButton.setBounds(250, 75, 100, 25);
		inputLabel.setBounds(8, 25, 100, 25);
		pwLabel.setBounds(8, 50, 100, 25);
		outputLabel.setBounds(8, 75, 100, 25);
		
		enButton.addActionListener(this);
		deButton.addActionListener(this);
		copyButton.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(enButton == e.getSource()){
			outputField.setText(encrypt.encryptString(inputField.getText(), pwField.getText()));
		}else if(deButton == e.getSource()){
			outputField.setText(decrypt.decrypt(inputField.getText(), pwField.getText()));
		}else if(copyButton == e.getSource()){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(outputField.getText()), null);
		}
	}
	
	

}
