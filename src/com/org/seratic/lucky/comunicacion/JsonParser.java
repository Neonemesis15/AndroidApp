package com.org.seratic.lucky.comunicacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.org.seratic.lucky.accessData.control.DinamicController;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercado;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteAuditoria;
import com.org.seratic.lucky.accessData.entities.E_ReporteBloqueAzul;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetenciaDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteEncuesta;
import com.org.seratic.lucky.accessData.entities.E_ReporteEstrella;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteImpulso;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_ReporteQuiebre;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_ReporteSod;
import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegPDV;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegistroFotografico;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepPromocion;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega_Detalle;
import com.org.seratic.lucky.accessData.entities.E_TipoPrecioPDV;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_PROMOCION;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_VISCOMP;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.entity.CategoriaManager;
import com.org.seratic.lucky.manager.entity.OpcReporteManager;
import com.org.seratic.lucky.manager.entity.ProductoManager;
import com.org.seratic.lucky.manager.entity.ReporteManager;
import com.org.seratic.lucky.model.E_Categoria;
import com.org.seratic.lucky.model.E_Cluster;
import com.org.seratic.lucky.model.E_Estado;
import com.org.seratic.lucky.model.E_Motivo;
import com.org.seratic.lucky.model.E_NoVisita;
import com.org.seratic.lucky.model.E_Opc_Reporte;
import com.org.seratic.lucky.model.E_Producto;
import com.org.seratic.lucky.model.E_Promocion;
import com.org.seratic.lucky.model.E_PuntoVenta;
import com.org.seratic.lucky.model.E_Reporte;
import com.org.seratic.lucky.vo.ActividadVo;
import com.org.seratic.lucky.vo.CapacitacionVo;
import com.org.seratic.lucky.vo.CategoriaVo;
import com.org.seratic.lucky.vo.ClusterVo;
import com.org.seratic.lucky.vo.CompetidoraVo;
import com.org.seratic.lucky.vo.CondExhibidorVo;
import com.org.seratic.lucky.vo.CreditoVo;
import com.org.seratic.lucky.vo.DatosPresenciaVo;
import com.org.seratic.lucky.vo.DepartamentoVo;
import com.org.seratic.lucky.vo.DistribuidoraPuntoVentaVO;
import com.org.seratic.lucky.vo.DistribuidoraVo;
import com.org.seratic.lucky.vo.DistritoVo;
import com.org.seratic.lucky.vo.EstadoVo;
import com.org.seratic.lucky.vo.FamiliaVo;
import com.org.seratic.lucky.vo.FaseVo;
import com.org.seratic.lucky.vo.GrupoObjetivoVo;
import com.org.seratic.lucky.vo.IncidenciaVo;
import com.org.seratic.lucky.vo.MarcaVo;
import com.org.seratic.lucky.vo.MaterialApoyoVo;
import com.org.seratic.lucky.vo.MotivoObsVo;
import com.org.seratic.lucky.vo.MotivoReporteVo;
import com.org.seratic.lucky.vo.NovisitaVo;
import com.org.seratic.lucky.vo.ObjMarcaVo;
import com.org.seratic.lucky.vo.ObservacionVo;
import com.org.seratic.lucky.vo.OpcPedidoVo;
import com.org.seratic.lucky.vo.OpcReporteVo;
import com.org.seratic.lucky.vo.PDVEstrellaVo;
import com.org.seratic.lucky.vo.PaisVo;
import com.org.seratic.lucky.vo.PerfilVo;
import com.org.seratic.lucky.vo.PoblacionVo;
import com.org.seratic.lucky.vo.PosicionVo;
import com.org.seratic.lucky.vo.PresentacionVo;
import com.org.seratic.lucky.vo.ProductoVo;
import com.org.seratic.lucky.vo.PromocionVo;
import com.org.seratic.lucky.vo.ProvinciaVo;
import com.org.seratic.lucky.vo.ReporteVo;
import com.org.seratic.lucky.vo.SubcategoriaVo;
import com.org.seratic.lucky.vo.SubfamiliaVo;
import com.org.seratic.lucky.vo.SubmarcaVo;
import com.org.seratic.lucky.vo.TipoExhibicionVo;
import com.org.seratic.lucky.vo.TipoIncidenciaVo;
import com.org.seratic.lucky.vo.TipoMaterialVo;
import com.org.seratic.lucky.vo.TipoPerfilVo;
import com.org.seratic.lucky.vo.UbicacionVo;
import com.org.seratic.lucky.vo.subEstadoVo;

public class JsonParser {

	String parameters = "<string xmlns='http://schemas.microsoft.com/2003/10/Serialization/'>{'u':'psalas','p':'1234'}</string>";
	String finalparams;
	String urlConnection;
	HttpConnector c = new HttpConnector();
	String rawData;
	String data1;
	String finalData;
	String[] temp1;
	String[] temp2;
	String delimiter1 = "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">";
	String delimiter2 = "</string>";
	Context ctx;

	public static List<E_Estado> es = new ArrayList<E_Estado>();
	public static List<E_PuntoVenta> pVenta = new ArrayList<E_PuntoVenta>();
	public static List<E_NoVisita> noVisita = new ArrayList<E_NoVisita>();
	public static List<E_Motivo> motivo = new ArrayList<E_Motivo>();
	public static List<E_Reporte> reporte = new ArrayList<E_Reporte>();
	public static List<E_Opc_Reporte> opcReporte = new ArrayList<E_Opc_Reporte>();
	public static List<E_Producto> producto = new ArrayList<E_Producto>();
	public static List<E_Categoria> categoria = new ArrayList<E_Categoria>();
	public static List<E_Cluster> cluster = new ArrayList<E_Cluster>();
	public static List<E_Promocion> promocion = new ArrayList<E_Promocion>();

	ReporteManager reporteManager;
	OpcReporteManager opcReporteManager;
	ProductoManager productoMg;
	CategoriaManager categoriaMg;

	StringBuilder resSincronizacion;

	Gson g = new Gson();
	DinamicController dinamicController;

	public JsonParser(Context ctx) {
		this.ctx = ctx;
		reporteManager = new ReporteManager(ctx);
		opcReporteManager = new OpcReporteManager(ctx);
		productoMg = new ProductoManager(ctx);
		categoriaMg = new CategoriaManager(ctx);
		dinamicController = new DinamicController(ctx);
	}

	public E_Usuario readJsonLogin(String url) {
		urlConnection = url;
		jSonConnection(urlConnection);
		E_Usuario usuario = new E_Usuario();
		com.org.seratic.lucky.model.E_Usuario response = g.fromJson(finalData,
				com.org.seratic.lucky.model.E_Usuario.class);
		if (response.getE() == 0) {
			usuario.setUsrValido(true);
			usuario.setIdUsuario(response.getU().getI());
			usuario.setCod_canal(response.getU().getE());
			usuario.setCodigo_compania(response.getU().getC());
			usuario.setNombre(response.getU().getN());
			usuario.setCod_equipo(response.getU().getF());
			usuario.setCod_perfil(response.getU().getR());
		} else {
			usuario.setUsrValido(false);
			usuario.setMsgUsuario(response.getD());
		}
		return usuario;

		// i string Código de Usuario
		// f string Código de equipo
		// e string Código de Canal
		// c string Código de Compañía
		// n string Nombre de Usuario
		// p string Código de País
		// q string Nombre de País
		// r string Codigo de perfil
		// d string Mensaje de Usuario
		// e int Estado
		// = 0 : Ok
		// != 0 : Error

	}

	// **************INICIO LEER JSON MARCACION
	public void readJSonMarcacion(String url) throws JSONException {

		urlConnection = url;
		jSonConnection(urlConnection);

		JSONObject json = new JSONObject(finalData);

		// Traemos el valor "e" (Estado)
		// System.out.println(json.getInt("e"));

		// Traemos el valor "d" (Mensaje de Usuario)
		// System.out.println(json.getString("d"));

	}

	// **************FIN LEER JSON MARCACION

	public String readJSonSincronizar(JSONObject json) throws JSONException {
		String res = "Error en Sincronización";
		if (json != null) {
			resSincronizacion = new StringBuilder();
			if (json.getString("e").equals("0")) {
				if (!DatosManager.getInstancia().dataEnviando()) {
					borrarDataTrabajada();
				} else {
					Log.i("JsonParser", "Se encontro data enviandose - No se borró la data anterior");
				}
				resSincronizacion.append(json.getString("d") + "|");
				JSONObject raizDatos = json.getJSONObject("a");
				// a List<E_Estado> Lista de Estados
				setEstados(raizDatos.getJSONArray("a"));
				// b List<E_Motivo> Lista de Motivos
				setSubEstados(raizDatos.getJSONArray("b"));
				// c List<E_NoVisita> Lista de Motivos de No Visita
				setMotivosNoVisita(raizDatos.getJSONArray("c"));
				// d List<E_Reporte> Lista de Reportes
				setReportes(raizDatos.getJSONArray("d"));
				// e List<E_Opc_Reporte> Lista de Flujo de Reportes
				setOpcReportes(raizDatos.getJSONArray("e"));
				// f List<E_PuntoVenta> Lista de Puntos de Venta
				setPuntosVenta(raizDatos.getJSONArray("f"));
				// g List<E_Categoria> Lista de Categorias
				setCategorias(raizDatos.getJSONArray("g"));
				// h List<E_Producto> Lista de Productos

				setProductos(raizDatos.getJSONArray("h"));
				// i List<E_Material_Apoyo> Lista de Materiales de Apoyo
				setMaterialesApoyo(raizDatos.getJSONArray("i"));
				// j List<E_Observacion> Lista de Observacion
				setObservaciones(raizDatos.getJSONArray("j"));

				// k List<E_Marca> Lista de Marca
				setMarcas(raizDatos.getJSONArray("k"));

				// l List<E_Promocion> Lista de Promociones

				setPromocion(raizDatos.getJSONArray("l"));
				// m List<E_Cluster> Lista de Cluster
				setCluster(raizDatos.getJSONArray("m"));
				// n List<E_Familia> Lista de Familias
				setFamilia(raizDatos.getJSONArray("n"));
				// o List<E_SubFamilia> Lista de Sub Familias
				setSubFamilia(raizDatos.getJSONArray("o"));
				// p List<E_Actividad> Lista de Actividades
				setActividades(raizDatos.getJSONArray("p"));
				// q List<E_Grupo_Objetivo> Lista de Grupo Objetivo
				setGrupoObjetivo(raizDatos.getJSONArray("q"));
				// r List<E_Cond_Exhib> Lista de Condición Exhibición
				setCondExhib(raizDatos.getJSONArray("r"));
				// s List<E_Obj_Marca> Lista de Objtivos x Marca
				setObjMarca(raizDatos.getJSONArray("s"));
				// t List<E_Servicio> Lista de Servicios

				// u List<E_Competidora> Lista de Competidoras
				setCompetidoras(raizDatos.getJSONArray("u"));
				// v List<E_Motivo_Reporte> Lista de Motivos por Reporte
				setMotivoReporte(raizDatos.getJSONArray("v"));
				// w List<E_Opc_Pedido> Lista de Opciones de Pedido
				setOpcPedido(raizDatos.getJSONArray("w"));
				// x List<E_Datos_Presencia> Lista de Datos de Reporte Presencia
				// -
				// Semana
				// Anterior
				setDatosPresencia(raizDatos.getJSONArray("x"));
				// y List<E_Distribuidora> Lista de Distribuidoras
				setDistribuidora(raizDatos.getJSONArray("y"));
				// z List<E_Distribuidora_PtoVenta> Lista de Asociación entre
				// distribuidora y punto de venta.
				setDistribuidoraPtoVenta(raizDatos.getJSONArray("z"));
				// aa List<E_Ubicacion> Lista de los datos de ubicación.
				// (Farmacia
				// IT)
				setUbicaciones(raizDatos.getJSONArray("aa"));
				// ab List<E_Posicion> Lista de los datos de posición. (Farmacia
				// IT)
				setPosicion(raizDatos.getJSONArray("ab"));
				// ac List<E_SubCategoria> Lista de SubCategorias
				setSubCategoria(raizDatos.getJSONArray("ac"));
				// ad List<E_SubMarca> Lista de SubMaras
				setSubMarca(raizDatos.getJSONArray("ad"));
				// ae List<E_Presentacion> Lista de Presentación
				setPresentacion(raizDatos.getJSONArray("ae"));
				// af List<E_Fase> Lista de las Fases. (Colgate Bodegas,
				// opciones de
				// la
				// pantalla inicial)
				setFase(raizDatos.getJSONArray("af"));
				// ag List<E_Poblacion> Lista de los tipos de población.
				setPoblacion(raizDatos.getJSONArray("ag"));
				// ai List<E_MotivoObs> Lista de motivos de observacion.
				setMotivosObs(raizDatos.getJSONArray("ai"));
				// ah List<E_TipoExhibicion> Lista de tipos de exhibicion.
				setTiposExhibicion(raizDatos.getJSONArray("ah"));
				// aj List<E_MarcajePrecio> Lista de marcajes precio.

				// ak List<E_Capacitacion> Lista de capacitaciones.

				// al List<E_Status> Lista de status.

				// am List<E_Incidencia> Lista de incidencias.
				setIncidencia(raizDatos.getJSONArray("am"));
				// an List<E_Credito> Lista de creditos.

				// ar List<E_Perfiles> Lista de perfiles.
				try {
					setPerfiles(raizDatos.getJSONArray("ar"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// as List<E_TiposPerfil> Lista de tipos de perfil.
				try {
					setTiposPerfil(raizDatos.getJSONArray("as"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// at List<E_TiposIncidencia> Lista de tipos de incidencia.
				try {
					setTiposIncidencia(raizDatos.getJSONArray("at"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// au List<E_PDVEstrella> Lista de reporte estrella.
				try {
					setPDVEstrella(raizDatos.getJSONArray("au"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// av List<E_TiposMaterial> Lista de tipos de material.
				try {
					setTiposMaterial(raizDatos.getJSONArray("av"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// aw List<E_TipoPrecioPDV> Lista de tipos de precio para el reporte PDV.
				try {
					setTiposPrecioPDV(raizDatos.getJSONArray("aw"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				Iterator iter = raizDatos.keys();
				if (iter != null) {
					while (iter.hasNext()) {
						Object o = iter.next();
						String valor = raizDatos.getString(o.toString());
						Log.i("RECUPERANDO CLAVES DEL JSON:", o.toString());
						Log.i("RECUPERANDO VALOR DEL JSON:", valor);
					}
				}

			} else {
				res = json.getString("d");
			}

			res = resSincronizacion.toString();
		}
		return res;
	}

	public StringBuilder readJsonDatosPrecarga(JSONObject json) {
		resSincronizacion = new StringBuilder();
		if (json != null) {
			try {
				if (json.getString("e").equals("0")) {
					boolean dbResponse = false;
					int contRegs = 0;
					JSONObject res_i;
					int tamRes;

					JSONObject raizDatos = json.getJSONObject("a");
					String valJson[] = { "a", "b", "c", "d" };
					Class valEntitys[] = { PaisVo.class, DepartamentoVo.class,
							ProvinciaVo.class, DistritoVo.class };
					JSONArray res;

					for (int i = 0; i < valJson.length; i++) {
						dinamicController.truncate(valEntitys[i]);
						contRegs = 0;
						dbResponse = false;
						res = raizDatos.getJSONArray(valJson[i]);
						if (res != null) {
							tamRes = res.length();
							if (tamRes > 0) {
								for (int j = 0; j < tamRes; j++) {
									res_i = res.getJSONObject(j);
									dbResponse = dinamicController.saveJson(
											valEntitys[i], res_i);
									if (dbResponse)
										contRegs += 1;
								}
								resSincronizacion.append(contRegs
										+ " registros guardados " + " de "
										+ tamRes + " de tipo " + valJson[i]);
							}
						}
					}
				}
			} catch (Exception e) {
				resSincronizacion.append(e);
			}
		} else {
			resSincronizacion.append("No se encontro ningun registro");
		}
		return resSincronizacion;
	}

	public void createJSON(Object bodyMessage) {

		finalparams = "<string xmlns='http://schemas.microsoft.com/2003/10/Serialization/'>"
				+ g.toJson(bodyMessage) + "</string>";
		// System.out.println(finalparams);
		// System.out.println(delimiter2);
	}

	public void jSonConnection(String url) {
		try {
			rawData = c.sendWithPOST(finalparams, url);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Error Logueo", e.getMessage());
		}

		finalData = "";
		data1 = "";
		if (rawData != null) {
			temp1 = rawData.split(delimiter1);
			for (int i = 0; i < temp1.length; i++)
				data1 = data1 + temp1[i];

			temp2 = data1.split(delimiter2);
			for (int i = 0; i < temp2.length; i++)
				finalData = finalData + temp2[i];
		}
		Log.i("dataRaw", rawData);
		// System.out.println("RawData= " + rawData);

	}

	public void borrarDataTrabajada() {
		Log.i("JSONParser", "borrando data trabajada");
		dinamicController.truncate(TblPuntoGPS.class);
		dinamicController.truncate(TBL_MOV_REP_PROMOCION.class);
		dinamicController.truncate(TBL_MOV_REP_VISCOMP.class);
		dinamicController.truncate(E_TBL_MOV_REP_COD_NEW_ITT.class);
		dinamicController.truncate(E_ReporteLayout.class);
		dinamicController.truncate(E_TblMovReporteCab.class);
		dinamicController.truncate(E_TblMovRegPDV.class);
		dinamicController.truncate(E_Tbl_Mov_RegistroBodega.class);
		dinamicController.truncate(E_Tbl_Mov_RegistroBodega_Detalle.class);
		dinamicController.truncate(E_TBL_MOV_REGISTROVISITA.class);
		dinamicController.truncate(E_TBL_MOV_REP_PRESENCIA.class);
		dinamicController.truncate(E_ReporteCompetencia.class);
		dinamicController.truncate(E_ReporteCompetenciaDet.class);
		dinamicController.truncate(E_ReporteExhibicion.class);
		dinamicController.truncate(E_ReporteExhibicionDet.class);
		dinamicController.truncate(E_ReporteIncidencia.class);
		dinamicController.truncate(E_ReporteImpulso.class);
		dinamicController.truncate(E_ReporteSod.class);
		dinamicController.truncate(E_ReporteSodDet.class);
		dinamicController.truncate(E_ReporteStock.class);
		dinamicController.truncate(E_ReporteQuiebre.class);
		dinamicController.truncate(E_ReportePrecio.class);
		dinamicController.truncate(E_TblFiltrosApp.class);
		dinamicController.truncate(E_TblMovRepPromocion.class);
		dinamicController.truncate(E_TblMovRepMaterialDeApoyo.class);
		//REPORTES QUE FALTAN POR BORRAR - LOS NUEVOS DE SAN FERNANDO
		dinamicController.truncate(E_ReporteAccionesMercado.class);
		dinamicController.truncate(E_ReporteAccionesMercadoDet.class);
		dinamicController.truncate(E_ReporteAuditoria.class);
		dinamicController.truncate(E_ReporteEstrella.class);
		dinamicController.truncate(E_ReportePotencial.class);
		dinamicController.truncate(E_ReporteRevestimiento.class);

		dinamicController.truncate(E_ReporteBloqueAzul.class);
		dinamicController.truncate(E_ReporteEncuesta.class);
		dinamicController.truncate(E_TblMovRegistroFotografico.class);
		dinamicController.truncate(E_tbl_mov_fotos.class);
		dinamicController.truncate(E_tbl_mov_videos.class);
	}

	// a List<E_Estado> Lista de Estados
	public void setEstados(JSONArray res) throws JSONException {
		if (res != null) {
			int tamEstado = res.length();
			if (tamEstado > 0) {
				dinamicController.truncate(EstadoVo.class);
				JSONObject res_i;
				// es = new ArrayList<E_Estado>();
				// E_Estado e;
				for (int i = 0; i < tamEstado; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(EstadoVo.class, res_i);
					// e = new E_Estado();
					// e.setA(res.getJSONObject(i).getString("a"));
					// e.setB(res.getJSONObject(i).getString("b"));
					// es.add(e);
				}
			}
		}
	}

	// b List<E_Motivo> Lista de Motivos
	public void setSubEstados(JSONArray resMotivo) throws JSONException {
		if (resMotivo != null) {
			dinamicController.truncate(subEstadoVo.class);
			int tamMotivo = resMotivo.length();
			// motivo = new ArrayList<E_Motivo>();
			// E_Motivo m;
			for (int i = 0; i < tamMotivo; i++) {
				dinamicController.saveJson(subEstadoVo.class,
						resMotivo.getJSONObject(i));
				// m = new E_Motivo();
				// m.setA(resMotivo.getJSONObject(i).getString("a"));
				// m.setB(resMotivo.getJSONObject(i).getString("b"));
				// m.setC(resMotivo.getJSONObject(i).getString("c"));
				// motivo.add(m);

			}
		}
	}

	// c List<E_NoVisita> Lista de Motivos de No Visita

	public void setMotivosNoVisita(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(NovisitaVo.class);
			int tamNoVisita = res.length();
			if (tamNoVisita > 0) {
				noVisita = new ArrayList<E_NoVisita>();
				// E_NoVisita nv;
				JSONObject res_i;
				for (int i = 0; i < tamNoVisita; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(NovisitaVo.class, res_i);
					// nv = new E_NoVisita();
					// nv.setA(res.getJSONObject(i).getString("a"));
					// nv.setB(res.getJSONObject(i).getString("b"));
					// noVisita.add(nv);
				}
			}
		}
	}

	// d List<E_Reporte> Lista de Reportes

	public void setReportes(JSONArray resReporte) throws JSONException {
		// ADD NEW ENTIDAD
		if (resReporte != null) {
			int tamReporte = resReporte.length();
			if (tamReporte > 0) {
				dinamicController.truncate(ReporteVo.class);
				// reporte = new ArrayList<E_Reporte>();
				// E_Reporte re;
				for (int i = 0; i < tamReporte; i++) {
					dinamicController.saveJson(ReporteVo.class,
							resReporte.getJSONObject(i));
					// re = new E_Reporte();
					// re.setA(resReporte.getJSONObject(i).getString("a"));
					// re.setB(resReporte.getJSONObject(i).getString("b"));
					// re.setC(resReporte.getJSONObject(i).getString("c"));
					// re.setD(resReporte.getJSONObject(i).getString("d"));
					// reporte.add(re);
				}
				resSincronizacion.append("Reportes: " + tamReporte + "\n");
			}
		}
		// reporteManager.setTabla();
	}

	// e List<E_Opc_Reporte> Lista de Flujo de Reportes
	public void setOpcReportes(JSONArray res) throws JSONException {
		// ADD NEW ENTIDAD
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(OpcReporteVo.class);
				// opcReporte = new ArrayList<E_Opc_Reporte>();
				// E_Opc_Reporte opcRe;
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(OpcReporteVo.class, res_i);
					// opcRe = new E_Opc_Reporte();
					// opcRe.setA(Integer.parseInt(res_i.getString("a")));
					// opcRe.setB(Integer.parseInt(res_i.getString("b")));
					// opcRe.setC(Integer.parseInt(res_i.getString("c")));
					// opcRe.setD(Integer.parseInt(res_i.getString("d")));
					// opcRe.setE(Integer.parseInt(res_i.getString("e")));
					// opcRe.setF(Integer.parseInt(res_i.getString("f")));
					// opcRe.setG(Integer.parseInt(res_i.getString("g")));
					// opcRe.setH(Integer.parseInt(res_i.getString("h")));
					// opcRe.setI(Integer.parseInt(res_i.getString("i")));
					// opcRe.setJ(Integer.parseInt(res_i.getString("j")));
					// opcReporte.add(opcRe);

				}
				// opcReporteManager.setTabla();
				// resSincronizacion.append("FlujoReportes: " + tamRes + "\n");

			}

		}
	}

	// f List<E_PuntoVenta> Lista de Puntos de Venta
	public void setPuntosVenta(JSONArray restampV) throws JSONException {
		if (restampV != null) {
			int tampV = restampV.length();
			if (tampV > 0) {
				// dinamicController.truncate(PuntoventaVo.class);
				pVenta = new ArrayList<E_PuntoVenta>();
				E_PuntoVenta pv;
				for (int i = 0; i < tampV; i++) {
					// dinamicController.saveJson(PuntoventaVo.class,
					// restampV.getJSONObject(i));
					pv = new E_PuntoVenta();
					pv.setA(restampV.getJSONObject(i).getString("a"));
					pv.setB(restampV.getJSONObject(i).getString("b"));
					pv.setC(restampV.getJSONObject(i).getString("c"));
					pv.setD(restampV.getJSONObject(i).getString("d"));
					pv.setE(restampV.getJSONObject(i).getString("e"));
					pv.setF(restampV.getJSONObject(i).getString("f"));
					pv.setG(restampV.getJSONObject(i).getString("g"));
					pv.setH(restampV.getJSONObject(i).getString("h"));
					pv.setI(restampV.getJSONObject(i).getString("i"));
					pv.setJ(restampV.getJSONObject(i).getString("j"));
					pv.setK(restampV.getJSONObject(i).getString("k"));

					pVenta.add(pv);
					// {campo:'latitud',propiedad:'latitud',tipoDato:'string',valueJson:'i'},{campo:'longitud',propiedad:'longitud',tipoDato:'string',valueJson:'j'},{campo:'cod_fase',propiedad:'codFase',tipoDato:'string',valueJson:'k'}]}");

				}
				resSincronizacion.append("Puntos de Venta: " + tampV + "\n");
			}
		}
	}

	// g List<E_Categoria> Lista de Categorias

	public void setCategorias(JSONArray res) throws JSONException {
		// ADD NEW ENTIDAD
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(CategoriaVo.class);
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(CategoriaVo.class, res_i);
				}
				resSincronizacion.append("Categorias: " + tamRes + "\n");
			}
		}
	}

	// h List<E_Producto> Lista de Productos
	public void setProductos(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(ProductoVo.class);
				// producto = new ArrayList<E_Producto>();
				// E_Producto pro;
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(ProductoVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Productos: " + tamRes + "\n");
			}
		}
	}

	// i List<E_Material_Apoyo> Lista de Materiales de Apoyo

	private void setMaterialesApoyo(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(MaterialApoyoVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(MaterialApoyoVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Materiales Apoyo: " + tamRes + "\n");
			}

		}
	}

	// j List<E_Observacion> Lista de Observacion

	public void setObservaciones(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(ObservacionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(ObservacionVo.class, res_i);
				}
				resSincronizacion.append("Observaciones: " + tamRes + "\n");
			}

		}
	}

	// k List<E_Marca> Lista de Marca
	public void setMarcas(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(MarcaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(MarcaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Marcas: " + tamRes + "\n");
			}
		}
	}

	// l List<E_Promocion> Lista de Promociones
	public void setPromocion(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(PromocionVo.class);
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(PromocionVo.class, res_i);
				}
				resSincronizacion.append("Promocion: " + tamRes + "\n");
			}
		}
	}

	// m List<E_Cluster> Lista de Cluster
	public void setCluster(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(ClusterVo.class);
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(ClusterVo.class, res_i);
				}
				resSincronizacion.append("Cluster: " + tamRes + "\n");
			}
		}
	}

	// n List<E_Familia> Lista de Familias
	public void setFamilia(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(FamiliaVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(FamiliaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Familias: " + tamRes + "\n");
			}
		}
	}

	// o List<E_SubFamilia> Lista de Sub Familias
	public void setSubFamilia(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(SubfamiliaVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(SubfamiliaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("SubFamilias: " + tamRes + "\n");
			}
		}
	}

	// p List<E_Actividad> Lista de Actividades
	public void setActividades(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(ActividadVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(ActividadVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Actividades: " + tamRes + "\n");
			}
		}
	}

	// q List<E_Grupo_Objetivo> Lista de Grupo Objetivo
	public void setGrupoObjetivo(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(GrupoObjetivoVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(GrupoObjetivoVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Grupos Objetivo: " + tamRes + "\n");
			}
		}
	}

	// r List<E_Cond_Exhib> Lista de Condición Exhibición
	public void setCondExhib(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(CondExhibidorVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(CondExhibidorVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Condiciones Exhibición: " + tamRes
						+ "\n");
			}
		}
	}

	// s List<E_Obj_Marca> Lista de Objtivos x Marca
	public void setObjMarca(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(ObjMarcaVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(ObjMarcaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Objetivos x Marca: " + tamRes + "\n");
			}
		}
	}

	// u List<E_Competidora> Lista de Competidoras

	public void setCompetidoras(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(CompetidoraVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(CompetidoraVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Competidoras: " + tamRes + "\n");
			}
		}
	}

	// v List<E_Motivo_Reporte> Lista de Motivos por Reporte
	public void setMotivoReporte(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(MotivoReporteVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(MotivoReporteVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Motivos Reporte: " + tamRes + "\n");
			}
		}
	}

	// w List<E_Opc_Pedido> Lista de Opciones de Pedido
	public void setOpcPedido(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(OpcPedidoVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(OpcPedidoVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Opciones Pedido: " + tamRes + "\n");
			}
		}
	}

	// x List<E_Datos_Presencia> Lista de Datos de Reporte Presencia - Semana

	public void setDatosPresencia(JSONArray res) throws JSONException {
		if (res != null) {
			int tamRes = res.length();
			if (tamRes > 0) {
				dinamicController.truncate(DatosPresenciaVo.class);
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(DatosPresenciaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Datos Presencia: " + tamRes + "\n");
			}
		}
	}

	// y List<E_Distribuidora> Lista de Distribuidoras

	public void setDistribuidora(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(DistribuidoraVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(DistribuidoraVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Distribuidoras: " + tamRes + "\n");
			}
		}
	}

	// z List<E_Distribuidora_PtoVenta> Lista de Asociación entre distribuidora
	// y
	// punto de venta.

	public void setDistribuidoraPtoVenta(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(DistribuidoraPuntoVentaVO.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(DistribuidoraPuntoVentaVO.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Distribuidora Punto Venta: " + tamRes
						+ "\n");
			}
		}
	}

	// aa List<E_Ubicacion> Lista de los datos de ubicación. (Farmacia IT)

	public void setUbicaciones(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(UbicacionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(UbicacionVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Ubicaciones: " + tamRes + "\n");
			}

		}
	}

	// ab List<E_Posicion> Lista de los datos de posición. (Farmacia IT)

	public void setPosicion(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(PosicionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(PosicionVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Posiciones: " + tamRes + "\n");
			}
		}
	}

	// ac List<E_SubCategoria> Lista de SubCategorias

	public void setSubCategoria(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(SubcategoriaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(SubcategoriaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("SubCategorias: " + tamRes + "\n");
			}
		}
	}

	// ad List<E_SubMarca> Lista de SubMaras

	public void setSubMarca(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(SubmarcaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(SubmarcaVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("SubMarcas: " + tamRes + "\n");
			}
		}
	}

	// ae List<E_Presentacion> Lista de Presentación

	public void setPresentacion(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(PresentacionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(PresentacionVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Presentación: " + tamRes + "\n");
			}
		}
	}

	// af List<E_Fase> Lista de las Fases. (Colgate Bodegas, opciones de la
	// pantalla inicial)

	public void setFase(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(FaseVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(FaseVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion.append("Fases: " + tamRes + "\n");
			}
		}
	}

	// ag List<E_Poblacion> Lista de los tipos de población.

	public void setPoblacion(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(PoblacionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(PoblacionVo.class,
							res.getJSONObject(i));
				}
				resSincronizacion
						.append("Tipos de población: " + tamRes + "\n");
			}
		}
	}

	public void setMotivosObs(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(MotivoObsVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(MotivoObsVo.class, res_i);
				}
				resSincronizacion.append("Motivos de observacion: " + tamRes
						+ "\n");
			}
		}
	}

	public void setTiposExhibicion(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(TipoExhibicionVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(TipoExhibicionVo.class, res_i);
				}
				resSincronizacion.append("Tipos de Exhibicion: " + tamRes
						+ "\n");
			}
		}
	}


	public void setIncidencia(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(IncidenciaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(IncidenciaVo.class, res_i);
				}
				resSincronizacion.append("Incidencia: " + tamRes + "\n");
			}
		}
	}

	public void setPerfiles(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(PerfilVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(PerfilVo.class, res_i);
				}
				resSincronizacion.append("Perfiles: " + tamRes + "\n");
			}
		}
	}

	public void setTiposPerfil(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(TipoPerfilVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(TipoPerfilVo.class, res_i);
				}
				resSincronizacion.append("Tipos de perfil: " + tamRes + "\n");
			}
		}
	}

	public void setTiposIncidencia(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(TipoIncidenciaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(TipoIncidenciaVo.class, res_i);
				}
				resSincronizacion.append("Tipos de incidencia: " + tamRes + "\n");
			}
		}
	}

	public void setTiposMaterial(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(TipoMaterialVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(TipoMaterialVo.class, res_i);
				}
				resSincronizacion.append("Tipos de material: " + tamRes + "\n");
			}
		}
	}

	public void setPDVEstrella(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(PDVEstrellaVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(PDVEstrellaVo.class, res_i);
				}
				resSincronizacion.append("reportes PDV Estrella: " + tamRes + "\n");
			}
		}
	}

	public void setTiposPrecioPDV(JSONArray res) throws JSONException {
		if (res != null) {
			dinamicController.truncate(E_TipoPrecioPDV.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				JSONObject res_i;
				for (int i = 0; i < tamRes; i++) {
					res_i = res.getJSONObject(i);
					dinamicController.saveJson(E_TipoPrecioPDV.class, res_i);
				}
				resSincronizacion.append("Tipos de precio PDV: " + tamRes + "\n");
			}
		}
	}

	// ***********************************************************************
	// ***********************************************************************
	public void setNuevoPDV(JSONObject res) throws JSONException {
		if (res != null) {
			pVenta = new ArrayList<E_PuntoVenta>();
			E_PuntoVenta pdv = new E_PuntoVenta();
			pdv.setA(res.getString("a"));
			pdv.setB(res.getString("b"));
			pdv.setC(res.getString("c"));
			pdv.setD(res.getString("d"));
			pdv.setE(res.getString("e"));
			pdv.setF(res.getString("f"));
			pdv.setG(res.getString("g"));
			pdv.setH(res.getString("h"));
			pdv.setI(res.getString("i"));
			pdv.setJ(res.getString("j"));
			pdv.setK(res.getString("k"));
			pVenta.add(pdv);
			System.out.println("Nuevo punto de venta: " + res.getString("a")
					+ "\n");
		}
	}

	public void agregarDistribuidora(JSONArray res) throws JSONException {
		if (res != null) {
			// dinamicController.truncate(DistribuidoraVo.class);
			int tamRes = res.length();
			if (tamRes > 0) {
				for (int i = 0; i < tamRes; i++) {
					dinamicController.saveJson(DistribuidoraVo.class,
							res.getJSONObject(i));
				}
				System.out.println("Distribuidoras: " + tamRes + "\n");
			}
		}
	}

}