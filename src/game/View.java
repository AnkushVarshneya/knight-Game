/**
 * @(#)View.java
 * Assignment#1
 * @author Ankush Varshneya
 * @student# 100853074
 * Main View
 */

package game;

import javax.swing.*;

import gameobjects.Board;
import gameobjects.GameTypeEnum;

import java.awt.*;

@SuppressWarnings("serial")
public class View extends JPanel {
	private Model				model; // The model to which this view is attached.
	private Board				board; // The board to which this view is attached.

	private JLabel				dimensionLabel, pawnLabel, frameRateLabel, typeLabel, pawnRemainingLabel, movesMadeLabel, statLabel;
	private JList<GameTypeEnum>	typeList;
	private JTextField			dimensionField, pawnField, frameRateField, pawnRemainingField, movesMadeField;
	private JButton				startStopButton;
	private JTextArea			statField;
	
	// Public method to allow access to board.
	public Board				getBoard() { return board;}

	// Public method to allow access to JComponents.
	public JLabel				getDimensionLabel()		{ return dimensionLabel; }
	public JLabel				getPawnLabel()			{ return pawnLabel; }
	public JLabel				geFrameRateLabel()		{ return frameRateLabel; }
	public JLabel				getTypeLabel()			{ return typeLabel; }
	public JLabel				getPawnRemainingLabel()	{ return pawnRemainingLabel; }
	public JLabel				getMovesMadeLabel()		{ return movesMadeLabel; }
	public JLabel				getStatLabel()			{ return statLabel; }
	
	public JList<GameTypeEnum>	getTypeList()			{ return typeList; }
	
	public JTextField			getDimensionField()		{ return dimensionField; }
	public JTextField			getPawnField()			{ return pawnField; }
	public JTextField			getFrameRateField()		{ return frameRateField; }
	public JTextField			getPawnRemainingField()	{ return pawnRemainingField; }
	public JTextField			getMovesMadeField()		{ return movesMadeField; }
	
	public JTextArea			getStatField()			{ return statField; }

	public JButton				getStartStopButton()	{ return startStopButton; }
	
	public View() {
    	// Choose grid bag layout
		this.setLayout(new GridBagLayout());

		//Dimension
		this.dimensionLabel = new JLabel("Dimentione:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.dimensionLabel,
			this.makeConstraints(	0, 0, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.dimensionLabel);
		this.dimensionField = new JTextField();
		this.dimensionField.setEditable(true);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.dimensionField,
			this.makeConstraints(	0, 1, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.dimensionField);

		//Pawn
		this.pawnLabel = new JLabel("Number Of Pawns:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.pawnLabel,
			this.makeConstraints(	0, 2, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.pawnLabel);

		this.pawnField = new JTextField();
		this.pawnField.setEditable(true);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.pawnField,
			this.makeConstraints(	0, 3, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.pawnField);

		//Frame Rate
		this.frameRateLabel = new JLabel("Frame Rate (ms):");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.frameRateLabel,
			this.makeConstraints(	0, 4, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.frameRateLabel);

		this.frameRateField = new JTextField();
		this.frameRateField.setEditable(true);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.frameRateField,
			this.makeConstraints(	0, 5, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.frameRateField);

		// Type
		this.typeLabel = new JLabel("Type:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.typeLabel,
			this.makeConstraints(	0, 6, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.typeLabel);
		
		this.typeList = new JList<GameTypeEnum>(GameTypeEnum.values());
		this.typeList.setSelectedIndex(0);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.typeList,
			this.makeConstraints(	0, 7, 1, 1, 0, 1,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.BOTH,
									GridBagConstraints.NORTH));
		this.add(this.typeList);

		//Pawns Remaining
		this.pawnRemainingLabel = new JLabel("Pawns Remaining:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.pawnRemainingLabel,
			this.makeConstraints(	0, 8, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.pawnRemainingLabel);

		this.pawnRemainingField = new JTextField();
		this.pawnRemainingField.setEditable(false);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.pawnRemainingField,
			this.makeConstraints(	0, 9, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.pawnRemainingField);

		//Pawn
		this.movesMadeLabel = new JLabel("Moves Made:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.movesMadeLabel,
			this.makeConstraints(	0, 10, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.movesMadeLabel);

		this.movesMadeField = new JTextField();
		this.movesMadeField.setEditable(false);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.movesMadeField,
			this.makeConstraints(	0, 11, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.movesMadeField);

		//Start/Stop button
		this.startStopButton = new JButton();
		((GridBagLayout) this.getLayout()).setConstraints(
			this.startStopButton,
			this.makeConstraints(	0, 12, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.startStopButton);

		//Stat
		this.statLabel = new JLabel("Stats:");
		((GridBagLayout) this.getLayout()).setConstraints(
			this.statLabel,
			this.makeConstraints(	0, 13, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.statLabel);

		this.statField = new JTextArea();
		this.statField.setEditable(false);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.statField,
			this.makeConstraints(	0, 14, 1, 1, 0, 0,
									new Insets(4, 4, 4, 4),
									GridBagConstraints.HORIZONTAL,
									GridBagConstraints.NORTH));
		this.add(this.statField);
		
		// Update the view
		this.update();
	}

	// Used make constraints and to shorten code.
	private GridBagConstraints makeConstraints(int gX, int gY, int gW, int gH, int wX, int wY, Insets insets, int fill, int anchor) {
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = gX;
		c.gridy = gY;
		c.gridwidth = gW;
		c.gridheight = gH;
		c.weightx = wX;
		c.weighty = wY;
		c.insets = insets;
		c.fill = fill;
		c.anchor = anchor;

		return c;
	}

	// Updates the view based on the inputed model.
	public void update() {

		// If the button is not selected its labeled "start" otherwise its labeled stop.
		if(!this.startStopButton.isSelected()) this.startStopButton.setText("Start");
		else this.startStopButton.setText("Stop");
		// Update fields.

		if (this.model != null) {
			this.dimensionField.setText(this.model.getDimentions()+"");
			this.pawnField.setText(this.model.getRemainingPawns()+"");
			this.frameRateField.setText(this.model.getFrameRate()+"");
			this.pawnRemainingField.setText(this.model.getRemainingPawns()+"");
			this.movesMadeField.setText(this.model.getMovesMade()+"");
			this.statField.setText(this.model.getStats());
		}
	}

	/**
	 *	Makes a new board based on the model passed in. 
	 * @param model the model to make the board with
	 */
	public void getNewBoard(Model model) {
		// Remove the board button, then the board if they exist
		if (this.board != null) {
			this.board.removeAll();
			this.remove(this.board);
		}
		// Update the model to the one passed in to this function.
		this.model = model;
		// Make a new board, then used the gridConstraints to add a new board.
		this.board = new Board(this.model);
		((GridBagLayout) this.getLayout()).setConstraints(
			this.board, 
			this.makeConstraints(	1, 0, 1, 15, 1, 1,
									new Insets(0, 0, 0, 0),
									GridBagConstraints.BOTH,
									GridBagConstraints.CENTER));
		this.add(this.board);
		// Update the UI to match the new board.
		this.board.updateUI();
		this.updateUI();
	}

    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {
		View aView = new View();
		aView.getNewBoard(new Model());
    	
		JFrame frame = new JFrame("The Knight Game View Tester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(952, 712); // Manually computed sizes.
		frame.getContentPane().add(aView);
		frame.setVisible(true);
	}

}