/**
 * General class that contains methods for joining and where clauses
 */
package Provisio;

import java.util.ArrayList;
import java.sql.SQLException;

public class DBStatement <T extends DBStatement<T>> implements DBStatementInterface, DBWhereClauseInterface, DBJoinClauseInterface {
	// Provide both Join and Where classes via composition:
	private DBWhereClause where_clause;
	private DBJoinClause join_clause;

	public DBStatement(){
		this.where_clause = new DBWhereClause();
		this.join_clause = new DBJoinClause();
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
    public T join(
        String table_name,
        String[] on
    ) throws SQLException {
    	this.join_clause.join(
    		table_name,
    		on
    	);

    	return (T)this;
    }

    /**
     * Get the complete join clause
     * @return string | null
     */
    protected String getJoinClause(){
        return this.join_clause.getJoinClause();
    }

    /**
     * Retrieve if join clause was specified
     * @return Boolean
     */
    protected Boolean didSpecifyJoinClause(){
        return this.join_clause.didSpecifyJoinClause();
    }

    /**
	 * Adds a where clause statement (e.g. field1 = "bob", field1 = "bob" OR field2 = 1, etc)
	 * @param clause_statement : String = ["field1 = ?", arg] (or ["field1 = 2"])
	 * @return this
	 */
	public T where(
		String[] clause_statement
	) throws SQLException {
		this.where_clause.where(
			clause_statement
		);

		return (T)this;
	}

	/**
	 * Specify whether where arguments should be concatenated
	 * with AND or OR
	 * @param use_and : if true, will use AND, otherwise OR
	 * @return this
	 */
	public T concatenateWithAnd(
		Boolean use_and
	){
		this.where_clause.concatenateWithAnd(use_and);
		return (T)this;
	}

	/**
	 * Get the complete where clause
	 * @return string | null
	 */
	protected String getWhereClause(){
		return this.where_clause.getWhereClause();
	}

	/**
	 * Get the complete list of where clause arguments
	 * @return ArrayList<String> | null
	 */
	protected ArrayList<String> getWhereClauseArguments(){
		return this.where_clause.getWhereClauseArguments();
	}

	/**
	 * Retrieve if where clause was specified
	 * @return Boolean
	 */
	protected Boolean didSpecifyWhereClause(){
		return this.where_clause.didSpecifyWhereClause();
	}
}