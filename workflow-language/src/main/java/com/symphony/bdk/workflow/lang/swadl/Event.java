package com.symphony.bdk.workflow.lang.swadl;

import com.symphony.bdk.workflow.lang.swadl.event.ActivityExpiredEvent;
import com.symphony.bdk.workflow.lang.swadl.event.FormReplyEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
  private String timeout = "PT5S";

  @JsonProperty("message")
  private LinkedHashMap<String, String> content;

  @JsonProperty("user-joined")
  private LinkedHashMap<String, String> streamId;

  private FormReplyEvent formReply;

  private ActivityExpiredEvent activityExpired;

  public Optional<String> getCommand() {
    if (content != null && content.get("content") != null) {
      return Optional.of(content.get("content"));
    }

    return Optional.empty();
  }

}