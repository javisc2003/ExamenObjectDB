package org.example.model;

import javax.persistence.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long totalVentas = 0L;
    private boolean activo = true;

    public Cliente(Long id, String nombre, Long totalVentas, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.totalVentas = totalVentas;
        this.activo = activo;
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Long totalVentas) {
        this.totalVentas = totalVentas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + totalVentas + '\'' +
                ", email='" + activo + '\'' +
                '}';
    }
}

