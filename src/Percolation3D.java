public class Percolation3D extends Percolation {
	public static final int NUM_OF_COLUMN = 43;
	public static final int OUT_OF_BOUND = -2;

	private int[][] layer = new int[NUM_OF_COLUMN][NUM_OF_COLUMN];
	private int[][] nextLayer = new int[NUM_OF_COLUMN][NUM_OF_COLUMN];

	protected void setupLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for (int j = 0; j < NUM_OF_COLUMN; j++) { // col
				if (i % 2 == 1 && j % 2 == 1) {
					this.layer[i][j] = SATURATED;
				} else {
					this.layer[i][j] = HARD;
				}
			}
		}
	}

	protected void setupNextLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for (int j = 0; j < NUM_OF_COLUMN; j++) { // col
				this.nextLayer[i][j] = EMPTY;
			}
		}
	}

	protected void copyNextLayer() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for (int j = 0; j < NUM_OF_COLUMN; j++) { // col
				this.layer[i][j] = this.nextLayer[i][j];
			}
		}
		// setup next row
		this.setupNextLayer();
	}

	protected void percolate() {
		for (int i = 0; i < NUM_OF_COLUMN; i++) { // row
			for (int j = 0; j < NUM_OF_COLUMN; j++) { // col
				if (this.layer[i][j] == SATURATED) {
					int[] a0 = { i - 1, j - 1 }, a1 = { i + 1, j - 1 }, a2 = {
							i + 1, j + 1 }, a3 = { i - 1, j + 1 };
					a0 = this.refineCoordinate(a0);
					a1 = this.refineCoordinate(a1);
					a2 = this.refineCoordinate(a2);
					a3 = this.refineCoordinate(a3);

					if (!(a0[0] == OUT_OF_BOUND || a0[1] == OUT_OF_BOUND)) {
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[a0[0]][a0[1]] = SATURATED;
						}
					}
					if (!(a1[0] == OUT_OF_BOUND || a1[1] == OUT_OF_BOUND)) {
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[a1[0]][a1[1]] = SATURATED;
						}
					}
					if (!(a2[0] == OUT_OF_BOUND || a2[1] == OUT_OF_BOUND)) {
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[a2[0]][a2[1]] = SATURATED;
						}
					}
					if (!(a3[0] == OUT_OF_BOUND || a3[1] == OUT_OF_BOUND)) {
						if (this.random.nextDouble() <= this.porosity) {
							this.nextLayer[a3[0]][a3[1]] = SATURATED;
						}
					}
					
					this.totalOil += 1;
				}
			}
		}
	}

	private int[] refineCoordinate(int[] coordinate) {
		int[] co = new int[2];
		co[0] = coordinate[0];
		co[1] = coordinate[1];

		if (co[0] == -1 || co[0] == NUM_OF_COLUMN) {
			co[0] = OUT_OF_BOUND;
		}
		if (co[1] == -1 || co[1] == NUM_OF_COLUMN) {
			co[1] = OUT_OF_BOUND;
		}
		return co;
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
		model.setup(0.295f, 20000, CONSTANT_POROSITY);
		model.start();
		// model.setup(0.25f, 1000, DIFFERENT_POROSITY);
		// model.start();
	}
}
