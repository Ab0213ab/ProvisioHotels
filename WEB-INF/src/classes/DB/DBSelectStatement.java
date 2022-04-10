/**
 * Helper class that contains helpful classes for performing select statements via DBHelper
 */
package Provisio;

import java.util.ArrayList;

public class DBSelectStatement extends DBStatement<DBSelectStatement> {
	private String target_table;
	private ArrayList<String> columns;

	public DBSelectStatement(){
		super();
	}

	/**
	 * Set target table
	 * @param table_name : the target table name
	 * @return this
	 */
	public DBSelectStatement fromTable(
		String table_name
	){
		this.target_table = table_name;
		return this;
	}

	/**
	 * Set desired columns (to be selected)
	 * @param columns : array of column names
	 * @return this
	 */
	public DBSelectStatement columns(
		String[] columns
	){
		if (this.columns == null)
			this.columns = new ArrayList<String>();

		for (String column: columns){
			this.columns.add(column);
		}

		return this;
	}

	/**
	 * Get target table
	 * @return String | null
	 */
	protected String getTableName(){
		return this.target_table;
	}

	/**
	 * Get all columns
	 * @return ArrayList<String> | null
	 */
	protected ArrayList<String> getColumns(){
		return this.columns;
	}

	/**
	 * Get all columns joined into an appropriate string
	 * @return String | null
	 */
	protected String getColumnsAsString(){
		if (this.columns != null)
			return String.join(",", this.columns);

		return null;
	}
}