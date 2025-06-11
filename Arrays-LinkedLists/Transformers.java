
class UpperCaseTransformer implements MyTransformer<String> {

	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

// Add your transformers here
class MultiplyBy2 implements MyTransformer<Integer> {
	
	public Integer transformElement(Integer s) {
		return s * 2;
	}
}

class MakeSameLetter implements MyTransformer<String> {
	
	public String transformElement(String s) {
		return s.replaceAll("a", "b");
	}
}