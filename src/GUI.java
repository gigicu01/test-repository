import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;

/**
 * @author ceseg
 *
 */
public class GUI extends JPanel {
    public Sequencer sequencer;
    public Sequence sequence;
    public Track track;
    public String[] trackList = { "Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
	    "Hand Clap" };
    public int lines = 6; // x
    public int beat = 8; // y
    public JCheckBox[][] checkBoxes = new JCheckBox[lines][beat];
    public int strut = 95;
    public int tempo = 100;
    int[] instruments = { 35, 42, 46, 38, 49, 39 };

    private static final long serialVersionUID = 8599443346776413041L;

    public GUI(int width, int height) {

	JPanel organizer = new JPanel();
	JPanel beatPanel = new JPanel();
	JPanel trackPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	this.add(organizer);

	organizer.add(trackPanel);
	organizer.add(beatPanel);
	organizer.add(buttonPanel);

	JComboBox<String>[] selectors = new JComboBox[6];

	trackPanel.setPreferredSize(new Dimension(125, 600));

	JButton start = new JButton("Start");
	start.addActionListener(new MyStartListener());

	JButton stop = new JButton("Stop");
	stop.addActionListener(new MyStopActionListener());

	JButton upTempo = new JButton("Tempo Up");
	upTempo.addActionListener(new MyUpTempoListener());

	JButton downTempo = new JButton("Tempo Down");
	downTempo.addActionListener(new MyDownTempoListener());

	JButton clear = new JButton("Clear");
	clear.addActionListener(new MyClearListener());

	JButton random = new JButton("Randomize");
	random.addActionListener(new MyRandomListener());

	buttonPanel.add(start);
	// buttonPanel.add(Box.createVerticalStrut(strut));
	buttonPanel.add(stop);
	// buttonPanel.add(Box.createVerticalStrut(strut));
	buttonPanel.add(upTempo);
	// buttonPanel.add(Box.createVerticalStrut(strut));
	buttonPanel.add(downTempo);
	// buttonPanel.add(Box.createVerticalStrut(strut));
	buttonPanel.add(clear);
	// buttonPanel.add(Box.createVerticalStrut(strut));
	buttonPanel.add(random);
	// buttonPanel.add(Box.createVerticalStrut(strut));

	// Initialize the values of the selector array
	JComboBox<String> trackOne = new JComboBox<>(trackList);
	trackOne.setSelectedItem(trackList[0]);

	JComboBox<String> trackTwo = new JComboBox<>(trackList);
	trackTwo.setSelectedItem(trackList[1]);

	JComboBox<String> trackThree = new JComboBox<>(trackList);
	trackThree.setSelectedItem(trackList[2]);

	JComboBox<String> trackFour = new JComboBox<>(trackList);
	trackFour.setSelectedItem(trackList[3]);

	JComboBox<String> trackFive = new JComboBox<>(trackList);
	trackFive.setSelectedItem(trackList[4]);

	JComboBox<String> trackSix = new JComboBox<>(trackList);
	trackSix.setSelectedItem(trackList[5]);

	selectors[0] = trackOne;
	trackOne.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// if((String)trackOne.getSelectedItem() )
		for (int i = 0; i < beat; i++) {
		    checkBoxes[0][i].setText((String) trackOne.getSelectedItem());
		}
	    }
	});

	selectors[1] = trackTwo;
	trackTwo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < beat; i++) {
		    checkBoxes[1][i].setText((String) trackTwo.getSelectedItem());
		}
	    }
	});

	selectors[2] = trackThree;
	trackThree.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < beat; i++) {
		    checkBoxes[2][i].setText((String) trackThree.getSelectedItem());
		}

	    }
	});

	selectors[3] = trackFour;
	trackFour.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < beat; i++) {
		    checkBoxes[3][i].setText((String) trackFour.getSelectedItem());
		}
	    }
	});

	selectors[4] = trackFive;
	trackFive.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < beat; i++) {
		    checkBoxes[4][i].setText((String) trackFive.getSelectedItem());
		}
	    }
	});

	selectors[5] = trackSix;
	trackSix.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < beat; i++) {
		    checkBoxes[5][i].setText((String) trackSix.getSelectedItem());
		}
	    }
	});

	trackPanel.add(selectors[0]);
	trackPanel.add(Box.createVerticalStrut(strut));
	trackPanel.add(selectors[1]);
	trackPanel.add(Box.createVerticalStrut(strut));
	trackPanel.add(selectors[2]);
	trackPanel.add(Box.createVerticalStrut(strut));
	trackPanel.add(selectors[3]);
	trackPanel.add(Box.createVerticalStrut(strut));
	trackPanel.add(selectors[4]);
	trackPanel.add(Box.createVerticalStrut(strut));
	trackPanel.add(selectors[5]);
	trackPanel.add(Box.createVerticalStrut(strut));

	// Blahhh

	Color[] colors = new Color[6];

	// Initialize the values of the color array
	colors[0] = Color.red;
	colors[1] = Color.orange;
	colors[2] = Color.yellow;
	colors[3] = Color.green;
	colors[4] = Color.cyan;
	colors[5] = Color.magenta;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(screenSize.width / 2, screenSize.height / 2);
	this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
		screenSize.height / 2 - this.getSize().height / 2);

	beatPanel.setLayout(new GridLayout(lines, beat));

	for (int currentLine = 0; currentLine < lines; currentLine++) {
	    for (int currentBeat = 0; currentBeat < beat; currentBeat++) {
		JCheckBox box = new JCheckBox(selectors[currentLine].getSelectedItem().toString());
		checkBoxes[currentLine][currentBeat] = box;
		box.setPreferredSize(new Dimension(100, 100));
		box.setBackground(colors[currentLine]);
		beatPanel.add(box);
	    }
	}
	setUpMidi();
    }

    public void setUpMidi() {
	try {
	    sequencer = MidiSystem.getSequencer();
	    sequencer.open();
	    sequence = new Sequence(Sequence.PPQ, 4);
	    track = sequence.createTrack();
	    sequencer.setTempoInBPM(100);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void buildTrackAndStart() {
	// Getting all of the selected check boxes for a specific track
	int[] trackList = new int[beat];

	sequence.deleteTrack(track);
	track = sequence.createTrack();

	// For each track, get the selected boxes
	for (int currentLine = 0; currentLine < lines; currentLine++) {
	    int key = instruments[currentLine];

	    // For the number of beats,
	    for (int currentBeat = 0; currentBeat < instruments.length; currentBeat++) {
		// Retrieving the check box
		JCheckBox jc = (JCheckBox) checkBoxes[currentLine][currentBeat];
		if (jc.isSelected()) {
		    trackList[currentBeat] = key;
		} else {
		    trackList[currentBeat] = 0;
		}
	    }

	    makeTracks(trackList, 9);
	    track.add(makeEvent(176, 1, 127, 0, 16));
	}

	track.add(makeEvent(192, 9, 1, 0, 15));
	try {
	    sequencer.setSequence(sequence);
	    sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
	    sequencer.start();
	    sequencer.setTempoInBPM(100);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    class MyStartListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    buildTrackAndStart();
	}
    }

    class MyStopActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    sequencer.stop();
	}
    }

    class MyUpTempoListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    float tempoFactor = sequencer.getTempoFactor();
	    sequencer.setTempoFactor((float) (tempoFactor * 1.03));
	}
    }

    class MyDownTempoListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    float tempoFactor = sequencer.getTempoFactor();
	    sequencer.setTempoFactor((float) (tempoFactor * 0.97));
	}
    }

    class MyClearListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    for (int currentLine = 0; currentLine < lines; currentLine++) {
		for (int currentBeat = 0; currentBeat < beat; currentBeat++) {
		    checkBoxes[currentLine][currentBeat].setSelected(false);
		}
	    }
	}
    }

    class MyRandomListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    for (int currentLine = 0; currentLine < lines; currentLine++) {
		for (int currentBeat = 0; currentBeat < beat; currentBeat++) {
		    boolean vee = Math.random() < 0.1;
		    checkBoxes[currentLine][currentBeat].setSelected(vee);
		}
	    }
	}
    }

    public void makeTracks(int[] list, int soundBank) {
	for (int i = 0; i < trackList.length; i++) {
	    int key = list[i];

	    if (key != 0) {
		track.add(makeEvent(144, soundBank, key, 100, i));
		track.add(makeEvent(128, soundBank, key, 100, i + 1));
	    }
	}
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
	MidiEvent event = null;

	try {
	    ShortMessage a = new ShortMessage();
	    a.setMessage(comd, chan, one, two);
	    event = new MidiEvent(a, tick);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return event;
    }
}
