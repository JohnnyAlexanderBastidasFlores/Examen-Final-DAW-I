package pe.cibertec.i202313380.examen_final_daw1.service;

import pe.cibertec.i202313380.examen_final_daw1.dto.CarDetailDto;
import pe.cibertec.i202313380.examen_final_daw1.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    /* Consulta Total*/
  List<CarDto> getAllCars() throws Exception;

  /* Consulta por Car*/
  Optional<CarDetailDto> getCarById(int id) throws Exception;

  /* Actualizacion de los Datos por DTO*/
  boolean updateCar(CarDto carDto) throws Exception;

  /* Eliminacion por ID */
  boolean deleteCar(int id) throws Exception;

  /* Registra por Car*/
  boolean addCar(CarDetailDto carDetailDto) throws Exception;
}
