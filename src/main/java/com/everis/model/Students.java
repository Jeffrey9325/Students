package com.everis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Document(collection = "students")
public class Students implements Serializable {

  private static final long serialVersionUID = -4918046051378102684L;

  @Id
  @NotNull
   String id;

  @NotEmpty
  String fullName;
  String gender;
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date dateofBirth;
  String typeofIdentificationDocument;
  String identificationDocumentNumber;  
}
