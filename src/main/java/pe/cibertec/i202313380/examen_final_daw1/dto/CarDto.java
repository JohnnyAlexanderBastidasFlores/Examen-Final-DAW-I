package pe.cibertec.i202313380.examen_final_daw1.dto;

import java.util.Date;

public record CarDto(Integer carId,
                     String make,
                     String model,
                     Integer year,
                     String vin,
                     String licensePlate,
                     String ownerName,
                     String ownerContact,
                     Date purchaseDate,
                     Integer mileage,
                     String engineType,
                     String color,
                     String insuranceCompany,
                     String insurancePolicyNumber,
                     Date registrationExpirationDate,
                     Date serviceDueDate) {
}
