package com.org.seratic.lucky.comunicacion;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.content.SharedPreferences;

import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_FotoAndroid;
import com.org.seratic.lucky.model.E_MarcacionRequest;
import com.org.seratic.lucky.model.E_RegistrarMotivoColgateBodega_Request;
import com.org.seratic.lucky.model.E_RegistroPDV_Mov;
import com.org.seratic.lucky.model.E_ReporteAlicorpAutoservicio_Mov;
import com.org.seratic.lucky.model.E_ReporteAlicorpMayorista_Mov;
import com.org.seratic.lucky.model.E_ReporteColgateFarmaciaIT_Mov;
import com.org.seratic.lucky.model.E_ReporteColgateMayoristaMov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoAAVV_Mov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoModerno_Mov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoTradicional_Mov;
import com.org.seratic.lucky.model.E_SincronizacionRequest;
import com.org.seratic.lucky.model.E_VisitaRequest;
import com.org.seratic.lucky.model.ReporteColgateBodega_Mov;
import com.org.seratic.lucky.model.ReporteColgateFarmaciaDT_Mov;
import com.org.seratic.lucky.model.ReportesColgateBodega_RegistrarPDV_Mov_Request;

public class Conexion extends Comunication {

	private static Conexion instance;
	private static DefaultHttpClient	httpClient;
	private String urlVisitaPuntoVenta = "/EstadoService.svc/RegistrarVisita_Mov";
	private String urlMarcacion = "/EstadoService.svc/RegistrarMarcacion_Mov";
	private String urlReporteColgateMayorista = "/reporteservice.svc/ReporteColgateMayorista_Mov";
	private String urlReporteColgateMinorista = "/reporteservice.svc/ReporteColgateMinorista_Mov";
	private String urlReporteColgateFarmaciaIT = "/reporteservice.svc/ReporteColgateFarmaciaIT_Mov";
	private String urlReporteColgateFarmaciaDT = "/reporteservice.svc/ReporteColgateFarmaciaDT_Mov";
	private String urlReporteColgateBodega = "/reporteservice.svc/ReporteColgateBodega_Mov";
	private String urlRegistroFoto = "/reporteservice.svc/RegistrarFoto_Mov";
	private String urlReporteColgateBodega_RegistrarPDV_Mov = "/reporteservice.svc/ReporteColgateBodega_RegistrarPDV_Mov";
	private String urlRegistrarMotivoColgateBodega_Mov = "/EstadoService.svc/RegistrarMotivoColgateBodega_Mov";
	private String urlReporteSanFernandoAAVV_Mov = "/reporteservice.svc/ReporteSanFernandoAAVV_Mov";
	private String urlReporteSanFernandoModerno_Mov = "/reporteservice.svc/ReporteSanFernandoModerno_Mov";
	private String urlReporteSanFernandoTradicional_Mov = "/reporteservice.svc/ReporteSanFernandoTradicional_Mov";
	private String urlReporteAlicorpMayorista_Mov = "/reporteservice.svc/ReporteAlicorpMayorista_Mov";
	private String urlReporteAlicorpAutoservicio_Mov = "/reporteservice.svc/ReporteAlicorpAutoservicio_Mov";
	private String urlRegistrarMotivoColgateBodega = "/EstadoService.svc/RegistrarMotivoColgateBodega_Mov";
	
	public Conexion(Context ctx) {
		SharedPreferences preferences = ctx.getSharedPreferences("Config", Context.MODE_WORLD_READABLE );
		String url=preferences.getString("URL", DatosManager.DEFAULT_URL);
		
		 urlVisitaPuntoVenta = "http://"+url+urlVisitaPuntoVenta;
		 urlMarcacion = "http://"+url+urlMarcacion;
		 urlReporteColgateMayorista = "http://"+url+urlReporteColgateMayorista;
		 urlReporteColgateMinorista = "http://" + url + urlReporteColgateMinorista;
		 urlReporteColgateFarmaciaIT = "http://" + url + urlReporteColgateFarmaciaIT;
		 urlReporteColgateFarmaciaDT = "http://" + url + urlReporteColgateFarmaciaDT;
		 urlReporteColgateBodega = "http://" + url + urlReporteColgateBodega;
		 urlRegistroFoto = "http://" + url + urlRegistroFoto;
		 urlReporteColgateBodega_RegistrarPDV_Mov = "http://" + url + urlReporteColgateBodega_RegistrarPDV_Mov;
		 urlRegistrarMotivoColgateBodega_Mov = "http://" + url+urlRegistrarMotivoColgateBodega_Mov;
		 urlReporteSanFernandoAAVV_Mov = "http://" + url + urlReporteSanFernandoAAVV_Mov;
		 urlReporteSanFernandoModerno_Mov = "http://" + url + urlReporteSanFernandoModerno_Mov;
		 urlReporteSanFernandoTradicional_Mov = "http://" + url + urlReporteSanFernandoTradicional_Mov;
		 urlReporteAlicorpMayorista_Mov = "http://" + url + urlReporteAlicorpMayorista_Mov;
		 urlReporteAlicorpAutoservicio_Mov = "http://" + url + urlReporteAlicorpAutoservicio_Mov;
		 urlRegistrarMotivoColgateBodega = "http://" + url + urlRegistrarMotivoColgateBodega;
	}
	
	public static Conexion getInstance(Context ctx) {
		if (instance == null) {
			instance = new Conexion(ctx);
			httpConnector = new HttpConnector();
			

//			HttpParams httpParameters = new BasicHttpParams();
//			// Set the timeout in milliseconds until a connection is established.
//			int timeoutConnection = 3000;
//			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//			// Set the default socket timeout (SO_TIMEOUT) 
//			// in milliseconds which is the timeout for waiting for data.
//			int timeoutSocket = 3000;
//			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//
//	 httpClient = new DefaultHttpClient(httpParameters);
//	 HttpPost httpPost = new HttpPost(url);
//		StringEntity se = new StringEntity(envelope,HTTP.UTF_8);
//		httpPost.setEntity(se);		
//	 BasicHttpResponse httpResponse = (BasicHttpResponse)  httpClient.execute(httpPost);
////httpResponse.
//			HttpEntity entity = httpResponse.getEntity();
//			
		}
		return instance;
	}

	public void registrarVistaPuntoVenta(E_VisitaRequest e_VisitaRequest) {
		sendData(createJSON(e_VisitaRequest), urlVisitaPuntoVenta);
	}

	public void registrarMarcacion(E_MarcacionRequest e_MarcacionRequest) {
		sendData(createJSON(e_MarcacionRequest), urlMarcacion);
	}

	public void regReporteColgateMayorista(E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov) {
		sendData(createJSON(e_ReporteColgateMayoristaMov), urlReporteColgateMayorista);
	}

	public void regReporteColgateMinosrista(E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov) {
		sendData(createJSON(e_ReporteColgateMayoristaMov), urlReporteColgateMinorista);
	}

	public void regReporteColgateFarmaciaIT(E_ReporteColgateFarmaciaIT_Mov eFarmaciaIT_Mov) {
		sendData(createJSON(eFarmaciaIT_Mov), urlReporteColgateFarmaciaIT);
	}

	public void regReporteColgateFarmaciaDT_Mov(ReporteColgateFarmaciaDT_Mov reporteColgateFarmaciaDT_Mov) {
		sendData(createJSON(reporteColgateFarmaciaDT_Mov), urlReporteColgateFarmaciaDT);
	}

	public void regReporteColgateBodega_Mov(ReporteColgateBodega_Mov reporteColgateBodega_Mov) {
		sendData(createJSON(reporteColgateBodega_Mov), urlReporteColgateBodega);
	}

	public void registrarFoto_Mov(List<E_FotoAndroid> e_FotoAndroid) {
		sendData(createJSON(e_FotoAndroid), urlRegistroFoto);
	}

	public void reporteColgateBodegaRegistrarPDVMov(E_RegistroPDV_Mov e_RegistroPDV_Mov) {
		sendData(createJSON(e_RegistroPDV_Mov), urlReporteColgateBodega_RegistrarPDV_Mov);
	}

	public void RegistrarMotivoColgateBodegaMov(E_RegistrarMotivoColgateBodega_Request request) {
		sendData(createJSON(request), urlRegistrarMotivoColgateBodega_Mov);
	}

	/*********** SAN FERNANDO **************/
	public void reporteSanFernandoAAVV_Mov(E_ReporteSanFernandoAAVV_Mov reporte) {
		sendData(createJSON(reporte), urlReporteSanFernandoAAVV_Mov);
	}

	public void reporteSanFernandoModerno_Mov(E_ReporteSanFernandoModerno_Mov reporte) {
		sendData(createJSON(reporte), urlReporteSanFernandoModerno_Mov);
	}
	
	public void reporteSanFernandoTradicional_Mov(E_ReporteSanFernandoTradicional_Mov reporte){
		sendData(createJSON(reporte), urlReporteSanFernandoTradicional_Mov);
	}

	/****************** ALICORP ***********************/
	public void reporteAlicorpMayorista_Mov(E_ReporteAlicorpMayorista_Mov reporte) {
		sendData(createJSON(reporte), urlReporteAlicorpMayorista_Mov);
	}

	public void reporteAlicorpAutoservicio_Mov(E_ReporteAlicorpAutoservicio_Mov reporte) {
		sendData(createJSON(reporte), urlReporteAlicorpAutoservicio_Mov);
	}

	public void sincronizar(E_SincronizacionRequest sincRequest, Context context) {
		Sincronizacion sincronizacion = Sincronizacion.getInstance(context);
		sincronizacion.setComListener(comListener, context);
		sincronizacion.sincronizar(sincRequest);
	}

	//****************************************************************************************
	//****************************************************************************************
	
	
	
	public void registrarNoVistaBodega(
			E_RegistrarMotivoColgateBodega_Request e_NoVisitaBodegaRequest) {
		// TODO Auto-generated method stubds
		sendData(createJSON(e_NoVisitaBodegaRequest), urlRegistrarMotivoColgateBodega);
	}

	public void reporteColgateBodegaRegistrarPDVMov(ReportesColgateBodega_RegistrarPDV_Mov_Request e_RegistroPDV_Mov){
		Conexion.TYPE_SERVICE = 3;
		sendData(createJSON(e_RegistroPDV_Mov),urlReporteColgateBodega_RegistrarPDV_Mov);		
	}
	
	public void sincronizarPreDatos(E_SincronizacionRequest sincRequest, Context context) {
		Sincronizacion sincronizacion = Sincronizacion.getInstance(context);
		sincronizacion.setComListener(comListener, context);
		sincronizacion.datosPrecargaRequest(sincRequest);
	}	
}
