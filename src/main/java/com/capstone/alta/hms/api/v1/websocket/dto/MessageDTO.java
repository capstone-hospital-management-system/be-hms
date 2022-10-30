package com.capstone.alta.hms.api.v1.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
  private String type;
  private String title;
  private String description;
}
