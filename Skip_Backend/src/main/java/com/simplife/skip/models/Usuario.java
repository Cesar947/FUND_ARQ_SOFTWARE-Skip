package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "dni")
    private String dni;

    @Column(name = "correo_upc")
    private String correoUPC;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "licencia_conducir")
    private String licenciaConductir;

    @Column(name = "sede")
    private String sede;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;


}
