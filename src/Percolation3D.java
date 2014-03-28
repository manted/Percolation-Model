
public class Percolation3D extends Percolation {
	private int[][] layer = new int[NUM_OF_COLUMN][NUM_OF_COLUMN];
	private int[][] nextLayer = new int[NUM_OF_COLUMN][NUM_OF_COLUMN];
	
	protected void setupLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for(int j = 0; j < NUM_OF_COLUMN; j++){ // col
				if (i % 2 == 0) {
					if(j % 2 == 0){
						this.layer[i][j] = HARD;
					}else{
						this.layer[i][j] = SATURATED;
					}
				} else {
					if(j % 2 == 0){
						this.layer[i][j] = SATURATED;
					}else{
						this.layer[i][j] = HARD;
					}
				}
			}
		}
	}

	protected void setupNextLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for(int j = 0; j < NUM_OF_COLUMN; j++){ // col
				this.nextLayer[i][j] = EMPTY;
			}
		}
	}
	
	protected void copyNextLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for(int j = 0; j < NUM_OF_COLUMN; j++){ // col
				this.layer[i][j] = this.nextLayer[i][j];
			}
		}
		// setup next row
		this.setupNextLayer();
	}
	
	protected void percolate() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for(int j = 0; j < NUM_OF_COLUMN; j++){ // col
				if (this.layer[i][j] == SATURATED) {
					// center cells
					if(i >= 1 && i <= NUM_OF_COLUMN - 2 && j >= 1 && j <= NUM_OF_COLUMN - 2){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j - 1] = SATURATED;
						}
					}
					//top center
					else if(i == 0 && j >= 1 && j <= NUM_OF_COLUMN - 2){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j - 1] = SATURATED;
						}
					}
					//right center
					else if(j == NUM_OF_COLUMN - 1 && i >= 1 && i <= NUM_OF_COLUMN - 2){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j - 1] = SATURATED;
						}
					}
					//bottom center
					else if(i == NUM_OF_COLUMN - 1 && j >= 1 && j <= NUM_OF_COLUMN - 2){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][j - 1] = SATURATED;
						}
					}
					//left center
					else if(j == 0 && i >= 1 && i <= NUM_OF_COLUMN - 2){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][NUM_OF_COLUMN - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][j + 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i + 1][NUM_OF_COLUMN - 1] = SATURATED;
						}
					}
					//top left corner
					else if(i == 0 && j == 0){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][NUM_OF_COLUMN - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[1][1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[1][NUM_OF_COLUMN - 1] = SATURATED;
						}
					}
					//top right corner
					else if(i == 0 && j == NUM_OF_COLUMN - 1){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[NUM_OF_COLUMN - 1][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[1][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[1][j - 1] = SATURATED;
						}
					}
					//bottom right corner
					else if(i == NUM_OF_COLUMN - 1 && j == NUM_OF_COLUMN - 1){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][j - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][0] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][j - 1] = SATURATED;
						}
					}
					//bottom left corner
					else if(i == NUM_OF_COLUMN - 1 && j == 0){
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][NUM_OF_COLUMN - 1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[i - 1][1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][1] = SATURATED;
						}
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[0][NUM_OF_COLUMN - 1] = SATURATED;
						}
					}
				}
			}
		}
	}
	
	protected int numberOfOilInCurrentLayer() {
		int count = 0;
		for (int[] i : this.layer) {
			for (int j : i) {
				if (j == SATURATED)
					count++;
			}
		}
		return count;
	}
	
	public static void main(String args[]) {
		Percolation3D model = new Percolation3D();
		model.setup(0.65f);
		model.start();
	}
}
