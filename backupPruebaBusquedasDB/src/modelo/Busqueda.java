package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Busqueda {
	private IntegerProperty idBusqueda;
	private StringProperty nombreUsuario;
	private StringProperty tagBuscado;
	private IntegerProperty cantidadResultados;
	private IntegerProperty duracionConsulta;
	private Date fechaBusqueda;

	public Busqueda(int idBusqueda, String nombreUsuario, String tagBuscado, int cantidadResultados,
			int duracionConsulta, Date fechaBusqueda) {
		this.idBusqueda = new SimpleIntegerProperty(idBusqueda);
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
		this.tagBuscado = new SimpleStringProperty(tagBuscado);
		this.cantidadResultados = new SimpleIntegerProperty(cantidadResultados);
		this.duracionConsulta = new SimpleIntegerProperty(duracionConsulta);
		this.fechaBusqueda = fechaBusqueda;
	}

	// Metodos atributo: idBusqueda
	public int getIdBusqueda() {
		return idBusqueda.get();
	}

	public void setIdBusqueda(int idBusqueda) {
		this.idBusqueda = new SimpleIntegerProperty(idBusqueda);
	}

	public IntegerProperty IdBusquedaProperty() {
		return idBusqueda;
	}

	// Metodos atributo: nombreUsuario
	public String getNombreUsuario() {
		return nombreUsuario.get();
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
	}

	public StringProperty NombreUsuarioProperty() {
		return nombreUsuario;
	}

	// Metodos atributo: tagBuscado
	public String getTagBuscado() {
		return tagBuscado.get();
	}

	public void setTagBuscado(String tagBuscado) {
		this.tagBuscado = new SimpleStringProperty(tagBuscado);
	}

	public StringProperty TagBuscadoProperty() {
		return tagBuscado;
	}

	// Metodos atributo: cantidadResultados
	public int getCantidadResultados() {
		return cantidadResultados.get();
	}

	public void setCantidadResultados(int cantidadResultados) {
		this.cantidadResultados = new SimpleIntegerProperty(cantidadResultados);
	}

	public IntegerProperty CantidadResultadosProperty() {
		return cantidadResultados;
	}

	// Metodos atributo: duracionConsulta
	public int getDuracionConsulta() {
		return duracionConsulta.get();
	}

	public void setDuracionConsulta(int duracionConsulta) {
		this.duracionConsulta = new SimpleIntegerProperty(duracionConsulta);
	}

	public IntegerProperty DuracionConsultaProperty() {
		return duracionConsulta;
	}

	// Metodos atributo: fechaBusqueda
	public Date getFechaBusqueda() {
		return fechaBusqueda;
	}

	public void setFechaBusqueda(Date fechaBusqueda) {
		this.fechaBusqueda = fechaBusqueda;
	}

	public static void llenarInformacionTBLVWBusquedas(Connection connection, ObservableList<Busqueda> lista){

		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT	BUS.id_busqueda, "+
						"BUS.duracion_consulta, "+
						"BUS.nombre_usuario, "+
					    "BUS.tag_buscado, "+
					    "BUS.cantidad_resultados, "+
					    "BUS.fecha_busqueda "+
				    "FROM busquedas BUS"
							);
			while (resultado.next()){
				lista.add(
						new Busqueda(
								resultado.getInt("id_busqueda"),
								resultado.getString("nombre_usuario"),
								resultado.getString("tag_buscado"),
								resultado.getInt("cantidad_resultados"),
								resultado.getInt("duracion_consulta"),
								resultado.getDate("fecha_busqueda"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}