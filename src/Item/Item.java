package Item;

import java.util.List;

public class Item {
	private String nom;
	protected List<String> statNames;
	protected List<Integer> statValues;
	
	public Item(String nom, List<String> statNames, List<Integer> statValues) {
		this.nom = nom;
		this.statNames = statNames;
		this.statValues = statValues;
	}
	
	public List<String> getStatNames() {return statNames;}
	
	public List<Integer> getStatValues() {return statValues;}
	
	public String getName() {return nom;}
		
}
