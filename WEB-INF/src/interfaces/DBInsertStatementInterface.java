/**
 * Quick look interface for public DBInsertStatement method definitions
 */
package Provisio;

import java.util.ArrayList;

interface DBInsertStatementInterface {
    /**
     * Specify table name to be inserted
     * @param table_name : String
     * @return this
     */
    public DBInsertStatementInterface intoTable(
        String table_name
    );

    /**
     * Set columns for insert
     * @param insert_columns : ArrayList<String>
     * @return this
     */
    public DBInsertStatementInterface columns(
        ArrayList<String> column_names
    );

    /**
     * Set values for insert
     * @param insert_values : ArrayList<String>
     * @return this
     */
    public DBInsertStatementInterface values(
        ArrayList<String> insert_values
    );
}