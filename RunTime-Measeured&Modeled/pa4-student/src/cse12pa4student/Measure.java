package cse12pa4student;

import java.util.List;
import java.util.ArrayList;
import static cse12pa4mysteries.Mysteries.*;

public class Measure {


	public static List<Measurement> measure(String[] toRun, int startN, int stopN) {
		
		ArrayList<Measurement> Measure = new ArrayList<Measurement>();
		for(String letter : toRun){
			mysteryA(1);
			if(letter == "A") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryA(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
			
			if(letter == "B") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryB(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
			
			if(letter == "C") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryC(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
			
			if(letter == "D") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryD(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
			
			if(letter == "E") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryE(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
			
			if(letter == "F") {
				for(int i = startN; i <= stopN; i+=1) {
					long startTime = System.nanoTime();
					mysteryF(i);
					long estimatedTime = System.nanoTime() - startTime;
					Measurement runTime = new Measurement(letter, i, estimatedTime);
					Measure.add(runTime);
				}
			}
		}
		if(Measure.size() == 0) {
			return null;
		}
		return Measure;
	}
	

	public static String measurementsToCSV(List<Measurement> toConvert) {
		String chart;
		chart = "name,n,nanoseconds" + "\n";
		for(Measurement i : toConvert) {
			chart += i.name + "," + String.valueOf(i.valueOfN) + "," + String.valueOf(i.nanosecondsToRun) + "\n";
		}
		return chart;
	}
	
	/* Add any helper methods you find useful */
		
}
