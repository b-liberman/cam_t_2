package boris.camunda;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.model.bpmn.instance.IntermediateThrowEvent;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendEvent implements JavaDelegate {

	static final String CUSTOMER_ID = "customerId";

	@Override
	public void execute(final DelegateExecution execution) throws Exception {

		log.info("sending the event !!!");
		IntermediateThrowEvent event = (IntermediateThrowEvent) execution.getBpmnModelElementInstance();
		MessageEventDefinition messageEventDefinition = event.getEventDefinitions().stream()
				.filter((ed) -> ed instanceof MessageEventDefinition).map((ed) -> ((MessageEventDefinition) ed))
				.findFirst().get();

		final RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		String messageName = messageEventDefinition.getMessage().getName();
		log.info("message name {}", messageName);
		String businessKey = execution.getProcessBusinessKey();
		DelegateExecution processInstance = execution.getProcessInstance();
		var var1 = processInstance.getVariable(RunCheckSoft.CUSTOMER_ID);
//		log.info("business key:{}", businessKey);
		startNewThread(runtimeService, businessKey, processInstance);

	}

	private void startNewThread(final RuntimeService runtimeService, String businessKey, DelegateExecution processInstance) {
		new Thread(() -> {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			EventSubscription eventSubscription = runtimeService.createEventSubscriptionQuery()
					.processInstanceId(processInstance.getId()).list().stream()
					.filter(es -> es.getEventName().startsWith("auto_check_ok_mid")).findAny().get();
			// forEach(es -> log.info("sbscr: {} - {}", es.getEventType(),
			// es.getEventName()));

			EventSubscription subscription = runtimeService.createEventSubscriptionQuery()
					.processInstanceId(processInstance.getId()).eventName(eventSubscription.getEventName())
					.singleResult();
			// runtimeService.messageEventReceived(subscription.getEventName(),
			// subscription.getExecutionId(), Map.of("one_m", "no"));

			runtimeService.createMessageCorrelation(subscription.getEventName()).processInstanceBusinessKey(businessKey)
					.setVariable("one_m", "no").correlate();
			log.info("sent the event {}!!", eventSubscription.getEventName());
		}).start();
	}

}
