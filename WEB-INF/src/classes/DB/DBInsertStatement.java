/**
 * Helper class that contains helpful classes for performing insert statements via DBHelper
 */
package Provisio;

import java.util.ArrayList;

public class DBInsertStatement implements DBInsertStatementInterface {
    private ArrayList<String> insert_columns;
    private ArrayList<String> insert_values;
    private String into_table;

    public DBInsertStatement(){
        this.insert_columns = new ArrayList<String>();
        this.insert_values = new ArrayList<String>();
        this.into_table = "";
    }

    /**
     * Specify table name to be inserted
     * @param table_name : String
     * @return this
     */
    public DBInsertStatement intoTable(
        String table_name
    ){
        this.into_table = table_name;
        return this;
    }

    /**
     * Set columns for insert
     * @param insert_columns : ArrayList<String>
     * @return this
     */
    public DBInsertStatement columns(
        ArrayList<String> column_names
    ){
        this.insert_columns = column_names;
        return this;
    }

    /**
     * Set values for insert
     * @param insert_values : ArrayList<String>
     * @return this
     */
    public DBInsertStatement values(
        ArrayList<String> insert_values
    ){
        this.insert_values = insert_values;
        return this;
    }

    /**
     * Get column names
     * @return ArrayList<String> | null
     */
    protected ArrayList<String> getColumnNames(){
        return this.insert_columns;
    }

    /**
     * Get column values
     * @return ArrayList<String> | null
     */
    protected ArrayList<String> getColumnValues(){
        return this.insert_values;
    }

    /**
     * Get table name
     * @return String | null
     */
    protected String getTableName(){
        return this.into_table;
    }

    /**
     * Get column names as a string
     * @return String | null
     */
    protected String getColumnNamesAsString(){
        if (this.insert_columns == null)
            return null;

        String column_names = this.insert_columns.get(0);

        for (int i = 1; i < this.insert_columns.size(); i++){
            column_names += "," + this.insert_columns.get(i);
        }

        return column_names;
    }

    /**
     * Check if contains a valid insert statement (holistacally)
     * @return Boolean
     */
    protected Boolean isValidInsertStatement(){
        if (
            this.insert_columns.size() == 0 ||
            this.insert_values.size() == 0 ||
            this.insert_columns.size() != this.insert_values.size() ||
            this.into_table.equals("")
        )
            return false;

        return true;
    }
}