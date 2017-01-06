package mx.tokencash.api.apitokencash.TypeRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Buruel
 * @since 01/04/2017
 * @version 1.0.0
 */

class JsonToken
{
	//private data
	Map<String, String> o_DATOS_JSON;

	//Constructor
	JsonToken()
	{
		this.o_DATOS_JSON = new HashMap<>();
	}

	/**
	 * Obtain JSON in String
	 * @return JSON in String
	 */
	@Override
	public String toString()
	{
		String e_JSON = o_DATOS_JSON.toString();
		return e_JSON.replace("=", ":");
	}
}
