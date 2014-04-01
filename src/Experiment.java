import java.util.ArrayList;


public class Experiment {
	public static final int TWO_D = 0;
	public static final int THREE_D = 1;
	
	public static final float MAX_POROSITY = 0.75f;
	public static final float INITIAL_POROSITY = 0.1f;
	public static final float POROSITY_STEP = 0.005f;
	
	private int porosityMode = 0;
	private int maxTicks = 0;
	
//	private long finalDepth = 0;
//	private long finalTotalOil = 0;
	
	private ArrayList<ExperimentResult> results = new ArrayList<ExperimentResult>((int)(MAX_POROSITY / POROSITY_STEP));
	
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
			// used to get average result
			Result result = new Result();
			
			for(int j = 0; j < Result.NUMBER_OF_RUNS; j++){
				this.model.setup(newPorosity, this.maxTicks, this.porosityMode);
				this.model.start();
				long finalDepth = this.model.getCurrentRow();
				long finalTotalOil = this.model.getTotalOil();
//				this.porosityDepthResult[i][0] = newPorosity;
//				this.porosityDepthResult[i][1] = finalDepth;
//				this.porosityDepthResult[i][2] = finalTotalOil;
				result.addFinalDepth(finalDepth);
				result.addFinalTotalOil(finalTotalOil);
			}
			// result of current porosity experiment
			ExperimentResult er = new ExperimentResult(newPorosity,result.getAverageFinalDepth(),result.getAverageTotalOil());
			this.results.add(er);
			// increase porosity
			newPorosity += POROSITY_STEP;
		}
		this.printResults();
	}
	
	private void printResults(){
		for(ExperimentResult aResult : this.results){
			System.out.format("%.1f%%, %5d, %7d",aResult.getPorosity() * 100,aResult.getFinalDepth(),aResult.getFinalTotalOil());
			System.out.println();
		}
	}
	
	public static void main(String args[]){
		Experiment constant2D = new Experiment();
		constant2D.setup(TWO_D, Percolation.CONSTANT_POROSITY, 1000);
		constant2D.run();
	}
}
