
public class Block
{
    double h, g, f;
    int x, y;
    boolean isEndPoint, isStartPoint, isSelected, isBestPath, isTerrain;

    public Block(int y, int x)
    {
        this.x = x;
        this.y = y;
    }
    //Draws block based on its type
    public void drawBlock(){
        if(isTerrain){
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5); 
        }
        else if(isBestPath){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5); 
        }
        else if(isEndPoint || isStartPoint){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        }
        else if(isSelected){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        }
        else{
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.square(x + 0.5, y + 0.5, 0.5);
        }
    }
}
