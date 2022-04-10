/**
 * Helper class that contains helpful classes for performing delete statements via DBHelper
 */
package Provisio;

public class DBDeleteStatement extends DBWhereClause implements DBDeleteStatementInterface {
    private String from_table;

    public DBDeleteStatement(){
        super();
    }

    /**
     * Set table name
     * @param table_name : String
     * @return this
     */
    public DBDeleteStatement fromTable(
        String table_name
    ){
        this.from_table = table_name;
        return this;
    }

    /**
     * Get table name
     * @return String | null
     */
    protected String getTableName(){
        return this.from_table;
    }

    /**
     * See if is valid delete statement
     * @return Boolean
     */
    protected Boolean isValidDeleteStatement(){
        if (
            this.didSpecifyWhereClause() == false || // Prevent accidental deletions of whole table
            this.from_table == null ||
            this.from_table.equals("")
        )
            return false;

        return true;
    }
}