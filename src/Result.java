import java.util.ArrayList;


public class Result {
	public static final int NUMBER_OF_RUNS = 10;
	
	private ArrayList<Long> finalDepthAL = new ArrayList<Long>(NUMBER_OF_RUNS);
	private ArrayList<Long> finalTotalOilAL = new ArrayList<Long>(NUMBER_OF_RUNS);
	
	public void addFinalDepth(long depth){
		this.finalDepthAL.add(depth);
	}
	
	public void addFinalTotalOil(long oil){
		this.finalTotalOilAL.add(oil);
	}
	
	public long getAverageFinalDepth(){
		long sum = 0;
		for(long depth : this.finalDepthAL){
			sum += depth;
		}
		return sum / NUMBER_OF_RUNS;
	}
	
	public long getAverageTotalOil(){
		long sum = 0;
		for(long oil : this.finalTotalOilAL){
			sum += oil;
		}
		return sum / NUMBER_OF_RUNS;
	}
}
