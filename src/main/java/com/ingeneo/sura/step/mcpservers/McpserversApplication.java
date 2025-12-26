package com.ingeneo.sura.step.mcpservers;

import com.ingeneo.sura.step.mcpservers.tools.ClaimCreateTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpserversApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpserversApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider claimTools(ClaimCreateTools claimCreateTools) {
		return  MethodToolCallbackProvider.builder().toolObjects(claimCreateTools).build();
	}
}
