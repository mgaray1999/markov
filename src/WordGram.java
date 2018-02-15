import java.util.*;

public class WordGram implements Comparable<WordGram>{
	
	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		for (int k = 0; k<size; k++) {
			myWords[k] = source[k+start];//create array from source
		}
		int hash = 0;
		for (int k = 0; k<myWords.length; k++) {//iterate across all words
			hash = myWords[k].hashCode() * 37; //37 is an arbitrary prime
			hash = 37 * hash;//here you pay respect to positioning
		}
		myHash = hash;
	}
	
	@Override
	public int hashCode() {
		return myHash;
	}
	
	@Override
	public String toString() {
		String joined = String.join(" ", this.myWords);//.join as directed
		return joined;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) { //instance of class and not empty
			return false;
		
		}
		if (other == this) {//if same instance then same
			return true;
		}
		
		WordGram obj = (WordGram) other; //same class
		
		if (!Arrays.equals(myWords, obj.myWords)) {//check equal arrays
			return false;
		}
		
		if (this.myHash != obj.myHash) {//check equal hashes
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(WordGram wg) {
		int counter = 0;
		int holder = 0;
		int lesser = wg.myWords.length;
		if (wg.myWords.length > this.myWords.length) { //want to iterate across lesser so need this
			lesser = this.myWords.length;
		}
		for (int k = 0; k<lesser; k++) { //iterate length of shortest array
			if (!Objects.equals(this.myWords[k], wg.myWords[k])) {
				holder = this.myWords[k].compareTo(wg.myWords[k]);//store compare to if dif
				counter = counter + holder;//add all compares to for proper result
			}
		}
		if (counter == 0) {
				counter = this.myWords.length - wg.myWords.length;//if proper check lengths, if good it'll be 0
		}
		return counter;	
	}
	
	public int length() {
		return myWords.length; //just length
	}
	
	public WordGram shiftAdd(String last) {
		String[] shifter = new String[myWords.length]; //adds to array for manipulations
		for (int k = 1; k<myWords.length; k++) { //itterates through array to add to other
			shifter[k-1] = myWords[k]; //fills in array with terms in proper positions save end
		}
		shifter[shifter.length-1] = last; //adds last at the end
		return new WordGram(shifter,0,shifter.length); //returns above
	}
}
