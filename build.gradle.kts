plugins {
  id("java-library")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
  useJUnitPlatform()
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.0-M1")
  testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.7.0-M1")
}