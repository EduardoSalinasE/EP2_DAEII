package pe.isil.ep2_dae2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.ep2_dae2.model.Producto;
import pe.isil.ep2_dae2.repository.ProductoRepository;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping("/nuevo")
    String nuevo(Model model){
        model.addAttribute("producto", new Producto());
        return "registro";
    }
    @PostMapping("/guardar")
    String guardar(Model model, Producto producto){
        productoRepository.save(producto);
        return "login";
    }
}

