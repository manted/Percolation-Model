import java.util.Random;


public class Percolation {
	public static final int NUM_OF_COLUMN = 200;
	
	public static final int EMPTY = 0;
	public static final int SATURATED = 200;
	public static final int HARD = 200;
	
	private long currentRow = 0;
	private long totalOil = 0;
	
	private float porosity = 0.0f;
	private Random random = new Random();
	
	private int[] row = new int[NUM_OF_COLUMN];
	private int[] nextRow = new int[NUM_OF_COLUMN];
	
	public void setup(float porosity){
		this.totalOil = 0;
		this.currentRow = 0;
		this.porosity = porosity;
		
		this.setupRow();
		this.setupNextRow();
	}
	
	private void setupRow(){
		for(int i = 0; i < NUM_OF_COLUMN; i++){
			if(i % 2 == 0){
				this.row[i] = HARD;
			}else{
				this.row[i] = SATURATED;
			}
		}
	}
	
	private void setupNextRow(){
		int x = 0;
		
		if(this.row[0] == HARD){
			x = 1;
		}
		
		for(int i = 0; i < NUM_OF_COLUMN; i++){
			if(i % 2 == x){
				this.row[i] = HARD;
			}else{
				this.row[i] = EMPTY;
			}
		}
	}
	
	private void copyNextRow(){
		for(int i = 0; i < NUM_OF_COLUMN; i++){
			this.row[i] = this.nextRow[i];
		}
		//setup next row
		this.setupNextRow();
	}
	
	public void resetColor(){
		
	}
	
	public void resetTicks(){
		
	}
	
	public void go(){
		
	}
	
	public void percolate(){
		for(int i = 0; i < NUM_OF_COLUMN; i++){
			if(i >= 1 && i <= NUM_OF_COLUMN - 2){
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[i - 1] = SATURATED;
				}
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[i + 1] = SATURATED;
				}
			}else if(i == 0){
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[1] = SATURATED;
				}
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[NUM_OF_COLUMN - 1] = SATURATED;
				}
			}else if(i == NUM_OF_COLUMN - 1){
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[0] = SATURATED;
				}
				if(this.random.nextDouble() <= this.porosity){
					this.nextRow[NUM_OF_COLUMN - 2] = SATURATED;
				}
			}
		}
	}
	
	public void wrapOil(){
		
	}
	
	public static void main(String args[]){
		
	}
}
