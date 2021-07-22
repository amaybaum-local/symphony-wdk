package com.symphony.bdk.workflow;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.symphony.bdk.gen.api.model.V4Message;
import com.symphony.bdk.workflow.lang.WorkflowBuilder;
import com.symphony.bdk.workflow.lang.swadl.Workflow;

import org.junit.jupiter.api.Test;

class ExecuteScriptIntegrationTest extends IntegrationTest {

  @Test
  void executeScript() throws Exception {
    final Workflow workflow =
        WorkflowBuilder.fromYaml(getClass().getResourceAsStream("/execute-script.yaml"));

    final V4Message message = new V4Message();
    message.setMessageId("msgId");
    when(messageService.send(anyString(), anyString())).thenReturn(message);

    engine.execute(workflow);
    engine.messageReceived("9999", "/execute");

    verify(messageService, timeout(5000)).send("123", "bar");
  }
}