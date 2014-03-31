
public class Experiment {
	public static final int TWO_D = 0;
	public static final int THREE_D = 1;
	
	public static final float MAX_POROSITY = 0.75f;
	public static final float INITIAL_POROSITY = 0.005f;
	public static final float POROSITY_STEP = 0.005f;
	
	private int porosityMode = 0;
	private int maxTicks = 0;
	
//	private long finalDepth = 0;
//	private long finalTotalOil = 0;
	
	private float[][] porosityDepthResult = new float[(int)(MAX_POROSITY / POROSITY_STEP)][3];
	
	private Percolation model;
	
	public void setup(int modelMode, int porosityMode, int ticks){
		if(modelMode == TWO_D){
			this.model = new Percolation2D();
		}else if(modelMode == THREE_D){
			this.model = new Percolation3D();
		}
//		this.initialPorosity = porosity;
		this.porosityMode = porosityMode;
		this.maxTicks = ticks;
//		this.model.setup(porosity, ticks, mode);
	}
	
	public void run(){
		float newPorosity = INITIAL_POROSITY;
		for(int i = 0; newPorosity < MAX_POROSITY; i++){
			newPorosity += POROSITY_STEP;
			this.model.setup(newPorosity, this.maxTicks, this.porosityMode);
			this.model.start();
			long finalDepth = this.model.getCurrentRow();
			long finalTotalOil = this.model.getTotalOil();
			this.porosityDepthResult[i][0] = newPorosity;
			this.porosityDepthResult[i][1] = finalDepth;
			this.porosityDepthResult[i][2] = finalTotalOil;
//			System.out.println(i);
		}
		this.printResults();
	}
	
	private void printResults(){
		for(float[] aResult : this.porosityDepthResult){
//			System.out.println("Porosity = " + aResult[0]);
//			System.out.println("Final Depth = " + aResult[1]);
//			System.out.println("Final Total oil = " + aResult[2]);
			System.out.format("%.1f, %.0f, %.0f",aResult[0] * 100,aResult[1],aResult[2]);
			System.out.println();
		}
	}
	
	public static void main(String args[]){
		Experiment constant2D = new Experiment();
		constant2D.setup(TWO_D, Percolation.CONSTANT_POROSITY, 20000);
		constant2D.run();
	}
}
