package com.CodingBlocks;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    public static int Board_size = 3;


    public static enum GameStatus {
        Xwins, Zwins, Tie, Incomplete
    }

    private JButton[][] buttons = new JButton[Board_size][Board_size]; //create an 2d array in which button is present in every index;
    boolean crossTurn = true;


    public TicTacToe() {
        super.setTitle("TicTacToe");
        super.setSize(800, 800);
        GridLayout grid = new GridLayout(Board_size, Board_size);
        super.setLayout(grid);
        Font font = new Font("Comic Sans", 1, 150);
        for (int row = 0; row < Board_size; row++) {
            for (int col = 0; col < Board_size; col++) {
                JButton button = new JButton("");//mist1
                buttons[row][col] = button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }

        }
        super.setResizable(false);
        super.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedbutton = (JButton) e.getSource();
        makemove(clickedbutton);
        GameStatus gs = this.getgamestatus();
        if (gs == GameStatus.Incomplete) {
            return;
        }
        declareWinner(gs);

        int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart the Game");
        if (choice == JOptionPane.YES_OPTION) {
            for (int row = 0; row < Board_size; row++) {
                for (int col = 0; col < Board_size; col++) {
                    buttons[row][col].setText("");
                }
            }
            crossTurn = true;
        } else {
            super.dispose();
        }
    }

    private void makemove(JButton clickedbutton) {
        String btntext = clickedbutton.getText();
        if (btntext.length() > 0) {
            JOptionPane.showMessageDialog(this, "Invalid Move");
        } else {
            if (crossTurn) {
                clickedbutton.setText("X");
            } else {
                clickedbutton.setText("0");
            }
            crossTurn = !crossTurn;
        }
    }

    private GameStatus getgamestatus() {
        String text1 = "", text2 = "";
        int row = 0, col = 0;
        //text inside rows
        row=0;
        while (row < Board_size) {
            col = 0;
            while (col < Board_size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row][col + 1].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                col++;

            }
            if (col == Board_size - 1) {
                if (text1.equals("X")) {
                    return GameStatus.Xwins;
                } else {
                    return GameStatus.Zwins;

                }
            }
            row++;

        }
        //text inside cols
        col = 0;
        while (col< Board_size) {
            row = 0;
            while (row < Board_size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row + 1][col].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;

            }
            if (row == Board_size - 1) {
                if (text1.equals("X")) {
                    return GameStatus.Xwins;
                } else {
                    return GameStatus.Zwins;

                }
            }
            col++;


        }
        //test in diagnol 1
        row=0;
        col=0;
        while (row < Board_size - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row++;
            col++;

        }
        if (row == Board_size - 1) {
            if (text1.equals("X")) {
                return GameStatus.Xwins;
            } else {
                return GameStatus.Zwins;

            }
        }
        //test in 2nd diagnol
        row = Board_size - 1;
        col=0;
        while (row > 0) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row - 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row--;
            col++;
        }
        if (row == 0) {
            if (text1.equals("X")) {
                return GameStatus.Xwins;
            } else {
                return GameStatus.Zwins;

            }
        }
        String txt = "";
        for (row = 0; row < Board_size; row++) {
            for (col = 0; col < Board_size; col++) {
                txt = buttons[row][col].getText();
                if (txt.length() == 0) {
                    return GameStatus.Incomplete;
                }

            }
        }

        return GameStatus.Tie;

    }


    private void declareWinner(GameStatus gs) {
        if (gs == GameStatus.Xwins) {
            JOptionPane.showMessageDialog(this, "X Wins");
        } else if (gs == GameStatus.Zwins) {
            JOptionPane.showMessageDialog(this, "Z Wins");
        } else {
            JOptionPane.showMessageDialog(this, "It is Tie");

        }


    }
}




