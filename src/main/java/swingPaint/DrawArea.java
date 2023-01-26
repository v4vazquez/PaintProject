package swingPaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

public class DrawArea extends JComponent {

    // Image in which we're going to draw
    private Image image;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    private int currentX, currentY, oldX, oldY, thePencilSize, penSize, oldxRectangle, oldYRectangle,oldXCircle,oldYCircle;
    //potentially get the brush size
    private TextField bSize;
    private int currentAction =1;

    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //save coordinates x,y when mouse is pressed

                oldX = e.getX();
                oldY = e.getY();

//                oldX = e.getX();
//                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                currentX = e.getX();
                currentY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //gets the coordinates x,y when mouse is dragged
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    // draw line if g2 context not null
                    //brushSize(g2.drawLine(oldX,oldY,currentX,currentY));

                    //g2.setStroke(new BasicStroke(brushSize()));
                    g2.setStroke(new BasicStroke(4));
                    g2.drawLine(oldX, oldY, currentX, currentY);

                    // refresh draw area to repaint
                    repaint();
                    // store current coordinates x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;

                }
            }

        });


    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            // enable antialiasing, this is the process of making the edges of lins less jagged
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    //method to clear the painting
    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }


    //sets the initial color of the brush to black
    public void black() {
        g2.setPaint(Color.black);
    }
    public void eraser(){
        g2.setPaint(Color.white);
    }

    //set up of the button to pick which color you want your brush
    //courtesy of https://www.javatpoint.com/java-jcolorchooser
    public void setColorPicker(ActionEvent e){
        //sets the initial color when you open up the color picker
        Color initialColor = Color.red;
        //On top of the newly opened window it will ask you to select a color
        Color color = JColorChooser.showDialog(this,"Select a color",initialColor);
        g2.setPaint(color);
    }

    //creates a rectangle when dragged
    public void rectangle(){
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldxRectangle = e.getX();
                oldYRectangle = e.getY();
                System.out.println(oldxRectangle+ " " + oldYRectangle +" "+ currentX + " " +currentY);

            }

            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                drawRectangle();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Draw a preview of the rectangle
                g2.fillRect(oldxRectangle, oldYRectangle, e.getX() - oldxRectangle, e.getY() - oldYRectangle);
                repaint();
            }

        });
    }

    public void drawRectangle() {
        g2.drawRect(oldxRectangle, oldYRectangle, currentX - oldxRectangle, currentY - oldYRectangle);
        repaint();
        System.out.println(oldxRectangle+ " " + oldYRectangle +" "+ currentX + " " +currentY);
    }


    //creates a circle
    public void circle(){

        g2.drawOval(oldX,oldY,currentX,currentY);
        //fills oval
        //       g2.fillOval(oldX,oldY,currentX,currentY);
    }
    //    public void circle(){
//    addMouseListener(new MouseAdapter() {
//        public void mousePressed(MouseEvent e) {
//            oldXCircle = e.getX();
//            oldYCircle = e.getY();
//            System.out.println(oldXCircle + " " + oldYCircle +" "+ currentX + " " +currentY);
//
//        }
//
//        public void mouseReleased(MouseEvent e) {
//            currentX = e.getX();
//            currentY = e.getY();
//            drawCircle();
//        }
//    });
//
//    addMouseMotionListener(new MouseMotionAdapter() {
//        public void mouseDragged(MouseEvent e) {
//            // Draw a preview of the rectangle
//            g2.fillOval(oldXCircle, oldYCircle, e.getX() - oldXCircle, e.getY() - oldYCircle);
//            repaint();
//        }
//
////               public void mouseMoved(MouseEvent e){
////                   oldX = e.getX();
////                   oldY = e.getY();
////               }
//    });
//}
//    public void drawCircle() {
//        g2.drawOval(oldXCircle, oldYCircle, currentX - oldXCircle, currentY - oldYCircle);
//        repaint();
//        System.out.println(oldXCircle + " " + oldYCircle +" "+ currentX + " " +currentY);
//    }
    public int brushSize(){

        JTextField strokeSizeField = new JTextField(5);
        int strokeSize = 0;
        strokeSizeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strokeSizeText = strokeSizeField.getText();
                int strokeSize = Integer.parseInt(strokeSizeText);
            }
        });
        return strokeSize;
//       what was before
//        TextField brushSize = new TextField();
//        String inputBrushSize = bSize.getText();
//        int inputBrushSizeValue = Integer.parseInt(inputBrushSize);
//
//        return inputBrushSizeValue;
//

        //the slider attempt
//       JLabel label = new JLabel();
//
//        JSlider jSlider = new JSlider(1,10,5);
//        //set the paint ticks and tracks
//        jSlider.setPaintTrack(true);
//        jSlider.setPaintTicks(true);
//        jSlider.setPaintLabels(true);
//        //set spacing
//        jSlider.setMajorTickSpacing(2);
//        jSlider.setMinorTickSpacing(1);
//      //  jSlider.addChangeListener();
//        //
//        label.setText("the slider is currently at a "+ jSlider.getValue());



//        g2.setStroke(jSlider.setMajorTickSpacing(1));
//        jSlider.setMinorTickSpacing(0);
//       g2.setStroke();

    }

    //shoutout to sharen torres for the reminder on how to call this method
    public String drawingPrompt(){

        String [] animalDrawingPrompts = {"pig","rat","ox","tiger","rabbit","dragon","snake",
                "horse","goat","monkey","rooster","dog","bee","giraffe","squirrel"};

        String [] whatAnimalIsDoing = {" packing heat", " multiplying"," scratching their butt"," in a rap battle"," reading a book about capitalism"," doing planks",
                " learning a new cooking recipe"," doing pilates"," mowing the lawn"," putting up a christmas tree", " learning to drive", " shopping for a new outfit",
                " learning to code", " chopping lumber", " looking at themselves in the mirror"," gardening"," flying"," catching butterflies",
                " robbing a bank", " in a drag race", " in a beauty pageant"," playing basketball"," playing soccer"," brushing hair",
                " doing a stand up comedy routine"," walking a dog", " in space", " going on a date", " crying while watching a movie",
                " dancing in the rain"," painting a portrait of you"," scolding their kid"," cleaning the house"," building a fence"};

        int arrayIndex1 = new Random().nextInt(animalDrawingPrompts.length);
        int arrayIndex2 = new Random().nextInt(whatAnimalIsDoing.length);
        String randomDrawingPrompt = "Draw a "+ (animalDrawingPrompts[arrayIndex1] + whatAnimalIsDoing[arrayIndex2] + "!");
        return randomDrawingPrompt;
    }


    public void saveButton(){
        try{
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bufferedImage.createGraphics();
            bGr.drawImage(image,0,0,null);
            javax.imageio.ImageIO.write(bufferedImage,"PNG",new File("C:/Users/Student/Downloads/paint.png"));
            bGr.dispose();
            System.out.println("Saved Successfully");
        }catch (Exception ex){
            Logger.getLogger("it didnt save");
        }

    }

}