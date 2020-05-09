package com.whispir;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Message {

  private static class Pair {
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");
    private static final Pattern STR_PATTERN = Pattern.compile("^\\\"(.*)\\\"$");

    private final String key;
    private final String val;

    public Pair(final String key, final String val) {
      this.key = key;
      this.val = val;
    }

    public String getKey() {
      return key;
    }

    public Object valToObject() {
      return INT_PATTERN.matcher(val).matches() ? valToInteger() : valToString();
    }

    private Object valToInteger() {
      return Integer.parseInt(val);
    }

    private Object valToString() {
      final Matcher matcher = STR_PATTERN.matcher(val);
      if (matcher.find() && matcher.groupCount() == 1) {
        return matcher.group(1);
      } else {
        throw new RuntimeException("Data format unexpected [" + val + "]");
      }
    }
  }

  /**
   * For a Java Solution: Each object should contain a map with keys matching
   * the column names and values matching the values of corresponding fields in
   * the CSV.
   */
  private final Map<String, Object> delegate;

  public Message(final String[] keys, final String[] values) {
    if (keys.length != values.length) {
      throw new IllegalArgumentException("Data length mismatch");
    }
    delegate = IntStream.range(0, keys.length)
        .mapToObj((i) -> new Pair(keys[i], values[i]))
        .collect(Collectors.toMap(Pair::getKey, Pair::valToObject));
  }

  public Object get(String key) {
    return delegate.get(key);
  }

}
