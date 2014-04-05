import java.util.Random;

public class Percolation {
//	public static final int NUM_OF_COLUMN = 200;

	public static final int EMPTY = 0;
	public static final int SATURATED = 1;
	public static final int HARD = 2;
	
	public static final int CONSTANT_POROSITY = 0;
	public static final int DIFFERENT_POROSITY = 1;
	
	private int porosityMode = 0;

	protected long currentLayer = 1;
	protected long totalOil = 0;

	protected float porosity = 0.0f;
	protected Random random = new Random();

	protected boolean hasOil = false;
	private int maxTicks = 0;

	public void setup(float porosity, int maxTicks, int porosityMode) {
		this.maxTicks = maxTicks;
		this.porosityMode = porosityMode;
		
		this.totalOil = 0;
		this.currentLayer = 1;
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
		while (this.hasOil == true && this.currentLayer < this.maxTicks) {
			this.go();
			if(this.porosityMode == DIFFERENT_POROSITY) this.changePorosity();
		}
		System.out.println("Porosity Mode = " + this.porosityMode);
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
			this.currentLayer++;
		}
		
	}

	protected void percolate() {
		
	}

	private void changePorosity(){
		this.porosity = this.porosity * 0.99f;
	}
	
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
