package boris.camunda;

import java.io.File;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Checker {

	@Autowired
	private File myFile;
	
	public void printName(DelegateExecution execution) {
		log.info("execution123: {}", execution.getCurrentActivityName());
		myFile.toPath();  
	}

}
