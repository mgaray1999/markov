import java.util.*; 

public class EfficientMarkov extends MarkovModel {
	
	Map<String,ArrayList<String>> myMap;
	
	EfficientMarkov(int order){
		super(order);
		myMap = new HashMap<>();//map
		myOrder = order;//saves order
	}
	@Override
	public void setTraining (String text){
		myText = text;
		myMap.clear();
		for (int k = 0; k<=myText.length()-myOrder; k++) { //iterates through string
			if (myMap.get(myText.substring(k, k+myOrder)) == null){
				myMap.put(myText.substring(k, k+myOrder), new ArrayList<String>()); //add array list to value if none existed
			}
			if (k == myText.length()-myOrder) {
				myMap.get(myText.substring(k, k+myOrder)).add(PSEUDO_EOS); //PSEUDO_EOS if next
			}
			
			else{
				myMap.get(myText.substring(k, k+myOrder)).add(myText.substring(k+myOrder, k+myOrder+1)); //Add substring to array for non ending keys
			}
		}
		
		}
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.get(key) == null) {
			throw new NoSuchElementException("No Characters Follow This K-Gram"); //error if no characters follow
		}
		else {
			return myMap.get(key);	
		}
	}
}
