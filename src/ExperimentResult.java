
public class ExperimentResult {
	private float porosity = 0.0f;
	private long finalDepth = 0;
	private long finalTotalOil = 0;
	
	public ExperimentResult(float porosity,long depth,long oil){
		this.porosity = porosity;
		this.finalDepth = depth;
		this.finalTotalOil = oil;
	}
	
	public float getPorosity() {
		return porosity;
	}
	public void setPorosity(float porosity) {
		this.porosity = porosity;
	}
	public long getFinalDepth() {
		return finalDepth;
	}
	public void setFinalDepth(long finalDepth) {
		this.finalDepth = finalDepth;
	}
	public long getFinalTotalOil() {
		return finalTotalOil;
	}
	public void setFinalTotalOil(long finalTotalOil) {
		this.finalTotalOil = finalTotalOil;
	}
	
	
}
