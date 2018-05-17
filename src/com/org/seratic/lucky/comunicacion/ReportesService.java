package com.org.seratic.lucky.comunicacion;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;

import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroNoVisitaBodegaController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegMotivosBodega;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_Foto;
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
import com.org.seratic.lucky.model.E_Visita_Mov;
import com.org.seratic.lucky.model.ReporteColgateBodega_Mov;
import com.org.seratic.lucky.model.ReporteColgateFarmaciaDT_Mov;
import com.org.seratic.lucky.model.ReportesColgateBodega_RegistrarPDV_Mov_Request;
import com.org.seratic.lucky.model.Utilidades;

public class ReportesService extends Comunication implements IComunicacionListener {

	private static final String TAG = ReportesService.class.getSimpleName();
	private static final int ENV_DISPONIBLE = 0;
	private static final int ENV_MARCACION = 1;
	private static final int ENV_VISITAS = 2;
	private static final int[] secuencia = { ENV_MARCACION, ENV_VISITAS };

	private static ReportesService instance;
	private static int contVisitas;
	private static int cantVisitas;
	private static int contMarcaciones;
	private static int cantMarcaciones;
	private static int reintentos;
	private static int posSecuencia = ENV_DISPONIBLE;
	private static SQLiteDatabase db;
	private static E_tbl_mov_fotosController controller;
	private static MovRegistroVisitaController visitaController;
	private static E_TblMovReporteCabController cabController;
	private static MovMarcacionController marcacionController;

	private List<E_TBL_MOV_REGISTROVISITA> visitas;
	private List<E_MovMarcacion> marcaciones;

	private String urlVisitaPuntoVenta = "/EstadoService.svc/RegistrarVisita_Mov";
	private String urlMarcacion = "/EstadoService.svc/RegistrarMarcacion_Mov";
	private String urlReporteColgateMayorista = "/reporteservice.svc/ReporteColgateMayorista_Mov";
	private String urlReporteColgateMinorista = "/reporteservice.svc/ReporteColgateMinorista_Mov";
	private String urlReporteColgateFarmaciaIT = "/reporteservice.svc/ReporteColgateFarmaciaIT_Mov";
	private String urlReporteColgateFarmaciaDT = "/reporteservice.svc/ReporteColgateFarmaciaDT_Mov";
	private String urlReporteColgateBodega = "/reporteservice.svc/ReporteColgateBodega_Mov";
	private String urlReporteColgateBodega_RegistrarPDV_Mov = "/reporteservice.svc/ReporteColgateBodega_RegistrarPDV_Mov";
	private String urlRegistrarMotivoColgateBodega_Mov = "/EstadoService.svc/RegistrarMotivoColgateBodega_Mov";
	private String urlReporteSanFernandoAAVV_Mov = "/reporteservice.svc/ReporteSanFernandoAAVV_Mov";
	private String urlReporteSanFernandoModerno_Mov = "/reporteservice.svc/ReporteSanFernandoModerno_Mov";
	private String urlReporteSanFernandoTradicional_Mov = "/reporteservice.svc/ReporteSanFernandoTradicional_Mov";
	private String urlReporteAlicorpMayorista_Mov = "/reporteservice.svc/ReporteAlicorpMayorista_Mov";
	private String urlReporteAlicorpAutoservicio_Mov = "/reporteservice.svc/ReporteAlicorpAutoservicio_Mov";
	private String urlRegistrarMotivoColgateBodega = "/EstadoService.svc/RegistrarMotivoColgateBodega_Mov";

	private E_ReporteColgateMayoristaMov colgateMayorista;
	private E_ReporteColgateMayoristaMov colgateMinorista;
	private E_ReporteColgateFarmaciaIT_Mov colgateFarmaciaIT;
	private ReporteColgateFarmaciaDT_Mov colgateFarmaciaDT;
	private ReporteColgateBodega_Mov colgateBodega;
	private E_RegistroPDV_Mov registroPDV;
	private ReportesColgateBodega_RegistrarPDV_Mov_Request registroBodegaPDV;
	private E_RegistrarMotivoColgateBodega_Request motivosBogega;
	private E_ReporteSanFernandoAAVV_Mov sanfdoAAVV;
	private E_ReporteSanFernandoModerno_Mov sanfdoModerno;
	private E_ReporteSanFernandoTradicional_Mov sanfdoTradicional;
	private E_ReporteAlicorpMayorista_Mov alicorpMayorista;
	private E_ReporteAlicorpAutoservicio_Mov alicorpAutoservicio;
	private E_VisitaRequest visitaNoVisita;
	private E_MarcacionRequest marcacion;
	private Context context;

	public static ReportesService getInstance(SQLiteDatabase sql_db, Context context) {
		if (instance == null) {
			db = sql_db;
			instance = new ReportesService(context);
			httpConnector = new HttpConnector();
			controller = new E_tbl_mov_fotosController(db);
			visitaController = new MovRegistroVisitaController(db);
			cabController = new E_TblMovReporteCabController(db);
			marcacionController = new MovMarcacionController(db);
		}
		return instance;
	}

	public ReportesService(Context context) {
		this.context = context;
		SharedPreferences preferences = context.getSharedPreferences("Config", Context.MODE_WORLD_READABLE);
		String url = preferences.getString("URL", DatosManager.DEFAULT_URL);
		urlVisitaPuntoVenta = "http://" + url + urlVisitaPuntoVenta;
		urlMarcacion = "http://" + url + urlMarcacion;
		urlReporteColgateMayorista = "http://" + url + urlReporteColgateMayorista;
		urlReporteColgateMinorista = "http://" + url + urlReporteColgateMinorista;
		urlReporteColgateFarmaciaIT = "http://" + url + urlReporteColgateFarmaciaIT;
		urlReporteColgateFarmaciaDT = "http://" + url + urlReporteColgateFarmaciaDT;
		urlReporteColgateBodega = "http://" + url + urlReporteColgateBodega;
		urlReporteColgateBodega_RegistrarPDV_Mov = "http://" + url + urlReporteColgateBodega_RegistrarPDV_Mov;
		urlRegistrarMotivoColgateBodega_Mov = "http://" + url + urlRegistrarMotivoColgateBodega_Mov;
		urlReporteSanFernandoAAVV_Mov = "http://" + url + urlReporteSanFernandoAAVV_Mov;
		urlReporteSanFernandoModerno_Mov = "http://" + url + urlReporteSanFernandoModerno_Mov;
		urlReporteSanFernandoTradicional_Mov = "http://" + url + urlReporteSanFernandoTradicional_Mov;
		urlReporteAlicorpMayorista_Mov = "http://" + url + urlReporteAlicorpMayorista_Mov;
		urlReporteAlicorpAutoservicio_Mov = "http://" + url + urlReporteAlicorpAutoservicio_Mov;
		urlRegistrarMotivoColgateBodega = "http://" + url + urlRegistrarMotivoColgateBodega;
	}

	public synchronized void registrarEnviando() {
		Log.i("ReportesService", "** Iniciando registrarEnviando()");
		if (posSecuencia == ENV_DISPONIBLE) {
			prepararEnvioMarcacones();
			if (marcaciones != null && !marcaciones.isEmpty()) {
				Log.i("ReportesService", "Se encontraron marcaciones para enviar");
				registrarMarcaciones(/* marcaciones */);
			} else {
				prepararEnvioVisitas();
				if (visitas != null && !visitas.isEmpty()) {
					Log.i("ReportesService", "Se encontraron visitas para enviar");
					registrarVisitas_Mov(/* visitas */);
				} else {
					Log.i("ReportesService", "No hay datos para enviar");
					posSecuencia = ENV_DISPONIBLE;
				}
			}
		} else {
			Log.i("ReportesService", "Ya se encuentran datos en proceso de envio");
			Log.i("ReportesService", "Reintento " + reintentos + " de " + 5);
			reintentos++;
			if (reintentos >= 5) {
				Log.i("ReportesService", "*** FIN DE LOS REINTENTOS - REINICIANDO ENVIOS ***");
				if (marcaciones != null) {
					contMarcaciones++;
					if (contMarcaciones < cantMarcaciones) {
						clarearVariablesEnvio();
						enviarMarcacion();
					} else {
						Log.i("ReportesService", "Termino envio de marcaciones");
						marcaciones = null;
						clarearVariablesEnvio();
						prepararEnvioVisitas();
						if(visitas!=null){
							registrarVisitas_Mov();
						}
						else{
							visitas = null;
							clarearVariablesEnvio();
							posSecuencia = ENV_DISPONIBLE;
						}
					}
				} else {
					if (visitas != null) {
						contVisitas++;
						if (contVisitas < cantVisitas) {
							clarearVariablesEnvio();
							enviarVisita();
						} else {
							Log.i("ReportesService", "Termino envio de visitas");
							visitas = null;
							clarearVariablesEnvio();
							posSecuencia = ENV_DISPONIBLE;
						}

					}
				}
			}
		}
	}

	private void prepararEnvioMarcacones() {
		posSecuencia = ENV_MARCACION;
		visitas = null;
		marcaciones = null;
		marcacion = null;
		contMarcaciones = 0;
		cantMarcaciones = 0;
		marcaciones = marcacionController.obtenerEstadosEnviando();
	}

	private void prepararEnvioVisitas() {
		posSecuencia = ENV_VISITAS;
		marcaciones = null;
		visitas = null;
		contVisitas = 0;
		cantVisitas = 0;
		visitas = visitaController.obtenerVisitasNoVisitaPendientes(MovRegistroVisitaController.ESTADO_VISITA_ENVIANDO_FIN);
	}

	public void registrarVisitas_Mov(/* List<E_TBL_MOV_REGISTROVISITA> e_visitas */) {
		// if (visitas == null) {
		// visitas = e_visitas;
		if ((visitas != null) && (!visitas.isEmpty())) {
			Log.i("ReportesService", "Visitas a enviar: " + visitas.size());
			cantVisitas = visitas.size();
			contVisitas = 0;
			comListener = this;
			setComListener(this, context);
			clarearVariablesEnvio();
			enviarVisita();
		}
		/*
		 * } else { Log.i("ReportesService",
		 * "Ya se encuentran visitas en proceso de envío -- Reintento " +
		 * reintentos + " de " + 5); reintentos++; if (reintentos == 5) {
		 * Log.i("ReportesService",
		 * "*** FIN DE LOS REINTENTOS - REINICIANDO VISITA ***"); contVisitas++;
		 * reintentos = 0; if (contVisitas < cantVisitas) {
		 * clarearVariablesEnvio(); enviarVisita(); } else {
		 * Log.i("ReportesService", "Termino envio de visitas"); visitas = null;
		 * clarearVariablesEnvio(); } } }
		 */
	}

	public void registrarMarcaciones(/* List<E_MovMarcacion> e_marcaciones */) {
		// if (marcaciones == null) {
		// marcaciones = e_marcaciones;
		if ((marcaciones != null) && (!marcaciones.isEmpty())) {
			Log.i("ReportesService", "Marcaciones a enviar: " + marcaciones.size());
			cantMarcaciones = marcaciones.size();
			contMarcaciones = 0;
			comListener = this;
			setComListener(this, context);
			clarearVariablesEnvio();
			enviarMarcacion();
		}
		/*
		 * } else { Log.i("ReportesService",
		 * "Ya se encuentran marcaciones en proceso de envío -- Reintento " +
		 * reintentos + " de " + 5); reintentos++; if (reintentos == 5) {
		 * Log.i("ReportesService",
		 * "*** FIN DE LOS REINTENTOS - REINICIANDO MARCACIONES ***");
		 * contMarcaciones++; reintentos = 0; if (contMarcaciones <
		 * cantMarcaciones) { clarearVariablesEnvio(); enviarMarcacion(); } else
		 * { Log.i("ReportesService", "Termino envio de visitas"); marcaciones =
		 * null; clarearVariablesEnvio(); } } }
		 */
	}

	private TblPuntoGPS getPuntoGps(int idPunto) {
		TblPuntoGPS puntoGps = new TblPuntoGPSController(db).getPuntoById(idPunto);
		return puntoGps;
	}

	private void registrarVisitaNoVisita(E_VisitaRequest e_VisitaRequest) {
		if (visitaNoVisita == null) {
			visitaNoVisita = e_VisitaRequest;
			if (visitaNoVisita != null) {
				Log.i("ReportesService", "Enviando No Visita");
				sendData(createJSON(visitaNoVisita), urlVisitaPuntoVenta);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra una No visita enviandose");
		}
	}

	private void registrarMarcacion(E_MarcacionRequest e_MarcacionRequest) {
		if (marcacion == null) {
			marcacion = e_MarcacionRequest;
			if (marcacion != null) {
				Log.i("ReportesService", "Enviando marcacion");
				sendData(createJSON(marcacion), urlMarcacion);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra una marcacion enviandose");
		}

	}

	private void regReporteColgateMayorista(E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov) {
		if (colgateMayorista == null) {
			colgateMayorista = e_ReporteColgateMayoristaMov;
			if (colgateMayorista != null) {
				Log.i("ReportesService", "Enviando reportes colgate mayorista");
				sendData(createJSON(colgateMayorista), urlReporteColgateMayorista);

			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte colgate mayorista enviandose");
		}
	}

	private void regReporteColgateMinosrista(E_ReporteColgateMayoristaMov e_ReporteColgateMayoristaMov) {
		if (colgateMinorista == null) {
			colgateMinorista = e_ReporteColgateMayoristaMov;
			if (colgateMinorista != null) {
				Log.i("ReportesService", "Enviando reportes colgate minorista");
				sendData(createJSON(colgateMinorista), urlReporteColgateMinorista);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte colgate minorista enviandose");
		}
	}

	private void regReporteColgateFarmaciaIT(E_ReporteColgateFarmaciaIT_Mov eFarmaciaIT_Mov) {
		if (colgateFarmaciaIT == null) {
			colgateFarmaciaIT = eFarmaciaIT_Mov;
			if (colgateFarmaciaIT != null) {
				Log.i("ReportesService", "Enviando reportes colgate farmacias it");
				sendData(createJSON(colgateFarmaciaIT), urlReporteColgateFarmaciaIT);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un Reporte colgate farmacias it enviandose");
		}
	}

	private void regReporteColgateFarmaciaDT_Mov(ReporteColgateFarmaciaDT_Mov reporteColgateFarmaciaDT_Mov) {
		if (colgateFarmaciaDT == null) {
			colgateFarmaciaDT = reporteColgateFarmaciaDT_Mov;
			if (colgateFarmaciaDT != null) {
				Log.i("ReportesService", "Enviando reportes colgate farmacias dt");
				sendData(createJSON(colgateFarmaciaDT), urlReporteColgateFarmaciaDT);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte colgate farmacias dt enviandose");
		}
	}

	private void regReporteColgateBodega_Mov(ReporteColgateBodega_Mov reporteColgateBodega_Mov) {
		if (colgateBodega == null) {
			colgateBodega = reporteColgateBodega_Mov;
			if (colgateBodega != null) {
				Log.i("ReportesService", "Enviando reportes colgate bodega");
				sendData(createJSON(colgateBodega), urlReporteColgateBodega);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte colgate bodega enviandose");
		}
	}

	private void registrarPDVMov(E_RegistroPDV_Mov e_RegistroPDV_Mov) {

		if (registroPDV == null) {
			registroPDV = e_RegistroPDV_Mov;
			if (registroPDV != null) {
				Log.i("ReportesService", "Enviando registro PDV");
				sendData(createJSON(registroPDV), urlReporteColgateBodega_RegistrarPDV_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un registro PDV enviandose");
		}
	}

	/*********** SAN FERNANDO **************/
	private void reporteSanFernandoAAVV_Mov(E_ReporteSanFernandoAAVV_Mov reporte) {
		if (sanfdoAAVV == null) {
			sanfdoAAVV = reporte;
			if (sanfdoAAVV != null) {
				Log.i("ReportesService", "Enviando reportes san fernando aavv");
				sendData(createJSON(sanfdoAAVV), urlReporteSanFernandoAAVV_Mov);
			}
		} else {
			Log.i("ReportesService", "ya se encuentra un reporte san fernando aavv enviandose");
		}
	}

	private void reporteSanFernandoModerno_Mov(E_ReporteSanFernandoModerno_Mov reporte) {
		if (sanfdoModerno == null) {
			sanfdoModerno = reporte;
			if (sanfdoModerno != null) {
				Log.i("ReportesService", "Enviando reportes san fernando moderno");
				sendData(createJSON(sanfdoModerno), urlReporteSanFernandoModerno_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte san fernando moderno enviandose");
		}
	}

	private void reporteSanFernandoTradicional_Mov(E_ReporteSanFernandoTradicional_Mov reporte) {
		if (sanfdoTradicional == null) {
			sanfdoTradicional = reporte;
			if (sanfdoTradicional != null) {
				Log.i("ReportesService", "Enviando reportes san fernando tradicional");
				sendData(createJSON(sanfdoTradicional), urlReporteSanFernandoTradicional_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte san fernando tradicional enviandose");
		}
	}

	/****************** ALICORP ***********************/
	private void reporteAlicorpMayorista_Mov(E_ReporteAlicorpMayorista_Mov reporte) {
		if (alicorpMayorista == null) {
			alicorpMayorista = reporte;
			if (alicorpMayorista != null) {
				Log.i("ReportesService", "Enviando reportes alicorp mayoristas");
				sendData(createJSON(alicorpMayorista), urlReporteAlicorpMayorista_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte alicorp mayoristas enviandose");
		}
	}

	private void reporteAlicorpAutoservicio_Mov(E_ReporteAlicorpAutoservicio_Mov reporte) {
		if (alicorpAutoservicio == null) {
			alicorpAutoservicio = reporte;
			if (alicorpAutoservicio != null) {
				Log.i("ReportesService", "Enviando reportes alicorp autorservicios");
				sendData(createJSON(alicorpAutoservicio), urlReporteAlicorpAutoservicio_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un reporte alicorp autoservicios enviandose");
		}
	}

	/****************** BODEGA ***********************/
	private void registrarNoVistaBodega(E_RegistrarMotivoColgateBodega_Request e_NoVisitaBodegaRequest) {
		// TODO Auto-generated method stubds
		if (motivosBogega == null) {
			motivosBogega = e_NoVisitaBodegaRequest;
			if (motivosBogega != null) {
				Log.i("ReportesService", "Enviando motivos bodeba");
				sendData(createJSON(motivosBogega), urlRegistrarMotivoColgateBodega);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentran motivos bodega enviandose");
		}
	}

	private void reporteColgateBodegaRegistrarPDVMov(ReportesColgateBodega_RegistrarPDV_Mov_Request e_RegistroPDV_Mov) {
		Conexion.TYPE_SERVICE = 3;
		if (registroBodegaPDV == null) {
			registroBodegaPDV = e_RegistroPDV_Mov;
			if (registroBodegaPDV != null) {
				Log.i("ReportesService", "Enviando registro bodega pdv");
				sendData(createJSON(registroBodegaPDV), urlReporteColgateBodega_RegistrarPDV_Mov);
			}
		} else {
			Log.i("ReportesService", "Ya se encuentra un Registro bodega pdv enviandose");
		}
	}

	private void enviarMarcacion() {
		Log.i("ReportesService", "Entro a enviar marcacion");
		E_MovMarcacion e_marcacion = marcaciones.get(contMarcaciones);
		if (e_marcacion != null) {
			E_Usuario e_Usuario = DatosManager.getInstancia().getUsuario();
			TblPuntoGPS puntoGpsInicial = getPuntoGps(e_marcacion.getIdPunto_inicio());
			TblPuntoGPS puntoGpsFinal = null;
			if (e_marcacion.getIdPunto_fin() != 0) {
				puntoGpsFinal = getPuntoGps(e_marcacion.getIdPunto_fin());
			}
			E_MarcacionRequest e_marc = new E_MarcacionRequest(e_marcacion, e_Usuario, puntoGpsInicial, puntoGpsFinal);
			registrarMarcacion(e_marc);
			System.gc();
		} else {
			respuestaEnvio(-1, "marcacion recuperada = null");
		}
	}

	private void enviarVisita() {
		Log.i("ReportesService", "Entro a enviar visita");
		E_TBL_MOV_REGISTROVISITA e_visita = visitas.get(contVisitas);
		if (e_visita != null) {
			DatosManager.getInstancia().inicializarControladores();
			DatosManager.getInstancia().setVisita_envio(e_visita);
			Log.i("ReportesService", "*** ENVIANDO VISITA NUMERO " + contVisitas + " DE " + cantVisitas + " ***");
			E_Usuario e_Usuario = DatosManager.getInstancia().getUsuario();
			String canal = e_Usuario.getCod_canal();
			String cod_cliente = e_Usuario.getCodigo_compania();
			TblPuntoGPS puntoGpsInicial = getPuntoGps(e_visita.getIdPuntoGPSInicio());
			TblPuntoGPS puntoGpsFinal = null;
			if (e_visita.getIdPuntoGPSFin() != 0) {
				puntoGpsFinal = getPuntoGps(e_visita.getIdPuntoGPSFin());
			}
			int id_foto = e_visita.getIdFoto();
			E_tbl_mov_fotosController movFotosController = new E_tbl_mov_fotosController(db);
			E_tbl_mov_fotos mov_fotos = movFotosController.getById(id_foto);
			E_Visita_Mov e_visita_mov = new E_Visita_Mov(e_visita, e_Usuario, puntoGpsInicial, puntoGpsFinal, mov_fotos);

			if (e_visita.getIdmotivoNoVisita() == 0) {
				Log.i("ReportesService", "enviando visita con reportes que se quedaron en estado ENVIANDO");
				String msg = DatosManager.getInstancia().fijarDatosEnvío(db, context, E_TblMovReporteCab.ESTADO_ENVIANDO);
				if (msg == null) {
					if (DatosManager.CLIENTE_COLGATE.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_MAYORISTAS.equalsIgnoreCase(canal)) {
							E_ReporteColgateMayoristaMov reporte = new E_ReporteColgateMayoristaMov(DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCodITT(), e_visita_mov, 0);
							regReporteColgateMayorista(reporte);
							Log.i("ReportesService", "Enviar reporte de mayoristas");

						} else if (DatosManager.CANAL_MINORISTAS.equalsIgnoreCase(canal)) {
							E_ReporteColgateMayoristaMov reporte = new E_ReporteColgateMayoristaMov(DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCodITT(), e_visita_mov, 0);
							regReporteColgateMinosrista(reporte);
							Log.i("ReportesService", "Enviar reporte de minoristas");

						} else if (DatosManager.CANAL_FARMACIAS_IT.equalsIgnoreCase(canal)) {
							E_ReporteColgateFarmaciaIT_Mov reporte = new E_ReporteColgateFarmaciaIT_Mov(DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCodITT(), e_visita_mov, 0);
							regReporteColgateFarmaciaIT(reporte);
							Log.i("ReportesService", "Enviar reporte de farmacias IT");

						} else if (DatosManager.CANAL_FARMACIAS_DT.equalsIgnoreCase(canal)) {
							ReporteColgateFarmaciaDT_Mov reporte = new ReporteColgateFarmaciaDT_Mov(DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepCodITT(), DatosManager.getInstancia().getListrepPromocion(), DatosManager.getInstancia().getListrepMaterialApoyo(), DatosManager.getInstancia().getListrepVisCompetencia(), e_visita_mov, 0);
							regReporteColgateFarmaciaDT_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de farmacias DT");

						} else if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(canal)) {
							ReporteColgateBodega_Mov reporte = new ReporteColgateBodega_Mov(DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepCodITT(), e_visita_mov, DatosManager.getInstancia().getListrepFotografico(), 0);
							regReporteColgateBodega_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de bodegas");
						}
					} else if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_ALICORP_MAYORISTAS.equals(canal)) {
							E_ReporteAlicorpMayorista_Mov reporte = new E_ReporteAlicorpMayorista_Mov(DatosManager.getInstancia().getListrepPrecio(), DatosManager.getInstancia().getListrepSod(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCompetencia(), DatosManager.getInstancia().getListrepStock(), e_visita_mov, 0);
							reporteAlicorpMayorista_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de alicorp - mayoristas");

						} else if (DatosManager.CANAL_ALICORP_AUTOSERVICIOS.equals(canal)) {
							E_ReporteAlicorpAutoservicio_Mov reporte = new E_ReporteAlicorpAutoservicio_Mov(DatosManager.getInstancia().getListrepPrecio(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCompetencia(), DatosManager.getInstancia().getListrepExhibicion(), DatosManager.getInstancia().getListrepQuiebre(), DatosManager.getInstancia().getListrepLayout(), e_visita_mov, 0);
							reporteAlicorpAutoservicio_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de alicorp - autoservicios");
						}
					} else if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
						if (DatosManager.CANAL_SANFDO_AAVV.equals(canal)) {
							E_ReporteSanFernandoAAVV_Mov reporte = new E_ReporteSanFernandoAAVV_Mov(DatosManager.getInstancia().getListrepPotencial(), DatosManager.getInstancia().getListrepPrecioPVP(), DatosManager.getInstancia().getListrepPrecioPDV(), DatosManager.getInstancia().getListrepIncidencia(), DatosManager.getInstancia().getListrepAccionMdo(), DatosManager.getInstancia().getListrepRevestimiento(), DatosManager.getInstancia().getListrepAuditoria(), DatosManager.getInstancia().getListrepFotografico(), e_visita_mov, 0);
							reporteSanFernandoAAVV_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de san fernando - aavv");

						} else if (DatosManager.CANAL_SANFDO_MODERNO.equals(canal)) {
							E_ReporteSanFernandoModerno_Mov reporte = new E_ReporteSanFernandoModerno_Mov(DatosManager.getInstancia().getListrepPrecio(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepCompetencia(), DatosManager.getInstancia().getListrepStock(), DatosManager.getInstancia().getListrepImpulso(), e_visita_mov, 0);
							reporteSanFernandoModerno_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de san fernando - moderno");

						} else if (DatosManager.CANAL_SANFDO_TRADICIONAL_CHIKARA.equalsIgnoreCase(canal)) {
							//E_ReporteSanFernandoTradicional_Mov reporte = new E_ReporteSanFernandoTradicional_Mov(DatosManager.getInstancia().getListrepPrecio(), DatosManager.getInstancia().getListrepExhibicion(), DatosManager.getInstancia().getListrepMarcajePrecio(), DatosManager.getInstancia().getListrepMaterialApoyo(), DatosManager.getInstancia().getListrepCapacitacion(), DatosManager.getInstancia().getListrepIncidencia(), DatosManager.getInstancia().getListrepCredito(), DatosManager.getInstancia().getListRepPresencia(), e_visita_mov, 0, DatosManager.getInstancia().getListRepFotografico());
							E_ReporteSanFernandoTradicional_Mov reporte = new E_ReporteSanFernandoTradicional_Mov(DatosManager.getInstancia().getListrepPrecio(), DatosManager.getInstancia().getListrepPresencia(), DatosManager.getInstancia().getListrepMaterialApoyo(), DatosManager.getInstancia().getListrepIncidencia(), DatosManager.getInstancia().getListrepBloqueAzul(), DatosManager.getInstancia().getListrepAccionMdo(), DatosManager.getInstancia().getListrepLayout(), DatosManager.getInstancia().getListrepFotografico(), DatosManager.getInstancia().getListrepEncuesta(), DatosManager.getInstancia().getListrepVideo(), e_visita_mov, 0);
							reporteSanFernandoTradicional_Mov(reporte);
							Log.i("ReportesService", "Enviar reporte de san fernando - tradicional");
						}
					}
				}
				else{
					Log.i("ReportesService", "visita sin reportes - los reportes ya fueron enviados");
					E_VisitaRequest e_visitaV = new E_VisitaRequest(e_visita, e_Usuario, puntoGpsInicial, puntoGpsFinal, mov_fotos);
					registrarVisitaNoVisita(e_visitaV);
					if (e_visita.getIdmotivoNoVisita() == -1) {
						MovRegistroNoVisitaBodegaController movNoVisitaBodegaController = new MovRegistroNoVisitaBodegaController(db);
						E_Tbl_Mov_RegistroBodega visitaBodega = movNoVisitaBodegaController.getByIdVisita(e_visita.getId());
						visitaBodega.setDetalle(new MovRegistroNoVisitaBodegaController(db).getDetalle(e_visita.getId()));
						TblPuntoGPS puntoGps = getPuntoGps(visitaBodega.getIdPuntoGPS());
						motivosBogega = new E_RegistrarMotivoColgateBodega_Request(visitaBodega, DatosManager.getInstancia().getUsuario(), puntoGps);
						registrarNoVistaBodega(motivosBogega);
					}
				}
			} else {
				// TODO Enviar las no visitas
				Log.i("ReportesService", "visita sin reportes");
				E_VisitaRequest e_visitaNoVisita = new E_VisitaRequest(e_visita, e_Usuario, puntoGpsInicial, puntoGpsFinal, mov_fotos);
				registrarVisitaNoVisita(e_visitaNoVisita);
				if (e_visita.getIdmotivoNoVisita() == -1) {
					MovRegistroNoVisitaBodegaController movNoVisitaBodegaController = new MovRegistroNoVisitaBodegaController(db);
					E_Tbl_Mov_RegistroBodega visitaBodega = movNoVisitaBodegaController.getByIdVisita(e_visita.getId());
					visitaBodega.setDetalle(new MovRegistroNoVisitaBodegaController(db).getDetalle(e_visita.getId()));
					TblPuntoGPS puntoGps = getPuntoGps(visitaBodega.getIdPuntoGPS());
					motivosBogega = new E_RegistrarMotivoColgateBodega_Request(visitaBodega, DatosManager.getInstancia().getUsuario(), puntoGps);
					registrarNoVistaBodega(motivosBogega);
				}
			}
			System.gc();

		} else {
			Log.i("ReportesService", "Visita a enviar esta en NULL");
			respuestaEnvio(-1, "visita recuperada = null");
		}
	}

	public void respuestaEnvio(int cod, String msg) {
		Log.i("ReportesServices", "Respuesta envio: cod = " + cod + " msg = " + msg);
		switch (posSecuencia) {
		case ENV_MARCACION:
			E_MovMarcacion e_marcacion = marcaciones.get(contMarcaciones);
			if (e_marcacion != null) {
				if (cod == 0 || cod == 1) {
					Log.i("ReportesService", "Actualizando estado de marcacion");
					e_marcacion.setEstado(MovMarcacionController.ESTADO_MARCACION_FIN_ENVIADO);
					marcacionController.edit(e_marcacion);
				} else {
					Log.i("ReportesService", "Dejando marcacion como pendiente");
					e_marcacion.setEstado(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);
					marcacionController.edit(e_marcacion);
				}
			}
			clarearVariablesEnvio();
			contMarcaciones++;
			if (contMarcaciones < cantMarcaciones) {
				enviarMarcacion();
			} else {
				Log.i("ReportesService", "Termino envio de marcaciones");
				prepararEnvioVisitas();
				if (visitas != null && !visitas.isEmpty()) {
					Log.i("ReportesService", "Se encontraron visitas para enviar");
					registrarVisitas_Mov(/* visitas */);
				} else {
					Log.i("ReportesService", "No hay datos para enviar");
					visitas = null;
					posSecuencia = ENV_DISPONIBLE;
				}
			}
			break;
		case ENV_VISITAS:
			if (cod == 0 || cod == 1) {
				Log.i("ReportesService", "Actualizando estado de visita y cabeceras");
				DatosManager datosManager = DatosManager.getInstancia();
				E_TBL_MOV_REGISTROVISITA visita = visitas.get(contVisitas);
				if (visita != null) {
					visita.setEstado(MovRegistroVisitaController.ESTADO_VISITA_FIN_ENVIADO);
					MovRegistroVisitaController movController = new MovRegistroVisitaController(db);
					movController.edit(visita);
					E_TblMovReporteCabController e_TblMovReporteCabController = new E_TblMovReporteCabController(db);
					e_TblMovReporteCabController.updateEstadoCabeceraByUsuarioVisita(E_TblMovReporteCab.ESTADO_ENVIADA, datosManager.getUsuario().getIdUsuario(), visita.getId());
				}
			} else {
				Log.i("ReportesService", "Dejando visita y reportes como pendiente");
				DatosManager.getInstancia().dejarPendienteEnvio();
			}
			clarearVariablesEnvio();
			contVisitas++;
			if (contVisitas < cantVisitas) {
				enviarVisita();
			} else {
				Log.i("ReportesService", "Termino envio de visitas");
				visitas = null;
				posSecuencia = ENV_DISPONIBLE;
			}
			break;
		}
	}

	private void clarearVariablesEnvio() {
		Log.i("ReportesService", "clareando variables de envío");
		DatosManager.getInstancia().setVisita_envio(null);
		colgateMayorista = null;
		colgateMinorista = null;
		colgateFarmaciaIT = null;
		colgateFarmaciaDT = null;
		colgateBodega = null;
		motivosBogega = null;
		registroPDV = null;
		registroBodegaPDV = null;
		alicorpMayorista = null;
		alicorpAutoservicio = null;
		sanfdoModerno = null;
		sanfdoAAVV = null;
		sanfdoTradicional = null;
		visitaNoVisita = null;
		marcacion = null;
		reintentos = 0;
	}

}
