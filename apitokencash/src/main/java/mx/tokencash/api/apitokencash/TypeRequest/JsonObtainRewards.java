package mx.tokencash.api.apitokencash.TypeRequest;

/**
 * @author Carlos Buruel
 * @since 01/05/2017
 * @version 1.0.0
 */

public class JsonObtainRewards
	extends JsonToken
{
	public JsonObtainRewards()
	{
		super();
	}

	public void setAPI_KEY(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"APIKEY\"", "\"" + e_VALUE + "\"");
	}

	public boolean validateRequiered()
	{
		return this.o_DATOS_JSON.containsKey("\"APIKEY\"");
	}
}