package vttp.paf.day23.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BeerStyle {

    private Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static BeerStyle create(SqlRowSet rs) {
        final BeerStyle style = new BeerStyle();
        style.setId(rs.getInt("id"));
        style.setName(rs.getString("style_name"));
        return style;
    }
    
}
