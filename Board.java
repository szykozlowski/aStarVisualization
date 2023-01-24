import java.util.ArrayList;
public class Board
{
    int rows, cols;
    int [] endPoint = new int[2], startPoint = new int[2];
    ArrayList<Block> open = new ArrayList<Block>(), closed = new ArrayList<Block>(), possChoices = new ArrayList<Block>();
    Block[][] board;
    static boolean loop = true, loop2 = true;
    //Creates a board with specified parameters
    public Board(int rows, int cols, int startX, int startY)
    {
        this.rows = rows;
        this.cols = cols;
        board = new Block[rows][cols];
        startPoint[0] = startX;
        startPoint[1] = rows - startY - 1;
    }
    //Creates an array of Blocks
    public void setup(){
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < cols;j++){
                board[i][j] = new Block(i,j);
            }
        }
        board[rows - startPoint[1] - 1][startPoint[0]].isStartPoint = true;
        open.add(board[rows - startPoint[1] - 1][startPoint[0]]);
    }
    //Draws the array of Blocks
    public void drawBoard(){
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < cols;j++){
                board[i][j].drawBlock();
            }
        }
    }
    //Gets the fScore of block at x,y
    public double getFScore(int x, int y, double prevG){
        //board[y][x].h = Math.sqrt(Math.pow(endPoint[0] - x + 1, 2) + Math.pow((endPoint[1] - y), 2));
        board[y][x].h = Math.abs(endPoint[0] - x + 1) + Math.abs(endPoint[1] - y);
        board[y][x].f = board[y][x].h + board[y][x].g;
        return board[y][x].h + board[y][x].g;
    }
    //Adds available neighbors of the current block in queue to the queue
    public void addTheNeighbors(int y, int x, double bigG){
        if(x + 1 < cols && !(board[y][x + 1].isSelected) && !(board[y][x + 1].isTerrain)){
            open.add(board[y][x + 1]);
            board[y][x + 1].g = bigG + 1;
        }
        if(y + 1 < rows && !(board[y + 1][x].isSelected) && !(board[y + 1][x].isTerrain)){
            open.add(board[y + 1][x]);
            board[y + 1][x].g = bigG + 1;
        }
        if(x - 1 > 0 && !(board[y][x - 1].isSelected) && !(board[y][x - 1].isTerrain)){
            open.add(board[y][x - 1]);
            board[y][x - 1].g = bigG + 1;
        }
        if(y - 1 > 0 && !(board[y - 1][x].isSelected) && !(board[y - 1][x].isTerrain)){
            open.add(board[y - 1][x]);
            board[y - 1][x].g = bigG + 1;
        }
    }
    //Loops through the queue of available blocks, picks the one with the lowest fScore, and adds its neighbors to the queue
    public void doTheThing(){
        double bestF = 100000000;
        int theNum = 0;
        for(int i = 0; i < open.size();i++){
            open.get(i).isSelected = true;
            if(getFScore(open.get(i).x,open.get(i).y,open.get(i).g) < bestF){
                bestF = open.get(i).f;
                theNum = i;
            }
        }
        if(board[endPoint[1]][endPoint[0]].isSelected){
            loop = false;
        }
        addTheNeighbors(open.get(theNum).y,open.get(theNum).x, open.get(theNum).g);
        open.remove(theNum);
    }
    //Backtracks from the end block, to the start block through the blocks with the lowest gScore
    public void findBestPath(){
        double bestF = 10000000;
        int theNum = 0;
        closed.get(0).isBestPath = true;
        if(closed.get(0).y + 1 < rows && board[closed.get(0).y + 1][closed.get(0).x].isTerrain != true){
            possChoices.add(board[closed.get(0).y + 1][closed.get(0).x]);
        }
        if(closed.get(0).y - 1 >= 0 && board[closed.get(0).y - 1][closed.get(0).x].isTerrain != true){
            possChoices.add(board[closed.get(0).y - 1][closed.get(0).x]);
        }
        if(closed.get(0).x + 1 < cols && board[closed.get(0).y][closed.get(0).x + 1].isTerrain != true){
            possChoices.add(board[closed.get(0).y][closed.get(0).x + 1]);
        }
        if(closed.get(0).x - 1 >= 0 && board[closed.get(0).y][closed.get(0).x - 1].isTerrain != true){
            possChoices.add(board[closed.get(0).y][closed.get(0).x - 1]);
        }
        for(int i = 0;i < possChoices.size();i++){
            if(possChoices.get(i).g < bestF && (possChoices.get(i).g > 0 || possChoices.get(i).isSelected)){
                bestF = possChoices.get(i).g;
                theNum = i;
            }
        }
        closed.clear();
        closed.add(possChoices.get(theNum));
        possChoices.clear();
    }
}

