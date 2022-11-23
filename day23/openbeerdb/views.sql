create view breweries_styles as
	select s.id, s.style_name, br.name, br.website
        from styles s
		join beers b 
		on s.id = b.style_id
		join breweries br 
		on br.id = b.brewery_id
		order by br.name;