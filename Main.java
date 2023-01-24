
public class Main
{

    public static void main(String args[])
    {
        int cols = 30, rows = 30;
        boolean mousePressed = false;
        StdDraw.setCanvasSize(cols * 30,rows * 30);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0,cols);
        StdDraw.setYscale(0,rows);
        Board map = new Board(rows,cols,0,0);
        map.setup();
        //Sets up terrain on shift click, until space is pressed
        while(!(StdDraw.isKeyPressed(32))){
            if(StdDraw.isKeyPressed(16)){
                map.board[(int)(StdDraw.mouseY())][(int)(StdDraw.mouseX())].isTerrain = true;
            }
            map.drawBoard();
            StdDraw.show();
            StdDraw.clear();
        }
        //While not at the end block, or movement is possible, tries to find the best path
        while(Board.loop){
            if(StdDraw.isMousePressed() && !(mousePressed)){
                map.endPoint[1] = (int)(StdDraw.mouseY());
                map.endPoint[0] = (int)(StdDraw.mouseX());
                map.board[map.endPoint[1]][map.endPoint[0]].isEndPoint = true;
                mousePressed = true;
            }
            if(mousePressed){
                map.doTheThing();
            }
            map.drawBoard();
            StdDraw.show();
            StdDraw.clear();
        }
        map.closed.add(map.board[map.endPoint[1]][map.endPoint[0]]);
        //Finds best path back
        while(Board.loop2){

            map.findBestPath();
            map.drawBoard();
            StdDraw.show();
            if(map.board[rows - map.startPoint[1] - 1][map.startPoint[0]].isBestPath){
                map.loop2 = false;
            }
        }
        StdDraw.show();
    }
}
