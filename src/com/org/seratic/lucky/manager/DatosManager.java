package com.org.seratic.lucky.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.seratic.location.IGPSManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.org.seratic.lucky.ReporteGeneral;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_UsuarioController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_videosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.PuntoVentaController;
import com.org.seratic.lucky.accessData.control.ReporteFotograficoController;
import com.org.seratic.lucky.accessData.control.ReporteVideoController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMovRegDistribPDVController;
import com.org.seratic.lucky.accessData.control.TblMovRepMaterialApoyoController;
import com.org.seratic.lucky.accessData.control.TblMovRepNewCodigoITTController;
import com.org.seratic.lucky.accessData.control.TblMovRepPresencia;
import com.org.seratic.lucky.accessData.control.TblMovRepPromocionController;
import com.org.seratic.lucky.accessData.control.TblMovRepRegDistribuidoraController;
import com.org.seratic.lucky.accessData.control.TblMovRepVisComController;
import com.org.seratic.lucky.accessData.control.TblMstMovFiltrosAppController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercado;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteAuditoria;
import com.org.seratic.lucky.accessData.entities.E_ReporteBloqueAzul;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetenciaDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteEncuesta;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_ReporteImpulso;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_ReporteQuiebre;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_ReporteSod;
import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegDistribuidora;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegistroFotografico;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMstOpcReporte;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REG_DISTRIB_PDV;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_PROMOCION;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_VISCOMP;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.comunicacion.ArchivoService;
import com.org.seratic.lucky.comunicacion.Conexion;
import com.org.seratic.lucky.comunicacion.FotoService;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.model.E_Codigo_ITT_New;
import com.org.seratic.lucky.model.E_ReporteAlicorpAutoservicio_Mov;
import com.org.seratic.lucky.model.E_ReporteAlicorpMayorista_Mov;
import com.org.seratic.lucky.model.E_ReporteColgateFarmaciaIT_Mov;
import com.org.seratic.lucky.model.E_ReporteColgateMayoristaMov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoAAVV_Mov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoModerno_Mov;
import com.org.seratic.lucky.model.E_ReporteSanFernandoTradicional_Mov;
import com.org.seratic.lucky.model.E_Reporte_AMercado_Mov;
import com.org.seratic.lucky.model.E_Reporte_AMercado_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Auditoria_Mov;
import com.org.seratic.lucky.model.E_Reporte_Auditoria_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_BloqueAzul_Mov;
import com.org.seratic.lucky.model.E_Reporte_BloqueAzul_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Codigo_ITT_New_Mov;
import com.org.seratic.lucky.model.E_Reporte_Competencia_Mov;
import com.org.seratic.lucky.model.E_Reporte_Competencia_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Encuesta_Mov;
import com.org.seratic.lucky.model.E_Reporte_Encuesta_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Exhibicion_Mov;
import com.org.seratic.lucky.model.E_Reporte_Fotografico_Mov;
import com.org.seratic.lucky.model.E_Reporte_Impulso_Mov;
import com.org.seratic.lucky.model.E_Reporte_Impulso_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Incidencia_Mov;
import com.org.seratic.lucky.model.E_Reporte_Incidencia_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_LayOut_Mov;
import com.org.seratic.lucky.model.E_Reporte_Mat_Apoyo_Mov;
import com.org.seratic.lucky.model.E_Reporte_Mat_Apoyo_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Potencial_Mov;
import com.org.seratic.lucky.model.E_Reporte_Potencial_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Precio_Mov;
import com.org.seratic.lucky.model.E_Reporte_Precio_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Precio_PDV_Mov;
import com.org.seratic.lucky.model.E_Reporte_Precio_PDV_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Precio_PVP_Mov;
import com.org.seratic.lucky.model.E_Reporte_Precio_PVP_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Presencia_Mov;
import com.org.seratic.lucky.model.E_Reporte_Presencia_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Promocion_Mov;
import com.org.seratic.lucky.model.E_Reporte_Quiebre_Mov;
import com.org.seratic.lucky.model.E_Reporte_Quiebre_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Revestimiento_Mov;
import com.org.seratic.lucky.model.E_Reporte_Revestimiento_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_SOD_Mov;
import com.org.seratic.lucky.model.E_Reporte_Stock_Mov;
import com.org.seratic.lucky.model.E_Reporte_Stock_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_Video_Mov;
import com.org.seratic.lucky.model.E_Reporte_VisCompentencia_Mov_Detalle;
import com.org.seratic.lucky.model.E_Reporte_VisCompetencia_Mov;
import com.org.seratic.lucky.model.E_Visita_Mov;
import com.org.seratic.lucky.model.ReporteColgateBodega_Mov;
import com.org.seratic.lucky.model.ReporteColgateFarmaciaDT_Mov;
import com.org.seratic.lucky.model.ReportesColgateBodega_RegistrarPDV_Mov_Request;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class DatosManager implements IGPSManager {

	private static DatosManager instancia;

	// public static final String PREFERENCE_NAME = "preference_gestion";
	// public static final String ID_USER = "id_usuario";
	// public static final String ID_CLIENTE = "id_cliente";
	// public static final String N_CLIENTE = "n_cliente";
	// public static final String T_DOC = "t_doc";
	// public static final String N_DOC = "n_doc";
	public static final String DATABASE_NAME = "LuckyDataBasev2";
	//public static final String DEFAULT_URL = "190.81.171.52:8081";
	public static final String DEFAULT_URL = "localhost:58700";
	
	// / CLIENTES //
	public static final String CLIENTE_COLGATE = "1561";
	public static final String CLIENTE_ALICORP = "1562";
	public static final String CLIENTE_SANFDO = "1572";

	// / CANALES COLGATE //
	public static final String CANAL_MAYORISTAS = "1000";
	public static final String CANAL_MINORISTAS = "1023";
	public static final String CANAL_FARMACIAS_IT = "1242";
	public static final String CANAL_FARMACIAS_DT = "1243";
	public static final String CANAL_BODEGAS = "2008";
	// / CANALES ALICORP //
	public static final String CANAL_ALICORP_MAYORISTAS = "1000";
	public static final String CANAL_ALICORP_AUTOSERVICIOS = "1241";
	// / CANALES SAN FERNANDO //
	public static final String CANAL_SANFDO_AAVV = "1025";
	public static final String CANAL_SANFDO_MODERNO = "1003";
	public static final String CANAL_SANFDO_TRADICIONAL_CHIKARA = "1002";

	public boolean guardoReporte;

	private static E_MovMarcacion marcacion = null;
	private static TblPuntoGPS localizacion = null;
	private static E_TBL_MOV_REGISTROVISITA visita = null;
	private static E_TBL_MOV_REGISTROVISITA visita_envio = null;

	public int idReporte;
	public int idReporteCabecera;

	public E_ReporteCompetenciaDet repC;
	public E_ReporteCompetencia rep;
	private E_Usuario usuario;
	// private E_PuntosVenta puntoVentaSeleccionado;
	private PuntoventaVo puntoVentaSeleccionado;

	private E_TblMstOpcReporte opcionReporte;
	private E_TblFiltrosApp filtrosSeleccionados;

	private boolean fotoTomada;

	private String nombreFoto = "";

	Conexion conexion;
	private boolean esperandoRespuesta = false;
	private ReporteGeneral myReporteCargado;

	private String codEmpresaSelect;

	// Lista de distribuidoras que se asocian al PDV
	// Traidas desde RegistroPDVDistribuidoraActivity
	private ArrayList<Object> codigosITT;

	public ArrayList<Object> getCodigosITT() {
		return codigosITT;
	}

	public void setCodigosITT(ArrayList<Object> codigosITT) {
		this.codigosITT = codigosITT;
	}

	public void setIdReporteCabecera(int idReporteCabecera) {
		this.idReporteCabecera = idReporteCabecera;
	}

	public int getIdReporteCabecera() {
		return idReporteCabecera;
	}

	public boolean appIniciada;

	private int idSubReporteActivo;

	public boolean isAppIniciada() {
		return appIniciada;
	}

	public void setAppIniciada(boolean appIniciada) {
		this.appIniciada = appIniciada;
	}

	public boolean terminarLabor;

	public boolean isTerminarLabor() {
		return terminarLabor;
	}

	public void setTerminarLabor(boolean terminarLabor) {
		this.terminarLabor = terminarLabor;
	}

	public DatosManager() {
	}

	public void inicializarControladores() {
		repFotograficoController = new ReporteFotograficoController(db);
		movFotosController = new E_tbl_mov_fotosController(db);
		movFiltrosAppController = new TblMstMovFiltrosAppController(db);
		movRepPresenciaController = new TblMovRepPresencia(db);
		movRepNewCodigoITTController = new TblMovRepNewCodigoITTController(db);
		movRegDistribPDVController = new TblMovRegDistribPDVController(db);
		movRepRegDistribuidoraController = new TblMovRepRegDistribuidoraController(db);
		movRepPromocionController = new TblMovRepPromocionController(db);
		movRepMaterialApoyoController = new TblMovRepMaterialApoyoController(db);
		movRepVisComController = new TblMovRepVisComController(db);
		movRepCabController = new E_TblMovReporteCabController(db);
		reportesController = new ReportesController(db);
		puntoGPSController = new TblPuntoGPSController(db);
		pvController = new PuntoVentaController(db);
		movVideosController = new E_tbl_mov_videosController(db);
		repVideoController = new ReporteVideoController(db);
	}

	public PuntoventaVo getPuntoVentaSeleccionado() {
		return puntoVentaSeleccionado;
	}

	public void setPuntoVentaSeleccionado(PuntoventaVo puntoVentaSeleccionado) {
		this.puntoVentaSeleccionado = puntoVentaSeleccionado;
	}

	public static DatosManager getInstancia() {
		if (instancia == null) {
			Log.i("Datos Manager", "Iniciando DatosManager");
			instancia = new DatosManager();
		}
		return instancia;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {

			NetworkInfo[] info = connectivity.getAllNetworkInfo();

			if (info != null) {

				for (int i = 0; i < info.length; i++) {

					if (info[i].getState() == NetworkInfo.State.CONNECTED) {

						return true;
					}
				}
			}
		}
		return false;
	}

	public void setUsuarioLogeado(E_Usuario usr) {
		Log.i("setUsuarioLogeado", usr == null ? "null" : usr.toString());
		this.usuario = usr;

	}

	public E_Usuario getUsuario() {
		Log.i("E_Usuario getUsuario",
				usuario == null ? "null" : usuario.toString());
		return usuario;
	}

	public int getPoscionActualfromBD(SQLiteDatabase db, Context ctx) {
		TblPuntoGPS puntoGPS = GPSManager.getManager().getPuntoGPS(db, ctx, true);
		return puntoGPS.getId();
	}

	public void setMarcacion(E_MovMarcacion marcacion) {
		DatosManager.marcacion = marcacion;
	}

	public void setVisita(E_TBL_MOV_REGISTROVISITA visita) {

		Log.i("Datos Manager", "ID *---" + visita.getId());
		DatosManager.visita = visita;
	}

	public TblPuntoGPS getLocalizacion() {
		return localizacion;
	}

	public E_MovMarcacion getMarcacion() {
		return marcacion;
	}

	public E_TBL_MOV_REGISTROVISITA getVisita() {
		return visita;
	}

	// Crea el reporte de foroma temporal
	public int crearCabeceraReporte(String idSubReporte, int idFiltro, SQLiteDatabase db, int estado, Context ctx) {

		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		if ((idReporteCabecera = reporteCabController.getIDCabecera(visita.getId(), E_TblMovReporteCab.ESTADO_TEMPORAL, idReporte, idSubReporte, idFiltro)) == 0 || idReporte == 115) {

			E_TblMovReporteCab mov_repCab = new E_TblMovReporteCab();
			mov_repCab.setId_usuario(getUsuario().getIdUsuario());
			mov_repCab.setId_punto_de_venta(getPuntoVentaSeleccionado().getIdPtoVenta());
			mov_repCab.setCod_reporte(String.valueOf(idReporte));
			mov_repCab.setCod_subreporte(idSubReporte);
			mov_repCab.setId_filtros_app(idFiltro);
			mov_repCab.setId_punto_gps("" + getPoscionActualfromBD(db, ctx));
			mov_repCab.setComentario("");
			mov_repCab.setEstado_envio(estado);
			mov_repCab.setIdVisita(visita.getId());
			mov_repCab.setCod_competidora("");
			idReporteCabecera = reporteCabController.createR(mov_repCab);
			Log.i("crearCabeceraReporte", "Creando cabecera de Reporte " + idReporte + "idFiltro " + idFiltro + "Cabecera Asignada" + idReporteCabecera);
		} else {
			Log.i("DatosManeger", "Cabecera Encontrada" + idReporteCabecera);
			if (estado == E_TblMovReporteCab.ESTADO_GUARDADA) {
				Log.i("DatosManeger", "Actualizando Cabecera" + idReporteCabecera);
				reporteCabController.updateEstadoCabecera(idReporteCabecera);
			}
		}
		return idReporteCabecera;
	}

	// Crea el reporte de foroma temporal
	public int crearCabeceraReporteCompetidora(String idSubReporte, int idFiltro, String codCompetidora, SQLiteDatabase db, int estado, Context ctx) {

		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		if ((idReporteCabecera = reporteCabController.getIDCabeceraCompetidora(visita.getId(), E_TblMovReporteCab.ESTADO_TEMPORAL, idReporte, idSubReporte, idFiltro, codCompetidora)) == 0) {
			E_TblMovReporteCab mov_repCab = new E_TblMovReporteCab();
			mov_repCab.setId_usuario(getUsuario().getIdUsuario());
			mov_repCab.setId_punto_de_venta(getPuntoVentaSeleccionado().getIdPtoVenta());
			mov_repCab.setCod_reporte(String.valueOf(idReporte));
			mov_repCab.setCod_subreporte(idSubReporte);
			mov_repCab.setId_filtros_app(idFiltro);
			mov_repCab.setId_punto_gps("" + getPoscionActualfromBD(db, ctx));
			mov_repCab.setComentario("");
			mov_repCab.setEstado_envio(estado);
			mov_repCab.setIdVisita(visita.getId());
			mov_repCab.setCod_competidora(codCompetidora);
			idReporteCabecera = reporteCabController.createR(mov_repCab);
			Log.i("crearCabeceraReporte", "Creando cabecera de Reporte " + idReporte + "idFiltro " + idFiltro + "codCompetidora" + codCompetidora + "Cabecera Asignada" + idReporteCabecera);
		} else {
			Log.i("DatosManeger", "Cabecera Encontrada" + idReporteCabecera);
			if (estado == E_TblMovReporteCab.ESTADO_GUARDADA) {
				Log.i("DatosManeger", "Actualizando Cabecera" + idReporteCabecera);
				reporteCabController.updateEstadoCabecera(idReporteCabecera);
			}
		}
		return idReporteCabecera;
	}

	public void actualizarCabecera(int idCabeceraGuardada, SQLiteDatabase db, String comentario) {
		Log.i("DatosManager", "actualizarCabecera. idCabeceraGuardada=" + idCabeceraGuardada + ", comentario=" + comentario);
		comentario = DatosManager.getInstancia().validarCaracteresEspeciales(comentario);
		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		reporteCabController.updateCabecera(idCabeceraGuardada, comentario);
	}

	public void actualizarCabecera(int idCabeceraGuardada, SQLiteDatabase db) {
		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		reporteCabController.updateEstadoCabecera(idCabeceraGuardada);
	}

	public E_TblMstOpcReporte getOpcionesReporte() {

		return opcionReporte;
	}

	public void setOpcionReporte(E_TblMstOpcReporte opcionReporte) {
		this.opcionReporte = opcionReporte;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	// public String getNombreFoto() {
	// Log.i("DatosManager", "Nombre de la Foto:" + nombreFoto);
	// return nombreFoto;
	// }

	public void setIdSubReporteActivo(int idSubreporte) {
		idSubReporteActivo = idSubreporte;

	}

	public int getIdSubReporteActivo() {
		return idSubReporteActivo;
	}

	public E_TblFiltrosApp getFiltrosSeleccionados() {
		return filtrosSeleccionados;
	}

	public void setFiltrosSeleccionados(E_TblFiltrosApp filtrosSeleccionados) {
		this.filtrosSeleccionados = filtrosSeleccionados;
	}

	public void enviarReportesColgateMayoristas(
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Codigo_ITT_New_Mov> repCodITT, E_Visita_Mov visita,
			int e, IComunicacionListener cxL, Context context) {
		Log.i("*", "enviarReportesColgateMayoristas. hayRepPresencia = "
				+ (repPresencia != null && repPresencia.size() > 0)
				+ " hayRepFotografico = "
				+ (repFotografico != null && repFotografico.size() > 0)
				+ " hayRepCodITT = "
				+ (repCodITT != null && repCodITT.size() > 0) + " hayVisita = "
				+ (visita != null));
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov = new E_ReporteColgateMayoristaMov(repPresencia, repFotografico, repCodITT, visita, e);
		conexion.regReporteColgateMayorista(e_ReporteColgateMayoristaMov);
	}

	public void enviarReportesColgateMinoristas(
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Codigo_ITT_New_Mov> repCodITT, E_Visita_Mov visita,
			int e, IComunicacionListener cxL, Context context) {
		Log.i("*", "enviarReportesColgateMinoristas. hayRepPresencia = "
				+ (repPresencia != null && repPresencia.size() > 0)
				+ " hayRepFotografico = "
				+ (repFotografico != null && repFotografico.size() > 0)
				+ " hayRepCodITT = "
				+ (repCodITT != null && repCodITT.size() > 0) + " hayVisita = "
				+ (visita != null));
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov = new E_ReporteColgateMayoristaMov(repPresencia, repFotografico, repCodITT, visita, e);
		conexion.regReporteColgateMinosrista(e_ReporteColgateMayoristaMov);
	}

	public void enviarReportesColgateFarmaciaIT(
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Codigo_ITT_New_Mov> repCodITT, E_Visita_Mov visita,
			int e, IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteColgateFarmaciaIT_Mov eFarmaciaIT_Mov = new E_ReporteColgateFarmaciaIT_Mov(repPresencia, repFotografico, repCodITT, visita, e);
		conexion.regReporteColgateFarmaciaIT(eFarmaciaIT_Mov);
	}

	public void enviarReportesColgateFarmaciaDT_Mov(
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Codigo_ITT_New_Mov> repCodITT,
			List<E_Reporte_Promocion_Mov> repPromocion,
			List<E_Reporte_Mat_Apoyo_Mov> repMaterialApoyo,
			List<E_Reporte_VisCompetencia_Mov> repVisCompetencia,
			E_Visita_Mov visita, int e, IComunicacionListener cxL,
			Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		ReporteColgateFarmaciaDT_Mov reporteColgateFarmaciaDT_Mov = new ReporteColgateFarmaciaDT_Mov(repPresencia, repCodITT, repPromocion, repMaterialApoyo, repVisCompetencia, visita, e);
		conexion.regReporteColgateFarmaciaDT_Mov(reporteColgateFarmaciaDT_Mov);
	}

	public void enviarReporteColgateBodega_Mov(
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Codigo_ITT_New_Mov> repCodITT,
			List<E_Reporte_Fotografico_Mov> fotografico, E_Visita_Mov visita,
			int e, IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		ReporteColgateBodega_Mov reporteColgateBodega_Mov = new ReporteColgateBodega_Mov(repPresencia, repCodITT, visita, fotografico, e);
		conexion.regReporteColgateBodega_Mov(reporteColgateBodega_Mov);
	}

	public void enviarFoto(List<E_tbl_mov_fotos> e_FotoAndroid,
			SQLiteDatabase db, Context context) {
		esperandoRespuesta = true;
		FotoService fotoService = FotoService.getInstance(db, context);
		fotoService.registrarFoto_Mov(e_FotoAndroid);
	}

	public void enviarArchivo(List<E_tbl_mov_videos> e_ArchivoAndroid, SQLiteDatabase db, Context context) {
		esperandoRespuesta = true;
		ArchivoService archivoService = ArchivoService.getInstance(db, context);
		archivoService.registrarArchivo_Mov(e_ArchivoAndroid);
	}

	public void enviarReportesAlicorpAutoservicios_Mov(
			List<E_Reporte_Precio_Mov> repPrecio,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Competencia_Mov> repCompetencia,
			List<E_Reporte_Exhibicion_Mov> repExhibicion,
			List<E_Reporte_Quiebre_Mov> repQuiebre,
			List<E_Reporte_LayOut_Mov> repLayout, E_Visita_Mov visita, int e,
			IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteAlicorpAutoservicio_Mov reportes = new E_ReporteAlicorpAutoservicio_Mov(repPrecio, repFotografico, repCompetencia, repExhibicion, repQuiebre, repLayout, visita, e);
		conexion.reporteAlicorpAutoservicio_Mov(reportes);
	}

	public void enviarReportesAlicorpMayoristas_Mov(
			List<E_Reporte_Precio_Mov> repPrecio,
			List<E_Reporte_SOD_Mov> repSod,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Competencia_Mov> repCompetencia,
			List<E_Reporte_Stock_Mov> repStock, E_Visita_Mov visita, int e,
			IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteAlicorpMayorista_Mov reportes = new E_ReporteAlicorpMayorista_Mov(repPrecio, repSod, repFotografico, repCompetencia, repStock, visita, e);
		conexion.reporteAlicorpMayorista_Mov(reportes);
	}

	public void enviarReportesSanFernandoAAVV_Mov(
			List<E_Reporte_Potencial_Mov> repPotencial,
			List<E_Reporte_Precio_PVP_Mov> repPrecioPVP,
			List<E_Reporte_Precio_PDV_Mov> repPrecioPDV,
			List<E_Reporte_Incidencia_Mov> repIncidencia,
			List<E_Reporte_AMercado_Mov> repAccionMdo,
			List<E_Reporte_Revestimiento_Mov> repRevestimiento,
			List<E_Reporte_Auditoria_Mov> repAuditoria,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			E_Visita_Mov visita, int e, IComunicacionListener cxL,
			Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteSanFernandoAAVV_Mov reportes = new E_ReporteSanFernandoAAVV_Mov(repPotencial, repPrecioPVP, repPrecioPDV, repIncidencia, repAccionMdo, repRevestimiento, repAuditoria, repFotografico, visita, e);
		conexion.reporteSanFernandoAAVV_Mov(reportes);
	}

	public void enviarReportesSanFernandoModerno_Mov(
			List<E_Reporte_Precio_Mov> repPrecio,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Competencia_Mov> repCompetencia,
			List<E_Reporte_Stock_Mov> repStock,
			List<E_Reporte_Impulso_Mov> repImpulso, E_Visita_Mov visita, int e,
			IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteSanFernandoModerno_Mov reportes = new E_ReporteSanFernandoModerno_Mov(repPrecio, repFotografico, repCompetencia, repStock, repImpulso, visita, e);
		conexion.reporteSanFernandoModerno_Mov(reportes);
	}

	public void enviarReportesSanFernandoTradicional_Mov(
			List<E_Reporte_Precio_Mov> repPrecio,
			List<E_Reporte_Presencia_Mov> repPresencia,
			List<E_Reporte_Mat_Apoyo_Mov> repMaterialApoyo,
			List<E_Reporte_Incidencia_Mov> repIncidencia,
			List<E_Reporte_BloqueAzul_Mov> repBloqueAzul,
			List<E_Reporte_AMercado_Mov> repAAmercado,
			List<E_Reporte_LayOut_Mov> repLayout,
			List<E_Reporte_Fotografico_Mov> repFotografico,
			List<E_Reporte_Encuesta_Mov> repEncuesta,
			List<E_Reporte_Video_Mov> repVideo, E_Visita_Mov visita, int e, 
			IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		E_ReporteSanFernandoTradicional_Mov reportes = new E_ReporteSanFernandoTradicional_Mov(repPrecio, repPresencia, repMaterialApoyo, repIncidencia, repBloqueAzul, repAAmercado, repLayout, repFotografico, repEncuesta, repVideo, visita, e);
		conexion.reporteSanFernandoTradicional_Mov(reportes);
	}

	public void setFotoTomada(boolean fotoTomada) {
		this.fotoTomada = fotoTomada;
	}

	public boolean isFotoTomada() {
		return fotoTomada;
	}

	public String crearNombreFoto() {
		Calendar c = Calendar.getInstance();
		//
		int mes = c.get(Calendar.MONTH) + 1;
		int a�o = c.get(Calendar.YEAR);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minutos = c.get(Calendar.MINUTE);
		int segundos = c.get(Calendar.SECOND);

		if (DatosManager.getInstancia().getUsuario() == null) {
			Log.e("DatosManager", "crearNombreFoto. usuario es null");
		}
		nombreFoto = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ "/"
				+ a�o
				+ mes
				+ dia
				+ hora
				+ minutos
				+ segundos + usuario.getIdUsuario() + ".jpg";
		return nombreFoto;
	}

	public String crearNombreVideo() {
		Calendar c = Calendar.getInstance();
		//
		String mes = String.valueOf(c.get(Calendar.MONTH) + 1);
		String year = String.valueOf(c.get(Calendar.YEAR));
		String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hora = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String minutos = String.valueOf(c.get(Calendar.MINUTE));
		String segundos = String.valueOf(c.get(Calendar.SECOND));

		if (DatosManager.getInstancia().getUsuario() == null) {
			Log.e("DatosManager", "crearNombreVideo. usuario es null");
		}
		mes = mes.length() == 1 ? "0" + mes : mes;
		dia = dia.length() == 1 ? "0" + dia : dia;
		hora = hora.length() == 1 ? "0" + hora: hora;
		minutos = minutos.length() == 1 ? "0" + minutos : minutos;
		segundos = segundos.length() == 1 ? "0" + segundos : segundos;
		
		String nombreVideo = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + year + mes + dia + hora + minutos + segundos + usuario.getIdUsuario() + ".mp4";
		return nombreVideo;
	}

	public ReporteGeneral getMyReporteCargado() {
		return myReporteCargado;
	}

	public void crearReporteFotografico(String comentario, int idCabeceraGuardada, SQLiteDatabase db, byte foto[]) {
		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		Log.i("DatosManager", "ReportesFotografico. crearReporteFotografico. cabecera: " + idCabeceraGuardada + ", comentario = " + comentario);

		reporteCabController.updateEstadoCabecera(idCabeceraGuardada);

		E_tbl_mov_fotos mov_fotos = new E_tbl_mov_fotos(crearNombreFoto(), E_tbl_mov_fotos.FOTO_GUARDADA, 0, DatosManager.getInstancia().validarCaracteresEspeciales(comentario), foto);
		int idFotos = new E_tbl_mov_fotosController(db).createR(mov_fotos);

		Log.i("idFoto", "" + idFotos);
		E_TblMovRegistroFotografico registroFoto = new E_TblMovRegistroFotografico(idCabeceraGuardada, idFotos);
		new ReporteFotograficoController(db).createR(registroFoto);
	}

	public void crearReporteVideo(String comentario, int idCabeceraGuardada, SQLiteDatabase db, String s_uri_video, Context context) {
		E_TblMovReporteCabController reporteCabController = new E_TblMovReporteCabController(db);
		Log.i("DatosManager", "ReportesVideo. crearReporteVideo. cabecera: " + idCabeceraGuardada + ", comentario = " + comentario);

		reporteCabController.updateEstadoCabecera(idCabeceraGuardada);
		String nombre_video = crearNombreVideo();
		E_tbl_mov_videos mov_videos = new E_tbl_mov_videos(nombre_video, E_tbl_mov_videos.VIDEO_GUARDADO, s_uri_video, DatosManager.getInstancia().validarCaracteresEspeciales(comentario));
		int idVideo = new E_tbl_mov_videosController(db).createR(mov_videos);

		Log.i("DatosManager - CrearReporteVideo", "id_video: " + idVideo + " --- nombre_video: " + nombre_video);
		E_TblMovRegistroFotografico registroFoto = new E_TblMovRegistroFotografico(idCabeceraGuardada, idVideo);
		new ReporteVideoController(db).createR(registroFoto);
	}

	public TblPuntoGPS getPuntoGps(int idPunto, SQLiteDatabase db) {
		TblPuntoGPSController puntoGPSController = new TblPuntoGPSController(db);
		TblPuntoGPS puntoGps = puntoGPSController.getPuntoById(idPunto);
		return puntoGps;
	}

	public void setCodEmpresaSelect(String codEmpresaSelect) {
		this.codEmpresaSelect = codEmpresaSelect;
	}

	public String getCodEmpresaSelect() {
		return codEmpresaSelect;
	}

	public void setGuardoReporte(boolean guardoReporte) {
		this.guardoReporte = guardoReporte;
	}

	public boolean isEsperandoRespuesta() {
		return esperandoRespuesta;
	}

	public boolean isGuardoReporte() {
		return guardoReporte;
	}

	public static void setInstancia(DatosManager instancia) {
		DatosManager.instancia = instancia;
	}

	public void cargarDatos(SQLiteDatabase db) {
		E_UsuarioController usr = new E_UsuarioController(db, null);
		usuario = usr.getUltimoUsuario();
		Log.i("cargarDatos", usuario == null ? "null" : usuario.toString());
		MovRegistroVisitaController rVController = new MovRegistroVisitaController(db);
		E_TBL_MOV_REGISTROVISITA rv = rVController.getVisitaPendiente();
		if (rv != null) {
			// System.out.println("rv Punto de Venta: " + rv.getIdPuntoVenta());
			setVisita(rv);
			setPuntoVentaSeleccionado(rVController.getPuntoVentaVisitaPendiente(rv));
			// System.out.println("Punto Venta: " +
			// DatosManager.getInstancia().getPuntoVentaSeleccionado().getRazonSocial());
		}
	}

	public String validarCaracteresEspeciales(String texto) {
		String retorno = "";
		StringBuffer sb = new StringBuffer();
		if (texto != null && !texto.trim().isEmpty()) {
			for (int i = 0; i < texto.length(); i++) {
				sb.append(getLetterOrNumber(texto.charAt(i)));
			}
		}
		retorno = sb.toString();
		return retorno;
	}

	private String getLetterOrNumber(Character letra) {
		boolean found = false;
		String retorno = "";
		for (int i = 0; i < CARACTERES_ESPECIALES.length; i++) {
			found = letra.equals(CARACTERES_ESPECIALES[i]);
			if (found) {
				retorno = CARACTERES_MAPEADOS[i];
				break;
			}
		}
		return retorno;
	}

	private static final Character[] CARACTERES_ESPECIALES = new Character[] {
			'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '0', ' ' };
	private static final String[] CARACTERES_MAPEADOS = new String[] { "a",
			"e", "i", "o", "u", "A", "E", "I", "O", "U", "n", "N", "a", "b",
			"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
			"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2",
			"3", "4", "5", "6", "7", "8", "9", "0", " " };

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		// TODO Auto-generated method stub

	}

	public E_ReporteCompetenciaDet getRepC() {
		return repC;
	}

	public void setRepC(E_ReporteCompetenciaDet repC) {
		this.repC = repC;
	}

	public E_ReporteCompetencia getRep() {
		return rep;
	}

	public void setRep(E_ReporteCompetencia rep) {
		this.rep = rep;
	}

	// *************************************************************************************************************************
	// *************************************************************************************************************************
	// *************************************************************************************************************************
	// Joseph Gonzales
	// Lucky SAC

	public void enviarNuevaBodega_Mov(ReportesColgateBodega_RegistrarPDV_Mov_Request nuevoPtoVenta, IComunicacionListener cxL, Context context) {
		esperandoRespuesta = true;
		conexion = Conexion.getInstance(context);
		conexion.setComListener(cxL, context);
		conexion.reporteColgateBodegaRegistrarPDVMov(nuevoPtoVenta);
	}

	// /*******************************************************//

	private TblPuntoGPSController puntoGPSController;
	private E_TblMovReporteCabController movRepCabController;
	private TblMovRepPresencia movRepPresenciaController;
	private TblMstMovFiltrosAppController movFiltrosAppController;
	private ReporteFotograficoController repFotograficoController;
	private E_tbl_mov_fotosController movFotosController;
	private TblMovRepNewCodigoITTController movRepNewCodigoITTController;
	private TblMovRepRegDistribuidoraController movRepRegDistribuidoraController;
	private TblMovRegDistribPDVController movRegDistribPDVController;
	private TblMovRepPromocionController movRepPromocionController;
	private TblMovRepMaterialApoyoController movRepMaterialApoyoController;
	private TblMovRepVisComController movRepVisComController;
	private ReporteVideoController repVideoController;
	private E_tbl_mov_videosController movVideosController;
	private ReportesController reportesController;
	private PuntoVentaController pvController;
	private SQLiteDatabase db;
	private Context context;

	// Reporte Presencia
	List<E_Reporte_Presencia_Mov> listrepPresencia;
	// Reporte fotografico
	List<E_Reporte_Fotografico_Mov> listrepFotografico;
	// ArrayList<E_Reporte_Codigo_ITT_Mov>();
	List<E_Reporte_Codigo_ITT_New_Mov> listrepCodITT;
	// Reporte Promocion
	List<E_Reporte_Promocion_Mov> listrepPromocion;
	// Reporte Material Apoyo
	List<E_Reporte_Mat_Apoyo_Mov> listrepMaterialApoyo;
	// Reporte Visibilidad Competencia
	List<E_Reporte_VisCompetencia_Mov> listrepVisCompetencia;
	// Reporte Precio
	List<E_Reporte_Precio_Mov> listrepPrecio;
	// Reporte Competencia
	List<E_Reporte_Competencia_Mov> listrepCompetencia;
	// Reporte Stock
	List<E_Reporte_Stock_Mov> listrepStock;
	// Reporte Sod
	List<E_Reporte_SOD_Mov> listrepSod;
	// Reporte Quiebre
	List<E_Reporte_Quiebre_Mov> listrepQuiebre;
	// Reporte Layout
	List<E_Reporte_LayOut_Mov> listrepLayout;
	// Reporte Impulso
	List<E_Reporte_Impulso_Mov> listrepImpulso;
	// Reporte Incidencia
	List<E_Reporte_Incidencia_Mov> listrepIncidencia;
	// Reporte Exhibicion
	List<E_Reporte_Exhibicion_Mov> listrepExhibicion;
	// Reporte Potencial
	List<E_Reporte_Potencial_Mov> listrepPotencial;
	// Reporte Precio PVP
	List<E_Reporte_Precio_PVP_Mov> listrepPrecioPVP;
	// Reporte Precio PDV
	List<E_Reporte_Precio_PDV_Mov> listrepPrecioPDV;
	// Reporte Accion Mdo
	List<E_Reporte_AMercado_Mov> listrepAccionMdo;
	// Reporte Revestimiento
	List<E_Reporte_Revestimiento_Mov> listrepRevestimiento;
	// Reporte Revestimiento
	List<E_Reporte_Auditoria_Mov> listrepAuditoria;
	// Reporte Encuesta
	List<E_Reporte_Encuesta_Mov> listrepEncuesta;
	// Reporte Bloque Azul
	List<E_Reporte_BloqueAzul_Mov> listrepBloqueAzul;
	List<E_Reporte_Video_Mov> listrepVideo;

	public List<E_Reporte_Codigo_ITT_New_Mov> getListrepCodITT() {
		return listrepCodITT;
	}

	public List<E_Reporte_Competencia_Mov> getListrepCompetencia() {
		return listrepCompetencia;
	}

	public List<E_Reporte_Exhibicion_Mov> getListrepExhibicion() {
		return listrepExhibicion;
	}

	public List<E_Reporte_Fotografico_Mov> getListrepFotografico() {
		return listrepFotografico;
	}

	public List<E_Reporte_Impulso_Mov> getListrepImpulso() {
		return listrepImpulso;
	}

	public List<E_Reporte_Incidencia_Mov> getListrepIncidencia() {
		return listrepIncidencia;
	}

	public List<E_Reporte_LayOut_Mov> getListrepLayout() {
		return listrepLayout;
	}

	public List<E_Reporte_Mat_Apoyo_Mov> getListrepMaterialApoyo() {
		return listrepMaterialApoyo;
	}

	public List<E_Reporte_Precio_Mov> getListrepPrecio() {
		return listrepPrecio;
	}

	public List<E_Reporte_Presencia_Mov> getListrepPresencia() {
		return listrepPresencia;
	}

	public List<E_Reporte_Promocion_Mov> getListrepPromocion() {
		return listrepPromocion;
	}

	public List<E_Reporte_Quiebre_Mov> getListrepQuiebre() {
		return listrepQuiebre;
	}

	public List<E_Reporte_SOD_Mov> getListrepSod() {
		return listrepSod;
	}

	public List<E_Reporte_Stock_Mov> getListrepStock() {
		return listrepStock;
	}

	public List<E_Reporte_VisCompetencia_Mov> getListrepVisCompetencia() {
		return listrepVisCompetencia;
	}

	public List<E_Reporte_Encuesta_Mov> getListrepEncuesta() {
		return listrepEncuesta;
	}

	public TblMstMovFiltrosAppController getMovFiltrosAppController() {
		return movFiltrosAppController;
	}

	public E_tbl_mov_fotosController getMovFotosController() {
		return movFotosController;
	}

	public E_tbl_mov_videosController getMovVideosController() {
		return movVideosController;
	}

	public TblMovRegDistribPDVController getMovRegDistribPDVController() {
		return movRegDistribPDVController;
	}

	public E_TblMovReporteCabController getMovRepCabController() {
		return movRepCabController;
	}

	public TblMovRepMaterialApoyoController getMovRepMaterialApoyoController() {
		return movRepMaterialApoyoController;
	}

	public TblMovRepNewCodigoITTController getMovRepNewCodigoITTController() {
		return movRepNewCodigoITTController;
	}

	public TblMovRepPresencia getMovRepPresenciaController() {
		return movRepPresenciaController;
	}

	public TblMovRepPromocionController getMovRepPromocionController() {
		return movRepPromocionController;
	}

	public TblMovRepRegDistribuidoraController getMovRepRegDistribuidoraController() {
		return movRepRegDistribuidoraController;
	}

	public TblMovRepVisComController getMovRepVisComController() {
		return movRepVisComController;
	}

	public List<E_Reporte_Potencial_Mov> getListrepPotencial() {
		return listrepPotencial;
	}

	public List<E_Reporte_Precio_PVP_Mov> getListrepPrecioPVP() {
		return listrepPrecioPVP;
	}

	public List<E_Reporte_Precio_PDV_Mov> getListrepPrecioPDV() {
		return listrepPrecioPDV;
	}

	public List<E_Reporte_AMercado_Mov> getListrepAccionMdo() {
		return listrepAccionMdo;
	}

	public List<E_Reporte_Revestimiento_Mov> getListrepRevestimiento() {
		return listrepRevestimiento;
	}

	public List<E_Reporte_Auditoria_Mov> getListrepAuditoria() {
		return listrepAuditoria;
	}

	public List<E_Reporte_BloqueAzul_Mov> getListrepBloqueAzul() {
		return listrepBloqueAzul;
	}

	public void setListrepBloqueAzul(
			List<E_Reporte_BloqueAzul_Mov> listrepBloqueAzul) {
		this.listrepBloqueAzul = listrepBloqueAzul;
	}

	public List<E_Reporte_Video_Mov> getListrepVideo() {
		return listrepVideo;
	}

	public E_TBL_MOV_REGISTROVISITA getVisita_envio() {
		return visita_envio;
	}

	public void setVisita_envio(E_TBL_MOV_REGISTROVISITA visita_envio) {
		DatosManager.visita_envio = visita_envio;
	}

	private TblPuntoGPS getPuntoGps(int idPunto) {
		puntoGPSController = new TblPuntoGPSController(db);
		TblPuntoGPS puntoGps = null;
		if (idPunto > 0) {
			puntoGps = puntoGPSController.getPuntoById(idPunto);
		}
		return puntoGps;
	}

	private TblPuntoGPS actualizarPuntoGPS(TblPuntoGPS pOrigina,
			TblPuntoGPS puntoVisita) {
		if (puntoVisita != null) {
			pOrigina.setX(puntoVisita.getX());
			pOrigina.setY(puntoVisita.getY());
			pOrigina.setProveedor(puntoVisita.getProveedor());
		}
		return pOrigina;
	}

	public String fijarDatosEnv�o(SQLiteDatabase db, Context context,
			int estado_cab) {
		String msg = null;

		this.context = context;

		if (this.db == null) {
			this.db = db;
		}

		// Reporte Presencia
		listrepPresencia = new ArrayList<E_Reporte_Presencia_Mov>();
		// Reporte fotografico
		listrepFotografico = new ArrayList<E_Reporte_Fotografico_Mov>();
		// ArrayList<E_Reporte_Codigo_ITT_Mov>();
		listrepCodITT = new ArrayList<E_Reporte_Codigo_ITT_New_Mov>();
		// Reporte Promocion
		listrepPromocion = new ArrayList<E_Reporte_Promocion_Mov>();
		// Reporte Material Apoyo
		listrepMaterialApoyo = new ArrayList<E_Reporte_Mat_Apoyo_Mov>();
		// Reporte Visibilidad Competencia
		listrepVisCompetencia = new ArrayList<E_Reporte_VisCompetencia_Mov>();
		// Reporte Precio
		listrepPrecio = new ArrayList<E_Reporte_Precio_Mov>();
		// Reporte Competencia
		listrepCompetencia = new ArrayList<E_Reporte_Competencia_Mov>();
		// Reporte Stock
		listrepStock = new ArrayList<E_Reporte_Stock_Mov>();
		// Reporte Sod
		listrepSod = new ArrayList<E_Reporte_SOD_Mov>();
		// Reporte Quiebre
		listrepQuiebre = new ArrayList<E_Reporte_Quiebre_Mov>();
		// Reporte Layout
		listrepLayout = new ArrayList<E_Reporte_LayOut_Mov>();
		// Reporte Impulso
		listrepImpulso = new ArrayList<E_Reporte_Impulso_Mov>();
		// Reporte Incidencia
		listrepIncidencia = new ArrayList<E_Reporte_Incidencia_Mov>();
		// Reporte Exhibicion
		listrepExhibicion = new ArrayList<E_Reporte_Exhibicion_Mov>();
		// Reporte Potencial
		listrepPotencial = new ArrayList<E_Reporte_Potencial_Mov>();
		// Reporte Precio PVP
		listrepPrecioPVP = new ArrayList<E_Reporte_Precio_PVP_Mov>();
		// Reporte Precio PDV
		listrepPrecioPDV = new ArrayList<E_Reporte_Precio_PDV_Mov>();
		// Reporte Accion Mdo
		listrepAccionMdo = new ArrayList<E_Reporte_AMercado_Mov>();
		// Reporte Revestimiento
		listrepRevestimiento = new ArrayList<E_Reporte_Revestimiento_Mov>();
		// Reporte Auditoria
		listrepAuditoria = new ArrayList<E_Reporte_Auditoria_Mov>();
		// Reporte Encuesta
		listrepEncuesta = new ArrayList<E_Reporte_Encuesta_Mov>();
		// Reporte BloqueAzul
		listrepBloqueAzul = new ArrayList<E_Reporte_BloqueAzul_Mov>();
		// Reporte Video
		listrepVideo = new ArrayList<E_Reporte_Video_Mov>();

		E_Usuario e_Usuario = getUsuario();
		String canal = e_Usuario.getCod_canal();

		if (visita_envio != null) {
			// movRepCabController = new E_TblMovReporteCabController(db);
			inicializarControladores();
			visita_envio
					.setEstado(MovRegistroVisitaController.ESTADO_VISITA_ENVIANDO_FIN);
			new MovRegistroVisitaController(db).edit(visita_envio);
			Log.i("DatosManager", "Fijando estado visita en ENVIANDO FIN");
			TblPuntoGPS puntoGpsInicial = getPuntoGps(visita_envio.getIdPuntoGPSInicio());
			TblPuntoGPS puntoGpsFinal = null;
			if (visita_envio.getIdPuntoGPSFin() != 0) {
				puntoGpsFinal = getPuntoGps(visita_envio.getIdPuntoGPSFin());
			}
			int id_foto = visita_envio.getIdFoto();
			E_tbl_mov_fotos e_foto = new E_tbl_mov_fotosController(db).getById(id_foto);
			List<E_TblMovReporteCab> e_MovReporteCab = movRepCabController.getByIdUsuarioIdPuntoVentaIdVisita(e_Usuario.getIdUsuario(), visita_envio.getIdPuntoVenta(), estado_cab, visita_envio.getId());
			if (e_MovReporteCab != null) {
				int tamCabeceras = e_MovReporteCab.size();
				String cod_cliente = e_Usuario.getCodigo_compania();
				for (int i = 0; i < tamCabeceras; i++) {
					E_TblMovReporteCab emovRepCab = (E_TblMovReporteCab) e_MovReporteCab.get(i);
					emovRepCab.setEstado_envio(E_TblMovReporteCab.ESTADO_ENVIANDO);
					Log.i("DatosManager", "Fijando estado cabecera en ENVIANDO");
					new E_TblMovReporteCabController(db).updateEstadoCabecera(emovRepCab.getId(), E_TblMovReporteCab.ESTADO_ENVIANDO);
					if (DatosManager.CLIENTE_COLGATE.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_MAYORISTAS.equalsIgnoreCase(canal)) {
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteITT(emovRepCab, listrepCodITT, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de mayoristas");

						} else if (DatosManager.CANAL_MINORISTAS.equalsIgnoreCase(canal)) {
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteITT(emovRepCab, listrepCodITT, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de minoristas");

						} else if (DatosManager.CANAL_FARMACIAS_IT.equalsIgnoreCase(canal)) {
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario,emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteITT(emovRepCab, listrepCodITT, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de farmacias IT");

						} else if (DatosManager.CANAL_FARMACIAS_DT.equalsIgnoreCase(canal)) {
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteITT(emovRepCab, listrepCodITT, puntoGpsFinal);
							setDataEnvioReportePromocion(e_Usuario, emovRepCab, listrepPromocion, puntoGpsFinal);
							setDataEnvioReporteMaterialApoyo(e_Usuario, emovRepCab, listrepMaterialApoyo, puntoGpsFinal);
							setDataEnvioReporteVistaCompetencia(emovRepCab, e_Usuario, listrepVisCompetencia, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de farmacias DT");

						} else if (DatosManager.CANAL_BODEGAS .equalsIgnoreCase(canal)) {
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteITT(emovRepCab, listrepCodITT, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de bodegas");
						}
					} else if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_ALICORP_MAYORISTAS.equals(canal)) {
							setDataEnvioReportePrecio(e_Usuario, emovRepCab, listrepPrecio, puntoGpsFinal);
							setDataEnvioReporteSod(e_Usuario, emovRepCab, listrepSod, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteCompetencia(e_Usuario, emovRepCab, listrepCompetencia, puntoGpsFinal);
							setDataEnvioReporteStock(e_Usuario, emovRepCab, listrepStock, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de alicorp - mayoristas");

						} else if (DatosManager.CANAL_ALICORP_AUTOSERVICIOS.equals(canal)) {
							setDataEnvioReportePrecio(e_Usuario, emovRepCab, listrepPrecio, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteCompetencia(e_Usuario, emovRepCab, listrepCompetencia, puntoGpsFinal);
							setDataEnvioReporteExhibicion(e_Usuario, emovRepCab, listrepExhibicion, puntoGpsFinal);
							setDataEnvioReporteQuiebre(e_Usuario, emovRepCab, listrepQuiebre, puntoGpsFinal);
							setDataEnvioReporteLayout(e_Usuario, emovRepCab, listrepLayout, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de alicorp - autoservicios");
						}
					} else if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_SANFDO_AAVV.equals(canal)) {
							setDataEnvioReportePotencial(e_Usuario, emovRepCab, listrepPotencial, puntoGpsFinal);
							setDataEnvioReportePrecioPVP(e_Usuario, emovRepCab, listrepPrecioPVP, puntoGpsFinal);
							setDataEnvioReportePrecioPDV(e_Usuario, emovRepCab, listrepPrecioPDV, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteIncidencia(e_Usuario, emovRepCab, listrepIncidencia, puntoGpsFinal);
							setDataEnvioReporteAMercado(e_Usuario, emovRepCab, listrepAccionMdo, puntoGpsFinal);
							setDataEnvioReporteRevestimiento(e_Usuario, emovRepCab, listrepRevestimiento, puntoGpsFinal);
							setDataEnvioReporteAuditoria(e_Usuario, emovRepCab, listrepAuditoria, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de san fernando - aavv");

						} else if (DatosManager.CANAL_SANFDO_MODERNO.equals(canal)) {
							setDataEnvioReportePrecio(e_Usuario, emovRepCab, listrepPrecio, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteCompetencia(e_Usuario, emovRepCab, listrepCompetencia, puntoGpsFinal);
							setDataEnvioReporteIngreso(e_Usuario, emovRepCab, listrepStock, puntoGpsFinal);
							setDataEnvioReporteImpulso(e_Usuario, emovRepCab, listrepImpulso, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de san fernando - moderno");

						} else if (DatosManager.CANAL_SANFDO_TRADICIONAL_CHIKARA.equalsIgnoreCase(canal)) {
							setDataEnvioReportePrecio(e_Usuario, emovRepCab, listrepPrecio, puntoGpsFinal);
							setDataEnvioReporteMaterialApoyo(e_Usuario, emovRepCab, listrepMaterialApoyo, puntoGpsFinal);
							setDataEnvioReporteIncidencia(e_Usuario, emovRepCab, listrepIncidencia, puntoGpsFinal);
							setDataEnvioReportePresencia(e_Usuario, emovRepCab, listrepPresencia, puntoGpsFinal);
							setDataEnvioReporteEncuesta(e_Usuario, emovRepCab, listrepEncuesta, puntoGpsFinal);
							setDataEnvioReporteFotografico(e_Usuario, emovRepCab, listrepFotografico, puntoGpsFinal);
							setDataEnvioReporteAMercado(e_Usuario, emovRepCab, listrepAccionMdo, puntoGpsFinal);
							setDataEnvioReporteLayout(e_Usuario, emovRepCab, listrepLayout, puntoGpsFinal);
							setDataEnvioReporteBloqueAzul(e_Usuario, emovRepCab, listrepBloqueAzul, puntoGpsFinal);
							setDataEnvioReporteVideo(e_Usuario, emovRepCab, listrepVideo, puntoGpsFinal);
							Log.i("ListaDeReporte", "Enviar reporte de san fernando - tradicional");
						}
					}
				}
			} else {
				msg = "No hay reportes para enviar";
			}
		} else {
			msg = "No hay visitas para enviar";
		}
		return msg;
	}

	public String enviarReportes(IComunicacionListener listener) {
		String msg = null;
		E_Usuario e_Usuario = getUsuario();
		String canal = e_Usuario.getCod_canal();
		String cod_cliente = e_Usuario.getCodigo_compania();
		inicializarControladores();
		TblPuntoGPS puntoGpsInicial = getPuntoGps(visita_envio.getIdPuntoGPSInicio());
		TblPuntoGPS puntoGpsFinal = null;
		if (visita_envio.getIdPuntoGPSFin() != 0) {
			puntoGpsFinal = getPuntoGps(visita_envio.getIdPuntoGPSFin());
		}
		int id_foto = visita_envio.getIdFoto();
		E_tbl_mov_fotos mov_fotos = new E_tbl_mov_fotosController(db).getById(id_foto);
		E_Visita_Mov e_Visita_Mov = new E_Visita_Mov(visita_envio, e_Usuario, puntoGpsInicial, puntoGpsFinal, mov_fotos);

		if (DatosManager.CLIENTE_COLGATE.equalsIgnoreCase(cod_cliente)) {
			if (DatosManager.CANAL_MAYORISTAS.equalsIgnoreCase(canal)) {
				enviarReportesColgateMayoristas(listrepPresencia, listrepFotografico, listrepCodITT, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_MINORISTAS.equalsIgnoreCase(canal)) {
				enviarReportesColgateMinoristas(listrepPresencia, listrepFotografico, listrepCodITT, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_FARMACIAS_IT.equalsIgnoreCase(canal)) {
				enviarReportesColgateFarmaciaIT(listrepPresencia, listrepFotografico, listrepCodITT, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_FARMACIAS_DT.equalsIgnoreCase(canal)) {
				enviarReportesColgateFarmaciaDT_Mov(listrepPresencia, listrepCodITT, listrepPromocion, listrepMaterialApoyo, listrepVisCompetencia, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(canal)) {
				enviarReporteColgateBodega_Mov(listrepPresencia, listrepCodITT, listrepFotografico, e_Visita_Mov, 0, listener, context);
			}
		} else if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(cod_cliente)) {
			if (DatosManager.CANAL_ALICORP_MAYORISTAS.equals(canal)) {
				enviarReportesAlicorpMayoristas_Mov(listrepPrecio, listrepSod, listrepFotografico, listrepCompetencia, listrepStock, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_ALICORP_AUTOSERVICIOS.equals(canal)) {
				enviarReportesAlicorpAutoservicios_Mov(listrepPrecio, listrepFotografico, listrepCompetencia, listrepExhibicion, listrepQuiebre, listrepLayout, e_Visita_Mov, 0, listener, context);
			}
		} else if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
			if (DatosManager.CANAL_SANFDO_AAVV.equals(canal)) {
				enviarReportesSanFernandoAAVV_Mov(listrepPotencial, listrepPrecioPVP, listrepPrecioPDV, listrepIncidencia, listrepAccionMdo, listrepRevestimiento, listrepAuditoria, listrepFotografico, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_SANFDO_MODERNO.equals(canal)) {
				enviarReportesSanFernandoModerno_Mov(listrepPrecio, listrepFotografico, listrepCompetencia, listrepStock, listrepImpulso, e_Visita_Mov, 0, listener, context);
			} else if (DatosManager.CANAL_SANFDO_TRADICIONAL_CHIKARA.equals(canal)) {
				enviarReportesSanFernandoTradicional_Mov(listrepPrecio, listrepPresencia, listrepMaterialApoyo, listrepIncidencia, listrepBloqueAzul, listrepAccionMdo, listrepLayout, listrepFotografico, listrepEncuesta, listrepVideo, e_Visita_Mov, 0, listener, context);
			}
		} else {
			msg = "No se encuentra configurado el servicio de env�o para el cliente: " + cod_cliente;
		}
		return msg;
	}

	private void setDataEnvioReportePresencia(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Presencia_Mov> listRepPresencia, TblPuntoGPS puntoGPSInicioVisita) {
		String idFase = pvController.getPuntoVentaMapa(visita_envio.getIdPuntoVenta()).getCodFase();
		ArrayList<E_TBL_MOV_REP_PRESENCIA> detalleRepPresencia = movRepPresenciaController.getByidRepCab(emovRepCab.getId());
		ArrayList<E_Reporte_Presencia_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Presencia_Mov_Detalle>();
		if (detalleRepPresencia == null) {
			Log.i("*", "Reporte de presencia sin detalles");
		} else {
			for (E_TBL_MOV_REP_PRESENCIA r : detalleRepPresencia) {
				detalleReporte_m.add(new E_Reporte_Presencia_Mov_Detalle(r));
			}
		}

		if (detalleReporte_m.size() > 0 || emovRepCab.getCod_subreporte().equals("8")) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Presencia_Mov e_ReportePresencia = new E_Reporte_Presencia_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp, idFase);
			listRepPresencia.add(e_ReportePresencia);
		}
	}

	private void setDataEnvioReporteFotografico(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Fotografico_Mov> listRepFotografico, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_TblMovRegistroFotografico> reporte = repFotograficoController.getByIdRepCab(emovRepCab.getId());
		if (reporte != null && !reporte.isEmpty()) {
			for(E_TblMovRegistroFotografico registroFoto:reporte){
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(registroFoto.getIdFoto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(registroFoto.getIdFoto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
				E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
				E_Reporte_Fotografico_Mov e_Reporte_Fotografico_Mov = new E_Reporte_Fotografico_Mov(emovRepCab, e_Usuario, puntoGps, mov_fotos, e_MovFiltrosApp);
				listRepFotografico.add(e_Reporte_Fotografico_Mov); // LUEGO
			}
		} else {
			Log.i("*", "No hay reportes fotogr�ficos para enviar");
		}
	}

	private void setDataEnvioReportePromocion(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Promocion_Mov> listrepPromocion, TblPuntoGPS puntoGPSInicioVisita) {
		TBL_MOV_REP_PROMOCION movRepPromocion = movRepPromocionController.getByIdReporteCab(emovRepCab.getId());
		if (movRepPromocion == null) {
			Log.i("*", "No hay reporte Promocion para enviar");
		} else {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(movRepPromocion.getId_foto());
			if (mov_fotos != null) {
				movFotosController.updateEstadoFotoById(movRepPromocion.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
			}
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Promocion_Mov e_Reporte_Promocion_Mov = new E_Reporte_Promocion_Mov(emovRepCab, e_Usuario, puntoGps, movRepPromocion, mov_fotos, e_MovFiltrosApp);
			listrepPromocion.add(e_Reporte_Promocion_Mov);
		}
	}

	private void setDataEnvioReporteMaterialApoyo(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Mat_Apoyo_Mov> listrepMaterialApoyo, TblPuntoGPS puntoGPSInicioVisita) {
		ArrayList<E_TblMovRepMaterialDeApoyo> movRepMaterialApoyo = movRepMaterialApoyoController.getByIdReporteCab(emovRepCab.getId());
		ArrayList<E_Reporte_Mat_Apoyo_Mov_Detalle> detalleReporte_matApoyo = new ArrayList<E_Reporte_Mat_Apoyo_Mov_Detalle>();
		if (movRepMaterialApoyo == null) {
			Log.i("*", "No hay reporte Material de Apoyo para enviar");
		} else {
			for (E_TblMovRepMaterialDeApoyo r : movRepMaterialApoyo) {
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(r.getId_foto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(r.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				detalleReporte_matApoyo.add(new E_Reporte_Mat_Apoyo_Mov_Detalle(r, mov_fotos));
			}
		}
		if (detalleReporte_matApoyo.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Mat_Apoyo_Mov e_Reporte_Mat_Apoyo_Mov = new E_Reporte_Mat_Apoyo_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_matApoyo, e_MovFiltrosApp);
			listrepMaterialApoyo.add(e_Reporte_Mat_Apoyo_Mov);
		}
	}

	private void setDataEnvioReporteVistaCompetencia(E_TblMovReporteCab emovRepCab, E_Usuario e_Usuario,List<E_Reporte_VisCompetencia_Mov> listrepVisCompetencia, TblPuntoGPS puntoGPSInicioVisita) {
		ArrayList<TBL_MOV_REP_VISCOMP> movRepVisComList = movRepVisComController.getByIdReporteCab(emovRepCab.getId());
		ArrayList<E_Reporte_VisCompentencia_Mov_Detalle> detalleReporte_vistacom = new ArrayList<E_Reporte_VisCompentencia_Mov_Detalle>();
		if (movRepVisComList == null) {
			Log.i("*", "No hay reportes Vista Competencia para enviar");
		} else {
			for (TBL_MOV_REP_VISCOMP r : movRepVisComList) {
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(r.getId_foto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(r.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				detalleReporte_vistacom.add(new E_Reporte_VisCompentencia_Mov_Detalle(r, mov_fotos));
			}
		}
		if (detalleReporte_vistacom.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_VisCompetencia_Mov e_Reporte_VisCompentencia = new E_Reporte_VisCompetencia_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_vistacom, e_MovFiltrosApp);
			listrepVisCompetencia.add(e_Reporte_VisCompentencia);
		}
	}

	private void setDataEnvioReportePrecio(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Precio_Mov> listRepPrecio, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReportePrecio> detalle = reportesController.getReportePreciosByIdCab(emovRepCab.getId());
		List<E_Reporte_Precio_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Precio_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de precio sin detalles");
		} else {
			for (E_ReportePrecio r : detalle) {
				detalleReporte_m.add(new E_Reporte_Precio_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Precio_Mov reporte = new E_Reporte_Precio_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepPrecio.add(reporte);
		}
	}

	private void setDataEnvioReporteStock(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Stock_Mov> listRepStock, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteStock> detalle = reportesController.getReporteStockByIdCab(emovRepCab.getId());
		List<E_Reporte_Stock_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Stock_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte stock sin detalles");
		} else {
			for (E_ReporteStock r : detalle) {
				detalleReporte_m.add(new E_Reporte_Stock_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Stock_Mov reporte = new E_Reporte_Stock_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepStock.add(reporte);
		}
	}

	private void setDataEnvioReporteIngreso(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Stock_Mov> listRepStock, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteStock> detalle = reportesController.getReporteIngresoByIdCab(emovRepCab.getId());
		List<E_Reporte_Stock_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Stock_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte stock sin detalles");
		} else {
			for (E_ReporteStock r : detalle) {
				detalleReporte_m.add(new E_Reporte_Stock_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Stock_Mov reporte = new E_Reporte_Stock_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepStock.add(reporte);
		}
	}


	private void setDataEnvioReporteSod(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_SOD_Mov> listRepSod, TblPuntoGPS puntoGPSInicioVisita) {
		E_ReporteSod rep = reportesController.getReporteSodByIdRepCab(emovRepCab.getId());
		if (rep != null) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(rep.getId_foto());
			if (mov_fotos != null) {
				movFotosController.updateEstadoFotoById(rep.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
			}
			E_Reporte_SOD_Mov reporte = new E_Reporte_SOD_Mov(emovRepCab, e_Usuario, puntoGps, rep, e_MovFiltrosApp, mov_fotos);
			listRepSod.add(reporte);
		}
	}

	private void setDataEnvioReporteCompetencia(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Competencia_Mov> listRepCompetencia, TblPuntoGPS puntoGPSInicioVisita) {
		E_ReporteCompetencia rep = reportesController.getReporteCompetenciaByIdCab(emovRepCab.getId());
		if (rep != null) {
			List<E_Reporte_Competencia_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Competencia_Mov_Detalle>();
			List<E_ReporteCompetenciaDet> detalle = rep.getDetalles();
			if (detalle == null) {
				Log.i("*", "Reporte de competencia sin detalles");
			} else {
				for (E_ReporteCompetenciaDet r : detalle) {
					if (!"0".equals(r.getCod_material())) {
						detalleReporte_m.add(new E_Reporte_Competencia_Mov_Detalle(r));
					}
				}
			}
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(rep.getId_foto());
			if (mov_fotos != null) {
				movFotosController.updateEstadoFotoById(rep.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
			}
			E_Reporte_Competencia_Mov reporte = null;
			if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(e_Usuario.getCodigo_compania())) {
				reporte = new E_Reporte_Competencia_Mov(emovRepCab, e_Usuario, puntoGps, rep, mov_fotos, detalleReporte_m, e_MovFiltrosApp);
			} else if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(e_Usuario.getCodigo_compania())) {
				reporte = new E_Reporte_Competencia_Mov(emovRepCab, e_Usuario, puntoGps, rep, mov_fotos, e_MovFiltrosApp);
			}
			listRepCompetencia.add(reporte);
		} else {
			Log.i("*", "Sin reporte de competencia");
		}
	}

	private void setDataEnvioReporteAMercado(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_AMercado_Mov> listRepAMercado, TblPuntoGPS puntoGPSInicioVisita) {
		E_ReporteAccionesMercado rep = reportesController.getReporteAccionesMercadoByIdCab(emovRepCab.getId());
		if (rep != null) {
			List<E_Reporte_AMercado_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_AMercado_Mov_Detalle>();
			List<E_ReporteAccionesMercadoDet> detalle = rep.getDetalles();
			if (detalle == null) {
				Log.i("*", "Reporte de Acciones de Mercado sin detalles");
			} else {
				for (E_ReporteAccionesMercadoDet r : detalle) {
					detalleReporte_m.add(new E_Reporte_AMercado_Mov_Detalle(r, emovRepCab.getCod_subreporte()));
				}
			}
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(rep.getId_foto());
			if (mov_fotos != null) {
				movFotosController.updateEstadoFotoById(rep.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
			}
			E_Reporte_AMercado_Mov reporte = new E_Reporte_AMercado_Mov(emovRepCab, e_Usuario, puntoGps, rep, detalleReporte_m, e_MovFiltrosApp, mov_fotos);
			listRepAMercado.add(reporte);
		} else {
			Log.i("*", "Sin reporte de Acciones de Mercado");
		}
	}

	private void setDataEnvioReporteRevestimiento(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Revestimiento_Mov> listRepRevestimiento,
			TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteRevestimiento> detalle = reportesController.getReporteRevestimientoByIdCab(emovRepCab.getId());
		List<E_Reporte_Revestimiento_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Revestimiento_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de Revestimiento sin detalles");
		} else {
			for (E_ReporteRevestimiento r : detalle) {
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(r.getId_foto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(r.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				detalleReporte_m.add(new E_Reporte_Revestimiento_Mov_Detalle(r, mov_fotos, emovRepCab));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Revestimiento_Mov reporte = new E_Reporte_Revestimiento_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepRevestimiento.add(reporte);
		}
	}

	private void setDataEnvioReporteEncuesta(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Encuesta_Mov> listRepEncuesta, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteEncuesta> detalles = reportesController.getReporteEncuestaByIdCab(emovRepCab.getId());
		List<E_Reporte_Encuesta_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Encuesta_Mov_Detalle>();
		if (detalles == null) {
			Log.i("*", "Reporte de Encuesta sin detalles");
		} else {
			for (E_ReporteEncuesta r : detalles) {
				detalleReporte_m.add(new E_Reporte_Encuesta_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Encuesta_Mov reporte = new E_Reporte_Encuesta_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepEncuesta.add(reporte);
		}
	}

	private void setDataEnvioReporteAuditoria(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Auditoria_Mov> listRepAuditoria, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteAuditoria> detalle = reportesController.getReporteAuditoriaByIdCab(emovRepCab.getId());
		List<E_Reporte_Auditoria_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Auditoria_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de Revestimiento sin detalles");
		} else {
			for (E_ReporteAuditoria r : detalle) {
				detalleReporte_m.add(new E_Reporte_Auditoria_Mov_Detalle(r, emovRepCab));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Auditoria_Mov reporte = new E_Reporte_Auditoria_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepAuditoria.add(reporte);
		}
	}

	private void setDataEnvioReporteQuiebre(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Quiebre_Mov> listRepQuiebre, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteQuiebre> detalle = reportesController.getReporteQuiebreByIdCab(emovRepCab.getId());
		List<E_Reporte_Quiebre_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Quiebre_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de quiebre sin detalles");
		} else {
			for (E_ReporteQuiebre r : detalle) {
				detalleReporte_m.add(new E_Reporte_Quiebre_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Quiebre_Mov reporte = new E_Reporte_Quiebre_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepQuiebre.add(reporte);
		}
	}

	private void setDataEnvioReporteLayout(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_LayOut_Mov> listRep, TblPuntoGPS puntoGPSInicioVisita) {
		E_ReporteLayout detalle = reportesController.getReporteLayoutByIdCab(emovRepCab.getId());
		if (detalle == null) {
			Log.i("*", "Reporte de layout sin detalles");
		} else {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_LayOut_Mov reporte = new E_Reporte_LayOut_Mov(emovRepCab, e_Usuario, puntoGps, detalle, e_MovFiltrosApp);
			listRep.add(reporte);
		}
	}

	private void setDataEnvioReporteIncidencia(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Incidencia_Mov> listRepIncidencia, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteIncidencia> detalle = reportesController.getReporteIncidenciaByIdCab(emovRepCab.getId());
		List<E_Reporte_Incidencia_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Incidencia_Mov_Detalle>();
		List<Integer> tipoIncidencia = new ArrayList<Integer>();
		if (detalle == null) {
			Log.i("*", "Reporte de incidencia sin detalles");
		} else {
			for (E_ReporteIncidencia r : detalle) {
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(r.getId_foto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(r.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				detalleReporte_m.add(new E_Reporte_Incidencia_Mov_Detalle(r, mov_fotos, emovRepCab));
				String s_tipoInc = r.getCod_tipo_incidencia();
				int i_tipoInc = 0;
				if (s_tipoInc != null && !s_tipoInc.trim().isEmpty()) {
					try {
						i_tipoInc = Integer.parseInt(s_tipoInc);
					} catch (Exception ex) {
					}
				}
				tipoIncidencia.add(new Integer(i_tipoInc));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			int tipoInc = getTipoIncidencia(tipoIncidencia);
			E_Reporte_Incidencia_Mov reporte = new E_Reporte_Incidencia_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp, tipoInc);
			listRepIncidencia.add(reporte);
		}
	}

	private int getTipoIncidencia(List<Integer> tipos) {
		int tipoIncidencia = 0;
		for (int i = 0; i < tipos.size(); i++) {
			if (i == 0) {
				tipoIncidencia = tipos.get(i).intValue();
			} else {
				if (tipoIncidencia != tipos.get(i).intValue()) {
					tipoIncidencia = 0;
					break;
				}
			}
		}
		return tipoIncidencia;
	}

	private void setDataEnvioReporteImpulso(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Impulso_Mov> listRepImpulso, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteImpulso> detalle = reportesController.getReporteImpulso(emovRepCab.getId());
		List<E_Reporte_Impulso_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Impulso_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de impulso sin detalles");
		} else {
			for (E_ReporteImpulso r : detalle) {
				detalleReporte_m.add(new E_Reporte_Impulso_Mov_Detalle(r));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Impulso_Mov reporte = new E_Reporte_Impulso_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepImpulso.add(reporte);
		}
	}

	private void setDataEnvioReporteExhibicion(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Exhibicion_Mov> listRepExhibicion, TblPuntoGPS puntoGPSInicioVisita) {
		E_ReporteExhibicion rep = reportesController.getReporteExhibByIdCab(emovRepCab.getId());
		if (rep != null) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(rep.getIdFoto());
			if (mov_fotos != null) {
				movFotosController.updateEstadoFotoById(rep.getIdFoto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
			}
			E_Reporte_Exhibicion_Mov reporte = new E_Reporte_Exhibicion_Mov(emovRepCab, e_Usuario, puntoGps, rep, e_MovFiltrosApp, mov_fotos);
			listRepExhibicion.add(reporte);
		} else {
			Log.i("*", "Sin reporte de exhibicion");
		}
	}

	private void setDataEnvioReportePotencial(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Potencial_Mov> listRepPotencial, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReportePotencial> detalle = reportesController.getReportePotencialByIdCab(emovRepCab.getId());
		List<E_Reporte_Potencial_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Potencial_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte potencial sin detalles");
		} else {
			for (E_ReportePotencial r : detalle) {
				detalleReporte_m.add(new E_Reporte_Potencial_Mov_Detalle(r, emovRepCab.getCod_subreporte()));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_Potencial_Mov reporte = new E_Reporte_Potencial_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listrepPotencial.add(reporte);
		}
	}

	private void setDataEnvioReportePrecioPVP(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Precio_PVP_Mov> listRepPrecio, TblPuntoGPS puntoGPSInicioVisita) {
		if (emovRepCab.getCod_reporte().equals("112")) {
			List<E_ReportePrecio> detalle = reportesController.getReportePreciosByIdCab(emovRepCab.getId());
			List<E_Reporte_Precio_PVP_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Precio_PVP_Mov_Detalle>();
			if (detalle == null) {
				Log.i("*", "Reporte de precio pvp sin detalles");
			} else {
				for (E_ReportePrecio r : detalle) {
					detalleReporte_m.add(new E_Reporte_Precio_PVP_Mov_Detalle(r));
				}
			}
			if (detalleReporte_m.size() > 0) {
				TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
				E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
				E_Reporte_Precio_PVP_Mov reporte = new E_Reporte_Precio_PVP_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
				listRepPrecio.add(reporte);
			}
		}

	}

	private void setDataEnvioReportePrecioPDV(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Precio_PDV_Mov> listRepPrecio, TblPuntoGPS puntoGPSInicioVisita) {
		if (emovRepCab.getCod_reporte().equals("113")) {
			List<E_ReportePrecio> detalle = reportesController.getReportePreciosByIdCab(emovRepCab.getId());
			List<E_Reporte_Precio_PDV_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_Precio_PDV_Mov_Detalle>();
			if (detalle == null) {
				Log.i("*", "Reporte de precio pdv sin detalles");
			} else {
				for (E_ReportePrecio r : detalle) {
					detalleReporte_m.add(new E_Reporte_Precio_PDV_Mov_Detalle(r, emovRepCab));
				}
			}
			if (detalleReporte_m.size() > 0) {
				TblPuntoGPS puntoGps = actualizarPuntoGPS(
						getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
				E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
				E_Reporte_Precio_PDV_Mov reporte = new E_Reporte_Precio_PDV_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
				listRepPrecio.add(reporte);
			}
		}

	}

	private void setDataEnvioReporteBloqueAzul(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_BloqueAzul_Mov> listRepRevestimiento, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_ReporteBloqueAzul> detalle = reportesController.getReporteBloqueAzulByIdCab(emovRepCab.getId());
		List<E_Reporte_BloqueAzul_Mov_Detalle> detalleReporte_m = new ArrayList<E_Reporte_BloqueAzul_Mov_Detalle>();
		if (detalle == null) {
			Log.i("*", "Reporte de Bloque Azul sin detalles");
		} else {
			for (E_ReporteBloqueAzul r : detalle) {
				E_tbl_mov_fotos mov_fotos = movFotosController.getById(r.getId_foto());
				if (mov_fotos != null) {
					movFotosController.updateEstadoFotoById(r.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
				}
				detalleReporte_m.add(new E_Reporte_BloqueAzul_Mov_Detalle(r,mov_fotos, emovRepCab));
			}
		}
		if (detalleReporte_m.size() > 0) {
			TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
			E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
			E_Reporte_BloqueAzul_Mov reporte = new E_Reporte_BloqueAzul_Mov(emovRepCab, e_Usuario, puntoGps, detalleReporte_m, e_MovFiltrosApp);
			listRepRevestimiento.add(reporte);
		}
	}

	private void setDataEnvioReporteVideo(E_Usuario e_Usuario, E_TblMovReporteCab emovRepCab, List<E_Reporte_Video_Mov> listRepVideo, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_TblMovRegistroFotografico> reporte = repVideoController.getByIdRepCab(emovRepCab.getId());
		if (reporte == null || reporte.isEmpty()) {
			Log.i("*", "No hay reportes video para enviar");
		} else {
			for(E_TblMovRegistroFotografico registroVideo:reporte){
				E_tbl_mov_videos mov_videos = movVideosController.getById(registroVideo.getIdFoto());
				if (mov_videos != null) {
					movVideosController.updateEstadoVideoById(registroVideo.getIdFoto(), E_tbl_mov_videos.VIDEO_GUARDADO_PARA_ENVIO);
				}
				TblPuntoGPS puntoGps = actualizarPuntoGPS(getPuntoGps(Integer.parseInt(emovRepCab.getId_punto_gps())), puntoGPSInicioVisita);
				E_TblFiltrosApp e_MovFiltrosApp = movFiltrosAppController.getById(emovRepCab.getId_filtros_app());
				E_Reporte_Video_Mov e_Reporte_Video_Mov = new E_Reporte_Video_Mov(emovRepCab, e_Usuario, puntoGps, mov_videos, e_MovFiltrosApp);
				listrepVideo.add(e_Reporte_Video_Mov);
			}
		}
	}

	// ************************************************************************************
	private void setDataEnvioReporteITT(E_TblMovReporteCab emovRepCab, List<E_Reporte_Codigo_ITT_New_Mov> listrepCodITT, TblPuntoGPS puntoGPSInicioVisita) {
		List<E_TBL_MOV_REP_COD_NEW_ITT> movRepCodITTList = movRepNewCodigoITTController.getByIdRepCab(emovRepCab.getId());
		List<E_Codigo_ITT_New> e_Codigo_ITT_Distribuidora = new ArrayList<E_Codigo_ITT_New>();
		if (movRepCodITTList == null) {
			Log.i("*", "No hay reportes ITT para enviar");
		} else {
			if (movRepCodITTList != null) {
				for (E_TBL_MOV_REP_COD_NEW_ITT r : movRepCodITTList) {
					e_Codigo_ITT_Distribuidora.add(new E_Codigo_ITT_New(r));
				}
			}
			if (e_Codigo_ITT_Distribuidora.size() > 0) {
				E_Reporte_Codigo_ITT_New_Mov e_Reporte_Codigo_ITT_Mov = new E_Reporte_Codigo_ITT_New_Mov(emovRepCab, e_Codigo_ITT_Distribuidora);
				listrepCodITT.add(e_Reporte_Codigo_ITT_Mov);
			}
		}
	}

	public void clearNavegacion(Context ctx) {
		SharedPreferences preferencesApp = ctx.getSharedPreferences("Navegacion", Activity.MODE_WORLD_READABLE | Activity.MODE_WORLD_WRITEABLE);
		Editor edit = preferencesApp.edit();
		edit.clear();
		edit.commit();
	}

	public void clearNaveKey(Context ctx, String keyReportes) {
		SharedPreferences prefNav = ctx.getSharedPreferences("Nav" + keyReportes, Activity.MODE_WORLD_READABLE | Activity.MODE_WORLD_WRITEABLE);
		Editor editor = prefNav.edit();
		editor.clear();
		editor.commit();
	}

	public void dejarPendienteEnvio() {
		Log.i("DatosManager", "Dejando los reportes y visitas como pendientes");
		if (visita_envio != null) {
			visita_envio.setEstado(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO);
			new MovRegistroVisitaController(db).edit(visita_envio);
			List<E_TblMovReporteCab> e_MovReporteCab = new E_TblMovReporteCabController(db).getByIdUsuarioIdPuntoVentaIdVisita(getUsuario().getIdUsuario(), visita_envio.getIdPuntoVenta(), E_TblMovReporteCab.ESTADO_ENVIANDO, visita_envio.getId());
			if (e_MovReporteCab != null) {
				int tamCabeceras = e_MovReporteCab.size();
				for (int i = 0; i < tamCabeceras; i++) {
					E_TblMovReporteCab emovRepCab = (E_TblMovReporteCab) e_MovReporteCab.get(i);
					emovRepCab.setEstado_envio(E_TblMovReporteCab.ESTADO_GUARDADA);
					new E_TblMovReporteCabController(db).updateEstadoCabecera(emovRepCab.getId(), E_TblMovReporteCab.ESTADO_GUARDADA);
				}
			}
		}
	}

	public boolean dataEnviando() {
		List<E_MovMarcacion> marcaciones = new MovMarcacionController(db).obtenerEstadosEnviando();
		List<E_TBL_MOV_REGISTROVISITA> visitas = new MovRegistroVisitaController(db).obtenerVisitasNoVisitaPendientes(MovMarcacionController.ESTADO_MARCACION_FIN_ENVIANDO);
		List<Integer> cabeceras = new E_TblMovReporteCabController(db).getIdsCabecerasReportesPendientes();
		boolean hayMarcEnviando = (marcaciones != null && !marcaciones.isEmpty());
		boolean hayVisitaEnviando = (visitas != null && !visitas.isEmpty());
		boolean hayReporteEnviando = (cabeceras != null && !cabeceras.isEmpty());
		return (hayMarcEnviando || hayVisitaEnviando || hayReporteEnviando);
	}

	public void setDB(SQLiteDatabase db) {
		this.db = db;
	}

}
