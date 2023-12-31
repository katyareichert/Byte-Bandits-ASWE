val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
	kotlin("jvm") version "1.9.10"
	id("io.ktor.plugin") version "2.3.5"
	kotlin("plugin.serialization") version "1.9.10"
	id("io.gitlab.arturbosch.detekt") version("1.23.1")
	jacoco
}

group = "bytebandits"
version = "0.0.1"

application {
	mainClass.set("bytebandits.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}

repositories {
	mavenCentral()
}

tasks.named<Test>("test") {
	ignoreFailures=false
	finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.named<JacocoReport>("jacocoTestReport"){
	dependsOn(tasks.test) 
	reports{
		xml.isEnabled = true
		csv.isEnabled = true
		html.isEnabled = true
	}
}


dependencies {
	implementation("io.ktor:ktor-server-core-jvm")
	implementation("io.ktor:ktor-server-auth-jvm")
	implementation("io.ktor:ktor-server-auth-jwt-jvm")
	implementation("io.ktor:ktor-server-cors-jvm")
	implementation("io.ktor:ktor-server-openapi")
	implementation("io.ktor:ktor-server-swagger-jvm")
	implementation("io.ktor:ktor-server-content-negotiation-jvm")
	implementation("io.ktor:ktor-serialization-gson-jvm")
	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	testImplementation("io.ktor:ktor-server-tests-jvm")
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
	testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
	testImplementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

}

