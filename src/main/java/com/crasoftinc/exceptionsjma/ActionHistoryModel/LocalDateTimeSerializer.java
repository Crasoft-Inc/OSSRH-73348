package com.crasoftinc.exceptionsjma.ActionHistoryModel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
  @Override
  public void serialize(LocalDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws
      IOException {
    arg1.writeString(arg0.toString());
  }
}
class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
  @Override
  public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
    return LocalDateTime.parse(arg0.getText());
  }
}