package common;

import java.util.Vector;

public class Key {

	private Vector<String> key;
	
	public Key(String id) {
		this(null, id);
	}	
	
	public Key(Key parent, String id) {
		this.key=new Vector<String>();
		if (null!=parent) {
			for(String subKey: parent.key) {
				key.add(subKey);
			}
		}
		key.add(id);
		
	}

	public int hashCode() {
		return concat().hashCode();
		
	}
	
	public boolean equals(Object rhs) {
		if (rhs.getClass()==this.getClass()) {
			if (concat().equals( ( (Key)rhs ).concat())) {
				return true;
			}
		}
		return false;
	}
	
	private String concat() {
		StringBuffer sb=new StringBuffer();
		for (int i=0; i<key.size(); i++) {
			if (0!=i) sb.append("//");
			sb.append(key.elementAt(i));
			
		}
		
		return sb.toString();
	}
	
	public String toString() {
		return concat();
	}
	
	public String subKey(int index) {
		if (index<key.size()) {
			return key.elementAt(index);
		} else {
			return null;
		}
	}
	
	
	
}
