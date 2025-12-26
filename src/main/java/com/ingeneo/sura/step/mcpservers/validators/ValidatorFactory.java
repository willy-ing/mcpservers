package com.ingeneo.sura.step.mcpservers.validators;

import com.ingeneo.sura.step.mcpservers.dto.DatosIncidente;
import com.ingeneo.sura.step.mcpservers.dto.Persona;
import com.ingeneo.sura.step.mcpservers.dto.Vehiculo;

import java.util.List;

public class ValidatorFactory {

    public static Validator<DatosIncidente> datosIncidenteValidator() {
        return new CompositeValidator<>(
                List.of(
                        new NotBlankValidator<>(DatosIncidente::numeroPoliza, "numeroPoliza"),
                        //new NotBlankValidator<>(DatosIncidente::fechaSiniestro, "fechaSiniestro"),
                        //new DateValidator<>(DatosIncidente::fechaSiniestro, "fechaSiniestro"),
                        new NotBlankValidator<>(DatosIncidente::tipoSiniestro, "tipoSiniestro")
                )
        );
    }

    public static Validator<Persona> datosPersona() {
        return new CompositeValidator<>(
                List.of(
                        new NotBlankValidator<>(Persona::primerNombre, "primerNombre"),
                        new NotBlankValidator<>(Persona::primerApellido, "primerApellido"),
                        new MailValidator<>(Persona::correoElectronico, "correoElectronico"),
                        new NotBlankValidator<>(Persona::tipoIdentificacion, "tipoIdentificacion"),
                        new NotBlankValidator<>(Persona::numeroIdentificacion, "numeroIdentificacion"),
                        new NotBlankValidator<>(Persona::numeroTelefono, "numeroTelefono")
                )
        );
    }

    public static Validator<Vehiculo> datosVehiculo() {
        return new CompositeValidator<>(
                List.of(
                        new NotBlankValidator<>(Vehiculo::placa, "primerNombre"),
                        new NotBlankValidator<>(Vehiculo::ano, "primerApellido"),
                        new NotBlankValidator<>(Vehiculo::modelo, "correoElectronico"),
                        new NotBlankValidator<>(Vehiculo::marca, "tipoIdentificacion"),
                        new NotBlankValidator<>(Vehiculo::tipoVehiculo, "numeroIdentificacion"),
                        new NotBlankValidator<>(Vehiculo::color, "numeroTelefono")
                )
        );
    }
}
