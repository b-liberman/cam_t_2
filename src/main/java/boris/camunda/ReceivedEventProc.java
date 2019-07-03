package boris.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivedEventProc implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		log.info("stopping processing!! - {}", execution.getVariable("one_m"));

	}

}
