import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class Plot {
	ChartPanel frame1;
	private DefaultXYDataset dataSet = new DefaultXYDataset();

	public Plot(int modelMode, int porosityMode){
		String mMode;
		String pMode;
		if(modelMode == Experiment.TWO_D){
			mMode = "2D";
		}else{
			mMode = "3D";
		}
		if(porosityMode == Percolation.CONSTANT_POROSITY){
			pMode = "Constant Porosity";
		}else{
			pMode = "Different Porosity";
		}
		String name = mMode + " " + pMode + " Experiment";
//		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createXYLineChart(
				name, "Porosity", "Depth", this.dataSet, PlotOrientation.VERTICAL, false, true,
				false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		NumberAxis domain = (NumberAxis) xyplot.getDomainAxis();
		if(modelMode == Experiment.TWO_D){
			domain.setRange(Experiment.INITIAL_POROSITY_2D, Experiment.MAX_POROSITY_2D);
		}else{
			domain.setRange(Experiment.INITIAL_POROSITY_3D, Experiment.MAX_POROSITY_3D);
		}
        
        domain.setTickUnit(new NumberTickUnit(0.05));
        domain.setVerticalTickLabels(true);
//        NumberAxis range = (NumberAxis) xyplot.getRangeAxis();
//        range.setStandardTickUnits(ticks);
        
        // render shapes and lines
        XYLineAndShapeRenderer renderer =
            new XYLineAndShapeRenderer(true, false);
        xyplot.setRenderer(renderer);
//        renderer.setBaseShapesVisible(true);
//        renderer.setBaseShapesFilled(true);
        
		frame1 = new ChartPanel(jfreechart, true);

	}

	public void addSeries(String name, ArrayList<ExperimentResult> results){
		double[][] series = new double[2][results.size()];
		for(int i = 0; i < results.size(); i++){
			series[0][i] = (double) results.get(i).getPorosity();
            series[1][i] = results.get(i).getFinalDepth();
		}
		dataSet.addSeries(name, series);
	}
	
//	private static XYDataset createDataset() {	
//		
//		return dataSet;
//	}

	public ChartPanel getChartPanel() {
		return frame1;

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Percolation");
		frame.setLayout(new GridLayout(1, 1, 10, 10));
		frame.add(new Plot(0,0).getChartPanel());
		frame.setBounds(50, 50, 500, 400);
		frame.setVisible(true);
	}

}
