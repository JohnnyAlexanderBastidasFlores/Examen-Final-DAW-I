package pe.cibertec.i202313380.examen_final_daw1.dto;

import java.util.Date;

public record CarDetailDto(Integer carId,
                           String make,
                           String model,
                           Integer year,
                           String vin,
                           Integer mileage,
                           String engineType,
                           String insuranceCompany,
                           Date registrationExpirationDate,
                           Date serviceDueDate) {
}
