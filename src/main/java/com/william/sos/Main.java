package com.william.sos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SOS Game");
	JLabel label = new JLabel("Welcome to SOS!", SwingConstants.CENTER);
	frame.add(label);
	frame.setSize(400, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}
