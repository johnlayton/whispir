package com.whispir;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CSVParser {

  private static final String SEPARATOR = ";";

  public static Message[] parse(final String file) throws Exception {
    return parse(Path.of(file));
  }

  public static Message[] parse(final Path path) throws Exception {
    try (final BufferedReader br = Files.newBufferedReader(path)) {
      final String[] header = br.readLine().split(SEPARATOR);
      return br.lines()
          .map(line -> new Message(header, line.split(SEPARATOR)))
          .toArray(Message[]::new);
    }
  }
}
