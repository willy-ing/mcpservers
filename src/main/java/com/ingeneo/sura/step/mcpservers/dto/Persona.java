package com.ingeneo.sura.step.mcpservers.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record Persona (
        @ToolParam(description = "Primer nombre") String primerNombre,
        @ToolParam(description = "Segundo nombre") String segundoNombre,
        @ToolParam(description = "Primer apellido") String primerApellido,
        @ToolParam(description = "Segundo apellido") String segundoApellido,
        @ToolParam(description = "Numero de telefono") String numeroTelefono,
        @ToolParam(description = "Tipo de identificacion C si es Cedula de Cuidadania, N si es Nit o P si es pasaporte") String tipoIdentificacion,
        @ToolParam(description = "Numero de identificacion") String numeroIdentificacion,
        @ToolParam(description = "Correo electronico") String correoElectronico,
        @ToolParam(description = "Direccion") String direccion
){
    @Override
    public String toString() {
        return "Persona{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
