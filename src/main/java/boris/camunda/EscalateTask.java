package boris.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EscalateTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		log.info("escalating! - {}", execution.getVariable("one_m"));

	}

}
