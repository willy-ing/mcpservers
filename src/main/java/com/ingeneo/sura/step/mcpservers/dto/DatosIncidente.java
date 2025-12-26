package com.ingeneo.sura.step.mcpservers.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record DatosIncidente (
        @ToolParam(description = "Numero de la poliza") String numeroPoliza,
        @ToolParam(description = "Fecha del reporte del siniestro toma los datos con este formato yyyy-MM-dd HH:mm:ss") String fechaSiniestro,
        //@ToolParam(description = "Hora del reporte del sinietro solamente la hora") String horaSiniestro,
        @ToolParam(description = "Tipo de siniestro (AUTO, LIFE)") String tipoSiniestro,
        @ToolParam(description = "Toma todo el mensaje enviado") String descripcion)
{
    @Override
    public String toString() {
        return "DatosIncidente{" +
                "numeroPoliza='" + numeroPoliza + '\'' +
                ", fechaSiniestro='" + fechaSiniestro + '\'' +
                //", horaSiniestro='" + horaSiniestro + '\'' +
                ", tipoSiniestro='" + tipoSiniestro + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
