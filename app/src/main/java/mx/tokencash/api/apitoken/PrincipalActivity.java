package mx.tokencash.api.apitoken;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import mx.tokencash.api.apitokencash.Request.RequestApi;
import mx.tokencash.api.apitokencash.TypeRequest.JsonCancelToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonChangeToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonCreateToken;
import mx.tokencash.api.apitokencash.TypeRequest.JsonObtainDetail;
import mx.tokencash.api.apitokencash.TypeRequest.JsonObtainRewards;

public class PrincipalActivity
	extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		//Objeto para llamadas
		RequestApi o_API = new RequestApi()
		{
			@Override
			public void getResponse(JSONObject e_OUT, String e_METHOD)
			{
				System.out.println(e_OUT);
			}
		};

		//Creacion de tipo de datos para creacion del token
		JsonCreateToken crearToken = new JsonCreateToken();
		crearToken.setApiKey("UIAQ9V9ZB3M884Q6BCVWXQORIOQ3FI043YMFO3X8");
		crearToken.setMONTO(1.0);
		crearToken.setREFERENCIA("REF-EXT4");
		crearToken.setRECOMPENSA(0);
		//Hacer llamada a crear token
		o_API.callCreateToken(crearToken);

		JsonChangeToken cambiarToken = new JsonChangeToken();
		cambiarToken.setAPI_KEY("UIAQ9V9ZB3M884Q6BCVWXQORIOQ3FI043YMFO3X8");
		cambiarToken.setMONTO(123.0);
		cambiarToken.setTOK_NUMERO("5936");
		cambiarToken.setTRANSACCION("P20170106123329YQ27QJQ1G9");
		//Hacer llamada a cambiar token
//		o_API.callChangeToken(cambiarToken);

		JsonCancelToken cancelarToken = new JsonCancelToken();
		cancelarToken.setAPI_KEY("UIAQ9V9ZB3M884Q6BCVWXQORIOQ3FI043YMFO3X8");
		cancelarToken.setTOKEN("5230");
		cancelarToken.setTRANSACCION("P20161229151338BIMOBVPL9N");
		//Hacer llamada a cancelar token
//		o_API.callCancelToken(cancelarToken);

		JsonObtainDetail obtenerToken = new JsonObtainDetail();
		obtenerToken.setAPI_KEY("UIAQ9V9ZB3M884Q6BCVWXQORIOQ3FI043YMFO3X8");
		obtenerToken.setTOKEN("2774");
		obtenerToken.setTRANSACCION("P201701041310169NFLAR1U8I");
		//o_API.callObtainDetail(obtenerToken);

		JsonObtainRewards obtenerRecompensas = new JsonObtainRewards();
		obtenerRecompensas.setAPI_KEY("UIAQ9V9ZB3M884Q6BCVWXQORIOQ3FI043YMFO3X8");
//		o_API.callObtainRewards(obtenerRecompensas);
	}
}