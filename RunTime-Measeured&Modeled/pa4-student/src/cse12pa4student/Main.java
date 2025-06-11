package cse12pa4student;
public class Main {
	
	public static void main(String[] args) {
		String data = (Measure.measurementsToCSV(Measure.measure(new String[] {"F"},0, 50)));
		System.out.println(data);
	}
}
