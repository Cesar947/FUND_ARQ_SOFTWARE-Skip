package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta")
@Entity
public class Cuenta implements Serializable {

    public  static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long Id;

    @Column(name = "codigo_upc")
    private String codigoUpc;

    @Column(name = "correo_upc")
    private String correoUPC;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cuenta_rol",
            joinColumns = @JoinColumn(name = "cuenta_id"),
            inverseJoinColumns = @JoinColumn(name ="rol_id"))
    private Set<Rol> roles;

}

