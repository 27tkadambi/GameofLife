/**
 * Cell class
 */
public class Cell {

    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private MooreRules rules;

    /**
     * Cell constructor: Creates a cell object
     * @param x: x coordinate of cell
     * @param y: y coordinate of cell
     * @param size: cell size
     * @param row: row number of cell
     * @param column: column number of cell
     * @param cellState: cellState of cell
     * @param rules: parameter to apply rules onto cells
     */
    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /**
     * getCellState() is a method that returns the cellState of the cell.
     * @return :Returns cellState of cell
     */
    public CellState getCellState(){
        return cellState;
    }

    /**
     * applyRules() calls rules() in MooreRules to find the next iteration of the cell based on the birth/death/survival rules.
     * @param cells: The grid of cells set up in main;
     */
    public void applyRules(Cell[][] cells){
        cellState = rules.applyRules(cellState, countLiveNeighbors(cells)); //calls apply birth and death rules on cell
    }

    /**
     * Display method for cell. Controls color of cell and draws cell.
     */
    public void display(){
        if (cellState == CellState.ALIVE){ //displays cells
            Main.app.fill(0);
        }
        if (cellState == CellState.DEAD){
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size, size);
    }

    /**
     * handleClick() changes the cellState of the cell based on mouseClicked().
     */
    public void handleClick(){
        if (cellState == CellState.ALIVE){ //changes cellState when mouse is clicked
            cellState = CellState.DEAD;
        }
        else{
            cellState = CellState.ALIVE;
        }
    }

    /**
     * countLiveNeighbors() counts the amount of alive cells directly next to the cell.
     * @param cells: cells is a cell array, used to find the status of the neighbors
     * @return : returns the number of live neighbors
     */
    private int countLiveNeighbors(Cell[][] cells) {
        int count = 0; //counts neighbors of cells
        for (int r = row - 1; r < row + 2; r++){
            for (int c = column -1; c < column + 2; c++){
                if(cells[r][c].getCellState() == CellState.ALIVE || cells[r][c].getCellState() == CellState.WILLDEAD){
                    count++;
                }
            }
        }
        if (cells[row][column].getCellState() == CellState.ALIVE){
            count--;
        }
        return count;
    }

    /**
     * evolve changes the cellState of the cell after applyRules has been called
     */
    public void evolve(){
        if (cellState == CellState.WILLALIVE){ //changes cell state post applyrules
            cellState = CellState.ALIVE;
        }
        if (cellState == CellState.WILLDEAD){
            cellState = CellState.DEAD;
        }
    }
}
