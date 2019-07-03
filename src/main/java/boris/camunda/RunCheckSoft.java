package boris.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunCheckSoft implements JavaDelegate {

	static final String CUSTOMER_ID = "customerId";

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		log.info("running software check for {}!!!", execution.getVariable(CUSTOMER_ID));

	}

}
