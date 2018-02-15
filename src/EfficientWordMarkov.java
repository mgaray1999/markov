import java.util.*;

public class EfficientWordMarkov extends WordMarkovModel {
	Map<WordGram,ArrayList<String>> myMap;
	
	EfficientWordMarkov(int order){
		super(order);
		myMap = new TreeMap<>(); // constructor
	}
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+"); //create array
		myMap.clear();
		for (int k = 0; k<myWords.length-(getOrder()-1); k++) { //iterate across words
			WordGram sWord = new WordGram(myWords,k, getOrder());
			if (!myMap.containsKey(sWord)){
				myMap.put(sWord, new ArrayList<String>());
			}
			ArrayList<String> alist = myMap.get(sWord); //if end PSEUDO_EOS
			if (k + getOrder()== myWords.length) {
				alist.add(PSEUDO_EOS);
			}
			if (k+ getOrder() < myWords.length){ //if not max
				alist.add(myWords[k+getOrder()]);
			}
			myMap.put(sWord, alist); //add to map
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key){
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		else {
			throw new NoSuchElementException("No Characters Follow This K-Gram");
		}
	}
}
