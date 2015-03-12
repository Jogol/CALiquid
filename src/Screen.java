import javax.swing.*;
import java.awt.*;

/**
 * Created by Jonathan Golan on 11/03/2015.
 */
public class Screen extends JPanel {

    int blockWidth;
    int blockHeight;
    int startX = 0;
    int startY = 0;

    int[][] grid;// = new int[10][10];

    public Screen() {
        repaint();
    }

    /**
     * 0 is air
     * 1,2,3,4,5 are different levels of water, 5 being full.
     * 6 is ground
     * @param g
     */
    public void paint (Graphics g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int value = grid[i][j];
                //clear old
                g.setColor(Color.WHITE);
                g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10, blockWidth*10, blockHeight*10);
                switch (value) {
                    case 0:
                        //draw air
                        g.setColor(Color.WHITE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10, blockWidth*10, blockHeight*10);
                        break;
                    case 1:
                        g.setColor(Color.BLUE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10 + 80, blockWidth*10, blockHeight*10 - 80);
                        break;
                    case 2:
                        g.setColor(Color.BLUE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10 + 60, blockWidth*10, blockHeight*10 - 60);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10 + 40, blockWidth*10, blockHeight*10 - 40);
                        break;
                    case 4:
                        g.setColor(Color.BLUE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10 + 20, blockWidth*10, blockHeight*10 - 20);
                        break;
                    case 5:
                        g.setColor(Color.BLUE);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10, blockWidth*10, blockHeight*10);
                        break;
                    case 6:
                        g.setColor(Color.GREEN);
                        g.fillRect(startX + blockWidth*i*10, startY + blockHeight*j*10, blockWidth*10, blockHeight*10);
                    default:
                        break;
                }

                g.setColor(Color.BLACK);
                g.drawRect(startX + blockWidth*i*10, startY + blockHeight*j*10, blockWidth*10, blockHeight*10);
            }
        }
        //g.drawRect(startX, startY, blockWidth, blockHeight);



        //g.drawRect(x, y, rectwidth, rectheight);

    }

    public void ReDoPaint() {
        repaint();
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setBlockWidth(int blockWidth) {
        this.blockWidth = blockWidth;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }
}
