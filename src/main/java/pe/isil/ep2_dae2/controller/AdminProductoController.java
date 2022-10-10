package pe.isil.ep2_dae2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.ep2_dae2.model.Producto;
import pe.isil.ep2_dae2.repository.ProductoRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    //listar los usuarios cuando carga la pagina
    @GetMapping("")
    String index(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "admin/productos/index";
    }

    //nuevo formulario para agregar usuarios
    @GetMapping("/nuevo")
    String nuevo(Model model){
        model.addAttribute("producto", new Producto());
        return "admin/productos/nuevo";
    }

    //registrar
    @PostMapping("/registrar")
    String registrar(Model model, Producto producto, RedirectAttributes ra){
        productoRepository.save(producto);
        ra.addFlashAttribute("msgExito", "Producto registrado con éxito!");
        return "redirect:/admin/productos";
    }


    //editar: formulario para mostrar los datos
    @GetMapping("/editar/{id}")
    String editar(Model model, @PathVariable Integer id){
        Producto producto = productoRepository.getById(id);
        model.addAttribute("producto", producto);
        return "admin/productos/editar";
    }

    //actualizar
    @PostMapping("/editar/{id}")
    String actualizar(Model model, @PathVariable Integer id,Producto producto, RedirectAttributes ra){
        Producto productoFromDB = productoRepository.getById(id);

        productoFromDB.setNombre(producto.getNombre());
        productoFromDB.setPrecio(producto.getPrecio());
        productoFromDB.setStock(producto.getStock());
        productoFromDB.setCategoria(producto.getCategoria());

        productoRepository.save(productoFromDB);
        ra.addFlashAttribute("msgExito", "Producto actualizado con éxito!");
        return "redirect:/admin/productos";
    }

    //eliminar
    @PostMapping("/eliminar/{id}")
    String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        productoRepository.deleteById(id);
        ra.addFlashAttribute("msgExito", "Producto eliminado con éxito!");
        return "redirect:/admin/productos";
    }
}

