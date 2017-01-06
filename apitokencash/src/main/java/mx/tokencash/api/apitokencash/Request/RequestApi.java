package mx.tokencash.api.apitokencash.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import mx.tokencash.api.apitokencash.Tools.Constant;
import mx.tokencash.api.apitokencash.TypeRequest.JsonCancelToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonChangeToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonCloseToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonCreateToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonObtainDetail;
import mx.tokencash.api.apitokencash.TypeRequest.JsonObtainRewards;

/**
 * @author Carlos Buruel
 * @since 01/04/2017
 * @version 1.0.0
 */

public abstract class RequestApi
{
	private JSONObject o_EX_JSON, o_EX_IO, o_EX_MU, o_EX_PROTOCOL, o_EX_INCOMPLETE;

	public RequestApi()
	{
		startExcepcion();
	}

	/**
	 * Call create a token
	 * @param o_JSON_TOKEN params for POST
	 */
	public void callCreateToken(JsonCreateToken o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_CREAR_TOKEN, o_JSON_TOKEN.toString());
	}

	public void callChangeToken(JsonChangeToken o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_CHANGE_TOKEN, o_JSON_TOKEN.toString());
	}
	public void callClosetoken(JsonCloseToken o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_CLOSE_TOKEN, o_JSON_TOKEN.toString());
	}

	public void callCancelToken(JsonCancelToken o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_CANCEL_TOKEN, o_JSON_TOKEN.toString());
	}

	public void callObtainDetail(JsonObtainDetail o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_OBTAIN_DETAIL, o_JSON_TOKEN.toString());
	}


	public void callObtainRewards(JsonObtainRewards o_JSON_TOKEN)
	{
		if( !o_JSON_TOKEN.validateRequiered() )
		{
			getResponse(o_EX_INCOMPLETE, "");
			return;
		}
		callRequest(Constant.e_OBTAIN_REWARDS, o_JSON_TOKEN.toString());
	}

	private void callRequest(final String e_METHOD, final String e_JSON_TOKEN)
	{
		//Recover 12/28/2016, http://stackoverflow.com/questions/15684846/convert-curl-call-into-java-urlconnection-call
		//final URL
		String e_PATH = Constant.e_BASE_URL + e_METHOD;
		try
		{
			//URL url = new URL("http://www.fakeresponse.com/api/?sleep=3");
			URL url = new URL(e_PATH);
			final HttpURLConnection o_CONNECTION = (HttpURLConnection) url.openConnection();
			o_CONNECTION.setConnectTimeout(5000);
			o_CONNECTION.setReadTimeout(5000);
			//Set properties
			o_CONNECTION.setRequestProperty("Content-Type", "application/json");
			//DoOutput in true
			o_CONNECTION.setDoOutput(true);

			o_CONNECTION.setRequestMethod("POST");

			//Get response
			Thread o_THREAD = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						OutputStreamWriter o_OUTPUT_STREAM_WRITER = new OutputStreamWriter(o_CONNECTION.getOutputStream());
						o_OUTPUT_STREAM_WRITER.write(e_JSON_TOKEN);
						o_OUTPUT_STREAM_WRITER.close();

						InputStreamReader inputStreamReader = new InputStreamReader(o_CONNECTION.getInputStream());
						int e_CODE_RESPONSE = o_CONNECTION.getResponseCode();

						//Value code response
						String e_ERROR_CLIENT_RESPONSE;
						switch(e_CODE_RESPONSE)
						{
							case 400: e_ERROR_CLIENT_RESPONSE = "Bad Request"; break;
							case 401: e_ERROR_CLIENT_RESPONSE = "Unauthorized"; break;
							case 402: e_ERROR_CLIENT_RESPONSE = "Payment Required"; break;
							case 403: e_ERROR_CLIENT_RESPONSE = "Forbidden"; break;
							case 404: e_ERROR_CLIENT_RESPONSE = "Not found"; break;
							case 405: e_ERROR_CLIENT_RESPONSE = "Method Not Allowed"; break;
							case 406: e_ERROR_CLIENT_RESPONSE = "Not Acceptable"; break;
							case 407: e_ERROR_CLIENT_RESPONSE = "Proxy Authentication Required"; break;
							case 408: e_ERROR_CLIENT_RESPONSE = "Request Timeout"; break;
							case 409: e_ERROR_CLIENT_RESPONSE = "Conflict"; break;
							case 410: e_ERROR_CLIENT_RESPONSE = "Gone"; break;
							case 411: e_ERROR_CLIENT_RESPONSE = "Length Required"; break;
							case 412: e_ERROR_CLIENT_RESPONSE = "Precondition Failed"; break;
							case 413: e_ERROR_CLIENT_RESPONSE = "Request Entity Too Large"; break;
							case 414: e_ERROR_CLIENT_RESPONSE = "Request-URI Too Long"; break;
							case 415: e_ERROR_CLIENT_RESPONSE = "Unsupported Media Type"; break;
							case 416: e_ERROR_CLIENT_RESPONSE = "Requested Range Not Satisfiable"; break;
							case 417: e_ERROR_CLIENT_RESPONSE = "Expectation Failed"; break;
							case 422: e_ERROR_CLIENT_RESPONSE = "Unprocessable Entity"; break;
							case 423: e_ERROR_CLIENT_RESPONSE = "Locked"; break;
							case 424: e_ERROR_CLIENT_RESPONSE = "Failed Dependency"; break;
							case 425: e_ERROR_CLIENT_RESPONSE = "Unassigned"; break;
							case 426: e_ERROR_CLIENT_RESPONSE = "Upgrade Required"; break;
							case 428: e_ERROR_CLIENT_RESPONSE = "Precondition Required"; break;
							case 429: e_ERROR_CLIENT_RESPONSE = "Too Many Requests"; break;
							case 431: e_ERROR_CLIENT_RESPONSE = "Request Header Fileds Too Large"; break;
							case 451: e_ERROR_CLIENT_RESPONSE = "Unavailable for Legal Reasons"; break;
							default:
								BufferedReader reader = new BufferedReader(inputStreamReader);
								String e_OUT = "";
								//Iterator on response
								for(String e_LINE; (e_LINE = reader.readLine()) != null;)
								{
									e_OUT += e_LINE;
								}
								JSONObject o_JSON = new JSONObject(e_OUT);
								//Make response
								o_JSON = makeResponse(o_JSON, e_METHOD);

								getResponse(o_JSON, e_METHOD);
								return;
						}

						JSONObject o_JSON_ERROR = new JSONObject();
						o_JSON_ERROR.put("Error", e_ERROR_CLIENT_RESPONSE);
						getResponse(o_JSON_ERROR, "");
					}
					catch(JSONException o_JSON_EX)
					{
						getResponse(o_EX_JSON, e_METHOD);
					}
					catch(IOException o_IO_EX)
					{
						getResponse(o_EX_IO, e_METHOD);
					}

				}
			});
			//start thread
			o_THREAD.start();

		}
		catch(MalformedURLException o_MU_EX)
		{
			getResponse(o_EX_MU, e_METHOD);
		}
		catch(ProtocolException o_P_EX)
		{
			getResponse(o_EX_PROTOCOL, e_METHOD);
		}
		catch (IOException e)
		{
			getResponse(o_EX_IO, e_METHOD);
		}
	}

	private JSONObject makeResponse(JSONObject o_JSON, String e_METHOD)
	{
		try
		{
			//catch exceptions when sucess value is false
			if( !o_JSON.getBoolean("success") )
			{
				//Read ERROR_CODE
				JSONObject o_JSON_RETURN = new JSONObject(o_JSON.getString("RETURN"));
				String e_ERROR_CODE = o_JSON_RETURN.getString("ERROR_CODE");
				switch(e_ERROR_CODE)
				{
					case "MONTO_INVALIDO":
						e_ERROR_CODE = "El monto es invalido";
						break;
					case "ERROR_SEGURIDAD":
						e_ERROR_CODE = "Error de seguridad";
						break;
					case "TOKEN_INCORRECTO":
						e_ERROR_CODE = "El token es incorrecto";
						break;
					case "TOKEN_PAGADO":
						e_ERROR_CODE = "El token fue pagado";
						break;
					case "TOKEN_CANCELADO":
						e_ERROR_CODE = "El token fue cancelado";
						break;
					case "TOKEN_SIN_ABONOS":
						e_ERROR_CODE = "Token sin abonos";
						break;
					case "TOKEN_CON_ABONOS":
						e_ERROR_CODE = "Token con abonos";
						break;
					default:
						e_ERROR_CODE = "Error desconocido";
				}
				JSONObject o_JSON_RESPONSE = new JSONObject();
				o_JSON_RESPONSE.put("SUCCESS", false);
				o_JSON_RESPONSE.put("ERROR_CODE", e_ERROR_CODE);
				return o_JSON_RESPONSE;
			}
			//Sucess response
			o_JSON.remove("MSGS");
			return o_JSON;
		//endregion
		}
		catch(Exception o_EX)
		{
			String a = o_EX.getMessage();
			System.out.println(a);
			return o_EX_JSON;
		}
	}

	private void startExcepcion()
	{
		o_EX_JSON = new JSONObject();
		o_EX_IO = new JSONObject();
		o_EX_MU = new JSONObject();
		o_EX_PROTOCOL = new JSONObject();
		o_EX_INCOMPLETE = new JSONObject();
		try
		{
			o_EX_JSON.put("Error", "Json is wrong");
			o_EX_IO.put("Error", "Input or output data is wrong");
			o_EX_MU.put("Error", "Incorrect URL formatting");
			o_EX_PROTOCOL.put("Error", "Protocol error");
			o_EX_INCOMPLETE.put("Error", "Data incomplete");
		}
		catch(Exception o_EX)
		{
			o_EX.printStackTrace();
		}
	}

	public abstract void getResponse(JSONObject e_OUT, String e_METHOD);
}