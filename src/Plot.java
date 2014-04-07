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

	public Plot(String name){
//		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createXYLineChart(
				name, "Porosity", "Depth", this.dataSet, PlotOrientation.VERTICAL, true, true,
				false);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		NumberAxis domain = (NumberAxis) xyplot.getDomainAxis();
        domain.setRange(0.10, 0.75);
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
		double[][] series = new double[2][Experiment.NUM_OF_POROSITIES];
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
		frame.add(new Plot("Experiment").getChartPanel());
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
	}

}
