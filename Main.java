import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //The frame will consist of a 24x24 grid
        int columns = 24;
        int rows = 24;
        //Each tile will be 32px
        int sizeTiles = 32;
        //Frame dimensions in pixels
        int boardHeight = rows*sizeTiles;
        int boardWidth = columns*sizeTiles;

        //Set up the application window
        JFrame frame = new JFrame("Table Manager System");
        frame.setLocationRelativeTo(null);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create the main panel and add it to the frame
        TableManager tm = new TableManager();
        frame.add(tm);
        frame.pack();
        tm.requestFocus();
        frame.setVisible(true);
    }
}