public class MooreRules {
    private static final int numNeighbors = 9;
    private boolean[] birth;
    private boolean[] survival;

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        birth = new boolean[numNeighbors]; //creating arrays to for rules. Put true in places where birth/dead should happen, put false in others
        survival = new boolean[numNeighbors];

        for (int neighbor: birthNeighbors){
            birth[neighbor] = true;
        }
        for (int neighbor: survivalNeighbors){
            survival[neighbor] = true;
        }

    }

    public boolean shouldBeBorn(int liveNeighbors){
        return birth[liveNeighbors]; //checks array to see if shouldBeBorn is true
    }

    public boolean shouldSurvive(int liveNeighbors){
        int random; //checks array to see if shouldSurvive is true
        if (liveNeighbors >= 4){
            random = (int)(Math.random()*100); //probability of death by overpopulation
            if (random >= 20){
                return false;
            }
            else{
                return true;
            }
        }
        if (liveNeighbors <= 1){
            random = (int)(Math.random()*100); //probability of death by loneliness
            if (random >= 40){
                return false;
            }
            else{
                return true;
            }
        }
        return survival[liveNeighbors];
    }

    public CellState applyRules (CellState cellState, int liveNeighbors){
        if (cellState == CellState.ALIVE && !shouldSurvive(liveNeighbors)){
            return CellState.WILLDEAD; //checks to find next state
        }
        else if(cellState == CellState.DEAD && shouldBeBorn(liveNeighbors)){
            return CellState.WILLALIVE;
        }
        else{
            return cellState;
        }
    }
}
