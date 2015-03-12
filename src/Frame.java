import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jonathan Golan on 11/03/2015.
 */
public class Frame extends JFrame {

    static Screen s;

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1007, 1030);
        setResizable(false);
        setTitle("Graphics");

        init();
    }

    private void init() {
        setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 1, 0, 0));

        s = new Screen();
        add(s);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();

        simulate();
       // s.ReDoPaint();

    }

    private static void simulate() {
        int[][] grid = makeMap();
        SimulateStep simulator = new SimulateStep();

        s.setBlockHeight(10);
        s.setBlockWidth(10);

        while(true) {
            s.setGrid(grid);
            grid = simulator.StepGrid(grid);
            s.ReDoPaint();
            System.out.println("Step");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                //Handle exception
            }
        }
    }

    //Ground is right side, up is left, bottom is right, left is top
    private static int[][] makeMap() {
        int[][] map =  {{6,6,6,6,6,6,6,6,6,6},
                        {0,0,0,0,0,0,5,0,6,6},
                        {0,0,0,0,0,0,0,0,0,6},
                        {0,0,0,0,0,0,0,0,0,6},
                        {0,5,0,6,0,0,0,0,0,6},
                        {0,0,0,0,0,0,0,0,0,6},
                        {0,0,0,0,0,0,0,0,0,6},
                        {0,0,0,0,0,0,0,0,0,6},
                        {0,0,5,0,0,6,0,5,0,6},
                        {6,6,6,6,6,6,6,6,6,6}};

        return map;
    }

    private static int[][] makeLarge() {
        int[][] map = new int[30][30];
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 6;
            map[29][i] = 6;
            map[i][29] = 6;
            if (i < 7)
                map[i][15] = 6;

            if (i < 5)
                map[i][7] = 5;
        }


        //Points



        return map;
    }


}
