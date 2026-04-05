plugins {
    id("java")
    id("application")
}

group = "com.ysl"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.oracle.database.jdbc:ojdbc11:23.6.0.24.10")
    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.2")
    implementation("org.postgresql:postgresql:42.7.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveFileName.set("cli-db-tools.jar")
    manifest {
        attributes(
            "Main-Class" to "Main",
            "Implementation-Version" to "0.1.1"
        )
    }
}