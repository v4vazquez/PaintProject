package swingPaint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SwingPaint extends swingPaint.DrawArea {

    //    JButton clearButton, squareButton, circleButton,
//            changeColorButton, eraseButton, inspirationButton, saveButton;
    //have it listed below to test icons
    //JButton blackButton;
    JSlider brushSize;
    swingPaint.DrawArea drawArea;
    JLabel drawingPrompt;
    JTextField textField;


    Icon drawIcon = new ImageIcon("C:\\Users\\Student\\workspace\\SideProjects\\src\\main\\resources\\buttonPictures\\draw.png");
    Icon eraseIcon = new ImageIcon("C:\\Users\\Student\\workspace\\SideProjects\\src\\main\\resources\\buttonPictures\\eraser.png");
    Icon paletteIcon = new ImageIcon("C:\\Users\\Student\\workspace\\SideProjects\\src\\main\\resources\\buttonPictures\\palette.png");
    Icon saveIcon = new ImageIcon("C:\\Users\\Student\\workspace\\SideProjects\\src\\main\\resources\\buttonPictures\\save.png");
    Icon garbageIcon = new ImageIcon("C:\\Users\\Student\\workspace\\SideProjects\\src\\main\\resources\\buttonPictures\\clear.png");
    Icon squareIcon = new ImageIcon();
    Icon circleIcon = new ImageIcon();
    JButton blackButton = new JButton(drawIcon);
    JButton eraseButton = new JButton(eraseIcon);
    JButton changeColorButton = new JButton(paletteIcon);
    JButton saveButton = new JButton(saveIcon);
    JButton squareButton = new JButton();
    JButton circleButton = new JButton();
    JButton clearButton = new JButton(garbageIcon);
    JButton newPromptButton = new JButton("new prompt");

    public static void main(String[] args) {
        new SwingPaint().show();

    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                drawArea.clear();
            } else if (e.getSource() == blackButton) {
                drawArea.black();
            }else if (e.getSource() == eraseButton){
                drawArea.eraser();
            }else if(e.getSource() == squareButton){
                drawArea.rectangle();
            }else if(e.getSource() == circleButton){
                drawArea.circle();
            }else if(e.getSource() == changeColorButton){
                drawArea.setColorPicker(e);
            }else if(e.getSource() == brushSize){
                drawArea.brushSize();
            }else if(e.getSource() == saveButton){
                drawArea.saveButton();
            }else if(e.getSource() == newPromptButton){
                drawingPrompt.setText(drawingPrompt());
            }
        }
    };

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            brushSize = new JSlider();
            brushSize.addChangeListener(changeListener);
        }
    };


    public void show() {
        // create main frame
        JFrame frame = new JFrame("Swing Paint");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        // create draw area
        drawArea = new swingPaint.DrawArea();

        // add to content pane
        content.add(drawArea, BorderLayout.CENTER);

        // create controls to apply colors and call clear feature
        JPanel controls = new JPanel();

        blackButton = new JButton(drawIcon);
        blackButton.addActionListener(actionListener);

        eraseButton = new JButton(eraseIcon);
        eraseButton.addActionListener(actionListener);

        clearButton = new JButton(garbageIcon);
        clearButton.addActionListener(actionListener);

        squareButton = new JButton("Square");
        squareButton.addActionListener(actionListener);

        circleButton = new JButton("Circle");
        circleButton.addActionListener(actionListener);

        changeColorButton = new JButton(paletteIcon);
        changeColorButton.addActionListener(actionListener);

        saveButton = new JButton(saveIcon);
        saveButton.addActionListener(actionListener);

        newPromptButton = new JButton("New Prompt");
        newPromptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPrompt = drawArea.drawingPrompt();
                drawingPrompt.setText(newPrompt);
            }
        });
        //newPromptButton.addActionListener(actionListener);
        //shows the drawing prompt method
        JLabel drawingPrompt = new JLabel(drawingPrompt());

        JTextField textField = new JTextField(brushSize());



        //THIS LINE BELOW WORKS UNCOMMENT IF YOU HAVE TO
//        JLabel drawingPrompt = new JLabel(drawingPrompt());


        // add to panel
        controls.add(blackButton);
        controls.add(eraseButton);
        controls.add(clearButton);
        controls.add(squareButton);
        controls.add(circleButton);
        controls.add(changeColorButton);
//        controls.add(brushSize);
        controls.add(textField);
        controls.add(saveButton);
        controls.add(drawingPrompt);
        controls.add(newPromptButton);


        // add to content pane
        content.add(controls, BorderLayout.NORTH);
        //sets the size of the frame
        frame.setSize(1200, 1000);
        // can close frame by clicking an X button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the swing paint result
        frame.setVisible(true);

    }

}