package pe.isil.ep2_dae2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String nombre;

    private Double precio;

    private Integer stock;

    private String categoria;

}
