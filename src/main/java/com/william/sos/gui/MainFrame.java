package com.william.sos.gui;

import com.william.sos.model.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int DEFAULT_SIZE = 5;

    private BoardPanel boardPanel;
    private JLabel turnLabel;

    private String blueLetter = "S";
    private String redLetter = "S";

    private String gameMode = "Simple";

    public MainFrame() {
        setTitle("SOS Game - Sprint 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initTopPanel();

        initPlayerAndBoardPanels(DEFAULT_SIZE);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        turnLabel = new JLabel("Current turn: " + boardPanel.getGame().getCurrentTurn());
        bottomPanel.add(turnLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(150, e -> updateTurnLabel());
        timer.start();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Board Size:"));
        JTextField sizeInput = new JTextField(String.valueOf(DEFAULT_SIZE), 5);
        topPanel.add(sizeInput);

        JRadioButton simpleGame = new JRadioButton("Simple", true);
        JRadioButton generalGame = new JRadioButton("General");
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(simpleGame);
        modeGroup.add(generalGame);
        topPanel.add(simpleGame);
        topPanel.add(generalGame);

        simpleGame.addActionListener(e -> gameMode = "Simple");
        generalGame.addActionListener(e -> gameMode = "General");

        JButton startButton = new JButton("New Game");
        startButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(sizeInput.getText());
                if (size < 3) throw new NumberFormatException();

                Component oldCenter = ((BorderLayout) getContentPane().getLayout())
                        .getLayoutComponent(BorderLayout.CENTER);
                if (oldCenter != null) remove(oldCenter);

                blueLetter = "S";
                redLetter = "S";

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
        boardPanel = new BoardPanel(boardSize, gameMode);

        JPanel bluePanel = createPlayerPanel("Blue", true);
        JPanel redPanel = createPlayerPanel("Red", false);

        JPanel boardWrapper = new JPanel(new GridBagLayout());
        boardWrapper.add(boardPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bluePanel, BorderLayout.WEST);
        mainPanel.add(boardWrapper, BorderLayout.CENTER);
        mainPanel.add(redPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        updateBoardLetter();
    }

    private JPanel createPlayerPanel(String name, boolean isBlue) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(name + " Player"));
        panel.setPreferredSize(new Dimension(100, 0));

        JRadioButton sButton = new JRadioButton("S", true);
        JRadioButton oButton = new JRadioButton("O");
        ButtonGroup group = new ButtonGroup();
        group.add(sButton);
        group.add(oButton);
        panel.add(sButton);
        panel.add(oButton);

        sButton.addActionListener(e -> {
            if (isBlue) blueLetter = "S"; else redLetter = "S";
            updateBoardLetter();
        });
        oButton.addActionListener(e -> {
            if (isBlue) blueLetter = "O"; else redLetter = "O";
            updateBoardLetter();
        });

        return panel;
    }

    private void updateTurnLabel() {
        Game.Player current = boardPanel.getGame().getCurrentTurn();
        turnLabel.setText("Current turn: " + current);
        updateBoardLetter();
    }

    private void updateBoardLetter() {
        Game.Player current = boardPanel.getGame().getCurrentTurn();
        if (current == Game.Player.BLUE)
            boardPanel.setCurrentLetter(blueLetter);
        else
            boardPanel.setCurrentLetter(redLetter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
