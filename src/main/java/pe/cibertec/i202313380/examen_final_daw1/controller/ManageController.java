package pe.cibertec.i202313380.examen_final_daw1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.i202313380.examen_final_daw1.dto.CarDetailDto;
import pe.cibertec.i202313380.examen_final_daw1.dto.CarDto;
import pe.cibertec.i202313380.examen_final_daw1.entity.Car;
import pe.cibertec.i202313380.examen_final_daw1.repository.CarRepository;
import pe.cibertec.i202313380.examen_final_daw1.service.ManageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private ManageService manageService;
    @Autowired
    private CarRepository carRepository;

    // Consulta de todos los items
    @GetMapping("/all")
    public String start(Model model) {
        try {
            List<CarDto> cars = manageService.getAllCars();
            model.addAttribute("cars", cars);
            model.addAttribute("error", null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los Datos");
        }
        return "manage";  // Asegúrate de que esta vista existe en src/main/resources/templates
    }

    // Consulta de un item por id
    @GetMapping("/detail/{id}")
    public String getCarById(@PathVariable int id, Model model) {
        try {
            Optional<CarDetailDto> carDetail = manageService.getCarById(id);
            if (carDetail.isPresent()) {
                model.addAttribute("carDetail", carDetail.get());
            } else {
                model.addAttribute("error", "El auto no se encuentra.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los detalles del auto.");
        }
        return "carDetail";
    }

    // Actualización de un elemento
    @PostMapping("/update/{id}")
    public String updateCar(@ModelAttribute CarDto carDto, @PathVariable("id") Long id, Model model) {
        try {
            Optional<CarDetailDto> carDetail = manageService.getCarById(Math.toIntExact(id));
            if (carDetail.isPresent()) {
                model.addAttribute("carUpdate", carDetail.get());
            } else {
                model.addAttribute("error", "El auto no se encuentra.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los detalles del auto.");
        }
        return "carUpdateForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable int id, Model model) throws Exception {
        try {
            boolean success = manageService.deleteCar(id);
            if (success) {
                model.addAttribute("message", "Auto eliminado correctamente.");
            } else {
                model.addAttribute("error", "No se pudo eliminar el auto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al eliminar el auto.");
        }

        // Vuelve a cargar la lista de autos
        List<CarDto> cars = manageService.getAllCars();
        model.addAttribute("cars", cars);

        return "manage";  // Regresa a la misma página con los mensajes y la lista actualizada
    }


    // Creación de un elemento
    @GetMapping("/create/{id}")
    public String showCreateForm(Model model) {
        model.addAttribute("car", new CarDetailDto(null, "", "", null, "", null, "", "", null, null));
        return "carCreateForm";
    }


    @PostMapping("/create/{id}")
    public String createCar(@ModelAttribute CarDetailDto carDetailDto, Model model) {
        try {
            boolean success = manageService.addCar(carDetailDto);
            if (success) {
                model.addAttribute("message", "Auto creado correctamente.");
            } else {
                model.addAttribute("error", "No se pudo crear el auto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al crear el auto.");
        }
        return "manage"; // Regresa a la misma página con los mensajes y la lista actualizada
    }
}

