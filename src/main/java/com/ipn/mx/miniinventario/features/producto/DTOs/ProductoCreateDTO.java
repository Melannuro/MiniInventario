package com.ipn.mx.miniinventario.features.producto.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDTO {
    private String nombreProducto;
    private String descripcionProducto;
    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = 3, fraction = 2, message = "El precio no puede exceder el formato de 3 enteros y 2 decimales (máximo 999.99)")
    private BigDecimal precioProducto;
    private int existencia;
    private LocalDate createAt;
    private CategoriaIdDTO idCategoria;
}
