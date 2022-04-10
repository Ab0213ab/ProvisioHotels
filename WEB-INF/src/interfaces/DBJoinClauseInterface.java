/**
 * Quick look interface for public DBJoinClause method definitions
 */
package Provisio;

import java.sql.SQLException;

interface DBJoinClauseInterface {
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
    public DBJoinClauseInterface join(
        String table_name,
        String[] on
    ) throws SQLException;
}