package com.berzenin.app.model;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(exclude = {"objectPlace", "merch"})
@ToString(exclude = {"objectPlace", "merch"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime time;	
	
	@NotNull
	@Size(min=1, max=100)
	@Column(name = "url_photo", nullable = false)
	@JsonIgnore
	private String pathFoPhoto;
	
	@NotNull
	@JsonIgnore
	private File file;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="objects_photos")
	private ObjectPlace objectPlace;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="merch_photos")
	private Merch merch;
}
