import processing.core.PApplet;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends PApplet {

    private final int NUM_ROWS;
    private final int NUM_COLUMNS;
    private final int CELL_SIZE;
    public static PApplet app;
    private Cell[][] cells;
    private boolean doEvolve;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
        NUM_ROWS = 50;
        NUM_COLUMNS = 100;
        CELL_SIZE = 10;
    }

    public void settings(){
        size(NUM_COLUMNS * CELL_SIZE,NUM_ROWS * CELL_SIZE);
    }

    public void setup(){
        doEvolve = false;
        MooreRules rules = new MooreRules(new int[]{3}, new int[]{2, 3}); //birth & death rules
        cells = new Cell[NUM_ROWS][NUM_COLUMNS]; //cell array grid
        for (int r = 0; r < cells.length; r++){ //put cells in array
            for (int c = 0; c < cells[r].length; c++){
                cells[r][c] = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD, rules);
            }
        }
    }

    public void draw(){
        for (int y = 0; y < NUM_ROWS; y++){
            for (int x = 0; x < NUM_COLUMNS; x++){
                cells[y][x].display(); //draws grid
            }
        }
        if (doEvolve){
            applyRules();
            evolve();
        }
    }

    public void mouseClicked(){
        cells[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick(); //go to mouseClicked() to change color
    }

    public void applyRules(){
        for (int r = 1; r < cells.length-1; r++){
            for (int c = 1; c < cells[r].length-1; c++){ //call applyRules on each cell
                cells[r][c].applyRules(cells);
            }
        }
    }

    public void evolve(){
        for (int r = 1; r < cells.length-1; r++) {
            for (int c = 1; c < cells[r].length-1; c++) { //call evolve on each cell
                cells[r][c].evolve();
            }
        }
    }

    public void keyPressed(){
        doEvolve = !doEvolve; //changes evolve boolean
    }
}