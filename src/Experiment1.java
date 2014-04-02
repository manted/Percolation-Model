
public class Experiment1 {
	public static void main(String args[]){
		System.out.println("Experiment 1: 2D Constant Porosity");
		Experiment constant2D = new Experiment();
		constant2D.setup(Experiment.TWO_D, Percolation.CONSTANT_POROSITY, 20000);
		constant2D.run();
		System.out.println("Experiment 1: 3D Constant Porosity");
		Experiment constant3D = new Experiment();
		constant3D.setup(Experiment.THREE_D, Percolation.CONSTANT_POROSITY, 20000);
		constant3D.run();
	}
}
