package boris.camunda;

import java.util.stream.IntStream;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Listener {

	@Autowired
	private RuntimeService runtimeService;

	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		ProcessInstantiationBuilder builder = runtimeService.createProcessInstanceByKey("loanApproval");
		IntStream.range(1, 2).forEach(i -> builder.setVariable(RunCheckSoft.CUSTOMER_ID, "custId_321_" + i)
				.businessKey("123_22_" + i).executeWithVariablesInReturn());
		log.info("started loan process");
	}
}
