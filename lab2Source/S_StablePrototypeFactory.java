package lab2Source;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class S_StablePrototypeFactory extends S_StableFactory {

	private HashMap<String, MapSite> types;

	public S_StablePrototypeFactory() {
		types = new HashMap<String, MapSite>();
	}
	
	public MapSite getClone(String name)
	{
		if(types.containsKey(name))
		{
			return types.get(name).cloneMe();
		}
		else
			throw new InvalidParameterException("type doesnt exist in store");
	}

	public void addType(String name, MapSite type) {
		if(!types.containsKey(name))
		{
			types.put(name, type);
		}
	}

}
