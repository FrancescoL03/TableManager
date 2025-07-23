import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class TableManager extends JPanel implements MouseListener {

    /*
    This mouse listener checks if the clicked table is occupied or not.
    If it's free a red "X" appears on the table, if it's already occupied,
    the red "X" is removed.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        int coordinateX = e.getX();
        int coordinateY = e.getY();

        for (Block tavolo : tables) {
            if(tavolo.x <= coordinateX && coordinateX <= tavolo.x+32 &&
               tavolo.y <= coordinateY && coordinateY <= tavolo.y+32){
                if(!tavolo.occupied){
                    tavolo.occupied = true;
                    repaint();
                }
                else{
                    tavolo.occupied = false;
                    repaint();
                }
            }
        }
    }

    //These listeners are unused but must be implemented
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    //This inner class represents a single tile in the panel
    class Block{
        int x,y, height, width;
        Image image;
        boolean occupied = false;

        Block(Image image, int x, int y, int width, int height){
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    //Required configuration variables
    private int columns = 24;
    private int rows = 24;
    private int sizeTile = 32;
    private int boardHeight = rows*sizeTile;
    private int boardWidth = columns*sizeTile;

    private Image tableImage;
    private Image floorImage;

    HashSet<Block> tables;
    HashSet<Block> floors;

    //T = table, f = floor
    //This is the tile map that defines the layout of the panel
    private String[] tileMap = {
            "ffffffffffffffffffffffff",
            "fTTfffTTffffTTfffTTfffTf",
            "ffffffffffffffffffffffff",
            "ffTTfffTTfffTTfffTTfffff",
            "ffffffffffffffffffffffff",
            "fTTffffTTfffTTffffTTfffT",
            "ffffffffffffffffffffffff",
            "fTTfffTTffffTTfffTTfffTf",
            "ffffffffffffffffffffffff",
            "fTTfffTTfffTTffffTTfffTf",
            "ffffffffffffffffffffffff",
            "ffTTffffTTfffTTfffTTffff",
            "ffffffffffffffffffffffff",
            "fTTfffTTffffTTfffTTfffTf",
            "ffffffffffffffffffffffff",
            "fTTffffTTfffTTffffTTfffT",
            "ffffffffffffffffffffffff",
            "fTTfffTTffffTTfffTTfffTf",
            "ffffffffffffffffffffffff",
            "ffTTfffTTfffTTfffTTfffff",
            "ffffffffffffffffffffffff",
            "fTTffffTTfffTTffffTTfffT",
            "ffffffffffffffffffffffff",
            "ffffffffffffffffffffffff",
            "ffffffffffffffffffffffff"
    };


    //Constructor of the panel class, initializes the panel and loads resources
    TableManager(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addMouseListener(this);

        tableImage = new ImageIcon(getClass().getResource("tables.jpeg")).getImage();
        floorImage = new ImageIcon(getClass().getResource("floor.png")).getImage();

        load();
    }

    //This method builds the table/floor layout based on the tile map
    public void load(){
        floors = new HashSet<Block>();
        tables = new HashSet<Block>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<columns; c++){
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);
                int x = c*sizeTile;
                int y = r*sizeTile;

                if(tileMapChar == 'f'){
                    Block floor = new Block(floorImage, x, y, sizeTile, sizeTile);
                    floors.add(floor);
                }
                else if(tileMapChar == 'T'){
                    Block table = new Block(tableImage, x, y, sizeTile, sizeTile);
                    tables.add(table);
                }

            }
        }
    }

    //Responsible for drawing the table and floor images
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        for(Block floor : floors){
            g.drawImage(floor.image, floor.x, floor.y, floor.width, floor.height, null);
        }
        for(Block table : tables){
            g.drawImage(table.image, table.x, table.y, table.width, table.height, null);
            if(table.occupied){
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("X", table.x + 10, table.y + 22);
            }
        }
    }
}
