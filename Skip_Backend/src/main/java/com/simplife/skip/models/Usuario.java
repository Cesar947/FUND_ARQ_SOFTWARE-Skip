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
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "sede", nullable = false)
    private String sede;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    public Usuario(String dni, String nombres, String apellidos, String sede, Cuenta cuenta) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sede = sede;
        this.cuenta = cuenta;

        this.estadoTabla = true;
    }
}
