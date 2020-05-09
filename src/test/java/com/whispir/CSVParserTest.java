package com.whispir;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVParserTest {

  @Test
  public void shouldHandlePath() throws Exception {
    checkMessages(CSVParser.parse(Paths.get(ClassLoader.getSystemResource("data.csv").toURI())));
  }

  @Test
  public void shouldHandleString() throws Exception {
    checkMessages(CSVParser.parse(ClassLoader.getSystemResource("data.csv").toURI().getPath()));
  }

  private void checkMessages(Message[] messages) {
    assertNotNull(messages);
    assertEquals(5, messages.length);
    assertEquals(1, messages[0].get("Message Id"));
    assertEquals("Hi there", messages[0].get("Body"));
    assertEquals("SUCCESS", messages[0].get("Delivery Status"));
    assertEquals(5, messages[4].get("Message Id"));
    assertEquals("There is a new sign in attempt for your account.", messages[4].get("Body"));
    assertEquals("SUCCESS", messages[4].get("Delivery Status"));
  }
}
