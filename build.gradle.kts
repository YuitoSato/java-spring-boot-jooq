plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.8.0" // 適切なバージョンを指定
  id("org.springframework.boot") version "3.3.4"
  id("io.spring.dependency-management") version "1.1.6"
  id("nu.studer.jooq") version "8.2"
}

tasks.withType<Test> {
  useJUnitPlatform()
}

group = "com.example.javaspringbootjooq"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // Spring Boot Starter
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-jooq")

  // PostgreSQL Driver
  runtimeOnly("org.postgresql:postgresql")

  // JOOQ Codegen（jooqGeneratorで指定）
  jooqGenerator("org.postgresql:postgresql")

  // テスト用依存関係
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

jooq {
  version.set("3.18.6")
  configurations {
    create("main") {
      generateSchemaSourceOnCompilation.set(false)
      jooqConfiguration.apply {
        jdbc.apply {
          driver = "org.postgresql.Driver"
          url = "jdbc:postgresql://localhost:5432/postgres"
          user = "postgres"
          password = "password"
        }
        generator.apply {
          name = "org.jooq.codegen.DefaultGenerator"
          database.apply {
            name = "org.jooq.meta.postgres.PostgresDatabase"
            inputSchema = "public"
          }
          generate.apply {
            isDaos = true
            isPojos = true
          }
          target.apply {
            packageName = "com.example.javaspringbootjooq.jooq"
            directory = "build/generated-src/jooq/main"
          }
        }
      }
    }
  }
}

sourceSets {
  main {
    java {
      srcDir("build/generated-src/jooq/main")
    }
  }
}
