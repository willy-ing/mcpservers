package com.ingeneo.sura.step.mcpservers.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record Vehiculo (
        @ToolParam(description = "Placa de circulacion del vehiculo") String placa,
        @ToolParam(description = "Marca del vehiculo") String marca,
        @ToolParam(description = "Modelo del vehiculo") String modelo,
        @ToolParam(description = "Año del vehiculo") String ano,
        @ToolParam(description = "Color del vehiculo") String color,
        @ToolParam(description = "Tipo de vehiculo") String tipoVehiculo
){


}
