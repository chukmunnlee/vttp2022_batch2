package vttp.paf.day23.repositories;

public class Queries {

    public static final String SQL_SELECT_STYLES = "select id, style_name from styles order by style_name asc";
    public static final String SQL_SELECT_BREWERIES_BY_STYLE = "SELECT name FROM breweries_styles where id = ?";
    
}
