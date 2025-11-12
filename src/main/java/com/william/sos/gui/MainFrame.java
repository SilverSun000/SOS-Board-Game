package com.william.sos.gui;

import com.william.sos.model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class MainFrame extends JFrame {

    private static final int DEFAULT_SIZE = 5;

    private BoardPanel boardPanel;
    private JLabel turnLabel;

    // Track which letter each player wants to place
    private String blueLetter = "S";
    private String redLetter = "S";

    // Game mode
    private String gameMode = "Simple";

    // Player panels
    private JPanel bluePanel;
    private JPanel redPanel;

    public MainFrame() {
        setTitle("SOS Game - Sprint 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==== Top Panel ====
        initTopPanel();

        // ==== Center: Board + Player Panels ====
        initPlayerAndBoardPanels(DEFAULT_SIZE);

        // ==== Bottom Panel ====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        turnLabel = new JLabel("Current turn: " + boardPanel.getGame().getCurrentTurn());
        bottomPanel.add(turnLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // ==== Timer to update turn label ====
        Timer timer = new Timer(100, e -> updateTurnLabel());
        timer.start();

        // ==== Frame settings ====
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();

        // Board size input
        topPanel.add(new JLabel("Board Size:"));
        JTextField sizeInput = new JTextField(String.valueOf(DEFAULT_SIZE), 5);
        topPanel.add(sizeInput);

        // Game mode selection
        JRadioButton simpleGame = new JRadioButton("Simple", true);
        JRadioButton generalGame = new JRadioButton("General");
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(simpleGame);
        modeGroup.add(generalGame);
        topPanel.add(simpleGame);
        topPanel.add(generalGame);

        simpleGame.addActionListener(e -> gameMode = "Simple");
        generalGame.addActionListener(e -> gameMode = "General");

        // New Game button
        JButton startButton = new JButton("New Game");
        startButton.addActionListener(e -> {
        int size;
        try {
            size = Integer.parseInt(sizeInput.getText());
            if (size < 3) throw new NumberFormatException();

            // Remove old main panel (board + player panels)
            // The previous main panel is the CENTER component of the JFrame
            Component oldCenter = ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if (oldCenter != null) {
                remove(oldCenter);
            }

            // Recreate board + player panels
            initPlayerAndBoardPanels(size);

            revalidate();
            repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid board size. Must be 3 or greater.");
        }
    });

        topPanel.add(startButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void initPlayerAndBoardPanels(int boardSize) {
        // ==== Board Panel ====
        boardPanel = new BoardPanel(boardSize);

        // ==== Blue Player Panel ====
        bluePanel = new JPanel();
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));
        bluePanel.setBorder(BorderFactory.createTitledBorder("Blue Player"));
        bluePanel.setPreferredSize(new Dimension(100, 0)); // slightly left
        JRadioButton blueS = new JRadioButton("S", true);
        JRadioButton blueO = new JRadioButton("O");
        ButtonGroup blueGroup = new ButtonGroup();
        blueGroup.add(blueS);
        blueGroup.add(blueO);
        bluePanel.add(blueS);
        bluePanel.add(blueO);
        blueS.addActionListener(e -> blueLetter = "S");
        blueO.addActionListener(e -> blueLetter = "O");

        // ==== Red Player Panel ====
        redPanel = new JPanel();
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.Y_AXIS));
        redPanel.setBorder(BorderFactory.createTitledBorder("Red Player"));
        redPanel.setPreferredSize(new Dimension(100, 0)); // slightly right
        JRadioButton redS = new JRadioButton("S", true);
        JRadioButton redO = new JRadioButton("O");
        ButtonGroup redGroup = new ButtonGroup();
        redGroup.add(redS);
        redGroup.add(redO);
        redPanel.add(redS);
        redPanel.add(redO);
        redS.addActionListener(e -> redLetter = "S");
        redO.addActionListener(e -> redLetter = "O");

        // ==== Center wrapper for board ====
        JPanel boardWrapper = new JPanel(new GridBagLayout());
        boardWrapper.add(boardPanel);

        // ==== Main panel combining everything ====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bluePanel, BorderLayout.WEST);
        mainPanel.add(boardWrapper, BorderLayout.CENTER);
        mainPanel.add(redPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // ==== Letter supplier for BoardPanel ====
        boardPanel.setLetterSupplier(() -> 
            boardPanel.getGame().getCurrentTurn() == Game.Player.BLUE ? blueLetter : redLetter
        );
    }

    private void updateTurnLabel() {
        Game.Player current = boardPanel.getGame().getCurrentTurn();
        turnLabel.setText("Current turn: " + current);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
