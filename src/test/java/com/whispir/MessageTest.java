package com.whispir;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MessageTest {

  @Test
  public void shouldFailForTooFewColumns() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Message(
          new String[] { "A", "B", "C" },
          new String[] { "1", "2" }
      );
    });
  }

  @Test
  public void shouldFailForTooManyColumns() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Message(
          new String[] { "A", "B", "C" },
          new String[] { "1", "2", "3", "4" }
      );
    });
  }

  @Test
  public void shouldFailForUnquotedStringData() {
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      new Message(
          new String[]{"A"},
          new String[]{"A"}
      );
    });

    assertEquals("Data format unexpected [A]", exception.getMessage());
  }

  @Test
  public void shouldZipString() {
    final Message message = new Message(
        new String[] { "A", "B", "C" },
        new String[] { "\"A\"", "\"B\"", "\"C\"" }
    );

    assertEquals("A", message.get("A"));
    assertEquals("B", message.get("B"));
    assertEquals("C", message.get("C"));
  }

  @Test
  public void shouldZipIntegers() {
    final Message message = new Message(
        new String[] { "A", "B", "C" },
        new String[] { "1", "2", "3" }
    );

    assertEquals(1, message.get("A"));
    assertEquals(2, message.get("B"));
    assertEquals(3, message.get("C"));
  }

  @Test
  public void shouldZipCombination() {
    final Message message = new Message(
        new String[] { "A", "B" },
        new String[] { "\"A\"", "2" }
    );

    assertEquals("A", message.get("A"));
    assertEquals(2, message.get("B"));
  }
}
