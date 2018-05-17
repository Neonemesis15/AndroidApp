package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import org.seratic.location.IGPSManager;
import org.seratic.location.MarcacionLocationHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_tblMstReporteController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroNoVisitaBodegaController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.comunicacion.Conexion;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.gui.adapters.PendienteAdapter;
import com.org.seratic.lucky.gui.vo.PendienteVO;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;
import com.org.seratic.lucky.model.E_MarcacionRequest;
import com.org.seratic.lucky.model.E_RegistrarMotivoColgateBodega_Request;
import com.org.seratic.lucky.model.E_VisitaRequest;

public class PendientesActivity extends Activity implements Runnable, IComunicacionListener, IGPSManager {

	public static final int ENVIAR_FIN_MARCACION = 1;
	private SQLiteDatabase db;
	ArrayList<PendienteVO> listPendientes;
	AlertDialog alert;
	private MarcacionLocationHandler locationHandler;
	private MovMarcacionController movMarcacionController;
	private E_TblMovReporteCabController movReporteController;
	private MovRegistroVisitaController rVController;
	int localizacion;
	List<E_TBL_MOV_REGISTROVISITA> visitasNoVisitasPendientes;
	List<E_TBL_MOV_REGISTROVISITA> visitasPendientes;
	Conexion conexion;
	private TblPuntoGPSController puntoGPSController;
	ProgressDialog indicadorProgreso;
	List<E_MovMarcacion> marcacionesPendientesEnvio;
	boolean envioExitoso = true;

	private TblPuntoGPS puntoGPS;

	// Joseph Gonzales
	private MovRegistroNoVisitaBodegaController movNoVisitaBodegaController;

	// int id;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case -1:
			case 1:
			case 2:
				if (visitasNoVisitasPendientes != null) {
					if (!visitasNoVisitasPendientes.isEmpty()) {
						enviarSiguienteVisita();
					}
				} else if (marcacionesPendientesEnvio != null) {
					if (!marcacionesPendientesEnvio.isEmpty()) {
						enviarSiguienteMarcacion();
					}
				}
			case -2:
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				// System.out.println("PendientesActivity Handler case 3");
				Intent nombre = new Intent(PendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;
			default:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				// System.out.println("PendientesActivity Handler case 3");
				Intent menu = new Intent(PendientesActivity.this, MainMenu.class);
				menu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(menu);
				break;
			}

			if (msg.obj != null && !msg.obj.toString().isEmpty()) {
				Toast.makeText(PendientesActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_pendientes);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("PendientesActivity", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		locationHandler = new MarcacionLocationHandler(db, this);
		rVController = new MovRegistroVisitaController(db);
		movMarcacionController = new MovMarcacionController(db);
		movReporteController = new E_TblMovReporteCabController(db);
		movNoVisitaBodegaController = new MovRegistroNoVisitaBodegaController(db);

		new E_tblMstReporteController(db);		
		loadDatos();
		init();
	}

	public void loadDatos() {

		listPendientes = new ArrayList<PendienteVO>();

		boolean hayPend1;
		boolean hayPend2;
		boolean hayPend3;

		hayPend1 = movMarcacionController.isMarcacionPendiente(1, MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO);
		hayPend2 = movMarcacionController.isMarcacionPendiente(1, MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO);
		hayPend3 = movMarcacionController.isMarcacionPendiente(1, MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);

		PendienteVO p;

		p = new PendienteVO("Marcación", hayPend1 || hayPend2 || hayPend3, hayPend1 || hayPend2);
		listPendientes.add(p);

		hayPend1 = movMarcacionController.isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPend2 = movMarcacionController.isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPend3 = movMarcacionController.isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));

		p = new PendienteVO("Estado", hayPend1 || hayPend2 || hayPend3, hayPend1 || hayPend2);
		listPendientes.add(p);

		hayPend1 = rVController.isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_INICIO_GUARDADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPend2 = rVController.isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_INICIO_ENVIADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPend3 = rVController.isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO, Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));

		int numVisitasNoVisitas;
		int numVisitas;
		visitasNoVisitasPendientes = rVController.obtenerVisitasNoVisitaPendientes(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO);
		visitasPendientes = rVController.obtenerVisitasPendientes();

		if (visitasNoVisitasPendientes != null) {
			numVisitasNoVisitas = rVController.obtenerContadorNoVisitasPendientes(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO);
		} else {
			numVisitasNoVisitas = 0;
		}
		numVisitas = rVController.obtenerContadorVisitasPendientes(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO);

		p = new PendienteVO("Visita", hayPend1 || hayPend2 || hayPend3, numVisitasNoVisitas, numVisitas, hayPend1 || hayPend2);
		listPendientes.add(p);

		p = new PendienteVO("Reporte", movReporteController.isReportesPendientes(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario())), false);
		listPendientes.add(p);
	}

	public void init() {
		ListView listView = (ListView) findViewById(R.id.list_pendientes);
		listView.setAdapter(new PendienteAdapter(this, R.layout.ly_pendientes_item_list, listPendientes));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long arg3) {
				onClickPendiente(adapterView, view, pos, arg3);
			}
		});

	}

	public void run() {

	}

	protected void onClickPendiente(AdapterView<?> adapterView, View view, int pos, long arg3) {

		if (pos == 0 && listPendientes.get(pos).isPendiente()) {

			// pendientes de marcacion
			E_MovMarcacion m = movMarcacionController.getLastMarcacionByEstado(1);
			marcacionesPendientesEnvio = movMarcacionController.obtenerMarcacionesPendientesdeEnvio(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);
			// E_MovMarcacion mp =
			// movMarcacionController.getLastMarcacionByEstadoMarcacion(1);
			if (marcacionesPendientesEnvio == null && m != null) {
				// pendiente de fin
				if (marcacionesPendientesEnvio == null && !verificarPendientes()) {
					registrarFinMarcacion(m);
				} else {
					Toast.makeText(this, "Debes enviar primero Pendientes para finalizar el día!", Toast.LENGTH_SHORT).show();
				}
			} else if (marcacionesPendientesEnvio != null && m == null) {
				// System.out.println("PendientesActivity marcacion fin guardado");
				enviarFinMarcacion();
			} else if (marcacionesPendientesEnvio != null && m != null) {
				// System.out.println("PendientesActivity marcacion fin guardado y marcacion pendiente de fin");
				registrarFinyEnviarPendientesFinMarcacion(m);

			}

		} else if (pos == 1 && listPendientes.get(pos).isPendiente()) {
			Intent i = new Intent(PendientesActivity.this, EstadosPendientesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(i);
		} else if (pos == 2 && listPendientes.get(pos).isPendiente()) {
			registroVisitaMotivoNovisita();

		} else if (pos == 3 && listPendientes.get(pos).isPendiente()) {
			registrarReportesPendientes();
		}
	}

	private void registrarReportesPendientes() {
		Intent intent = new Intent(this, ReportesPendientesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void registroVisitaMotivoNovisita() {
		if (listPendientes != null && !listPendientes.get(3).isPendiente()) {
			if (visitasNoVisitasPendientes != null && !visitasNoVisitasPendientes.isEmpty()) {
				AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Enviar Visitas");
				alertDialog.setMessage("¿Desea enviar las Visitas pendientes?");
				alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						envioExitoso = true;
						if (visitasNoVisitasPendientes != null) {
							mostrarMensajeNoVisitas("Enviando visitas pendientes de envío");
							enviarSiguienteVisita();
						}
						//
					}
				});
				alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// alertDialog.hide();
					}
				});
				alertDialog.show();
			} else {
				if (visitasPendientes != null) {
					AlertDialog alertDialog = new AlertDialog.Builder(this).create();
					alertDialog.setTitle("Enviar Visitas");
					alertDialog.setMessage("¿Desea enviar las Visitas pendientes?");
					alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							envioExitoso = true;
							if (visitasPendientes != null) {
								mostrarMensajeNoVisitas("Enviando visitas pendientes");
								eliminarVisitasSinTrabajar();
							}
						}
					});
					alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// alertDialog.hide();
						}
					});
					alertDialog.show();
				}
			}
		} else {
			Toast.makeText(this, "Antes de enviar las visitas, debe enviar los reportes pendietnes", Toast.LENGTH_SHORT).show();

		}
	}

	public void mostrarMensajeVisitas(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(PendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(visitasPendientes.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
			// indicadorProgreso = ProgressDialog.show(
			// ReportesPendientesActivity.this, "", msg, true);
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}
	}

	public void mostrarMensajeNoVisitas(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(PendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(visitasNoVisitasPendientes.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
			// indicadorProgreso = ProgressDialog.show(
			// ReportesPendientesActivity.this, "", msg, true);
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}
	}

	public void mostrarMensajeMarcaciones(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(PendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(marcacionesPendientesEnvio.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
			// indicadorProgreso = ProgressDialog.show(
			// ReportesPendientesActivity.this, "", msg, true);
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}
	}
	
	public void eliminarVisitasSinTrabajar(){
		if (!visitasPendientes.isEmpty()) {
			int tam = visitasPendientes.size();
			for (E_TBL_MOV_REGISTROVISITA visita : visitasPendientes) {
				rVController.borrarVisita(visita.getId());
				tam--;
				if (indicadorProgreso != null) {
					indicadorProgreso.setProgress(indicadorProgreso.getMax() - tam);
				}
			}
			Toast.makeText(this, "Se finalizaron todas las visitas", Toast.LENGTH_SHORT).show();
		}
		if (indicadorProgreso != null){
			indicadorProgreso.dismiss();
		}
		Intent nombre = new Intent(PendientesActivity.this, MainMenu.class);
		nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nombre);
		
	}

	public void enviarSiguienteVisita() {
		if (!visitasNoVisitasPendientes.isEmpty()) {
			E_TBL_MOV_REGISTROVISITA visita = visitasNoVisitasPendientes.get(visitasNoVisitasPendientes.size() - 1);
			enviarVisitasNoVisita(visita);
			if (visita.getIdmotivoNoVisita() == -1)
				enviarNoVisitaBodega(movNoVisitaBodegaController.getByIdVisita(visita.getId()));
		} else {
			if (envioExitoso) {
				Toast.makeText(this, "Visitas pendientes enviadas de forma satisfactoria", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Ocurrió un error al enviar las visitas", Toast.LENGTH_SHORT).show();
			}
			// finish();
		}
	}

	private void enviarNoVisitaBodega(E_Tbl_Mov_RegistroBodega regNoVisitaBodega) {
		// TODO Auto-generated method stub
		conexion = Conexion.getInstance(this);
		conexion.setComListener(this, PendientesActivity.this);
		E_Usuario e_UsuarioMarcacion = DatosManager.getInstancia().getUsuario();
		regNoVisitaBodega.setDetalle(movNoVisitaBodegaController.getDetalle(regNoVisitaBodega.getId()));
		TblPuntoGPS puntoGps = getPuntoGps(regNoVisitaBodega.getIdPuntoGPS());

		E_RegistrarMotivoColgateBodega_Request e_NoVisitaBodegaRequest = new E_RegistrarMotivoColgateBodega_Request(regNoVisitaBodega, e_UsuarioMarcacion, puntoGps);
		conexion.registrarNoVistaBodega(e_NoVisitaBodegaRequest);
	}

	public void enviarSiguienteMarcacion() {
		if (!marcacionesPendientesEnvio.isEmpty()) {
			enviarMarcacion(marcacionesPendientesEnvio.get(marcacionesPendientesEnvio.size() - 1));
		} else {
			if (envioExitoso) {
				Toast.makeText(this, "Estados pendientes enviados de forma satisfactoria", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Ocurrió un error al enviar los estados", Toast.LENGTH_SHORT).show();
			}
			// finish();
		}
	}

	public void enviarMarcacion(E_MovMarcacion movMarcacion) {
		// esperandoRespuesta = true;
		conexion = Conexion.getInstance(this);
		conexion.setComListener(this, PendientesActivity.this);
		E_Usuario e_UsuarioMarcacion = DatosManager.getInstancia().getUsuario();
		TblPuntoGPS puntoGpsInicial = getPuntoGps(movMarcacion.getIdPunto_inicio());
		// java.util.Date date = new java.util.Date();
		// puntoGpsInicial.setFecha(new Date(date.getTime()));
		TblPuntoGPS puntoGpsFinal = null;
		if (movMarcacion.getIdPunto_fin() != 0) {
			puntoGpsFinal = getPuntoGps(movMarcacion.getIdPunto_fin());
		}
		E_MarcacionRequest e_MarcacionRequest = new E_MarcacionRequest(movMarcacion, e_UsuarioMarcacion, puntoGpsInicial, puntoGpsFinal);
		conexion.registrarMarcacion(e_MarcacionRequest);
	}

	public void enviarVisitasNoVisita(E_TBL_MOV_REGISTROVISITA visita) {
		conexion = Conexion.getInstance(this);
		conexion.setComListener(this, PendientesActivity.this);
		E_Usuario e_UsuarioMarcacion = DatosManager.getInstancia().getUsuario();
		TblPuntoGPS puntoGpsInicial = getPuntoGps(visita.getIdPuntoGPSInicio());
		TblPuntoGPS puntoGpsFinal = null;
		if (visita.getIdPuntoGPSFin() != 0) {
			puntoGpsFinal = getPuntoGps(visita.getIdPuntoGPSFin());
		}
		int id_foto = visita.getIdFoto();
		E_tbl_mov_fotos e_foto = new E_tbl_mov_fotosController(db).getById(id_foto);
		E_VisitaRequest e_VisitaRequest = new E_VisitaRequest(visita, e_UsuarioMarcacion, puntoGpsInicial, puntoGpsFinal, e_foto);
		conexion.registrarVistaPuntoVenta(e_VisitaRequest);
	}

	private TblPuntoGPS getPuntoGps(int idPunto) {
		if (puntoGPSController == null) {
			puntoGPSController = new TblPuntoGPSController(db);
		}
		TblPuntoGPS puntoGps = puntoGPSController.getPuntoById(idPunto);
		return puntoGps;
	}

	public void mostrarProgress(int idString) {
		indicadorProgreso = new ProgressDialog(new ContextThemeWrapper(PendientesActivity.this, R.style.Alertas));
		indicadorProgreso.setCancelable(false);
		indicadorProgreso.setMessage(getString(idString));
		indicadorProgreso.show();
	}

	public void ocultarProgress() {
		indicadorProgreso.dismiss();
	}

	public void ocultarAlert() {
		alert.dismiss();
	}

	public void registrarFinMarcacion(E_MovMarcacion m) {
		final E_MovMarcacion finalM = m;
		DatosManager.getInstancia().setMarcacion(m);
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar Fin");
		alertDialog.setMessage("¿Desea registrar su fin de día?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				locationHandler.setMovMarcacion(DatosManager.getInstancia().getMarcacion());
				locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL, handler);
				locationHandler.crearFin(finalM);
				Intent nombre1 = new Intent(PendientesActivity.this, MainMenu.class);
				nombre1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre1);
			}
		});

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}

	public void registrarFinyEnviarPendientesFinMarcacion(E_MovMarcacion m) {
		final E_MovMarcacion finalM = m;
		DatosManager.getInstancia().setMarcacion(m);
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar Fin");
		alertDialog.setMessage("¿Desea registrar su fin de día y enviar el fin de día que quedó pendiente de las marcaciones?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				crearFin(finalM);

				envioExitoso = true;
				if (marcacionesPendientesEnvio != null) {
					mostrarMensajeMarcaciones("Enviando marcaciones pendientes de envío de fin");
					enviarSiguienteMarcacion();
				}

			}
		});

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}

	public void crearFin(E_MovMarcacion movMarcacion) {
		puntoGPS = GPSManager.getManager().getPuntoGPS(db, PendientesActivity.this, true);
		int id = puntoGPS.getId();
		movMarcacion.setIdUsuario(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		movMarcacion.setIdPunto_fin(id);
		movMarcacion.setEstado(3);
		movMarcacionController.edit(movMarcacion);
		Toast.makeText(PendientesActivity.this, "Fin registrado con exito!", Toast.LENGTH_SHORT).show();
		marcacionesPendientesEnvio.add(movMarcacion);
	}

	public void enviarFinMarcacion() {

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Enviar Fin");
		alertDialog.setMessage("¿Desea enviar el fin de día que quedó pendiente de las marcaciones?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				envioExitoso = true;
				if (marcacionesPendientesEnvio != null) {
					mostrarMensajeMarcaciones("Enviando marcaciones pendientes de envío de fin");
					enviarSiguienteMarcacion();
				}
			}
		});

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}

	public void actualizarLocalizacion() {
		loadDatos();
		init();
	}

	public boolean verificarPendientes() {
		boolean hayPendMarc;
		boolean hayPendVisita;
		boolean hayPendReportes;

		hayPendMarc = movMarcacionController.hayPendientesMovMarcacion(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPendVisita = rVController.hayVisitasPendientes(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPendReportes = movReporteController.isReportesPendientes(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		if (hayPendMarc || hayPendVisita || hayPendReportes) {
			return true;
		}
		return false;
	}

	@Override
	public void respuestaEnvio(int cod, String msg) {
		Log.i("PendientesActivity", "respuestaEnvio(int cod," + cod + "String msg " + msg + ") LP");
		DatosManager datosManager = DatosManager.getInstancia();
		E_TBL_MOV_REGISTROVISITA visita = datosManager.getVisita_envio();
		E_MovMarcacion marcacion = datosManager.getMarcacion();
		if (visitasNoVisitasPendientes != null) {
			if (!visitasNoVisitasPendientes.isEmpty()) {
				visita = visitasNoVisitasPendientes.get(visitasNoVisitasPendientes.size() - 1);
				visitasNoVisitasPendientes.remove(visitasNoVisitasPendientes.size() - 1);
			}
		} else if (marcacionesPendientesEnvio != null) {
			if (!marcacionesPendientesEnvio.isEmpty()) {
				marcacion = marcacionesPendientesEnvio.get(marcacionesPendientesEnvio.size() - 1);
				marcacionesPendientesEnvio.remove(marcacionesPendientesEnvio.size() - 1);
			}
		}
		String msgResultado = "";
		Message ms = new Message();

		switch (cod) {
		case -2:
			msgResultado = "La conexión a Internet es baja, por favor enviar su información por pendientes";
			envioExitoso = false;
			ms.arg1 = -2;
			break;
		case -1:
			msgResultado = "Ocurrió un error con el servicio de envío";
			ms.arg1 = -1;
			envioExitoso = false;
			break;
		case 0:
		case 1:
			if (visitasNoVisitasPendientes != null) {
				if (visita != null) {
					if (visita.getEstado() <= 3) {
						visita.setEstado(visita.getEstado() + 1);
						MovRegistroVisitaController movController = new MovRegistroVisitaController(db);
						movController.edit(visita);
					}
				}
			} else if (marcacionesPendientesEnvio != null) {
				if (marcacion != null) {
					if (marcacion.getEstado() <= 3) {
						marcacion.setEstado(marcacion.getEstado() + 1);
						MovMarcacionController movController = new MovMarcacionController(db);
						movController.edit(marcacion);
					}
				}
			}
			break;
		default:
			msgResultado = "Ocurrió un error en el envío";
			ms.arg1 = 2;
			envioExitoso = false;
			break;
		}

		if (indicadorProgreso != null) {
			if (visitasNoVisitasPendientes != null) {
				indicadorProgreso.setProgress(indicadorProgreso.getMax() - visitasNoVisitasPendientes.size());
			} else if (marcacionesPendientesEnvio != null) {
				indicadorProgreso.setProgress(indicadorProgreso.getMax() - marcacionesPendientesEnvio.size());
			}
		}
		// Toast.makeText(this, msgResultado, Toast.LENGTH_SHORT);
		if (visitasNoVisitasPendientes != null) {

			if (visitasNoVisitasPendientes.isEmpty()) {
				if (ms.arg1 != -2) {
					ms.arg1 = 3;
					msgResultado = "Se ha finalizado el envío de visitas: ";
					if (envioExitoso) {
						msgResultado += "Todas las visitas se enviaron de forma satisfactoria";
					} else {
						msgResultado += "Algunas visitas no fueron enviadas";
					}
				} else {
					Log.i("Fijando argumento", "Fijando arg mesage " + ms.arg1);
					ms.arg1 = 1;
				}

			} else {
				ms.arg1 = 1;
			}
		} else if (marcacionesPendientesEnvio != null) {
			if (marcacionesPendientesEnvio.isEmpty()) {
				if (ms.arg1 != -2) {
					ms.arg1 = 3;
					msgResultado = "Se ha finalizado el envío de marcaciones: ";
					if (envioExitoso) {
						msgResultado += "Todas las marcaciones se enviaron de forma satisfactoria";
					} else {
						msgResultado += "Algunas marcaciones no fueron enviadas";
					}
				} else {
					Log.i("Fijando argumento", "Fijando arg mesage " + ms.arg1);
					ms.arg1 = 1;
				}
			} else {
				ms.arg1 = 1;
			}
		}
		ms.obj = msgResultado;
		if (handler != null) {
			Log.i("PendientesActivity", "Handler en clase: " + handler.getClass().toString());
			handler.sendMessage(ms);
		} else {
			switch (ms.arg1) {
			case -1:
			case 1:
			case 2:
				if (visitasNoVisitasPendientes != null) {
					if (!visitasNoVisitasPendientes.isEmpty()) {
						enviarSiguienteVisita();
					}
				} else if (marcacionesPendientesEnvio != null) {
					if (!marcacionesPendientesEnvio.isEmpty()) {
						enviarSiguienteMarcacion();
					}
				}
			case -2:
			case 3:
				if (indicadorProgreso != null) {
					indicadorProgreso.dismiss();
				}
				Intent nombre = new Intent(PendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;
			default:
				if (indicadorProgreso != null) {
					indicadorProgreso.dismiss();
				}
				Intent menu = new Intent(PendientesActivity.this, MainMenu.class);
				menu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(menu);
				break;
			}
			if (ms.obj != null && !ms.obj.toString().isEmpty()) {
				Toast.makeText(PendientesActivity.this, (String) ms.obj, Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		// TODO Auto-generated method stub
		Log.i("PendientesActivity", "Llego posicion actualizada de GPS con peticion: " + peticion.getAccion() + "\n SIN MANEJO");
	}
}