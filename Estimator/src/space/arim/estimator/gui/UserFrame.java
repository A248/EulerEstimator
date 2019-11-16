package space.arim.estimator.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.mariuszgromada.math.mxparser.Function;

import space.arim.estimator.EulerApproximator;
import space.arim.estimator.EulerEstimator;

public class UserFrame implements AutoCloseable {
	
	private final JFrame frame;
	private final JPanel panel;
	
	private final GridBagLayout layout;
	private final GridBagConstraints constraints;
	
	private final JTextField amountField;
	private final JTextField stepField;
	private final JTextField precisionField;
	private final JTextField functionField;
	private final JTextField initialXField;
	private final JTextField initialYField;
	
	private final JButton runButton;
	private final JTextArea outputArea;
	private final PrintStream output;
	
	private int amount = 10;
	private double step = 0.1D;
	private int precision = 4;
	private String expression = "x+y";
	private double initialX = 0D;
	private double initialY = 2D;
	private HashMap<String, EulerApproximator> approximators = new HashMap<String, EulerApproximator>();
	
	private static final String INVALID_FUNCTION = "\n\nError:\nThe function you have entered is not a valid expression. Please check your syntax and try again.";
	
	public UserFrame(String title, String version) {
		
	   frame = new JFrame(title + " " + version);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   panel = new JPanel();
	   panel.setBorder(BorderFactory.createTitledBorder(title));
	   layout = new GridBagLayout();
	   constraints = new GridBagConstraints();
	   
	   panel.setLayout(layout);

	   add(new JLabel("Amount of iterations"), 1, 1, 8);
	   add(amountField = new JTextField("10"), 1, 2, 8);
	   
	   add(new JLabel("Step value"), 1, 4, 8);
	   add(stepField = new JTextField("0.1"), 1, 5, 8);
	   
	   add(new JLabel("Decimal precision"), 1, 7, 8);
	   add(precisionField = new JTextField("4"), 1, 8, 8);
	   
	   add(new JLabel("dy/dx in terms of x and y"), 1, 10, 8);
	   add(functionField = new JTextField("x+y"), 1, 11, 8);
	   
	   add(new JLabel("Initial X"), 1, 13, 8);
	   add(initialXField = new JTextField("0"), 1, 14, 8);
	   
	   add(new JLabel("Initial Y"), 1, 16, 8);
	   add(initialYField = new JTextField("2"), 1, 17, 8);
	   
	   add(runButton = new JButton("Execute"), 1, 19, 20, 1, 0, 1);
	   add(outputArea = new JTextArea(2,10), 10, 1, 8, 17, 15, 10);
	   add(new JScrollPane(outputArea), 10, 1, 8, 17, 15, 10);
	   runButton.addMouseListener(new GuiMouseListener(this));
	   
		/*
		 * add(new JLabel("Amount of iterations"), 1, 1, 6); add(new
		 * JLabel("Step value"), 8, 1, 5); add(new JLabel("Decimal precision"), 14, 1,
		 * 5);
		 * 
		 * add(amountField = new JTextField("10"), 1, 2, 6); add(stepField = new
		 * JTextField("0.1"), 8, 2, 5); add(precisionField = new JTextField("4"), 14, 2,
		 * 5);
		 * 
		 * add(new JLabel("dy/dx in terms of x and y"), 1, 4, 6); add(new
		 * JLabel("Initial X"), 8, 4, 5); add(new JLabel("Initial Y"), 14, 4, 5);
		 * 
		 * add(functionField = new JTextField("x+y"), 1, 5, 6); add(initialXField = new
		 * JTextField("0"), 8, 5, 5); add(initialYField = new JTextField("2"), 14, 5,
		 * 5);
		 * 
		 * add(runButton = new JButton("Execute"), 1, 7, 18, 1, 1, 0); add(outputArea =
		 * new JTextArea(2,10), 1, 9, 18, 10, 1, 10); add(new JScrollPane(outputArea),
		 * 1, 9, 18, 10, 1, 10); runButton.addMouseListener(new GuiMouseListener(this));
		 */

	   System.setErr(output = new PrintStream(new ExtendedOutput(outputArea)));
	   
	   frame().setContentPane(new JScrollPane(panel));
	   frame().pack();
	   frame().setVisible(true);
	}
	
	private void add(Component component, int x, int y, int width, int height, int weightx, int weighty) {
		constraints().gridx = x;
		constraints().gridy = y;
		constraints().gridwidth = width;
		constraints().gridheight = height;
		constraints().fill = GridBagConstraints.BOTH;
		constraints().weightx = weightx;
		constraints().weighty = weighty;
		layout().setConstraints(component, constraints());
		panel().add(component);
	}
	
	private void add(Component component, int x, int y, int width, int height) {
		add(component, x, y, width, height, 1, 1);
	}
	
	private void add(Component component, int x, int y, int width) {
		add(component, x, y, width, 1, 1, 1);
	}
	
	private JFrame frame() {
		return frame;
	}
	
	private JPanel panel() {
		return panel;
	}
	
	private GridBagLayout layout() {
		return layout;
	}
	
	private GridBagConstraints constraints() {
		return constraints;
	}
	
	public void start() {
		
	}
	
	private void parseFields() {
		try {
			int amount = Integer.parseInt(amountField.getText());
			if (amount > 0) {
				this.amount = amount;
			} else {
				amountField.setText(Integer.toString(this.amount));
			}
		} catch (NumberFormatException ex) {
			amountField.setText(Integer.toString(this.amount));
		}
		try {
			double step = Double.parseDouble(stepField.getText());
			if (step != 0D) {
				this.step = step;
			} else {
				stepField.setText(Double.toString(this.step));
			}
		} catch (NumberFormatException ex) {
			stepField.setText(Double.toString(this.step));
		}
		try {
			int precision = Integer.parseInt(precisionField.getText());
			if (precision > 0) {
				this.precision = precision;
			} else {
				precisionField.setText(Integer.toString(this.precision));
			}
		} catch (NumberFormatException ex) {
			precisionField.setText(Integer.toString(this.precision));
		}
		expression = functionField.getText();
		try {
			initialX = Double.parseDouble(initialXField.getText());
		} catch (NumberFormatException ex) {
			initialXField.setText(Double.toString(initialX));
		}
		try {
			initialY = Double.parseDouble(initialYField.getText());
		} catch (NumberFormatException ex) {
			initialYField.setText(Double.toString(initialY));
		}
	}
	
	private EulerApproximator fetchFromCache(String expression, double initialX, double initialY) {
		if (approximators.containsKey(expression)) {
			EulerApproximator approx = approximators.get(expression);
			if (approx.initialX() == initialX && approx.initialY() == initialY) {
				return approx;
			}
		}
		return null;
	}
	
	void execute() {
		parseFields();
		EulerApproximator approx = fetchFromCache(expression, initialX, initialY);
		if (approx == null) {
			Function function = new Function("D(x,y) = " + expression);
			if (function.checkSyntax()) {
				approx = new EulerApproximator(function, initialX, initialY);
				approximators.put(expression, approx);
			} else {
				output.println(INVALID_FUNCTION);
				return;
			}
		}
		
		long previous = System.nanoTime();
		approx.approximations(output, precision, step, amount);
		EulerEstimator.spitTime(output, previous, precision);
	}

	@Override
	public void close() throws Exception {
		
	}
	
	
	   
}
