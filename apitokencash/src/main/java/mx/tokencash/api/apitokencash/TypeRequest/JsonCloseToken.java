package mx.tokencash.api.apitokencash.TypeRequest;

/**
 * @author Carlos Buruel
 * @since 01/04/2017
 * @version 1.0.0
 */

public class JsonCloseToken
	extends JsonToken
{
	public JsonCloseToken()
	{
		super();
		this.o_DATOS_JSON.put("\"APIKEY\"", "");
		this.o_DATOS_JSON.put("\"TOK_NUMERO\"", "");
		this.o_DATOS_JSON.put("\"TRANSACCION\"", "");
	}

	public void setAPI_KEY(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"APIKEY\"", "\"" + e_VALUE + "\"");
	}

	public void setTOKEN(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"TOK_NUMERO\"", "\"" + e_VALUE + "\"");
	}

	public void setTRANSACCION(String e_VALUE)
	{
		this.o_DATOS_JSON.put("\"TRANSACCION\"", "\"" + e_VALUE + "\"");
	}

	public boolean validateRequiered()
	{
		return this.o_DATOS_JSON.containsKey("\"APIKEY\"") &&
			this.o_DATOS_JSON.containsKey("\"TOK_NUMERO\"") &&
			this.o_DATOS_JSON.containsKey("\"TRANSACCION\"");
	}
}
