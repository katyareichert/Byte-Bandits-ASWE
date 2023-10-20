# Byte-Bandits-ASWE
Advanced Software Engineering Fall 2023 Project


### Imported Code
- None


### Documented API
- byte-bandits/resources/openapi/documentation.yaml
- [ADD EXTERNAL LINK]


### System Tests Corresponding to API
- We used Postman to conduct code-calls-code-over-network system-level tests exercising every API entry point
- For each endpoint, we forced each possible status code (OK or Bad Gateway)
- We tested multiple clients by [DESCRIBE]
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
- All main functions are commented for readability
- Function and parameter names are self explanatory


### Style Compliant
- Style checked with [ADD STYLE CHECKER]
- For style checker report, see: [ADD PATH TO REPORT FILE]


### Build, Run, & Test Instructions
- Load the project into IntelliJ
- Run the Gradle Build
- Grade build will run the tests


### Tagged Revisions
- Current iteration is tagged as [TAG]
