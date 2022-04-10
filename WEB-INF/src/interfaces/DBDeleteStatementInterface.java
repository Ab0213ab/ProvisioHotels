/**
 * Quick look interface for public DBDeleteStatement method definitions
 */
package Provisio;

interface DBDeleteStatementInterface {
	/**
     * Set table name
     * @param table_name : String
     * @return this
     */
    public DBDeleteStatement fromTable(
        String table_name
    );
}