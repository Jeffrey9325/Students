package com.everis.model;

import java.io.Serializable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class Students implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918046051378102684L;

	@Id
	@NotNull
	String id;
	
	String Nombrecompleto;
    String Genero;
    //@JsonFormat(pattern = "yyy-MM-dd")
    String Fechanacimiento;
    String Tipodocumentoidentificacion;
    String Numerodocumentoidentificacion;  
}
