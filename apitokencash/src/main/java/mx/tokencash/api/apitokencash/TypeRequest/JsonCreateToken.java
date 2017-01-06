package mx.tokencash.api.apitokencash.TypeRequest;

/**
 * @author Carlos Buruel
 * @since 01/04/2017
 * @version 1.0.0
 */

public class JsonCreateToken
	extends JsonToken
{
	public JsonCreateToken()
	{
		super();
	}

	public void setApiKey(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"APIKEY\"", "\"" + e_VALUE + "\"");
	}

	public void setMONTO(Double e_VALUE)
	{
		this.o_DATOS_JSON.put("\"MONTO\"", "\"" + e_VALUE + "\"");
	}

	public void setREFERENCIA(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"REFERENCIA\"", "\"" + e_VALUE + "\"");
	}

	public void setRECOMPENSA(int e_VALUE)
	{
		this.o_DATOS_JSON.put("\"RECOMPENSA\"", "\"" + e_VALUE + "\"");
	}

	public boolean validateRequiered()
	{
		return
			this.o_DATOS_JSON.containsKey("\"APIKEY\"") &&
			this.o_DATOS_JSON.containsKey("\"MONTO\"");
	}
}