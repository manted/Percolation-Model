import java.util.Random;

public class Percolation {
	public static final int NUM_OF_COLUMN = 200;

	public static final int EMPTY = 0;
	public static final int SATURATED = 1;
	public static final int HARD = 2;

	private long currentRow = 0;
	private long totalOil = 0;

	private float porosity = 0.0f;
	private Random random = new Random();

	private int[] row = new int[NUM_OF_COLUMN];
	private int[] nextRow = new int[NUM_OF_COLUMN];

	private boolean hasOil = false;

	public void setup(float porosity) {
		this.totalOil = 0;
		this.currentRow = 0;
		this.porosity = porosity;

		this.setupRow();
		this.setupNextRow();

		this.hasOil = true;
	}

	private void setupRow() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			if (i % 2 == 0) {
				this.row[i] = HARD;
			} else {
				this.row[i] = SATURATED;
			}
		}
	}

	private void setupNextRow() {
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

	private void copyNextRow() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) {
			this.row[i] = this.nextRow[i];
		}
		// setup next row
		this.setupNextRow();
	}

	private void resetColor() {

	}

	private void resetTicks() {
		this.currentRow = 0;
		this.hasOil = false;
	}

	public void start() {
		while (this.hasOil == true && this.currentRow < 20000) {
			this.go();
		}
		System.out.println("Number of rows = " + this.currentRow);
		System.out.println("Total oil = " + this.totalOil);
	}

	public void go() {
		if (this.numberOfOilInRow(this.row) == 0) {
			this.hasOil = false;
		} else {
			this.percolate();
			this.totalOil += this.numberOfOilInRow(this.row);
			this.copyNextRow();
			// this.wrapOil();
		}
		this.currentRow++;
	}

	private void percolate() {
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

	private void wrapOil() {

	}

	private int numberOfOilInRow(int[] row) {
		int count = 0;
		for (int i : row) {
			if (i == SATURATED)
				count++;
		}
		return count;
	}

	public long getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(long currentRow) {
		this.currentRow = currentRow;
	}

	public long getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(long totalOil) {
		this.totalOil = totalOil;
	}

	public static void main(String args[]) {
		Percolation model = new Percolation();
		model.setup(0.65f);
		model.start();
	}
}
