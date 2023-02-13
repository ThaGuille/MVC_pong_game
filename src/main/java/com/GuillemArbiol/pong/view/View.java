package com.GuillemArbiol.pong.view;


import com.GuillemArbiol.pong.controller.Controller;
import com.GuillemArbiol.pong.model.BallModel;
import com.GuillemArbiol.pong.model.Model;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import com.GuillemArbiol.pong.model.ModelViewOps;
import com.GuillemArbiol.pong.model.Score;
import io.reactivex.subjects.PublishSubject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Observable;

import static javafx.application.Application.launch;


public class View /*implements KeyListener*/ {

    private JFrame frame;
    JPanel panel = new JPanel();
    JButton btnNewButton = new JButton("");
    JRadioButton rdbtnNewRadioButton = new JRadioButton("");
    JLabel label = new JLabel("0 hits, 0 misses");
    private final JLabel myName = new JLabel("Guillem");

    private final ModelViewOps modelViewOps = new Model();
    private Controller controller;

    //starts here
    public static void main(String[] args) {
        try {
            Model model = new Model();
        } catch (Exception e) {
            e.printStackTrace();
        }
        View window = new View();
        window.start();
        window.frame.setVisible(true);
        window.playModel();
    }

    public View() {}

    private void start(){
        initialize();
    }

    //starts controller
    private void playModel(){
        controller = new Controller(this, modelViewOps);
        controller.start();
    }

    //initialize UI
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 628, 452);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel.setBackground(Color.ORANGE);
        panel.setBounds(0, 398, 612, 15);
        frame.getContentPane().add(panel);
        panel.setLayout(null);


        label.setBounds(10, 0, 106, 14);
        panel.add(label);
        myName.setBounds(496, 0, 116, 14);

        panel.add(myName);


        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnNewButton.setBackground(Color.BLUE);
        btnNewButton.setBounds(228, 11, 150, 23);
        frame.getContentPane().add(btnNewButton);
        rdbtnNewRadioButton.setBackground(Color.WHITE);


        rdbtnNewRadioButton.setSelected(true);
        rdbtnNewRadioButton.setForeground(Color.YELLOW);
        rdbtnNewRadioButton.setBounds(286, 328, 21, 15);
        frame.getContentPane().add(rdbtnNewRadioButton);


    }

    /*------------setters-------------*/

    public void setPaddlePosition(int paddleX){
        btnNewButton.setBounds(paddleX , 11, 150, 23);
    }

    public void setBallPosition(int x, int y){
        System.out.println("ball position working   ");
        rdbtnNewRadioButton.setBounds(x, y, 21, 15);
        frame.repaint();
    }

    public void setScore(Score scores){
        System.out.println("score working");
        label.setText(scores.getHits() + " hits, " + scores.getMiss() + " misses");
    }



    }

