package com.ingeneo.sura.step.mcpservers.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record EstimacionPerdida (
        @ToolParam(description = "Valor de la pretencion de la reclamacion") String montoReclamacion,
        @ToolParam(description = "Moneda de la reclamacion") String moneda
){
}
