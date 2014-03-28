import java.util.Random;

public class Percolation {
	public static final int NUM_OF_COLUMN = 200;

	public static final int EMPTY = 0;
	public static final int SATURATED = 1;
	public static final int HARD = 2;

	protected long currentLayer = 0;
	protected long totalOil = 0;

	protected float porosity = 0.0f;
	protected Random random = new Random();

	protected boolean hasOil = false;

	public void setup(float porosity) {
		this.totalOil = 0;
		this.currentLayer = 0;
		this.porosity = porosity;

		this.setupLayer();
		this.setupNextLayer();

		this.hasOil = true;
	}

	protected void setupLayer() {

	}

	protected void setupNextLayer() {

	}

	protected void copyNextLayer() {

		// setup next row
		this.setupNextLayer();
	}

	public void start() {
		while (this.hasOil == true && this.currentLayer < 20000) {
			this.go();
		}
		System.out.println("Number of rows = " + this.currentLayer);
		System.out.println("Total oil = " + this.totalOil);
	}

	private void go() {
		if (this.numberOfOilInCurrentLayer() == 0) {
			this.hasOil = false;
		} else {
			this.percolate();
			this.totalOil += this.numberOfOilInCurrentLayer();
			this.copyNextLayer();
		}
		this.currentLayer++;
	}

	protected void percolate() {
		
	}

//	private void resetColor() {
//
//	}
//
//	private void resetTicks() {
//		this.currentRow = 0;
//		this.hasOil = false;
//	}
//	
//	private void wrapOil() {
//
//	}

	protected int numberOfOilInCurrentLayer() {
		return 0;
	}

	public long getCurrentRow() {
		return currentLayer;
	}

	public void setCurrentRow(long currentLayer) {
		this.currentLayer = currentLayer;
	}

	public long getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(long totalOil) {
		this.totalOil = totalOil;
	}

	public static void main(String args[]) {

	}
}
