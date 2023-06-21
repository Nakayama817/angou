package faceCipher;

import java.io.Serializable;
import java.util.ArrayList;

public class ConvertStringEntity implements Serializable {
	
	private ArrayList<String> rowStrings;
	private String specificator;
	private Converter[] converters;
	
	
	public ArrayList<String> getRowStrings() {
		return rowStrings;
	}
	public void setRowStrings(ArrayList<String> rowStrings) {
		this.rowStrings = rowStrings;
	}
	public String getSpecificator() {
		return specificator;
	}
	public void setSpecificator(String specificator) {
		this.specificator = specificator;
	}
	public Converter[] getConverters() {
		return converters;
	}
	public void setConverters(Converter[] converters) {
		this.converters = converters;
	}
	

}
