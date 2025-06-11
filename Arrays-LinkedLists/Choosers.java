
class LongWordChooser implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

} 

// Add your choosers here

class FavNumberChooser implements MyChooser<Integer>{
	
	public boolean chooseElement(Integer s) {
		return s == 3;
	}
}

class CharacterChooser implements MyChooser<String>{
	  public boolean chooseElement(String s) {
		  return s.length() == 1;
	  }
}