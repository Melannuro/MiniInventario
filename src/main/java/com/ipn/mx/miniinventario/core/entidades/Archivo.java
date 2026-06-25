package com.ipn.mx.miniinventario.core.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Archivo")
public class Archivo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArchvo;

    private String nombreArchivo;

    private String tipoArchivo;
    
    @JdbcType(VarbinaryJdbcType.class)
    @Column(name = "datos_archivo", columnDefinition = "bytea")
    private byte[] datosArchivo;
}
