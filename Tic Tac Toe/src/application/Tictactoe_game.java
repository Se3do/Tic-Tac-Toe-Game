package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class Tictactoe_game extends Application {
	
	private char whosturn = 'X';
	private Cell[][] cell = new Cell[3][3];
	private Label status = new Label("X to Play");
	
	@Override
	public void start(Stage primaryStage) {
		
		status.setFont(Font.font(20));
		GridPane Grid = new GridPane();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Grid.add(cell[i][j] = new Cell(), j, i);
			}
		}
		
		
		BorderPane pane = new BorderPane();
		pane.setCenter(Grid);
		BorderPane forlabl = new BorderPane();
		forlabl.setCenter(status);
		pane.setBottom(forlabl);
	
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TicTacToe");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	// function to check if all cells are filled
	public Boolean isfilled() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(cell[i][j].GetVal() == ' ')
					return false;
			}
		}
		return true;
	}
	
	public Boolean iswon(char s) {
		// Check each Row to find if one player won
		for(int i = 0; i < 3; i++) {
			if(cell[i][0].GetVal() == s && cell[i][1].GetVal() == s && cell[i][2].GetVal() == s)
				return true;
		}
		// Check each Col to find if one player won
		for(int i = 0; i < 3; i++) {
			if(cell[0][i].GetVal() == s && cell[1][i].GetVal() == s && cell[2][i].GetVal() == s)
				return true;
		}
		// Check both diagonals
		if(cell[0][0].GetVal() == s && cell[1][1].GetVal() == s && cell[2][2].GetVal() == s)
			return true;
		if(cell[0][2].GetVal() == s && cell[1][1].GetVal() == s && cell[2][0].GetVal() == s)
			return true;
		
		// Return false if nobody one
		return false;
	}
	
	public class Cell extends BorderPane{
		
		private Image x_image = new Image("X2.png");
		private Image o_image = new Image("O2.png");
		private ImageView x_imageview = new ImageView(x_image);
		private ImageView o_imageview = new ImageView(o_image);
		private char cellvalue = ' ';
		
		
		public Cell() {
			this.setStyle("-fx-border-color: black;-fx-border-width: 1px;");
			this.setPrefSize(105, 100);
			this.setOnMouseClicked(e -> SetVal(whosturn));
		}
		
		void SetVal(char s) {
			
			if(s == 'X' && cellvalue == ' ' && whosturn != ' ') {
				this.cellvalue = 'X';
				this.setCenter(x_imageview); // display X on cell
				if(iswon(s)) {
					status.setText(s + " won! The game is over");
					whosturn = ' ';
					return;
				} else if(isfilled()) {
					status.setText("It's a draw! the game is over");
					whosturn = ' ';
					return;
				}
				status.setText("O to play");
				whosturn = 'O';
			}
			
			else if(s == 'O' && cellvalue == ' ' && whosturn != ' ') {
				this.cellvalue = 'O';
				this.setCenter(o_imageview); // display O on cell
				if(iswon(s)) {
					status.setText(s + " won! The game is over");
					whosturn = ' ';
					return;
				} else if(isfilled()) {
					status.setText("It's a draw! the game is over");
					whosturn = ' ';
					return;
				}
				status.setText("X to Play");
				whosturn = 'X';
			}
		}
		
		
		public char GetVal() {
			return cellvalue;
		}		
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}

