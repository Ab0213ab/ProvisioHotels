/**
 * Contains all arguments needed for a join (inner join) clause via DBHelper class
 */
package Provisio;

import java.sql.SQLException;

public class DBJoinClause implements DBJoinClauseInterface {
    private String complete_join_clause;
    private Boolean did_init_private_fields;

    public DBJoinClause(){
        this.did_init_private_fields = false;
    }

    /**
     * Adds a join clause to the statement
     * 
     * Example:
     *      join(
     *          "table2",
     *          ["table1.column1", "table2.column2"]
     *      )
     * 
     *      "INNER JOIN table2 ON table1.column1 = table2.column2"
     * 
     * @param table_name : String = name of table to be joined to main table
     * @param on : String[] = ON clause (e.g. ["table1.column1", "table2.column1"])
     * @return this
     */
    public DBJoinClause join(
        String table_name,
        String[] on
    ) throws SQLException {
        // Abort if `on` doesn't have 2 elements:
        if (on.length != 2)
            throw new SQLException("JOIN clause statement doesn't have 2 elements for `on` parameter.");

        if (this.did_init_private_fields == false){
            this.complete_join_clause = "";
            this.did_init_private_fields = true;
        }

        this.complete_join_clause += " INNER JOIN " + table_name + " ON " + on[0] + " = " + on[1];

        return this;
    }

    /**
     * Get the complete join clause
     * @return string | null
     */
    protected String getJoinClause(){
        return this.complete_join_clause;
    }

    /**
     * Retrieve if join clause was specified
     * @return Boolean
     */
    protected Boolean didSpecifyJoinClause(){
        return this.did_init_private_fields;
    }
}