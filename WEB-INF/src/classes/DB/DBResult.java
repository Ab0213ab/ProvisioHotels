/**
 * Contains the basic structure of a returned result from DBHelper method
 */
package Provisio;

import java.util.Hashtable;
import java.util.ArrayList;

public class DBResult implements DBResultInterface {
	private ArrayList<Hashtable<String, String>> records;

	public DBResult(){
		this.records = new ArrayList<Hashtable<String, String>>();
	}

	/**
	 * Get records
	 * @return ArrayList<Hashtable<String, String>>
	 */
	public ArrayList<Hashtable<String, String>> getRecords(){
		return this.records;
	}

	/**
	 * Assign records
	 * @param records : ArrayList<Hashtable<String, String>>
	 * @return this
	 */
	protected DBResult setRecords(
		ArrayList<Hashtable<String, String>> records
	){
		this.records = records;
		return this;
	}
}