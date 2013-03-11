package ru.expandable.util;

import ru.expandable.interfaces.IRubric;
import ru.expandable.rubric.units.RubricItem;
import loc.org.json.JSONException;
import loc.org.json.JSONObject;

public class Parser {

	
	
	public static IRubric parseIRubric(JSONObject obj) {
		
		int id = 0;
		int sort = 0;
		int count = 0;
		
		String name_short = "none";
		String name_full = "none";
		
		
		try {
			
			id = obj.getInt("id");
			sort = obj.getInt("sort");
			count = obj.getInt("count");
			
			JSONObject name = obj.getJSONObject("name");
			
			name_full = name.getString("full");
			
			if( !name.isNull("short"))
				name_short = name.getString("short");
			
		} catch (JSONException e) {
			e.printStackTrace();
			NetLog.e("%s", e);
		}
		
		
		return new RubricItem(id, name_full, name_short, count, sort);
	}

}
