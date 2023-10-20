# Byte-Bandits-ASWE
Advanced Software Engineering Fall 2023 Project


### Imported Code
- None


### Documented API
- Our API is a code-calls-code-over-network API, not a user-facing CLI
- Documentation for all implemented entry points can be found via our Swagger
- For documentation, see: [here](byte-bandits/resources/openapi/documentation.yaml)
- [EXTERNAL DOCUMENTATION]


### System Tests Corresponding to API
- We used Postman to conduct code-calls-code-over-network system-level tests exercising every API entry point
- For each endpoint, we forced each possible status code (OK or Bad Gateway)
- We simulated multiple clients by using different JWT tokens, we have simulated this via postman tests by using multiple tokens.
- Our storage endpoint is our persistent data aspect, and this was tested through Postman as well
- For Postman responses, see: [here](/Byte-Bandits-ASWE/testing-screenshots/Iteration1/tests.md)


### Unit Tests
- In addition to over-network tests, we run unit tests, which run as a part of the gradle build
- Tests contain both Unit & Integration test
- Setup/Teardown: our file persistence tests add and then delete files used for testing
- Ktor also spins up the entire application to test the full lifecycle of the webserver
- For unit tests, see: [here](byte-bandits/src/test/kotlin/bytebandits)


### Commit Messages
- All commits have a descriptive and concise commit message


### Self-Documenting Code
- All main functions are documented with comments for readability
- Function and parameter names are self explanatory


### Style Compliant
- Style checked with the IntelliJ integrated tool (as per our Project Proposal)
- All issues reported by the IntelliJ integrated style check tool were addressed
- In addition to the style check apporach from the proposal, we decided to also use Detekt, which is significantly more detailed and strict (as we aim to have the cleanest code possible in our final stage)
- For the Detekt style checker report, see: [here - all formats](Byte-Bandits-ASWE/detekt) or [here - txt](/Byte-Bandits-ASWE/detekt/detekt.txt)
- Note that we still have some style issues (predominantly WildcardImport, MagicNumber, and Exception related). This is still work in progress as we try to address these - where applicable (which is not always the case). A portion of these is caused by the development stage (not all custom Exceptions are defined, a number of variables is hard-coded, etc.) and a portion of these is caused by using the standard rule set, which is extremely pedantic. We could use the @Supress notation, but since we plan to address the issues as much as possible, we decided againts it.


### Build, Run, & Test Instructions
- Load the project into IntelliJ (or build directly with `gradle build` from the project [directory](/Byte-Bandits-ASWE/byte-bandits))
- Run the Gradle Build
- Grade build will run the tests
- To run, use either IntelliJ or run directly with `gradle run` from the project [directory](/Byte-Bandits-ASWE/byte-bandits)


### Tagged Revisions
- Current iteration is tagged as `0.0.3 (first iteration)`
