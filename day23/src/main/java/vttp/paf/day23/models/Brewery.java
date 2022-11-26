package vttp.paf.day23.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Brewery {

    private String name;

	 public Brewery() {};
	 public Brewery(String name) {
		 this.name = name;
	 };

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static Brewery create(SqlRowSet rs) {
        final Brewery brewery = new Brewery();
        brewery.setName(rs.getString("name"));
        return brewery;
    }
    
}
