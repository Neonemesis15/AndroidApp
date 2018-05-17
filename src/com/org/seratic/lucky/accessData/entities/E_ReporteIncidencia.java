package com.org.seratic.lucky.accessData.entities;

public class E_ReporteIncidencia extends Entity {

	private int id;
	private int id_reporte_cab;
	private String cod_producto;
	private String cod_servicio;
	private String cod_status;
	private String cod_incidencia;
	private String descripcion;
	private String hasPedido;
	private Boolean hasFoto;
	private int id_foto;
	private String comentario;
	boolean hayCambio;
	private String cantidad;
	private String valor_status;
	private String valor_incidencia;
	private String cod_opc_pedido;
	private String hasCantidad;
	String nom_opc_pedido;
	private boolean selected;
	private String cod_tipo_incidencia;

	public static final int SUBREPORTE_PRODUCTOS = 1;
	public static final int SUBREPORTE_SERVICIOS = 2;
	public static final int SUBREPORTE_STATUS = 15;
	public static final int SUBREPORTE_INCIDENCIA = 16;
	

	public E_ReporteIncidencia() {
	}

	public E_ReporteIncidencia(int id_reporte_cab, String codigo, String hasPedido, int id_foto, String comentario, int subreporte) {
		this.id_reporte_cab = id_reporte_cab;
		this.hasPedido = hasPedido;
		this.id_foto = id_foto;
		this.comentario = comentario;
		switch(subreporte){
		case SUBREPORTE_PRODUCTOS:
			cod_producto = codigo;
			break;
		case SUBREPORTE_SERVICIOS:
			cod_servicio = codigo;
			break;
		}
	}

	public E_ReporteIncidencia(int id_reporte_cab, String cod_status, String valor_status) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_status = cod_status;
		this.valor_status = valor_status;
	}	
	
	public E_ReporteIncidencia(int id_reporte_cab, String cod_incidencia, String valor_incidencia, String cantidad) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_incidencia = cod_incidencia;
		this.valor_incidencia = valor_incidencia;
		this.cantidad = cantidad;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public String getHasPedido() {
		return hasPedido;
	}

	public void setHasPedido(String hasPedido) {
		this.hasPedido = hasPedido;
	}

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isHayCambio() {
		return hayCambio;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}

	public void setHasFoto(Boolean hasFoto) {
		this.hasFoto = hasFoto;
	}

	public Boolean isHasFoto() {
		return hasFoto;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCantidad() {
		return cantidad;
	}

	public String getCod_opc_pedido() {
		return cod_opc_pedido;
	}

	public void setCod_opc_pedido(String cod_opc_pedido) {
		this.cod_opc_pedido = cod_opc_pedido;
	}

	public String getHasCantidad() {
		return hasCantidad;
	}

	public void setHasCantidad(String hasCantidad) {
		this.hasCantidad = hasCantidad;
	}

	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}

	public String getCod_producto() {
		return cod_producto;
	}

	public void setCod_status(String cod_status) {
		this.cod_status = cod_status;
	}

	public String getCod_status() {
		return cod_status;
	}

	public void setCod_servicio(String cod_servicio) {
		this.cod_servicio = cod_servicio;
	}

	public String getCod_servicio() {
		return cod_servicio;
	}

	public void setCod_incidencia(String cod_incidencia) {
		this.cod_incidencia = cod_incidencia;
	}

	public String getCod_incidencia() {
		return cod_incidencia;
	}

	public void setValor_incidencia(String valor_incidencia) {
		this.valor_incidencia = valor_incidencia;
	}

	public String getValor_incidencia() {
		return valor_incidencia;
	}

	public void setValor_status(String valor_status) {
		this.valor_status = valor_status;
	}

	public String getValor_status() {
		return valor_status;
	}

	public String getNom_opc_pedido() {
		return nom_opc_pedido;
	}

	public void setNom_opc_pedido(String nom_opc_pedido) {
		this.nom_opc_pedido = nom_opc_pedido;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setCod_tipo_incidencia(String cod_tipo_incidencia) {
		this.cod_tipo_incidencia = cod_tipo_incidencia;
	}

	public String getCod_tipo_incidencia() {
		return cod_tipo_incidencia;
	}
	
	
}
