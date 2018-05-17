package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.seratic.location.MarcacionLocationHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.PuntoVentaController;
import com.org.seratic.lucky.accessData.control.TblMovRegPDVController;
import com.org.seratic.lucky.accessData.control.TblMstDepartamentoController;
import com.org.seratic.lucky.accessData.control.TblMstDistritoController;
import com.org.seratic.lucky.accessData.control.TblMstPoblacionController;
import com.org.seratic.lucky.accessData.control.TblMstProvinciaController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.accessData.entities.E_TblMovDistribRegPDV;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegPDV;
import com.org.seratic.lucky.accessData.entities.E_TblMstDepartamento;
import com.org.seratic.lucky.accessData.entities.E_TblMstDistrito;
import com.org.seratic.lucky.accessData.entities.E_TblMstPoblacion;
import com.org.seratic.lucky.accessData.entities.E_TblMstProvincia;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.comunicacion.JsonParser;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;
import com.org.seratic.lucky.model.E_Codigo_ITT_New;
import com.org.seratic.lucky.model.E_PuntoVenta;
import com.org.seratic.lucky.vo.PuntoventaVo;
import com.org.seratic.lucky.model.ReportesColgateBodega_RegistrarPDV_Mov_Request;

public class RegistroPDV_AAVVSanFernandoActivity extends Activity implements IComunicacionListener {

	public static final String COD_PAIS_PERU = "589";

	private static final String TAG = "RegistroPDVActivity";

	private SQLiteDatabase db;

	private TblMstDepartamentoController departamentoController;
	private TblMstDistritoController distritoController;
	private TblMstProvinciaController provinciaController;
	private TblMstPoblacionController poblacionController;

	private TblMovRegPDVController tblMovRegPDVController;
	// private TblMstPuntoVentaController tblMstPuntoVenta;
	// private TblMstDistribPuntoVentaController
	// tblMstDistribPuntoVentaController;
	// private TblMstDistribuidoraController distribuidoraController;

	private Spinner spMercado;
	private Spinner spTipoCliente;
	

	private static final int MIN_NOMBRE = 3;
	private static final int MIN_APELLIDO = 3;
	private static final int MIN_RAZON = 3;
	private static final int MIN_REFERENCIA = 3;
	
	private static final int ALERTA_GUARDAR = 0;
	public static final int ALERTA_IMPLEMENTAR = 1;
	private static final int ALERTA_ERROR = 2;

	MarcacionLocationHandler locationHandler;

	// Lista de distribuidoras que se asocian al PDV
	// Traidas desde RegistroPDVDistribuidoraActivity
	private ArrayList<Object> codigosITT;

	EditText txtnombre;
	EditText txtApellido;
	EditText txtRazon;
	EditText txtReferencia;

	InputFilter filter = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			String data = source.toString().substring(start, end);

			boolean isValid = data.matches("[A-Za-z 0-9.,/-]+");
			if (!isValid) {
				return "";
			}

			return null;
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_registro_pdv_aavv_san_fernando);

		txtnombre = (EditText) findViewById(R.id.etNombre);
		txtnombre.setFilters(new InputFilter[] { filter });
	

		txtApellido = (EditText) findViewById(R.id.etApellido);
		txtApellido.setFilters(new InputFilter[] { filter });

		txtRazon = (EditText) findViewById(R.id.etRazon);
		txtRazon.setFilters(new InputFilter[] { filter });

		txtReferencia = (EditText) findViewById(R.id.etReferencia);
		txtReferencia.setFilters(new InputFilter[] { filter });

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getReadableDatabase();
		DatosManager.getInstancia().setCodigosITT(null);
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Puntos de Venta", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
	
		// if (DatosManager.getInstancia().getPuntoVentaSeleccionado() == null)
		// {
		// MovRegistroVisitaController rVController=new
		// MovRegistroVisitaController(db);
		// E_TBL_MOV_REGISTROVISITA rv = rVController.getVisitaPendiente();
		// if (rv != null) {
		// System.out.println("rv Punto de Venta: " + rv.getIdPuntoVenta());
		// DatosManager.getInstancia().setVisita(rv);
		// DatosManager.getInstancia().setPuntoVentaSeleccionado(rVController.getPuntoVentaVisitaPendiente(rv));
		// System.out.println("Punto Venta: " +
		// DatosManager.getInstancia().getPuntoVentaSeleccionado().getRazonSocial());
		// }
		// }

		locationHandler = new MarcacionLocationHandler(db, RegistroPDV_AAVVSanFernandoActivity.this);
		// locationHandler.setActividad(MarcacionLocationHandler.ACTIVIDAD_NUEVO_PTO_VENTA);

		departamentoController = new TblMstDepartamentoController(db);
		distritoController = new TblMstDistritoController(db);
		provinciaController = new TblMstProvinciaController(db);
		poblacionController = new TblMstPoblacionController(db);
		tblMovRegPDVController = new TblMovRegPDVController(db);
		// tblMstDistribPuntoVentaController = new
		// TblMstDistribPuntoVentaController(db);
		// distribuidoraController = new TblMstDistribuidoraController(db);

		fillDepartamentosSpinner();
		fillPoblacionSpinner();
		fillTipoPoblacionSpinner();

		codigosITT = DatosManager.getInstancia().getCodigosITT();
		Log.i(TAG, "  -- Obtener distribuidoras regtivity");

		if (savedInstanceState != null) {
			txtnombre.setText(savedInstanceState.getString("nombre"));
			txtApellido.setText(savedInstanceState.getString("apellido"));
			txtRazon.setText(savedInstanceState.getString("razon"));
			txtReferencia.setText(savedInstanceState.getString("referencia"));
		}else{
			clearForm();
		}
	}

	public void guardar(View v) {
		if (validateForm()) {
			showDialog(ALERTA_GUARDAR);
		} else {

		}
	}

	public void agregar(View v) {
		codigosITT = DatosManager.getInstancia().getCodigosITT();
		Intent intent = new Intent(this, RegistrarPDVCodigoITTActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	private void fillPoblacionSpinner() {
		Spinner spPoblacion = (Spinner) findViewById(R.id.spTipoCliente);
		List<Entity> poblaciones = poblacionController.getAll();
		Log.d(TAG, "Polbacion cargados  : " + poblaciones.size());
		ArrayAdapter<Entity> poblacionAdapter = new ArrayAdapter<Entity>(this, android.R.layout.simple_spinner_item, poblaciones);
		poblacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spPoblacion.setAdapter(poblacionAdapter);
		spPoblacion.setSelection(0);
	}

	private void fillDepartamentosSpinner() {
		spMercado = (Spinner) findViewById(R.id.spMercado);
		List<Entity> mercados = departamentoController.getAll(COD_PAIS_PERU);
		Log.d(TAG, "Mercados cargados  : " + mercados.size());
		ArrayAdapter<Entity> departamentoAdapter = new ArrayAdapter<Entity>(this, android.R.layout.simple_spinner_item, mercados);
		departamentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spMercado.setAdapter(departamentoAdapter);
		spMercado.setSelection(0);

		spMercado.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				// your code here
			//	fillProvinciaSpinner();
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}
		});
	}




	private boolean guardarPDV() {
		boolean result = false;

		// 2. Se guarda el registro del pdv en la tabla TBL_MOV_REG_PDV
		// obteniendo las coordenadas
		// gps del punto de venta
		vo = fillE_TblMovRegPDV();
		codigosITT = DatosManager.getInstancia().getCodigosITT();
		TblPuntoGPS puntoGPS = GPSManager.getManager().getPuntoGPS(db, RegistroPDV_AAVVSanFernandoActivity.this, true);
		vo.setId_punto_gps(puntoGPS.getId());
		int id = tblMovRegPDVController.insert_reg_pdv(vo);
		if (id > 0) {
			if (codigosITT != null && !codigosITT.isEmpty()) {
				tblMovRegPDVController.borrar_distrib_pdv(id);
				for (int i = 0; i < codigosITT.size(); i++) {
					E_TBL_MOV_REP_COD_NEW_ITT cod = (E_TBL_MOV_REP_COD_NEW_ITT) codigosITT.get(i);
					E_TblMovDistribRegPDV distrib_pdv = new E_TblMovDistribRegPDV();
					distrib_pdv.setCod_distribuidora(cod.getId_distribuidora());
					distrib_pdv.setId_reg_pdv(id);
					distrib_pdv.setCod_itt(cod.getCodigo_ITT());
					int id_reg = tblMovRegPDVController.insert_distrib_pdv(distrib_pdv);
					Log.i("RegistroPDVActivity", "asociando distribuidora: " + distrib_pdv.getCod_distribuidora() + " con codigo itt: " + distrib_pdv.getCod_itt() + " al punto de venta registrado: " + distrib_pdv.getId_reg_pdv() + " el id de inserción: " + id_reg);
				}
			}

			// /if(distribuidoras != null && distribuidoras.size() > 0) {

			// 1. Se guardan las nuevas distribuidoras en la tabla
			// TBL_MOV_REG_DISTRIBUIDORA
			// con estado_envio = 1
			if (codigosITT == null)
				codigosITT = new ArrayList<Object>();
			//
			// for(DistribuidoraVO v : distribuidoras){
			// if(v.getEstado_envio() == -1){
			// //Se cambio en la version 3
			// //tblMovRegDistribuidoraController.create(new
			// E_TblMovRegDistribuidora(0, v.getNom_distribuidora(), 1));
			// }
			// }

			// locationHandler.setDistribuidoras(distribuidoras);
			// locationHandler.setNuevoPtoVenta(vo);
			// 23 - 06 - 2012
			// locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_NUEVO_PTO_VENTA);

			// locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_NUEVO_PTO_VENTA);
			// locationHandler.sendEmptyMessage(0);

			// Para despues, cuadno se tiene el codigo del punto de venta y e
			// codigo de las distribuidoras nuevas
			// /tblMovDistribRegPDVController.create(new
			// E_TblMovDistribRegPDV("0", ));

			// 3. Se guarda la relacion de las distribuidora con el registro de
			// pdv en la tabla
			// TBL_MOV_DISTRIB_REG_PDV para distribuidoras existentes
			// tblMovDistribRegPDVController.create(new
			// E_TblMovDistribRegPDV("", 0));

			// 4. Se guarda la relacion de las distribuidora con el registro de
			// pdv en la tabla
			// TBL_MOV_REG_DISTRIB_REG_PDV para las distribuidoras nuevas

			//
			// } else {
			result = true;
			// }

		} else {
			result = false;

		}

		if (result) {
			// DatosManager.getInstancia().setDistribuidoras(null);
			// showDialog(ALERTA_IMPLEMENTAR);
		} else {
			showDialog(ALERTA_ERROR);
		}

		return result;
	}

	private E_TblMovRegPDV fillE_TblMovRegPDV() {
		E_TblMovRegPDV vo = new E_TblMovRegPDV();

		/** verificar valores */
		vo.setId_usuario(DatosManager.getInstancia().getUsuario().getIdUsuario());
		vo.setId_punto_venta("0");
		vo.setNom_cliente(txtnombre.getText().toString());
		// vo.setNom_cliente(((EditText)
		// findViewById(R.id.etNombre)).getText().toString());

		vo.setApellido_cliente(txtApellido.getText().toString());
		// vo.setApellido_cliente(((EditText)
		// findViewById(R.id.etApellido)).getText().toString());

		vo.setRazon_social(txtRazon.getText().toString());
		// vo.setRazon_social(((EditText)
		// findViewById(R.id.etRazon)).getText().toString());

		/** verificar valores */

		if (spTipoCliente.getSelectedItemPosition() == 0) {
			/*if (((EditText) findViewById(R.id.etDNI)).getText().toString().length() != 0) {
				vo.setTipo_doc("01");
				vo.setNum_doc(((EditText) findViewById(R.id.etDNI)).getText().toString());
			}*/
		} else {
			/*if (((EditText) findViewById(R.id.etRUC)).getText().toString().length() != 0) {
				vo.setTipo_doc("02");
				vo.setNum_doc(((EditText) findViewById(R.id.etRUC)).getText().toString());
			}*/
		}
		// vo.setDireccion(((EditText)
		// findViewById(R.id.etDireccion)).getText().toString());

		vo.setReferencia(txtReferencia.getText().toString());
		// vo.setReferencia(((EditText)
		// findViewById(R.id.etReferencia)).getText().toString());

		vo.setTelefono(((EditText) findViewById(R.id.etTelefono)).getText().toString());

		vo.setCod_pais(COD_PAIS_PERU);
		vo.setCod_departamento(((E_TblMstDepartamento) spMercado.getSelectedItem()).getCod_departamento());
				
		vo.setEstado_envio(1);
		return vo;
	}

	private void callNoImplementacion() {
		finish();
		Intent intent = new Intent(RegistroPDV_AAVVSanFernandoActivity.this, MotivoNoVisitaBodega.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	private void clearForm() {
		EditText etNombre = (EditText) findViewById(R.id.etNombre);
		EditText etApellido = (EditText) findViewById(R.id.etApellido);
		EditText etRazon = (EditText) findViewById(R.id.etRazon);
		EditText etReferencia = (EditText) findViewById(R.id.etReferencia);
		
		etNombre.setText("");
		etApellido.setText("");
		etRazon.setText("");
		etReferencia.setText("");
		
		spMercado.setSelection(0);
	}

	private boolean validateForm() {
		boolean allDone = true;

		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

		ArrayList<EditText> fields = new ArrayList<EditText>();

		// EditText etNombre = (EditText) findViewById(R.id.etNombre);
		// EditText etApellido = (EditText) findViewById(R.id.etApellido);
		// EditText etRazon = (EditText) findViewById(R.id.etRazon);
		// EditText etDireccion = (EditText) findViewById(R.id.etDireccion);
		// EditText etReferencia = (EditText) findViewById(R.id.etReferencia);
		

		fields.add(txtnombre);
		fields.add(txtApellido);
		fields.add(txtRazon);
		fields.add(txtReferencia);
		
		for (EditText editText : fields) {
			if (editText.getText().toString().trim().equals("")) {
				editText.startAnimation(shake);
				allDone = false;
			}
		}

		if (!allDone) {
			Toast toast = Toast.makeText(getApplicationContext(), R.string.errorEmptyFields, Toast.LENGTH_SHORT);
			toast.show();
			return false;
		} else {
			Toast toastError = null;

			if (txtnombre.getText().toString().length() >= MIN_NOMBRE) {
				if (txtApellido.getText().toString().length() >= MIN_APELLIDO) {
					if (txtRazon.getText().toString().length() >= MIN_RAZON) {

						if (spTipoCliente.getSelectedItemPosition() == 0) {
									if (txtReferencia.getText().toString().length() >= MIN_REFERENCIA) {
										return true;
									} else {
										txtReferencia.requestFocus();
										txtReferencia.startAnimation(shake);
										toastError = Toast.makeText(getApplicationContext(), getString(R.string.errorMinLenght) + " " + MIN_REFERENCIA, Toast.LENGTH_SHORT);
										toastError.show();
										return false;
									}
								
						} else if (spTipoCliente.getSelectedItemPosition() == 1) {
							// if (etRUC.getText().toString().length() == 0 ||
							// etRUC.getText().toString().length() >= MIN_RUC)
									if (txtReferencia.getText().toString().length() >= MIN_REFERENCIA) {
										return true;
									} else {
										txtReferencia.requestFocus();
										txtReferencia.startAnimation(shake);
										toastError = Toast.makeText(getApplicationContext(), getString(R.string.errorMinLenght) + " " + MIN_REFERENCIA, Toast.LENGTH_SHORT);
										toastError.show();
										return false;
									}
								
							
						} else {
							return false;
						}

						// if (etDNI.getText().toString().length() == 0 ||
						// etDNI.getText().toString().length() >= MIN_DNI) {
						// if (etRUC.getText().toString().length() == 0 ||
						// etRUC.getText().toString().length() >= MIN_RUC) {
						// if (etDNI.getText().toString().length()== 0 &&
						// etRUC.getText().toString().length() == 0){
						// Toast toast =
						// Toast.makeText(getApplicationContext(),R.string.errorEmptyDNIRUC,
						// Toast.LENGTH_SHORT);
						// toast.show();
						// return false;
						// } else {
						// if (etDireccion.getText().toString().length() >=
						// MIN_DIRECCION) {
						// if (etReferencia.getText().toString().length() >=
						// MIN_REFERENCIA) {
						// if (etTelefono.getText().toString().length() >=
						// MIN_TELEFONO) {
						// return true;
						// } else {
						// etTelefono.requestFocus();
						// etTelefono.startAnimation(shake);
						// toastError =
						// Toast.makeText(getApplicationContext(),getString(R.string.errorMinLenght)+
						// MIN_TELEFONO,Toast.LENGTH_SHORT);
						// toastError.show();
						// return false;
						// }
						// } else {
						// etReferencia.requestFocus();
						// etReferencia.startAnimation(shake);
						// toastError =
						// Toast.makeText(getApplicationContext(),getString(R.string.errorMinLenght)+
						// MIN_REFERENCIA,Toast.LENGTH_SHORT);
						// toastError.show();
						// return false;
						// }
						// } else {
						// etDireccion.requestFocus();
						// etDireccion.startAnimation(shake);
						// toastError =
						// Toast.makeText(getApplicationContext(),getString(R.string.errorMinLenght)+
						// MIN_DIRECCION,Toast.LENGTH_SHORT);
						// toastError.show();
						// return false;
						// }
						// }
						// } else {
						// etRUC.requestFocus();
						// etRUC.startAnimation(shake);
						// toastError =
						// Toast.makeText(getApplicationContext(),getString(R.string.errorMinLenght)+
						// MIN_RUC, Toast.LENGTH_SHORT);
						// toastError.show();
						// return false;
						// }
						// }
						// else {
						// etDNI.requestFocus();
						// etDNI.startAnimation(shake);
						// toastError =
						// Toast.makeText(getApplicationContext(),getString(R.string.errorMinLenght)+
						// MIN_DNI, Toast.LENGTH_SHORT);
						// toastError.show();
						// return false;
						// }

					} else {
						txtRazon.requestFocus();
						txtRazon.startAnimation(shake);
						toastError = Toast.makeText(getApplicationContext(), getString(R.string.errorMinLenght) + " " + MIN_RAZON, Toast.LENGTH_SHORT);
						toastError.show();
						return false;
					}
				} else {
					txtApellido.requestFocus();
					txtApellido.startAnimation(shake);
					toastError = Toast.makeText(getApplicationContext(), getString(R.string.errorMinLenght) + " " + MIN_APELLIDO, Toast.LENGTH_SHORT);
					toastError.show();
					return false;
				}
			} else {
				txtnombre.requestFocus();
				txtnombre.startAnimation(shake);
				toastError = Toast.makeText(getApplicationContext(), getString(R.string.errorMinLenght).concat(" " + MIN_NOMBRE), Toast.LENGTH_SHORT);
				toastError.show();
				return false;
			}

		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;

		switch (id) {
		case ALERTA_GUARDAR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.registroPDVAgregarAlert)).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					if (guardarPDV()) {
						mostrarMensaje("Enviando Punto de Venta.");
						locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_NUEVO_PTO_VENTA, handler);
						locationHandler.crearFinRegistro();
					}

					dialog.dismiss();
				}
			});
			dialog = builder.create();
			break;

		case ALERTA_IMPLEMENTAR:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

			builder2.setMessage(getString(R.string.registroPDVImplementarAlert)).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();

					callNoImplementacion();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					// TODO Codificar la respuesta positiva
					dialog.dismiss();
					finish();
					Intent intrep = new Intent(RegistroPDV_AAVVSanFernandoActivity.this, PuntoVentaSeleccion.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intrep);
				}
			});
			dialog = builder2.create();
			break;

		case ALERTA_ERROR:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

			builder3.setMessage(getString(R.string.registroPDVImplementarAlert)).setCancelable(true).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			dialog = builder3.create();
			break;
		}

		return dialog;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		Log.i(TAG, "SAVE INSTANCE");

		outState.putString("nombre", ((EditText) findViewById(R.id.etNombre)).getText().toString());
		outState.putString("apellido", ((EditText) findViewById(R.id.etApellido)).getText().toString());
		outState.putString("razon", ((EditText) findViewById(R.id.etRazon)).getText().toString());
		outState.putString("referencia", ((EditText) findViewById(R.id.etReferencia)).getText().toString());
		
		// DatosManager.getInstancia().setDistribuidoras(distribuidoras);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);

		((EditText) findViewById(R.id.etNombre)).setText(savedInstanceState.getString("nombre"));
		((EditText) findViewById(R.id.etApellido)).setText(savedInstanceState.getString("apellido"));
		((EditText) findViewById(R.id.etRazon)).setText(savedInstanceState.getString("razon"));
		((EditText) findViewById(R.id.etDNI)).setText(savedInstanceState.getString("dni"));
		((EditText) findViewById(R.id.etRUC)).setText(savedInstanceState.getString("ruc"));
		((EditText) findViewById(R.id.etDireccion)).setText(savedInstanceState.getString("direccion"));
		((EditText) findViewById(R.id.etReferencia)).setText(savedInstanceState.getString("referencia"));
		((EditText) findViewById(R.id.etTelefono)).setText(savedInstanceState.getString("telefono"));

		Log.i(TAG, "RESTORE INSTANCE");

		codigosITT = DatosManager.getInstancia().getCodigosITT();

	}

	// *******************************************************
	// *******************************************************

	private void fillTipoPoblacionSpinner() {

		spTipoCliente = (Spinner) findViewById(R.id.spTipoCliente);

		ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tipo_documento, android.R.layout.simple_spinner_item);
		adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spTipoCliente.setAdapter(adap);

		spTipoCliente.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				//tipo de cliente
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}
		});
	}

	public void actualizarLocalizacion() {
		Toast.makeText(getBaseContext(), "Punto de Venta guardado exitosamente", Toast.LENGTH_LONG).show();
	}

	// *******************************************************
	// *******************************************************

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 1:
				enviarNuevoPtoVenta();
				break;
			case 3:
				if (DatosManager.getInstancia().getPuntoVentaSeleccionado() != null) {
					DatosManager.getInstancia().setCodigosITT(null);
					finish();
					SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(RegistroPDV_AAVVSanFernandoActivity.this);
					db = aSQLiteDatabaseAdapter.getReadableDatabase();
					if (DatosManager.getInstancia().getUsuario() == null) {
						DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
						if (instanciaDM == null) {
							Log.i("Puntos de Venta", "Instancia recuperada Null");
							DatosManager.getInstancia().cargarDatos(db);
						} else {
							DatosManager.setInstancia(instanciaDM);
						}
					}
					PuntoventaVo puntoVentaSelected = DatosManager.getInstancia().getPuntoVentaSeleccionado();
					Intent intrep = new Intent(RegistroPDV_AAVVSanFernandoActivity.this, PuntoVentaSeleccion.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intrep.putExtra("idPuntoVentaSeleccionado", puntoVentaSelected.getIdPtoVenta());
					DatosManager.getInstancia().setPuntoVentaSeleccionado(puntoVentaSelected);
					startActivity(intrep);
					// showDialog(ALERTA_IMPLEMENTAR);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "Ocurrio un error al registrar el punto de venta.", Toast.LENGTH_SHORT);
					toast.show();
				}
				break;
			default:
				Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}

		};
	};

	E_TblMovRegPDV vo;

	public void enviarNuevoPtoVenta() {
		Log.i("ListaDeReporte", "...enviarReporte");

		ArrayList<E_Codigo_ITT_New> e_Codigo_ITT_Distribuidora = new ArrayList<E_Codigo_ITT_New>();
		for (Object r : codigosITT) {// E_TBL_MOV_REP_COD_NEW_ITT
			e_Codigo_ITT_Distribuidora.add(new E_Codigo_ITT_New((E_TBL_MOV_REP_COD_NEW_ITT) r));
		}
		ReportesColgateBodega_RegistrarPDV_Mov_Request a = locationHandler.setRegistrarNuevoPtoVenta(vo, e_Codigo_ITT_Distribuidora);
		DatosManager datosManager = DatosManager.getInstancia();
		datosManager.enviarNuevaBodega_Mov(a, this, RegistroPDV_AAVVSanFernandoActivity.this);
	}

	ProgressDialog indicadorProgreso;

	@Override
//	public void respuestaEnvio(int cod, String msg) {
//		Log.i("RegistroPDV", "respuestaEnvio(int cod," + cod + "String msg " + msg + ") ");
//		// TODO Auto-generated method stub
//		switch (cod) {
//		case -2:
//			Message ms = new Message();
//			ms.arg1 = 4;
//			ms.obj = "Ocurrió un error: Revise su conexión a internet";
//			indicadorProgreso.dismiss();
//			handler.sendMessage(ms);
//			break;
//		case -1:
//			ms = new Message();
//			ms.arg1 = 4;
//			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
//			indicadorProgreso.dismiss();
//			handler.sendMessage(ms);
//			break;
//		case 0:
//			try {
//				Log.i("RegistroPDVActivity", "registro nuevo punto de venta");
//				List<E_PuntoVenta> puntosVenta = JsonParser.pVenta;
//				if (puntosVenta != null && !puntosVenta.isEmpty()) {
//					TblMovRegPDVController reg_pdv_controller = new TblMovRegPDVController(db);
//					List<E_TblMovRegPDV> reg_pdvs = reg_pdv_controller.getRegPDV();
//					if (reg_pdvs != null && !reg_pdvs.isEmpty()) {
//						for (int i = 0; i < reg_pdvs.size(); i++) {
//							E_PuntoVenta punto = puntosVenta.get(0);
//							if (((E_TblMovRegPDV) reg_pdvs.get(i)).getRazon_social().equalsIgnoreCase(punto.getB()) || ((E_TblMovRegPDV) reg_pdvs.get(i)).getDireccion().equalsIgnoreCase(punto.getC())) {
//								String id_punto_venta = punto.getA();
//								TblPuntoGPS punto_gps = new TblPuntoGPSController(db).getPuntoById(reg_pdvs.get(i).getId_punto_gps());
//								PuntoventaVo pdv = new PuntoventaVo();
//								pdv.setIdPtoVenta(punto.getA());
//								String razon_social = punto.getB()==null || punto.getB().isEmpty()?((E_TblMovRegPDV)reg_pdvs.get(i)).getRazon_social():punto.getB();
//								String direccion = punto.getC()==null || punto.getC().isEmpty()?((E_TblMovRegPDV)reg_pdvs.get(i)).getDireccion():punto.getC();
//								pdv.setRazonSocial(razon_social);
//								pdv.setDireccion(direccion);
//								pdv.setCodCadena(punto.getD());
//								pdv.setNomCadena(punto.getE());
//								pdv.setCodCanal(punto.getF());
//								pdv.setNomCanal(punto.getG());
//								pdv.setTipoMercado(punto.getH());
//								if (!punto.getJ().equalsIgnoreCase("0.0")) {
//									pdv.setLatitud(punto.getJ());
//								} else {
//									pdv.setLatitud(String.valueOf(punto_gps.getX()));
//								}
//								if (!punto.getI().equalsIgnoreCase("0.0")) {
//									pdv.setLongitud(punto.getI());
//								} else {
//									pdv.setLongitud(String.valueOf(punto_gps.getY()));
//								}
//								pdv.setCodFase(punto.getK());
//								pdv.setIdUsuario(reg_pdvs.get(i).getId_usuario());
//								pdv.setId(reg_pdvs.get(i).getId());
//								int id = new PuntoVentaController(db).insert_pdv(pdv);
//								Log.i("RegistroPDVActivity", "Respuesta envio pdv - actualizando datos con id: " + id);
//
//								reg_pdvs.get(i).setId_punto_venta(id_punto_venta);
//								reg_pdvs.get(i).setEstado_envio(2);
//								reg_pdv_controller.updateIdPDV(reg_pdvs.get(i), reg_pdvs.get(i).getId());
//
//								DatosManager.getInstancia().setPuntoVentaSeleccionado(pdv);
//
//								indicadorProgreso.dismiss();
//								ms = new Message();
//								ms.arg1 = 3;
//								ms.obj = msg;
//								handler.sendMessage(ms);
//								break;
//							}
//						}
//					}else{
//						ms = new Message();
//						ms.arg1 = 5;
//						ms.obj = "Punto de venta retornado no coincide con el punto de venta registrado";
//						indicadorProgreso.dismiss();
//						handler.sendMessage(ms);
//					}
//				} else {
//					ms = new Message();
//					ms.arg1 = 4;
//					ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
//					indicadorProgreso.dismiss();
//					handler.sendMessage(ms);
//				}
//
//			} catch (Exception ex) {
//				ms = new Message();
//				ms.arg1 = 4;
//				ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
//				indicadorProgreso.dismiss();
//				handler.sendMessage(ms);
//			}
//			break;
//		case 1:
//			ms = new Message();
//			ms.arg1 = 4;
//			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
//			indicadorProgreso.dismiss();
//			handler.sendMessage(ms);
//			break;
//		default:
//			ms = new Message();
//			ms.arg1 = 4;
//			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
//			indicadorProgreso.dismiss();
//			handler.sendMessage(ms);
//			break;
//		}
//	}
	
	
	public void respuestaEnvio(int cod, String msg) {
		Log.i("RegistroPDV", "respuestaEnvio(int cod," + cod + "String msg " + msg + ") ");
		// TODO Auto-generated method stub
		switch (cod) {
		case -2:
			Message ms = new Message();
			ms.arg1 = 4;
			ms.obj = "Ocurrió un error: Revise su conexión a internet";
			indicadorProgreso.dismiss();
			handler.sendMessage(ms);
			break;
		case -1:
			ms = new Message();
			ms.arg1 = 4;
			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
			indicadorProgreso.dismiss();
			handler.sendMessage(ms);
			break;
		case 0:
			try {
				Log.i("RegistroPDVActivity", "registro nuevo punto de venta");
				List<E_PuntoVenta> puntosVenta = JsonParser.pVenta;
				if (puntosVenta != null && !puntosVenta.isEmpty()) {
					TblMovRegPDVController reg_pdv_controller = new TblMovRegPDVController(db);
					List<E_TblMovRegPDV> reg_pdvs = reg_pdv_controller.getRegPDV();
					if (reg_pdvs != null && !reg_pdvs.isEmpty()) {
						for (int i = 0; i < reg_pdvs.size(); i++) {
							E_PuntoVenta punto = puntosVenta.get(0);
							Log.i("RegistroPDVActivity", "Punto de venta recuperado: ** cod= " + punto.getA() + " ** razon social = " + punto.getB() + " ** direccion = " + punto.getC());
							if (((E_TblMovRegPDV) reg_pdvs.get(i)).getRazon_social().equalsIgnoreCase(punto.getB()) || ((E_TblMovRegPDV) reg_pdvs.get(i)).getDireccion().equalsIgnoreCase(punto.getC())) {
								String id_punto_venta = punto.getA();
								TblPuntoGPS punto_gps = new TblPuntoGPSController(db).getPuntoById(reg_pdvs.get(i).getId_punto_gps());
								PuntoventaVo pdv = new PuntoventaVo();
								pdv.setIdPtoVenta(punto.getA());
								String razon_social = punto.getB() == null || punto.getB().isEmpty() ? ((E_TblMovRegPDV) reg_pdvs.get(i)).getRazon_social() : punto.getB();
								String direccion = punto.getC() == null || punto.getC().isEmpty() ? ((E_TblMovRegPDV) reg_pdvs.get(i)).getDireccion() : punto.getC();
								pdv.setRazonSocial(razon_social);
								pdv.setDireccion(direccion);
								pdv.setCodCadena(punto.getD());
								pdv.setNomCadena(punto.getE());
								pdv.setCodCanal(punto.getF());
								pdv.setNomCanal(punto.getG());
								pdv.setTipoMercado(punto.getH());
								if (!punto.getJ().equalsIgnoreCase("0.0")) {
									pdv.setLatitud(punto.getJ());
								} else {
									pdv.setLatitud(String.valueOf(punto_gps.getX()));
								}
								if (!punto.getI().equalsIgnoreCase("0.0")) {
									pdv.setLongitud(punto.getI());
								} else {
									pdv.setLongitud(String.valueOf(punto_gps.getY()));
								}
								pdv.setCodFase(punto.getK());
								pdv.setIdUsuario(reg_pdvs.get(i).getId_usuario());
								pdv.setId(reg_pdvs.get(i).getId());
								int id = new PuntoVentaController(db).insert_pdv(pdv);
								Log.i("RegistroPDVActivity", "Respuesta envio pdv - actualizando datos con id: " + id);

								reg_pdvs.get(i).setId_punto_venta(id_punto_venta);
								reg_pdvs.get(i).setEstado_envio(2);
								reg_pdv_controller.updateIdPDV(reg_pdvs.get(i), reg_pdvs.get(i).getId());

								DatosManager.getInstancia().setPuntoVentaSeleccionado(pdv);

								indicadorProgreso.dismiss();
								ms = new Message();
								ms.arg1 = 3;
								ms.obj = msg;
								handler.sendMessage(ms);
								break;
							} else {
								ms = new Message();
								ms.arg1 = 2;
								ms.obj = "Punto de venta registrado con éxito.  Para actualizar los datos vuelva a sincronizar.";
								indicadorProgreso.dismiss();
								handler.sendMessage(ms);
							}
						}
					} else {
						ms = new Message();
						ms.arg1 = 5;
						ms.obj = "Punto de venta retornado no coincide con el punto de venta registrado";
						indicadorProgreso.dismiss();
						handler.sendMessage(ms);
					}
				} else {
					ms = new Message();
					ms.arg1 = 4;
					ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
					indicadorProgreso.dismiss();
					handler.sendMessage(ms);
				}

			} catch (Exception ex) {
				ms = new Message();
				ms.arg1 = 4;
				ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
				indicadorProgreso.dismiss();
				handler.sendMessage(ms);
			}
			break;
		case 1:
			ms = new Message();
			ms.arg1 = 4;
			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
			indicadorProgreso.dismiss();
			handler.sendMessage(ms);
			break;
		default:
			ms = new Message();
			ms.arg1 = 4;
			ms.obj = "Ocurrió un error en el servicio al registrar el punto de venta";
			indicadorProgreso.dismiss();
			handler.sendMessage(ms);
			break;
		}
	}

	public void mostrarMensaje(String msg) {
		indicadorProgreso = ProgressDialog.show(RegistroPDV_AAVVSanFernandoActivity.this, "", msg, true);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		Intent intrep = new Intent(RegistroPDV_AAVVSanFernandoActivity.this, MenuBodegasActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intrep);
	}
}
