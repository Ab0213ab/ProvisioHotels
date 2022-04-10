/**
 * Quick look interface for public DBResult method definitions
 */
package Provisio;

import java.util.Hashtable;
import java.util.ArrayList;

interface DBResultInterface {
	/**
	 * Get records
	 * @return ArrayList<Hashtable<String, String>> ([{"column name" : value}])
	 */
	public ArrayList<Hashtable<String, String>> getRecords();
}