import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Experiment {
	public static final int TWO_D = 0;
	public static final int THREE_D = 1;
	
	public static final float MAX_POROSITY_2D = 0.75f;
	public static final float INITIAL_POROSITY_2D = 0.2f;
	public static final float MAX_POROSITY_3D = 0.50f;
	public static final float INITIAL_POROSITY_3D = 0.1f;
	public static final float POROSITY_STEP = 0.005f;
	public static final int NUM_OF_POROSITIES_2D = (int)((MAX_POROSITY_2D - INITIAL_POROSITY_2D) / POROSITY_STEP) + 1;
	public static final int NUM_OF_POROSITIES_3D = (int)((MAX_POROSITY_3D - INITIAL_POROSITY_3D) / POROSITY_STEP) + 1;
	
	private int modelMode = 0;
	private int porosityMode = 0;
	private int maxTicks = 0;
	private float initialPorosity = 0;
	private float maxPorosity = 0;
	

	
	private ArrayList<ExperimentResult> results;
	private Plot plot;
	
	private Percolation model;
	
	public void setup(int modelMode, int porosityMode, int ticks){
		this.modelMode = modelMode;
		if(modelMode == TWO_D){
			this.model = new Percolation2D();
			this.results = new ArrayList<ExperimentResult>(NUM_OF_POROSITIES_2D);
			this.initialPorosity = INITIAL_POROSITY_2D;
			this.maxPorosity = MAX_POROSITY_2D;
		}else if(modelMode == THREE_D){
			this.model = new Percolation3D();
			this.results = new ArrayList<ExperimentResult>(NUM_OF_POROSITIES_3D);
			this.initialPorosity = INITIAL_POROSITY_3D;
			this.maxPorosity = MAX_POROSITY_3D;
		}
		this.plot = new Plot(modelMode,porosityMode);

		this.porosityMode = porosityMode;
		this.maxTicks = ticks;
	}
	
	public void run(){
		float newPorosity = this.initialPorosity;
		for(; newPorosity <= this.maxPorosity; newPorosity += POROSITY_STEP){
			// used to get average result
			Result result = new Result();
			
			for(int j = 0; j < Result.NUMBER_OF_RUNS; j++){
				this.model.setup(newPorosity, this.maxTicks, this.porosityMode);
				this.model.start();
				long finalDepth = this.model.getCurrentRow();
				long finalTotalOil = this.model.getTotalOil();

				result.addFinalDepth(finalDepth);
				result.addFinalTotalOil(finalTotalOil);
			}
			// result of current porosity experiment
			ExperimentResult er = new ExperimentResult(newPorosity,result.getAverageFinalDepth(),result.getAverageTotalOil());
			this.results.add(er);
			// increase porosity
//			newPorosity += POROSITY_STEP;
		}
		if(this.modelMode == TWO_D){
			plot.addSeries("2D", this.results);
		}else{
			plot.addSeries("3D", this.results);
		}
		
		this.printResults();
		this.plot();
	}
	
	public void printResults(){
		for(ExperimentResult aResult : this.results){
			System.out.format("%.1f%%, %5d, %7d",aResult.getPorosity() * 100,aResult.getFinalDepth(),aResult.getFinalTotalOil());
			System.out.println();
		}
	}

	public ArrayList<ExperimentResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<ExperimentResult> results) {
		this.results = results;
	}
	
	private void plot(){
		JFrame frame = new JFrame("Percolation");
		frame.setLayout(new GridLayout(1, 1, 10, 10));
		frame.add(plot.getChartPanel());
		frame.setBounds(50, 50, 500, 400);
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		System.out.println("Experiment 1: 2D Constant Porosity");
		Experiment constant2D = new Experiment();
		constant2D.setup(Experiment.TWO_D, Percolation.CONSTANT_POROSITY, 10000);
		constant2D.run();
		
		System.out.println("Experiment 1: 3D Constant Porosity");
		Experiment constant3D = new Experiment();
		constant3D.setup(Experiment.THREE_D, Percolation.CONSTANT_POROSITY, 10000);
		constant3D.run();
		
		System.out.println("Experiment 1: 2D Different Porosity");
		Experiment different2D = new Experiment();
		different2D.setup(Experiment.TWO_D, Percolation.DIFFERENT_POROSITY, 10000);
		different2D.run();
		
		System.out.println("Experiment 1: 3D Different Porosity");
		Experiment different3D = new Experiment();
		different3D.setup(Experiment.THREE_D, Percolation.DIFFERENT_POROSITY, 10000);
		different3D.run();
	}
}
