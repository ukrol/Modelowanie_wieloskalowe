import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller{
	
	 @FXML
	 private Button buttonStart;

	 @FXML
	 private TextField row;

	 @FXML
	 private TextField column;

	 @FXML
	 private TextField numberOfGenerations;

	 @FXML
	 private TextField numberOfCells;

	 @FXML
	 public Canvas canvas;
	 
	 public GraphicsContext gc;
	 
	 private static int rows;
	 private static int columns;
	 private int generations;
	 private int cells;
	 private static boolean[][] board;
	 Timeline tl;
	 
	 public void getData(){
		 rows=Integer.parseInt(row.getText());
	     columns=Integer.parseInt(column.getText());
	     generations=Integer.parseInt(numberOfGenerations.getText());
	     cells=Integer.parseInt(numberOfCells.getText());
	 }

	 @FXML
	 public void buttonhandleStart(ActionEvent actionEvent) throws InterruptedException{
		 getData();
		 board=new boolean[rows][columns];
		 gc = canvas.getGraphicsContext2D();
	     drawBoard(gc);
	     random(cells);

	     KeyFrame kf= new KeyFrame(Duration.seconds(0.5),e->{
	    	 nextGeneration();
 	    	 colourCells(gc);
	     });
	     
	     tl = new Timeline(kf);
	     tl.setCycleCount(generations);
	     tl.play();
	    
	 }
	 
	 static void drawBoard(GraphicsContext gc){
		 gc.beginPath();
		 gc.setLineWidth(1);
	     gc.setStroke(Color.BLACK);

	     double x = gc.getCanvas().getWidth()/rows;
	     double y = gc.getCanvas().getHeight()/columns;

	     for(int i=0; i<=rows; i++) {
	    	 gc.moveTo(x * i, 0);
	         gc.lineTo(x * i, gc.getCanvas().getHeight());
	     }
	     for(int i=0; i<=columns; i++) {
	    	 gc.moveTo(0, y * i);
	    	 gc.lineTo(gc.getCanvas().getWidth(), y * i);
	     }
	     gc.stroke();
	     gc.closePath();
	 }

	 static void colourCells(GraphicsContext gc){
		 drawBoard(gc);
		 gc.beginPath();
		 gc.setLineWidth(5);

		 double x = gc.getCanvas().getWidth()/rows;
		 double y = gc.getCanvas().getHeight()/columns;
		 
		 for(int i=0; i<rows; i++){
	      	for(int j=0; j<columns; j++){
	         	if(board[i][j]==true){
	            	gc.setFill(Color.LIGHTBLUE);
	            	gc.fillRect(x*i+1,y*j+1,x-1,y-1);
	           	}
	         	else{
	         		gc.setFill(Color.WHITE);
	         		gc.fillRect(x*i+1,y*j+1,x-1,y-1);
	          	}
	      	}
		 }
		 gc.fill();
		 gc.closePath();
	 }
	 
	 void random(int number){
		int x;
		int y;
		Random random = new Random();
		for(int i =0; i<number; i++){
			x = random.nextInt(rows);
			y = random.nextInt(columns);
			board[x][y]=true;
	    }
		colourCells(gc);
	 }
	 
	 public int numberOfNeighbours(int x, int y){
		 int neighbours=0;
		for(int i = x-1; i <= x+1; i++) {
			if(i < 0 || i > rows-1) continue;
			for(int j = y-1; j <= y+1; j++) {
				if(j < 0 || j > columns-1 || (i == x && j == y)) continue;
				if(board[i][j]==true) {
					neighbours++;
				}
			}
		}
		return neighbours;
	}
	 
	public boolean evolution(int x, int y){
		int neighbours=numberOfNeighbours(x,y);
		if(board[x][y]==true){
			if(neighbours<2 || neighbours>3){
				return false;
			}
			if(neighbours==2||neighbours==3){
				return true;
			}
		}
		else{
			if(neighbours==3){
				return true;
			}
		}
		return false;
	}
	
	public void nextGeneration(){
		boolean[][] tmpBoard=new boolean[rows][columns];
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				tmpBoard[i][j]=evolution(i,j);
			}
		}
		board=tmpBoard;
	}
}
