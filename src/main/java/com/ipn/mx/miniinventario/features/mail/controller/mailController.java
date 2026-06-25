package com.ipn.mx.miniinventario.features.mail.controller;

import com.ipn.mx.miniinventario.core.entidades.Categoria;
import com.ipn.mx.miniinventario.core.entidades.Producto;
import com.ipn.mx.miniinventario.features.categoria.service.CategoriaService;
import com.ipn.mx.miniinventario.features.mail.service.EmailService;
import com.ipn.mx.miniinventario.features.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import com.ipn.mx.miniinventario.features.mail.DTO.EmailRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class mailController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private EmailService emailService;

    @Operation(
            summary = "Obtener producto y enviar notificación",
            description = "Busca un producto en la base de datos mediante su ID. Si existe, recupera su categoría asociada, envía un correo electrónico con los detalles del producto a la dirección proporcionada y retorna el objeto."
    )
    @PostMapping("/producto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Producto getProducto(@Valid @RequestBody EmailRequestDTO email, @PathVariable Long id){
        Producto p = productoService.findById(id);
        if (p != null){
            Categoria categoria = categoriaService.findById(p.getIdCategoria().getIdCategoria());
            emailService.enviarCorreoElectronico(
                    email.getCorreo(), "Producto con ID = " + id,
                    "Nombre: " + p.getNombreProducto() + "<br>Descripcion:"
                    + p.getDescripcionProducto() + "<br>Precio:" + p.getPrecioProducto()
                    + "<br>Categoria: " + categoria.getNombreCategoria()
                    + "<br>Fecha de Creacion: " + p.getCreateAt()
            );
            return p;
        }
        else {
            return null;
        }
    }

    @PostMapping("/categoria/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria getCategoria(@Valid @RequestBody EmailRequestDTO email, @PathVariable Long id){
        Categoria cat = categoriaService.findById(id);
        if (cat != null){
            emailService.enviarCorreoElectronico(
                    email.getCorreo()
                    , "Producto con ID = " + id,
                    "Nombre: " + cat.getNombreCategoria() + "<br>Descripcion:"
                            + cat.getDescripcionCategoria()
                            + "<br>Fecha de Creacion: " + cat.getCreateAt()
            );
            return cat;
        }
        else {
            return null;
        }
    }
}
