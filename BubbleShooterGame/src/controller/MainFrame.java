package controller;

import implementation.Canvas;
import utils.Constants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements ActionListener {

    private final SettingsPanel rightPanel;
    private final Canvas leftPanel;

    public MainFrame() {
        setLayout(new BorderLayout());

        rightPanel = new SettingsPanel(this);
        rightPanel.initComponents();

        leftPanel = new Canvas();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Bubble Shooter by CD :)");
        setSize(Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y);
        setResizable(true);
        setVisible(true);
        setSize(2 * Constants.WINDOW_SIZE_X - getContentPane().getSize().width,
                2 * Constants.WINDOW_SIZE_Y - getContentPane().getSize().height);

    }


    public void init() {
        leftPanel.displayHighscore(0, true);
    }


    public void gameWon(long score) {
        rightPanel.updateScore(score);
        leftPanel.displayHighscore(score, true);
    }

    public void gameLost(long score) {
        rightPanel.updateScore(score);
        leftPanel.displayHighscore(score, false);
    }


    public void updateScore(long score) {
        rightPanel.updateScore(score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEW GAME")) {
            leftPanel.newGame(rightPanel.getRow(), rightPanel.getColor());
            leftPanel.getGame().setMainFrame(this);
        } else if (e.getActionCommand().equals("STOP GAME")) {
            if (leftPanel.getGame() != null) {
                leftPanel.getGame().stop();
                leftPanel.displayHighscore(0, true);
            }
        }
    }
}
