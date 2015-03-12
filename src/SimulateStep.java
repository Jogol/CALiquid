/**
 * Created by Jonathan on 11/03/2015.
 */
public class SimulateStep {

    //int[][] grid;
    final int maxFluid = 5;
    final int AIR = 0;
    final int GROUND = 6;
    int[][] calculated;

    public SimulateStep() {

    }

    /**
     * We take a grid here, apply our rules on it, step it forward in the simulation and send it back.
     * @param grid the last step
     * @return the grid with the next step
     */
    public int[][] StepGrid(int[][] grid) {

        //0 = not calculated, 1 = calculated
        calculated = new int[grid.length][grid[0].length];

        int[][] updated = grid.clone();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                //Rules

                if (calculated[i][j] == 1)
                    continue;

                int here = grid[i][j];

                //If it passes this, we have fluid
                if (here == 0 || here == 6) {
                    calculated[i][j] = 1;
                    continue;
                }


                int below = grid[i][j+1];

                //Check below
                if (below == AIR) {
                    updated[i][j] = AIR;
                    updated[i][j+1] = here;
                    calculated[i][j] = 1;
                    calculated[i][j+1] = 1;
                    continue;
                }

                //If some amount of fluid, less than full
                if (below != GROUND && below != maxFluid) {
                    int spaceLeft = maxFluid - below;
                    if (spaceLeft >= here) {
                        updated[i][j] = AIR;
                        updated[i][j+1] = below + here;
                        calculated[i][j] = 1;
                        calculated[i][j+1] = 1;
                        continue;
                    } else {
                        updated[i][j] = here - spaceLeft;
                        updated[i][j+1] = maxFluid;
                        calculated[i][j] = 1;
                        calculated[i][j+1] = 1;
                    }

                }


                //Calculate where the rest of the fluid will go
                if (below == GROUND || updated[i][j+1] == maxFluid) {

                    int left = grid[i-1][j];
                    int right = grid[i+1][j];
                    //Check if both are full
                    boolean leftFull = false;
                    boolean rightFull = false;
                    if(left == maxFluid || left == GROUND)
                        leftFull = true;
                    if(right == maxFluid || right == GROUND)
                        rightFull = true;

                    if (leftFull && rightFull) {

                        calculated[i][j] = 1;
                        continue;
                    }


                    if (left < here && right >= here) { //flows left

                        int sideDifference = here - left;
                        sideDifference = sideDifference/2;
                        if (sideDifference == 0)
                            sideDifference = 1;
                        updated[i][j] = grid[i][j] - sideDifference;
                        updated[i-1][j] += sideDifference;
                        calculated[i][j] = 1;
                        calculated[i-1][j] = 1;

                    } else if (right < here && left >= here) { //flows right

                        int sideDifference = here - right;
                        sideDifference = sideDifference/2;
                        if (sideDifference == 0)
                            sideDifference = 1;
                        updated[i][j] = grid[i][j] - sideDifference;
                        updated[i+1][j] += sideDifference;
                        calculated[i][j] = 1;
                        calculated[i+1][j] = 1;

                    } else if (left < here && right < here) { //even out
                        int total = left + right + here;
                        total = total/3;
                        updated[i][j] = total+1;
                        updated[i+1][j] = total+1;
                        updated[i-1][j] = total+1;
                        calculated[i][j] = 1;
                        calculated[i-1][j] = 1;
                        calculated[i+1][j] = 1;
                    }


                }


            }
        }



        return updated;
    }

}
