import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Window {
	private ConversionInfo conversionInfo = new ConversionInfo();
	
	private String currencies[] = {"GBP","EUR","USD"};
	
	private JFrame frame = new JFrame("Currencies");
	private JPanel panel = new JPanel();

	private JTextField inputMoney = new JTextField();
	private JLabel outputMoney = new JLabel("0",JLabel.CENTER);
	private JLabel equals = new JLabel("=",JLabel.CENTER);

	private JComboBox<String> inputCombo = new JComboBox<String>(currencies);
	private JLabel convertTo = new JLabel("to",JLabel.CENTER);
	private JComboBox<String> outputCombo = new JComboBox<String>(currencies);
	
	private JButton switchCurrency = new JButton("Switch");
	
	public Window() { 
		setupWindow();
		setupElements();
		setComboBoxes(conversionInfo.currencies);
		setupListeners();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
	}
	
	private void setupWindow() {
		frame.setSize(400,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridBagLayout());
	    panel.setBackground(Color.white);
	    panel.setSize(300,300);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private void setupElements() {
		GridBagConstraints gbcontraints = new GridBagConstraints();

	    gbcontraints.fill = GridBagConstraints.HORIZONTAL;
		//row one
	    gbcontraints.gridx = 0;
	    gbcontraints.gridy = 0;
		panel.add(inputMoney,gbcontraints);
	    gbcontraints.gridx = 1;
	    gbcontraints.gridy = 0;
		panel.add(equals,gbcontraints);
	    gbcontraints.gridx = 2;
	    gbcontraints.gridy = 0;
		panel.add(outputMoney,gbcontraints);
		
		//row two
	    gbcontraints.gridx = 0;
	    gbcontraints.gridy = 1;
		panel.add(inputCombo,gbcontraints);
	    gbcontraints.gridx = 1;
	    gbcontraints.gridy = 1;
		panel.add(convertTo,gbcontraints);
	    gbcontraints.gridx = 2;
	    gbcontraints.gridy = 1;
		panel.add(outputCombo,gbcontraints);
		//remove input choice from output choices
		
		//row three
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 2;
		gbcontraints.gridwidth = 1;
		panel.add(switchCurrency,gbcontraints);
	}
	
	private void setupListeners() {
		//change output when input is changed		
		inputMoney.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				changeOutputBox();
			}
			public void removeUpdate(DocumentEvent e) {
				changeOutputBox();
			}
			public void changedUpdate(DocumentEvent e) {
				changeOutputBox();
			}
		});
		
		switchCurrency.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	switchCurrencies();
				changeOutputBox();
		    }
		});
		
		inputCombo.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				changeOutputBox();
		    }
		});
		
		outputCombo.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				changeOutputBox();
		    }
		});
		
	}
	
	private void setComboBoxes(String[] currencies) {
		inputCombo.removeAllItems();
		outputCombo.removeAllItems();
		for(String currency : currencies) {
			inputCombo.addItem(currency);
			outputCombo.addItem(currency);
		}
	}
	
	private void changeOutputBox() {
		String inputBoxPerm = inputMoney.getText();
		double result = ConversionInfo.getValue(inputBoxPerm, inputCombo.getSelectedItem().toString(), outputCombo.getSelectedItem().toString());
		//if nothings on the input box
		if(inputBoxPerm.length() == 0) {
			outputMoney.setText("0");
		} else {
			outputMoney.setText(Double.toString(result));
		}
	}
	
	private void switchCurrencies() {
		String temp = inputCombo.getSelectedItem().toString();
		inputCombo.setSelectedItem(outputCombo.getSelectedItem().toString());
		outputCombo.setSelectedItem(temp);
	}
}