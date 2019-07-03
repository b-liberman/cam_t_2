package boris.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Checker {
	
	public void printName(DelegateExecution execution) {
		log.info("execution123: {}", execution.getCurrentActivityName());
	}

}
