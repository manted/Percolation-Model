
public class Experiment {
	public static final int TWOD = 0;
	public static final int THREED = 1;
	
//	private float porosity = 0.0f;
//	private int porosityMode = 0;
//	private int maxTicks = 0;
	
	private Percolation model;
	
	public void setup(int model, float porosity, int mode, int ticks){
		if(model == TWOD){
			this.model = new Percolation2D();
		}else{
			this.model = new Percolation3D();
		}
//		this.porosity = porosity;
//		this.porosityMode = mode;
//		this.maxTicks = ticks;
		this.model.setup(porosity, ticks, mode);
	}
	
	public void run(){
		this.model.start();
	}
}
