/**
 * Helper class that contains helpful classes for performing update statements via DBHelper
 */
package Provisio;

import java.util.ArrayList;

public class DBUpdateStatement extends DBWhereClause implements DBUpdateStatementInterface {
    private String[][] set_pairs;
    private String table_name;
    private Boolean did_init_private_update_fields;

    public DBUpdateStatement(){
        super();

        this.did_init_private_update_fields = false;
    }

    /**
     * Set table name
     * @param table_name : String
     * @return this
     */
    public DBUpdateStatement table(
        String table_name
    ){
        this.table_name = table_name;
        return this;
    }

    /**
     * Set SET column_name, column_value pairs
     * @param set_pairs : String[][] = (e.g. [["column1", "value"]])
     * @return this
     */
    public DBUpdateStatement set(
        String[][] set_pairs
    ){
        this.set_pairs = set_pairs;
        
        if (this.table_name != null)
            this.did_init_private_update_fields = true;

        return this;
    }

    /**
     * See if they specified a valid update
     * @return Boolean
     */
    protected Boolean isValidUpdateStatement() {
        if (
            this.didSpecifyWhereClause() == false || // Prevent accidental updates of whole table
            this.did_init_private_update_fields == false ||
            this.set_pairs.length == 0 ||
            this.set_pairs[0].length < 2 ||
            this.table_name == null ||
            this.table_name.equals("")
        )
            return false;

        return true;
    }

    /**
     * Get table name
     * @return String | null
     */
    protected String getTableName(){
        return this.table_name;
    }

    /**
     * Get array of only set values
     * @return ArrayList<String>
     */
    protected ArrayList<String> getSetPairValuesAsArray(){
        ArrayList<String> set_pair_values = new ArrayList<String>();

        for (int i = 0; i < this.set_pairs.length; i++){
            if (this.set_pairs[i].length > 1)
                set_pair_values.add(set_pairs[i][1]);
        }

        return set_pair_values;
    }

    /**
     * Get set pairs as string
     * @return String
     */
    protected String getSetPairsAsString(){
        String set_pairs_clause = "";

        for (int i = 0; i < this.set_pairs.length; i++){
            if (this.set_pairs[i].length < 2)
                continue;

            if (i != 0)
                set_pairs_clause += ", " + this.set_pairs[i][0] + " = ?";
            else 
                set_pairs_clause = this.set_pairs[i][0] + " = ?";
        }

        return set_pairs_clause;
    }
}