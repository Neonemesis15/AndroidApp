package com.org.seratic.lucky.manager;

import java.util.HashMap;
import java.util.List;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_UsuarioController;
//import com.org.seratic.lucky.accessData.control.TiposReporteController;
//import com.org.seratic.lucky.accessData.entities.TiposReporte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TiposReportes {
	private SQLiteDatabase db;

	private static TiposReportes instancia;
	private HashMap<String, Integer> subReportesMap;

	/********** CODIGO DE LOS FILTROS APLICADOS **********/
	public static final int OPC_REPORTE_CATEGORIA = 1;
	public static final int OPC_REPORTE_SUB_CATEGORIA = 2;
	public static final int OPC_REPORTE_MARCA = 3;
	public static final int OPC_REPORTE_SUB_MARCA = 4;
	public static final int OPC_REPORTE_PRESENTACION = 5;
	public static final int OPC_REPORTE_FAMILIA = 6;
	public static final int OPC_REPORTE_SUB_FAMILIA = 7;
	public static final int OPC_REPORTE_PRODUCTO = 8;
	public static final int OPC_REPORTE_TIPO_ELEMENTOS_VISIB = 9;
	public static final int OPC_REPORTE_TIPO_MATERIAL_POP = 10;

	/********** CODIGO DE LOS TIPOS DE MATERIALES **********/
	public static final String TIPO_MATERIAL_SINMATERIAL = "0";
	public static final String TIPO_MATERIAL_POP = "1";
	public static final String TIPO_MATERIAL_ELEMENTOSVISIB = "2";
	public static final String TIPO_MATERIAL_REVESTIMIENTO = "3";
	public static final String TIPO_MATERIAL_POTENCIAL = "4";
	public static final String TIPO_MATERIAL_TIPOREVEST = "5";
	public static final String TIPO_MATERIAL_REJBLANCA = "6";
	public static final String TIPO_MATERIAL_PLASTAZUL = "7";
	public static final String TIPO_MATERIAL_PRESENMAT = "8";
	public static final String TIPO_MATERIAL_ACCIONMDO = "9";
	public static final String TIPO_MATERIAL_ENCUESTAS = "10";
	public static final String TIPO_MATERIAL_BLOQUE = "27";
	public static final String TIPO_MATERIAL_FRENTE = "28";
	public static final String TIPO_MATERIAL_ENCUESTA = "31";
	public static final String TIPO_MATERIAL_MERCHANDISE = "32";
	public static final String TIPO_MATERIAL_ACTIVIDADCOMP = "33";

	/************** TIPOS DE REPORTES PARA IDENTIFICAR EL MAPEO A REALIZAR *******************/
	public static final int TIPO_NO_IMPLEMENTADO = 0;
	public static final int TIPO_FOTOGRAFICO = 100;
	public static final int TIPO_CODIGOS_ITT_COLGATE = 101;
	public static final int TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD = 102;// material
	public static final int TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD_VENTANA = 103;// material
	public static final int TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO = 104;// producto
	public static final int TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO = 105;// producto
	public static final int TIPO_PRESENCIA_VISIBILIDAD_COMPETENCIA_COD_PRECIO = 106;// material
	public static final int TIPO_PRESENCIA_OBSERVACIONES_COLGATE = 107;// observacion
	public static final int TIPO_PRESENCIA_COMENTARIO_COLGATE = 108;//
	public static final int TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CUENTA_CUMPLE = 109;// material
	public static final int TIPO_PRESENCIA_STOCK_COLGATE_SKU_STOCK = 110;// producto
	public static final int TIPO_PRESENCIA_STOCK_COMPETENCIA_SKU_STOCK = 111;// producto
	public static final int TIPO_PRESENCIA_COLGATE_SKU_FRENTE_PROFUNDIDAD = 114;// producto
	public static final int TIPO_PRESENCIA_CLUSTER_PREGUNTA_MARQUE_CANTIDAD = 115;// cluster
	public static final int TIPO_PROMOCION_COLGATE_DT = 116;//
	public static final int TIPO_MATERIAL_POP_COLGATE_DT = 117;//
	public static final int TIPO_VISIBILIDAD_COMPETENCIA_DT = 118;//

	public static final int TIPO_PRECIOS_ALICORP_SKU_PPDV_POFERTA_MOBS = 119; // producto
	public static final int TIPO_PRECIOS_ALICORP_SKU_PMAYORISTA_PREVENTA_POFERTA_MOBS = 120;// producto

	public static final int TIPO_COMPETENCIA_ALICORP_MAYOR = 121;// marca
	public static final int TIPO_COMPETENCIA_ALICORP_AS = 123;// marca
	public static final int TIPO_EXHIBICION_ALICORP = 122;// tipos de Exibicion
	public static final int TIPO_LAYOUT_ALICORP = 124;//
	public static final int TIPO_QUIEBRES_ALICORP_SKU_MOBS = 125;// producto
	public static final int TIPO_SOD_ALICORP_ID_EXPRIM_EXSEC_MOBS_FOTO = 126;// ??
	public static final int TIPO_STOCK_ALICORP_COD_STOCK_MOBS = 127;// Familia

	public static final int TIPO_PRESENCIA_COLGATE_GESTOR_SKUPRESENCIA = 131;// producto
	public static final int TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKUPRESENCIA = 132;// producto

	public static final int TIPO_PRESENCIA_COLGATE_GESTOR_SKU_PRESENCIA_STOCK = 112;// producto
	public static final int TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKU_PRESENCIA_STOCK = 113;// producto


	// San Fdo AAVV
	public static final int TIPO_POTENCIAL_REVESTIMIENTO_SF_AAVV = 133;// material
	public static final int TIPO_POTENCIAL_POTENCIAL_SF_AAVV = 134;// material
	public static final int TIPO_PRECIOPVP_SF_AAVV = 135;// producto
	public static final int TIPO_PRECIOPDV_OBS_SF_AAVV = 136;// observacion
	public static final int TIPO_PRECIOPDV_PDV_SF_AAVV = 137;// producto
	public static final int TIPO_INCIDENCIA_SF_AAVV = 138;// producto/servicio/atencion
															// cliente
	public static final int TIPO_ACCIONESMERCADO_SF_AAVV = 139;// material
	public static final int TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV = 140;// material
	public static final int TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV = 141; // material
	public static final int TIPO_AUDITORIA_REJBLANCA_SF_AAVV = 142;// material
	public static final int TIPO_AUDITORIA_PLASTAZUL_SF_AAVV = 143;// material
	public static final int TIPO_ESTRELLA_SF_AAVV = 144;// estrella
	
	
	// San Fdo Moderno
	public static final int TIPO_PRECIOS_SF_MODERNO = 23;// producto
	public static final int TIPO_INGRESOS_SF_MODERNO = 27;// producto
	public static final int TIPO_IMPULSO_SF_MODERNO = 28;// producto
	public static final int TIPO_COMPETENCIA_SF_MODERNO_FORM = 128;// marca
	public static final int TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CANTIDAD = 129;// material
	public static final int TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CUENTA = 130;// material

	// San Fdo Tradicional/Chikara
	public static final int TIPO_PRECIOS_SF_TRADICIONAL_CHIKARA = 19;// producto
	public static final int TIPO_PRESENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA = 58;// producto
	public static final int TIPO_ENCUESTAS_SF_TRADICIONAL_CHIKARA = 148;// encuestas

	public static final int TIPO_INCIDENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA = 32;// producto
	public static final int TIPO_INCIDENCIA_SERVICIOS_SF_TRADICIONAL_CHIKARA = 33; // servicios
	public static final int TIPO_ACCIONESMDO_SF_TRADICIONAL_CHIKARA = 115;// material
	public static final int TIPO_ELEMENTOS_VISIB_SANFERNANDO_TRADICIONAL_CHIKARA = 145;// marcas
	public static final int TIPO_BLOQUEAZUL_BLOQUE_SF_TRADICIONAL_CHIKARA = 146;// material
	public static final int TIPO_BLOQUEAZUL_FRENTE_SF_TRADICIONAL_CHIKARA = 147; // material
	public static final int TIPO_VIDEO = 149; // material
	
	
	/*********** MAPEO DE REPORTES POR CODIGO CON CODIGO = REPORTE-SUBREPORTE-CANAL **************/

	// Reportes Colgate MAYORISTAS
	public static final String FOTOGRAFICO_MAYORISTA = "23-0-1000";
	public static final String CODIGOS_ITT_MAYORISTA = "102-0-1000";
	public static final String PRESENCIA_ElemVisibilidad_MAYORISTA = "58-3-1000";
	public static final String PRESENCIA_PresColgate_MAYORISTA = "58-4-1000";
	public static final String PRESENCIA_PresCompetencia_MAYORISTA = "58-5-1000";
	public static final String PRESENCIA_PresExhibidor_MAYORISTA = "58-6-1000";
	public static final String PRESENCIA_Observaciones_MAYORISTA = "58-7-1000";
	public static final String PRESENCIA_Comentarios_MAYORISTA = "58-8-1000";
	public static final String PRESENCIA_PresStockColgate_MAYORISTAS = "58-13-1000";
	public static final String PRESENCIA_PresStockCompetencia_MAYORISTAS = "58-14-1000";

	// Reportes Colgate MINORISTAS
	public static final String FOTOGRAFICO_MINORISTA = "23-0-1023";
	public static final String PRESENCIA_ElemVisibilidad_MINORISTA = "58-3-1023";
	public static final String PRESENCIA_PresColgate_MINORISTA = "58-4-1023";
	public static final String PRESENCIA_PresCompetencia_MINORISTA = "58-5-1023";
	public static final String PRESENCIA_PresExhibidor_MINORISTA = "58-6-1023";
	public static final String PRESENCIA_Observaciones_MINORISTA = "58-7-1023";
	public static final String PRESENCIA_Comentarios_MINORISTA = "58-8-1023";
	public static final String CODIGOS_ITT_MINORISTA = "102-0-1023";
	public static final String PRESENCIA_PresStockColgate_MINORISTAS = "58-13-1023";
	public static final String PRESENCIA_PresStockCompetencia_MINORISTAS = "58-14-1023";

	// Reportes Colgate FARMACIAS IT
	public static final String FOTOGRAFICO_FARMACIAS_IT = "23-0-1242";
	public static final String PRESENCIA_ElemVisibilidad_FARMACIAS_IT = "58-3-1242";
	public static final String PRESENCIA_PresColgate_FARMACIAS_IT = "58-4-1242";
	public static final String PRESENCIA_PresCompetencia_FARMACIAS_IT = "58-5-1242";
	public static final String PRESENCIA_Observaciones_FARMACIAS_IT = "58-7-1242";
	public static final String PRESENCIA_VISIB_COMPETENCIA_FARMACIAS_IT = "58-6-1242";

	// Reportes Colgate FARMACIAS DT
	public static final String CODIGOS_ITT_FARMACIAS_IT = "102-0-1242";
	public static final String PROMOCIONES_FARMACIAS_DT = "55-0-1243";
	public static final String PRESENCIA_ElemVisibilidad_FARMACIAS_DT = "58-3-1243";
	public static final String PRESENCIA_PresColgate_FARMACIAS_DT = "58-4-1243";
	public static final String PRESENCIA_PresCompetencia_FARMACIAS_DT = "58-5-1243";
	public static final String PRESENCIA_Observaciones_FARMACIAS_DT = "58-7-1243";
	public static final String VISIBILIDAD_Competencia_FARMACIAS_DT = "97-0-1243";
	public static final String MATERIALES_POP_FARMACIAS_DT = "78-0-1243";
	public static final String CODIGOS_ITT_FARMACIAS_DT = "102-0-1243";
	public static final String PRESENCIA_VISIB_COMPETENCIA_FARMACIAS_DT = "58-6-1243";

	// Reportes Colgate BODEGAS
	public static final String FOTOGRAFICO_BODEGA = "23-0-2008";
	public static final String CODIGOS_ITT_BODEGAS = "102-0-2008";
	public static final String PRESENCIA_ElemVisibilidad_BODEGAS = "58-3-2008";
	// TiposReportes.TIPO_PRESENCIA_FIT_VISIBILIDAD
	public static final String PRESENCIA_PresColgate_BODEGAS = "58-4-2008";
	public static final String PRESENCIA_PresComptencia_BODEGAS = "58-5-2008";
	public static final String PRESENCIA_Observaciones_BODEGAS = "58-7-2008";
	public static final String PRESENCIA_Comentarios_BODEGAS = "58-8-2008";
	public static final String PRESENCIA_Cluster_BODEGAS = "58-12-2008";
	public static final String PRESENCIA_VISIB_COMPETENCIA_BODEGAS = "58-6-2008";

	// Reportes Alicorp MAYORISTA
	public static final String PRECIOS_MAYOR_ALICORP = "19-0-1000";
	public static final String STOCK_MAYOR_ALICORP = "28-0-1000";
	public static final String SOD_MAYOR_ALICORP = "21-0-1000";
	public static final String COMPETENCIA_MAYOR_ALICORP = "25-0-1000";
	public static final String FOTOGRAFICO_EXHIB_MAYOR_ALICORP = "23-1-1000";
	public static final String FOTOGRAFICO_VISIB_MAYOR_ALICORP = "23-2-1000";
	public static final String FOTOGRAFICO_MAYOR_ALICORP = "23-0-1000";
	public static final String FOTOGRAFICO_CALIDAD_MAYOR_ALICORP = "23-20-1000";

	// Reportes Alicorp AUTOSERVICIOS
	public static final String PRECIOS_AUTO_ALICORP = "19-0-1241";
	public static final String QUIEBRES_AUTO_ALICORP = "56-0-1241";
	public static final String COMPETENCIA_AUTO_ALICORP = "25-0-1241";
	public static final String EXHIBICIONES_AUTO_ALICORP = "31-0-1241";
	public static final String LAYOUT_AUTO_ALICORP = "63-0-1241";
	public static final String FOTOGRAFICO_EXHIB_AUTO_ALICORP = "23-1-1241";
	public static final String FOTOGRAFICO_VISIB_AUTO_ALICORP = "23-2-1241";
	public static final String FOTOGRAFICO_CALIDAD_AUTO_ALICORP = "23-20-1241";
	public static final String FOTOGRAFICO_AUTO_ALICORP = "23-0-1241";

	// Reportes San Fdo MODERNO
	public static final String COMPETENCIA_MODERNO_SANFERNANDO = "25-0-1003";
	public static final String FOTOGRAFICO_MODERNO_SANFERNANDO = "23-0-1003";
	public static final String FOTOGRAFICO_EXHIB_MODERNO_SANFERNANDO = "23-1-1003";
	public static final String FOTOGRAFICO_VISIB_MODERNO_SANFERNANDO = "23-2-1003";
	public static final String FOTOGRAFICO_CALIDAD_MODERNO_SANFERNANDO = "23-20-1003";
	public static final String INRESOS_MODERNO_SANFERNANDO = "28-0-1003";
	public static final String IMPULSO_MODERNO_SANFERNANDO = "51-0-1003";
	public static final String PRECIOS_AAVV_SANFERNANDO = "19-0-1025";
	public static final String PRECIOS_MODERNO_SANFERNANDO = "19-0-1003";

	// Reportes San Fdo AAVV
	/*
	 * public static final String VENTAS_AAVV_SANFERNANDO = "17-0-1025"; public
	 * static final String FOTOGRAFICO_AAVV_SANFERNANDO = "23-0-1025"; public
	 * static final String FOTOGRAFICO_EXHIB_AAVV_SANFERNANDO = "23-1-1025";
	 * public static final String FOTOGRAFICO_VISIB_AAVV_SANFERNANDO =
	 * "23-2-1025"; public static final String
	 * FOTOGRAFICO_CALIDAD_AAVV_SANFERNANDO = "23-20-1025"; public static final
	 * String INCIDENCIA_AAVV_SANFERNANDO_PRODUCTOS = "57-17-1025"; public
	 * static final String INCIDENCIA_AAVV_SANFERNANDO_SERVICIOS = "57-18-1025";
	 */
	public static final String POTENCIAL_REVEST_AAVV_SANFERNANDO = "111-21-1025";
	public static final String POTENCIAL_POTENC_AAVV_SANFERNANDO = "111-22-1025";
	public static final String PVP_AAVV_SANFERNANDO = "112-0-1025";
	public static final String PDV_OBS_AAVV_SANFERNANDO = "113-23-1025";
	public static final String PDV_PDV_AAVV_SANFERNANDO = "113-24-1025";
	public static final String INCIDENCIA_AAVV_SANFERNANDO = "57-0-1025";
	public static final String ACCIONMDO_AAVV_SANFERNANDO = "115-0-1025";
	public static final String REVESTIMIENTO_TIPOREVEST_AAVV_SANFERNANDO = "116-25-1025";
	public static final String REVESTIMIENTO_PRESMAT_AAVV_SANFERNANDO = "116-26-1025";
	public static final String AUDITORIA_REJBLANCA_SANFERNANDO = "118-27-1025";
	public static final String AUDITORIA_PLASTAZUL_SANFERNANDO = "118-28-1025";
	public static final String ESTRELLA_AAVV_SANFERNANDO = "119-0-1025";
	public static final String FOTOGRAFICO_AAVV_SANFERNANDO = "23-0-1025";
	public static final String FOTOGRAFICO_EXHIB_AAVV_SANFERNANDO = "23-1-1025";
	public static final String FOTOGRAFICO_VISIB_AAVV_SANFERNANDO = "23-2-1025";
	public static final String FOTOGRAFICO_CALIDAD_AAVV_SANFERNANDO = "23-20-1025";

	// // Reportes San Fdo TRADICIONAL/CHIKARA

	/*
	 * public static final String PRECIOS_TRADICIONAL_SANFERNANDO = "19-0-1002";
	 * public static final String PRESENCIA_TRADICIONAL_SANFERNANDO =
	 * "58-0-1002"; public static final String
	 * EXHIBICION_TRADICIONAL_SANFERNANDO = "31-19-1002"; public static final
	 * String ENTREGA_MATERIALES_TRADICIONAL_SANFERNANDO = "78-0-1002"; public
	 * static final String ASESORAMIENTO_PROD_TRADICIONAL_SANFERNANDO =
	 * "108-0-1002"; public static final String
	 * INCIDENCIAS_STATUS_TRADICIONAL_SANFERNANDO = "57-15-1002"; public static
	 * final String INCIDENCIAS_INCI_TRADICIONAL_SANFERNANDO = "57-16-1002";
	 * public static final String CREDITO_COMPETENCIA_TRADICIONAL_SANFERNANDO =
	 * "109-0-1002"; public static final String
	 * MARCAJE_PRECIOS_TRADICIONAL_SANFERNANDO = "103-0-1002";
	 */
	public static final String PRECIOS_TRADICIONAL_CHIKARA_SANFERNANDO = "19-0-1002";
	public static final String PRESENCIA_TRADICIONAL_CHIKARA_SANFERNANDO = "58-0-1002";
	public static final String ELEMENTOS_VISIB_TRADICIONAL_CHIKARA_SANFERNANDO = "140-0-1002";
	public static final String INCIDENCIA_TRADICIONAL_CHIKARA_SANFERNANDO_PRODUCTOS = "57-92-1002";
	public static final String INCIDENCIA_TRADICIONAL_CHIKARA_SANFERNANDO_SERVICIOS = "57-91-1002";
	public static final String ACCIONESMDO_TRADICIONAL_CHIKARA_SANFERNANDO = "115-0-1002";
	public static final String FOTOGRAFICO_EXHIB_TRADICIONAL_CHIKARA_SANFERNANDO = "23-1-1002";
	public static final String FOTOGRAFICO_VISIB_TRADICIONAL_CHIKARA_SANFERNANDO = "23-2-1002";
	public static final String FOTOGRAFICO_CALIDAD_TRADICIONAL_CHIKARA_SANFERNANDO = "23-20-1002";
	public static final String FOTOGRAFICO_TRADICIONAL_CHIKARA_SANFERNANDO = "23-0-1002";
	public static final String ENCUESTA_CHIKARA_SANFERNANDO = "86-0-1002";
	public static final String VIDEO_TRADICIONAL_CHIKARA_SANFERNANDO = "139-0-1002";
	public static final String BLOQUEAZUL_BLOQUE_TRADICIONAL_CHIKARA_SANFERNANDO = "138-89-1002";
	public static final String BLOQUEAZUL_FRENTE_TRADICIONAL_CHIKARA_SANFERNANDO = "138-90-1002";

	public static TiposReportes getInstancia(Context context) {
		if (instancia == null) {
			instancia = new TiposReportes(context);
		}
		return instancia;
	}

	public TiposReportes(Context context) {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		loadMapSubReportes();
	}

	public int getIDSubReportefromMap(String key) {
		Integer res;
		res = subReportesMap.get(key);
		Log.i("ID", "Id for key " + key + "res" + res);
		return res == null ? TIPO_NO_IMPLEMENTADO : res;
	}


	private void loadMapSubReportes() {
		String tipo_perfil = new E_UsuarioController(db).getTipoPerfilByCodPerfil(DatosManager.getInstancia().getUsuario().getCod_perfil());
		subReportesMap = new HashMap<String, Integer>();

		subReportesMap.put(FOTOGRAFICO_MAYORISTA, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_MINORISTA, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_FARMACIAS_IT, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_BODEGA, TIPO_FOTOGRAFICO);

		subReportesMap.put(CODIGOS_ITT_BODEGAS, TIPO_CODIGOS_ITT_COLGATE);
		subReportesMap.put(CODIGOS_ITT_FARMACIAS_DT, TIPO_CODIGOS_ITT_COLGATE);
		subReportesMap.put(CODIGOS_ITT_FARMACIAS_IT, TIPO_CODIGOS_ITT_COLGATE);
		subReportesMap.put(CODIGOS_ITT_MAYORISTA, TIPO_CODIGOS_ITT_COLGATE);
		subReportesMap.put(CODIGOS_ITT_MINORISTA, TIPO_CODIGOS_ITT_COLGATE);

		subReportesMap.put(PRESENCIA_ElemVisibilidad_MAYORISTA, TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD);
		subReportesMap.put(PRESENCIA_ElemVisibilidad_MINORISTA, TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD);
		subReportesMap.put(PRESENCIA_ElemVisibilidad_FARMACIAS_DT, TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CUENTA_CUMPLE);
		subReportesMap.put(PRESENCIA_ElemVisibilidad_FARMACIAS_IT, TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD_VENTANA);
		subReportesMap.put(PRESENCIA_ElemVisibilidad_BODEGAS, TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD);

		subReportesMap.put(PRESENCIA_VISIB_COMPETENCIA_FARMACIAS_IT, TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CANTIDAD);
		subReportesMap.put(PRESENCIA_VISIB_COMPETENCIA_BODEGAS, TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CANTIDAD);
		subReportesMap.put(PRESENCIA_VISIB_COMPETENCIA_FARMACIAS_DT, TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CUENTA);

		subReportesMap.put(PRESENCIA_Observaciones_MAYORISTA, TIPO_PRESENCIA_OBSERVACIONES_COLGATE);
		subReportesMap.put(PRESENCIA_Observaciones_MINORISTA, TIPO_PRESENCIA_OBSERVACIONES_COLGATE);
		subReportesMap.put(PRESENCIA_Observaciones_FARMACIAS_DT, TIPO_PRESENCIA_OBSERVACIONES_COLGATE);
		subReportesMap.put(PRESENCIA_Observaciones_FARMACIAS_IT, TIPO_PRESENCIA_OBSERVACIONES_COLGATE);
		subReportesMap.put(PRESENCIA_Observaciones_BODEGAS, TIPO_PRESENCIA_OBSERVACIONES_COLGATE);

		// /dependiendo del perfil del usuario gestionar el reporte. Por ahora
		// sólo se mantendrán los reportes del perfil Gestor
		// PARA GESTOR DE INFORMACION
		if (tipo_perfil.equalsIgnoreCase("1")) {
			subReportesMap.put(PRESENCIA_PresColgate_MINORISTA, TIPO_PRESENCIA_COLGATE_GESTOR_SKUPRESENCIA);
			subReportesMap.put(PRESENCIA_PresColgate_MAYORISTA, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresColgate_FARMACIAS_DT, TIPO_PRESENCIA_COLGATE_GESTOR_SKU_PRESENCIA_STOCK);
			subReportesMap.put(PRESENCIA_PresColgate_FARMACIAS_IT, TIPO_PRESENCIA_COLGATE_GESTOR_SKU_PRESENCIA_STOCK);
			subReportesMap.put(PRESENCIA_PresColgate_BODEGAS, TIPO_PRESENCIA_COLGATE_GESTOR_SKUPRESENCIA);

			subReportesMap.put(PRESENCIA_PresCompetencia_MAYORISTA, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresCompetencia_MINORISTA, TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKUPRESENCIA);
			subReportesMap.put(PRESENCIA_PresCompetencia_FARMACIAS_DT, TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKU_PRESENCIA_STOCK);
			subReportesMap.put(PRESENCIA_PresCompetencia_FARMACIAS_IT, TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKU_PRESENCIA_STOCK);
			subReportesMap.put(PRESENCIA_PresComptencia_BODEGAS, TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKUPRESENCIA);
		}
		// PARA SUPERVISOR
		else if (tipo_perfil.equalsIgnoreCase("2")) {
			subReportesMap.put(PRESENCIA_PresColgate_MINORISTA, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresColgate_MAYORISTA, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresColgate_FARMACIAS_DT, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresColgate_FARMACIAS_IT, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresColgate_BODEGAS, TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO);

			subReportesMap.put(PRESENCIA_PresCompetencia_MAYORISTA, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresCompetencia_MINORISTA, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresCompetencia_FARMACIAS_DT, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresCompetencia_FARMACIAS_IT, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
			subReportesMap.put(PRESENCIA_PresComptencia_BODEGAS, TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO);
		}
		// //

		subReportesMap.put(PRESENCIA_PresExhibidor_MAYORISTA, TIPO_PRESENCIA_VISIBILIDAD_COMPETENCIA_COD_PRECIO);
		subReportesMap.put(PRESENCIA_PresExhibidor_MINORISTA, TIPO_PRESENCIA_VISIBILIDAD_COMPETENCIA_COD_PRECIO);

		subReportesMap.put(PRESENCIA_PresStockColgate_MAYORISTAS, TIPO_PRESENCIA_STOCK_COLGATE_SKU_STOCK);
		subReportesMap.put(PRESENCIA_PresStockColgate_MINORISTAS, TIPO_PRESENCIA_STOCK_COLGATE_SKU_STOCK);
		subReportesMap.put(PRESENCIA_PresStockCompetencia_MINORISTAS, TIPO_PRESENCIA_STOCK_COMPETENCIA_SKU_STOCK);
		subReportesMap.put(PRESENCIA_PresStockCompetencia_MAYORISTAS, TIPO_PRESENCIA_STOCK_COMPETENCIA_SKU_STOCK);
		subReportesMap.put(PRESENCIA_Cluster_BODEGAS, TIPO_PRESENCIA_CLUSTER_PREGUNTA_MARQUE_CANTIDAD);

		subReportesMap.put(PRESENCIA_Comentarios_BODEGAS, TIPO_PRESENCIA_COMENTARIO_COLGATE);
		subReportesMap.put(PRESENCIA_Comentarios_MAYORISTA, TIPO_PRESENCIA_COMENTARIO_COLGATE);
		subReportesMap.put(PRESENCIA_Comentarios_MINORISTA, TIPO_PRESENCIA_COMENTARIO_COLGATE);
		subReportesMap.put(FOTOGRAFICO_BODEGA, TIPO_FOTOGRAFICO);

		subReportesMap.put(VISIBILIDAD_Competencia_FARMACIAS_DT, TIPO_VISIBILIDAD_COMPETENCIA_DT);
		subReportesMap.put(PROMOCIONES_FARMACIAS_DT, TIPO_PROMOCION_COLGATE_DT);
		subReportesMap.put(MATERIALES_POP_FARMACIAS_DT, TIPO_MATERIAL_POP_COLGATE_DT);

		subReportesMap.put(PRECIOS_AUTO_ALICORP, TIPO_PRECIOS_ALICORP_SKU_PPDV_POFERTA_MOBS);
		subReportesMap.put(FOTOGRAFICO_EXHIB_AUTO_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_VISIB_AUTO_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_CALIDAD_AUTO_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_AUTO_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(EXHIBICIONES_AUTO_ALICORP, TIPO_EXHIBICION_ALICORP);
		subReportesMap.put(LAYOUT_AUTO_ALICORP, TIPO_LAYOUT_ALICORP);
		subReportesMap.put(QUIEBRES_AUTO_ALICORP, TIPO_QUIEBRES_ALICORP_SKU_MOBS);
		subReportesMap.put(COMPETENCIA_AUTO_ALICORP, TIPO_COMPETENCIA_ALICORP_AS);

		subReportesMap.put(FOTOGRAFICO_MAYOR_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_EXHIB_MAYOR_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_VISIB_MAYOR_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_CALIDAD_MAYOR_ALICORP, TIPO_FOTOGRAFICO);
		subReportesMap.put(PRECIOS_MAYOR_ALICORP, TIPO_PRECIOS_ALICORP_SKU_PMAYORISTA_PREVENTA_POFERTA_MOBS);
		subReportesMap.put(COMPETENCIA_MAYOR_ALICORP, TIPO_COMPETENCIA_ALICORP_MAYOR);
		subReportesMap.put(STOCK_MAYOR_ALICORP, TIPO_STOCK_ALICORP_COD_STOCK_MOBS);
		subReportesMap.put(SOD_MAYOR_ALICORP, TIPO_SOD_ALICORP_ID_EXPRIM_EXSEC_MOBS_FOTO);

		/*
		 * subReportesMap.put(PRECIOS_AAVV_SANFERNANDO,
		 * TIPO_PRECIOS_AAVV_SANFERNANDO);
		 * subReportesMap.put(VENTAS_AAVV_SANFERNANDO,
		 * TIPO_VENTAS_AAVV_SANFERNANDO);
		 * subReportesMap.put(INCIDENCIA_AAVV_SANFERNANDO_PRODUCTOS,
		 * TIPO_INCIDENCIA_SF_PRODUCTOS);
		 * subReportesMap.put(INCIDENCIA_AAVV_SANFERNANDO_SERVICIOS,
		 * TIPO_INCIDENCIA_SF_SERVICIOS);
		 */

		subReportesMap.put(POTENCIAL_REVEST_AAVV_SANFERNANDO, TIPO_POTENCIAL_REVESTIMIENTO_SF_AAVV);
		subReportesMap.put(POTENCIAL_POTENC_AAVV_SANFERNANDO, TIPO_POTENCIAL_POTENCIAL_SF_AAVV);
		subReportesMap.put(PVP_AAVV_SANFERNANDO, TIPO_PRECIOPVP_SF_AAVV);
		subReportesMap.put(PDV_OBS_AAVV_SANFERNANDO, TIPO_PRECIOPDV_OBS_SF_AAVV);
		subReportesMap.put(PDV_PDV_AAVV_SANFERNANDO, TIPO_PRECIOPDV_PDV_SF_AAVV);
		subReportesMap.put(INCIDENCIA_AAVV_SANFERNANDO, TIPO_INCIDENCIA_SF_AAVV);
		subReportesMap.put(ACCIONMDO_AAVV_SANFERNANDO, TIPO_ACCIONESMERCADO_SF_AAVV);
		subReportesMap.put(REVESTIMIENTO_TIPOREVEST_AAVV_SANFERNANDO, TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV);
		subReportesMap.put(REVESTIMIENTO_PRESMAT_AAVV_SANFERNANDO, TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV);
		subReportesMap.put(AUDITORIA_REJBLANCA_SANFERNANDO, TIPO_AUDITORIA_REJBLANCA_SF_AAVV);
		subReportesMap.put(AUDITORIA_PLASTAZUL_SANFERNANDO, TIPO_AUDITORIA_PLASTAZUL_SF_AAVV);
		subReportesMap.put(ESTRELLA_AAVV_SANFERNANDO, TIPO_ESTRELLA_SF_AAVV);

		subReportesMap.put(FOTOGRAFICO_AAVV_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_EXHIB_AAVV_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_VISIB_AAVV_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_CALIDAD_AAVV_SANFERNANDO, TIPO_FOTOGRAFICO);

		subReportesMap.put(FOTOGRAFICO_MODERNO_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_EXHIB_MODERNO_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_VISIB_MODERNO_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_CALIDAD_MODERNO_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(INRESOS_MODERNO_SANFERNANDO, TIPO_INGRESOS_SF_MODERNO);
		subReportesMap.put(IMPULSO_MODERNO_SANFERNANDO, TIPO_IMPULSO_SF_MODERNO);
		subReportesMap.put(PRECIOS_MODERNO_SANFERNANDO, TIPO_PRECIOS_SF_MODERNO);
		subReportesMap.put(COMPETENCIA_MODERNO_SANFERNANDO, TIPO_COMPETENCIA_SF_MODERNO_FORM);

		/*
		 * subReportesMap.put(PRECIOS_TRADICIONAL_SANFERNANDO,
		 * TIPO_PRECIOS_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(PRESENCIA_TRADICIONAL_SANFERNANDO,
		 * TIPO_PRESENCIA_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(EXHIBICION_TRADICIONAL_SANFERNANDO,
		 * TIPO_EXHIBICION_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(ENTREGA_MATERIALES_TRADICIONAL_SANFERNANDO,
		 * TIPO_ENTREGA_MATERIALES_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(ASESORAMIENTO_PROD_TRADICIONAL_SANFERNANDO,
		 * TIPO_ASESORAMIENTO_PROD_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(INCIDENCIAS_STATUS_TRADICIONAL_SANFERNANDO,
		 * TIPO_INCIDENCIAS_STATUS_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(INCIDENCIAS_INCI_TRADICIONAL_SANFERNANDO,
		 * TIPO_INCIDENCIAS_INCID_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(MARCAJE_PRECIOS_TRADICIONAL_SANFERNANDO,
		 * TIPO_MARCAJE_PRECIOS_TRADICIONAL_SANFERNANDO);
		 * subReportesMap.put(CREDITO_COMPETENCIA_TRADICIONAL_SANFERNANDO,
		 * TIPO_CREDITO_COMPETENCIA_TRADICIONAL_SANFERNANDO_LV);
		 */

		// San Fernando Tradicional/Chikara
		subReportesMap.put(PRECIOS_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_PRECIOS_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(PRESENCIA_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_PRESENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(INCIDENCIA_TRADICIONAL_CHIKARA_SANFERNANDO_PRODUCTOS, TIPO_INCIDENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put( INCIDENCIA_TRADICIONAL_CHIKARA_SANFERNANDO_SERVICIOS, TIPO_INCIDENCIA_SERVICIOS_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(ACCIONESMDO_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_ACCIONESMDO_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(FOTOGRAFICO_EXHIB_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_VISIB_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_CALIDAD_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(FOTOGRAFICO_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_FOTOGRAFICO);
		subReportesMap.put(ELEMENTOS_VISIB_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_ELEMENTOS_VISIB_SANFERNANDO_TRADICIONAL_CHIKARA);
		subReportesMap.put(ENCUESTA_CHIKARA_SANFERNANDO, TIPO_ENCUESTAS_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(VIDEO_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_VIDEO);
		subReportesMap.put(BLOQUEAZUL_BLOQUE_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_BLOQUEAZUL_BLOQUE_SF_TRADICIONAL_CHIKARA);
		subReportesMap.put(BLOQUEAZUL_FRENTE_TRADICIONAL_CHIKARA_SANFERNANDO, TIPO_BLOQUEAZUL_FRENTE_SF_TRADICIONAL_CHIKARA);
	}

	public void validarReporteconProductos() {

	}

	// ******* TIPOS DE INCIDENCIA DESCARGADAS *******//
	public static final int MST_TIPO_INC_SINTIPO = 0;// sin tipo de incidencia
	public static final int MST_TIPO_INC_PROD = 1;// producto
	public static final int MST_TIPO_INC_SERV = 2; // servicios

	// /////////

	// ******* CATEGORIAS PARA FILTRAR FUNCIONALIDADES *******//
		public static final String CATEG_POLLO = "10025";// sin tipo de incidencia

		// /////////
		
		
	public static final int COD_REP_PRESENCIA = 58;
	public static final int COD_REP_PROMOCION = 55;
	public static final int COD_REP_COD_ITT = 102;
	public static final int COD_REP_FOTOGRAFICO = 23;
	public static final int COD_REP_MATERIAL_POP = 78;
	public static final int COD_REP_VIS_COMPETENCIA = 97;
	public static final int COD_REP_PRECIO = 19;
	public static final int COD_REP_STOCK = 28;
	public static final int COD_REP_IMPULSO = 51;

	// /////
	public static final int COD_REP_SOD = 21;
	public static final int COD_REP_STOCK_INGRESO = 28;
	public static final int COD_REP_QUIEBRE = 56;
	public static final int COD_REP_LAYOUT = 63;
	public static final int COD_REP_EXHIBICION = 31;
	public static final int COD_REP_COMPETENCIA = 25;
	public static final int COD_REP_VENTA = 17;
	public static final int COD_REP_INCIDENCIA = 57;
	public static final int COD_REP_ENTREGA_MATERIALES = 78;
	public static final int COD_REP_ASESORAMIENTO_PRODUCTOS = 108;
	public static final int COD_REP_CREDITO_COMPETENCIA = 109;
	public static final int COD_REP_MARCAJE_PRECIOS = 103;
	public static final int COD_REP_ACCIONES_MERCADO = 115;
	public static final int COD_REP_ESTRELLA = 119;
	public static final int COD_REP_VIDEO = 139;


	// /////
	public static final int COD_SUBREP_INC_PROD_TRADCHIK = 92;
	public static final int COD_SUBREP_INC_SERV_TRADCHIK = 91;

}
