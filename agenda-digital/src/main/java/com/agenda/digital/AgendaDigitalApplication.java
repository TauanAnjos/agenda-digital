package com.agenda.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Habilita o agendamento de tarefas no Spring Boot
public class AgendaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaDigitalApplication.class, args);
	}

}
