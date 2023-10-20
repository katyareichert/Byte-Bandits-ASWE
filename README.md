# Byte-Bandits-ASWE
Advanced Software Engineering Fall 2023 Project


### Imported Code
- None


### Documented API
- Our API is a code-calls-code-over-network API, not a user-facing CLI
- Documentation for all implemented entry points can be found via our Swagger
- For documentation, see: byte-bandits/resources/openapi/documentation.yaml
- [EXTERNAL DOCUMENTATION]


### System Tests Corresponding to API
- We used Postman to conduct code-calls-code-over-network system-level tests exercising every API entry point
- For each endpoint, we forced each possible status code (OK or Bad Gateway)
- We simulated multiple clients by [DESCRIBE]
- Our storage endpoint is our persistent data aspect, and this was tested through Postman as well
- For Postman responses, see: [ADD PATH TO POSTMAN FOLDER]


### Unit Tests
- In addition to over-network tests, we run unit tests, which run as a part of the gradle build
- Tests contain both Unit & Integration test
- Setup/Teardown: our file persistence tests add and then delete files used for testing
- [MOCKING]
- For unit tests, see: byte-bandits/src/test/kotlin/bytebandits


### Commit Messages
- All commits have a descriptive and concise commit message


### Self-Documenting Code
- All main functions are documented with comments for readability
- Function and parameter names are self explanatory


### Style Compliant
- Style checked with Detekt
- For style checker report, see: [ADD PATH TO REPORT FILE]


### Build, Run, & Test Instructions
- Load the project into IntelliJ
- Run the Gradle Build
- Grade build will run the tests


### Tagged Revisions
- Current iteration is tagged as [TAG]
