package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import org.seratic.location.IGPSManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.EstadosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_Estados;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_Subestados;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.comunicacion.Conexion;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.gui.adapters.PendienteAdapter;
import com.org.seratic.lucky.gui.vo.PendienteVO;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;

import com.org.seratic.lucky.model.E_MarcacionRequest;

public class EstadosPendientesActivity extends Activity implements IComunicacionListener, IGPSManager {

	public static final int ENVIAR_PENDIENTES = 1;
	private SQLiteDatabase db;
	ArrayList<PendienteVO> listPendientes;
	AlertDialog alert;
	private MovMarcacionController movMarcacionController;
	List<E_MovMarcacion> estadosPendientesFin;
	List<E_MovMarcacion> estadosPendientesEnvio;
	Conexion conexion;

	private TblPuntoGPSController puntoGPSController;

	boolean envioExitoso = true;
	ProgressDialog indicadorProgreso;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 != 4 || msg.arg1 != 5)
				if (msg.obj != null) {
					Toast.makeText(EstadosPendientesActivity.this, (String) msg.obj + " :" + msg.arg1, Toast.LENGTH_LONG).show();
				}

			switch (msg.arg1) {
			case -1:
			case 1:
			case 2:
				if (estadosPendientesFin != null) {
					if (!estadosPendientesFin.isEmpty()) {
						finalizaryEnviarSiguienteEstado();
					}
				} else if (estadosPendientesEnvio != null) {
					if (!estadosPendientesEnvio.isEmpty()) {
						enviarSiguienteEstado();
					}
				}
			case -2:
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				// System.out.println("handler case 3");
				Intent nombre = new Intent(EstadosPendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;
			case 4:
				mostrarMensajeEnvio("Enviando Estados pendientes de envío");
				break;
			case 5:
				mostrarMensajeFinyEnvio("Finalizando y enviando estados pendientes de fin");
				break;
			default:
				break;
			}

			// if (estadosPendientesFin != null) {
			// if (!estadosPendientesFin.isEmpty()) {
			// finalizaryEnviarSiguienteEstado();
			// }
			// }else if (estadosPendientesEnvio != null) {
			// if (!estadosPendientesEnvio.isEmpty()) {
			// enviarSiguienteEstado();
			// }
			// }
			// Toast.makeText(EstadosPendientesActivity.this, (String) msg.obj,
			// Toast.LENGTH_LONG).show();
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
				Log.i("EstadosPendientes", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		movMarcacionController = new MovMarcacionController(db);

		loadDatos();
		init();

	}

	public void loadDatos() {
		estadosPendientesFin = movMarcacionController.obtenerEstadosPendientes(MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO, MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO);
		estadosPendientesEnvio = movMarcacionController.obtenerEstadosPendientes(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO, 0);

		MovMarcacionController mController = new MovMarcacionController(db);
		EstadosController eController = new EstadosController(db);

		listPendientes = new ArrayList<PendienteVO>();

		List<Entity> estados = eController.getAll();
		if (estados != null) {
			for (Entity estado : estados) {
				E_Estados e = (E_Estados) estado;

				if (e.getId() != 1) { // Por que son de marcación
					List<Entity> subestados = e.getSubestados();
					if (subestados != null && !subestados.isEmpty()) {
						for (Entity subestado : subestados) {
							E_Subestados se = (E_Subestados) subestado;
							loadPendientes(e.getDescripcion() + " - " + se.getDescripcion(), e.getId(), se.getId(), mController);
						}
					} else {
						loadPendientes(e.getDescripcion(), e.getId(), 0, mController);
					}
				}
			}
		}
	}

	public void loadPendientes(String nameEstado, int codEstado, int codSubestado, MovMarcacionController mController) {
		boolean hayPend1;
		boolean hayPend2;
		boolean hayPend3;

		hayPend1 = mController.isEstadoSubestadoPendiente(codEstado, codSubestado, MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO);
		hayPend2 = mController.isEstadoSubestadoPendiente(codEstado, codSubestado, MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO);
		hayPend3 = mController.isEstadoSubestadoPendiente(codEstado, codSubestado, MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);

		PendienteVO p;
		p = new PendienteVO(nameEstado, hayPend1 || hayPend2 || hayPend3, hayPend1 || hayPend2);
		listPendientes.add(p);
	}

	public void init() {
		TextView txtTit = (TextView) findViewById(R.id.pendientes_titulo);
		txtTit.setText(R.string.pendientes_tit_estados_pend);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_pendientes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// *****CASO VISITA***********
		case R.id.enviar_pendientes:
			if (estadosPendientesFin != null) {
				mostrarAlertFinYEnvio("Desea finalizar y enviar los estados pendientes de fin?");
			} else if (estadosPendientesEnvio != null) {
				mostrarAlertEnvio("Desea enviar los estados pendientes de envio?");
			}

			return true;
		}
		return false;
	}

	public void mostrarProgress(int idString) {
		indicadorProgreso = new ProgressDialog(new ContextThemeWrapper(EstadosPendientesActivity.this, R.style.Alertas));
		indicadorProgreso.setCancelable(false);
		indicadorProgreso.setMessage(getString(idString));
		indicadorProgreso.show();
	}

	public void ocultarProgress() {
		indicadorProgreso.dismiss();
	}

	public void mostrarAlertEnvio(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg);
		builder.setPositiveButton("Si", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// mostrarMensajeEnvio("Enviando estados pendientes de envio");
				// enviarSiguienteEstado();
				Message ms = handler.obtainMessage();
				ms.arg1 = 4;
				handler.sendMessage(ms);
				Thread t = new Thread() {
					public void run() {
						Looper.prepare();
						envioExitoso = true;
						if (estadosPendientesEnvio != null) {
							enviarSiguienteEstado();
						}
					}
				};
				t.start();
			}
		});
		builder.setNegativeButton("No", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ocultarAlert();
			}
		});
		alert = builder.create();
		alert.show();
	}

	public void mostrarAlertFinYEnvio(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg);
		builder.setPositiveButton("Si", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// mostrarMensajeFinyEnvio("Finalizando y enviando estados pendientes de fin");
				// finalizaryEnviarSiguienteEstado();
				Message ms = handler.obtainMessage();
				ms.arg1 = 5;
				handler.sendMessage(ms);
				Thread t = new Thread() {
					public void run() {
						Looper.prepare();
						envioExitoso = true;
						if (estadosPendientesFin != null) {
							finalizaryEnviarSiguienteEstado();
						}
					}
				};
				t.start();
			}
		});
		builder.setNegativeButton("No", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ocultarAlert();
			}
		});
		alert = builder.create();
		alert.show();
	}

	public void ocultarAlert() {
		alert.dismiss();
	}

	public void mostrarMensajeEnvio(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(EstadosPendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(estadosPendientesEnvio.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}
	}

	public void mostrarMensajeFinyEnvio(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(EstadosPendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(estadosPendientesFin.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}
	}

	public void enviarSiguienteEstado() {
		if (!estadosPendientesEnvio.isEmpty()) {
			enviarEstados(estadosPendientesEnvio.get(estadosPendientesEnvio.size() - 1));
		} else {
			if (envioExitoso) {
				Toast.makeText(this, "Estados pendientes enviados de forma satisfactoria", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Ocurrió un error al enviar los estados", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void finalizaryEnviarSiguienteEstado() {
		if (!estadosPendientesFin.isEmpty()) {
			finalizaryenviarEstados(estadosPendientesFin.get(estadosPendientesFin.size() - 1));
		} else {
			if (envioExitoso) {
				Toast.makeText(this, "Estados pendientes de fin finalizados y enviados de forma satisfactoria", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Ocurrió un error al finalizar y enviar los estados", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void enviarEstados(E_MovMarcacion movMarcacion) {

		conexion = Conexion.getInstance(this);
		conexion.setComListener(this, EstadosPendientesActivity.this);
		E_Usuario e_UsuarioMarcacion = DatosManager.getInstancia().getUsuario();
		TblPuntoGPS puntoGpsInicial = getPuntoGps(movMarcacion.getIdPunto_inicio());
		TblPuntoGPS puntoGpsFinal = null;
		if (movMarcacion.getIdPunto_fin() != 0) {
			puntoGpsFinal = getPuntoGps(movMarcacion.getIdPunto_fin());
		}
		E_MarcacionRequest e_MarcacionRequest = new E_MarcacionRequest(movMarcacion, e_UsuarioMarcacion, puntoGpsInicial, puntoGpsFinal);
		conexion.registrarMarcacion(e_MarcacionRequest);
	}

	public void finalizaryenviarEstados(E_MovMarcacion movMarcacion) {
		movMarcacion.setIdUsuario(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));

		movMarcacion.setIdPunto_fin(GPSManager.getManager().getPuntoGPS(db, this, true).getId());

		movMarcacion.setEstado(3);
		MovMarcacionController movController = new MovMarcacionController(db);
		movController.edit(movMarcacion);
		enviarEstados(movMarcacion);
	}

	private TblPuntoGPS getPuntoGps(int idPunto) {
		if (puntoGPSController == null) {
			puntoGPSController = new TblPuntoGPSController(db);
		}
		TblPuntoGPS puntoGps = puntoGPSController.getPuntoById(idPunto);
		return puntoGps;
	}

	@Override
	public void respuestaEnvio(int cod, String msg) {
		Log.i("EstadosPendientes", "respuestaEnvio(int cod," + cod + "String msg " + msg + ") LR");
		DatosManager datosManager = DatosManager.getInstancia();
		E_MovMarcacion marcacion = datosManager.getMarcacion();
		if (estadosPendientesFin != null) {
			if (!estadosPendientesFin.isEmpty()) {
				marcacion = estadosPendientesFin.get(estadosPendientesFin.size() - 1);
				estadosPendientesFin.remove(estadosPendientesFin.size() - 1);
			}
		} else if (estadosPendientesEnvio != null) {
			if (!estadosPendientesEnvio.isEmpty()) {
				marcacion = estadosPendientesEnvio.get(estadosPendientesEnvio.size() - 1);
				estadosPendientesEnvio.remove(estadosPendientesEnvio.size() - 1);
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
			msgResultado = "Ocurrió un error con el servicio al enviar el estado " + marcacion.getId();
			ms.arg1 = -1;
			envioExitoso = false;
			break;
		case 0:
		case 1:

			if (marcacion != null) {
				if (marcacion.getEstado() == 3) {
					marcacion.setEstado(marcacion.getEstado() + 1);
					MovMarcacionController movController = new MovMarcacionController(db);
					movController.edit(marcacion);
				}
				msgResultado = "El estado " + marcacion.getId() + " fue enviado satisfactoriamente";
				ms.arg1 = 1;
			}

			break;
		default:
			msgResultado = "La conexión a Internet es baja, por favor enviar su información por pendientes";
			ms.arg1 = 2;
			envioExitoso = false;
			break;
		}

		if (estadosPendientesFin != null) {
			indicadorProgreso.setProgress(indicadorProgreso.getMax() - estadosPendientesFin.size());
		} else if (estadosPendientesEnvio != null) {
			indicadorProgreso.setProgress(indicadorProgreso.getMax() - estadosPendientesEnvio.size());
		}
		if (estadosPendientesFin != null) {
			if (estadosPendientesFin.isEmpty()) {
				if (ms.arg1 != -2) {
					ms.arg1 = 3;
					msgResultado = "Se ha finalizado el envío de estados: ";
					if (envioExitoso) {
						msgResultado += "Todos los estados se finalizaron y enviaron de forma satisfactoria";
					} else {
						msgResultado += "Algunos estados no fueron enviados";
					}
				} else {
					Log.i("Fijando argumento", "Fijando arg mesage " + ms.arg1);
					ms.arg1 = 1;
				}
			} else {
				ms.arg1 = 1;
			}
		} else if (estadosPendientesEnvio != null) {
			if (estadosPendientesEnvio.isEmpty()) {
				if (ms.arg1 != -2) {
					ms.arg1 = 3;
					msgResultado = "Se ha finalizado el envío de estados: ";
					if (envioExitoso) {
						msgResultado += "Todos los estados se enviaron de forma satisfactoria";
					} else {
						msgResultado += "Algunos estados no fueron enviados";
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
		handler.sendMessage(ms);
	}

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		// TODO Auto-generated method stub

	}

}