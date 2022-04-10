/**
 * Quick look interface for public DBUpdateStatement method definitions
 */
package Provisio;

interface DBUpdateStatementInterface {
    /**
     * Set table name
     * @param table_name : String
     * @return this
     */
    public DBUpdateStatement table(
        String table_name
    );

    /**
     * Set SET column_name, column_value pairs
     * @param set_pairs : String[][] = (e.g. [["column1", "value"]])
     * @return this
     */
    public DBUpdateStatement set(
        String[][] set_pairs
    );
}