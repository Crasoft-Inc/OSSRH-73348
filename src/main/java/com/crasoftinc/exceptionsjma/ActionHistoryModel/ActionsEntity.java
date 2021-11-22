package com.crasoftinc.exceptionsjma.ActionHistoryModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "action-history")
public class ActionsEntity {
  @Id
  private String id;

  private String service;//

  private String committer;//

  private String entity;//

  private String action;//

  private String description;//

  //TODO: external jar
  private TagModel tag;//
  //danger : warning : info

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime time;

  private String extra;

  public ActionsEntity(@JsonProperty("id") String id) {
    this.id = id;
    this.time = LocalDateTime.now(ZoneOffset.UTC);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getCommitter() {
    return committer;
  }

  public void setCommitter(String committer) {
    this.committer = committer;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TagModel getTag() {
    return tag;
  }

  public void setTag(TagModel tag) {
    this.tag = tag;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }
}
