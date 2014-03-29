
public class Percolation2D extends Percolation {

	private int[] row = new int[NUM_OF_COLUMN];
	private int[] nextRow = new int[NUM_OF_COLUMN];
	
	protected void setupLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			if (i % 2 == 0) {
				this.row[i] = HARD;
			} else {
				this.row[i] = SATURATED;
			}
		}
	}

	protected void setupNextLayer() {
		int x = 0;

		if (this.row[0] == HARD) {
			x = 1;
		}

		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			if (i % 2 == x) {
				this.nextRow[i] = HARD;
			} else {
				this.nextRow[i] = EMPTY;
			}
		}
	}
	
	protected void copyNextLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			this.row[i] = this.nextRow[i];
		}
		// setup next row
		this.setupNextLayer();
	}
	
	protected void percolate() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			if (this.row[i] == SATURATED) {
				if (i >= 1 && i <= NUM_OF_COLUMN - 2) {
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[i - 1] = SATURATED;
					}
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[i + 1] = SATURATED;
					}
				} else if (i == 0) {
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[1] = SATURATED;
					}
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[NUM_OF_COLUMN - 1] = SATURATED;
					}
				} else if (i == NUM_OF_COLUMN - 1) {
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[0] = SATURATED;
					}
					if (this.random.nextDouble() <= this.porosity) {
						this.nextRow[NUM_OF_COLUMN - 2] = SATURATED;
					}
				}
			}
		}
	}
	
	protected int numberOfOilInCurrentLayer() {
		int count = 0;
		for (int i : this.row) {
			if (i == SATURATED)
				count++;
		}
		return count;
	}
	
	public static void main(String args[]) {
		Percolation2D model = new Percolation2D();
		model.setup(0.64f, 20000, SAME_POROSITY);
		model.start();
		model.setup(0.64f, 20000, DIFFERENT_POROSITY);
		model.start();
	}
}
