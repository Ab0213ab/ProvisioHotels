/**
 * Quick look interface for public DBWhereClause method definitions
 */
package Provisio;

import java.sql.SQLException;

interface DBWhereClauseInterface {
	/**
	 * Adds a where clause statement (e.g. field1 = "bob", field1 = "bob" OR field2 = 1, etc)
	 * @param clause_statement : String = ["field1 = ?", arg] (or ["field1 = 2"])
	 * @return this
	 */
	public DBWhereClauseInterface where(
		String[] clause_statement
	) throws SQLException;

	/**
	 * Specify whether where arguments should be concatenated
	 * with AND or OR
	 * @param use_and : if true, will use AND, otherwise OR
	 * @return this
	 */
	public DBWhereClauseInterface concatenateWithAnd(
		Boolean use_and
	);
}