# Byte-Bandits-ASWE
Advanced Software Engineering Fall 2023 Project

## Iteration 2


### Client
- For client app repository, see: [SafeStore](https://github.com/aryanamoh/safestore)
- The README documents how to run the client.


### Multiple Client Instances
- The service supports multiple simultaneous instances of the sample client, and the service provides each instance with unique JSON Web Token (JWT) to differentiate and authorize each instance.
- TO BE ADDED BY 12/7: Tests that show the multiple instances of the client running simultaneously.

### Continuous Integration
- This repository performs continuous integration. All build, analysis and testing tools are automatically run for every commit to the main branch of the codebase.
- For reports, see: [CI Reports](https://github.com/katyareichert/Byte-Bandits-ASWE/actions/runs/7055163158)

### Internal Integration Tests
- Integration tests run automatically during the CI.
- Reports can be found under [Continuous Integration](#continuous-integration)

### Branch Coverage
- Percentage of brange coverage can be found in reports generated by CI.
- Reports can be found under [Continuous Integration](#continuous-integration)
- TO BE ADDED BY 12/7: Full branch coverage will be acheived by demo day.

### Bug Finder
- Static analysis is done by CodeQL and style checking is done by Detekt.
- Both run automatically during CI, and reports can be found under [Continuous Integration](#continuous-integration)

### End-To-End Testing
- We completed end-to-end testing of the client utilizing the service's password generation. malware checker, file store, and file retrieval endpoints.
- Screenshots of tests can be found [here](https://github.com/katyareichert/Byte-Bandits-ASWE/tree/main/testing-screenshots/Iteration2)

### Third Party Code
- Client third party code is linked in the client's README
- Third party libraries for the service include:
  - Ktor framework
  - Netty Webserver
  - Logback for logging
  - Gson
- See: [Deps](/byte-bandits/build.gradle.kts#L43)

### API Documentation
- API documentation has been updated to reflect version 2.
- TO BE ADDED BY 12/7: additional clarification for some endpoints

### Configuration
- Build and run instructions can be found in the Iteration 1 section [here](#build-run-test-instruction)

### Client Target Audience
- Client target audience is elderly individuals who want to be safer online. More in depth explanation can be found in the client's README [here](https://github.com/aryanamoh/safestore)

### External Integration Tests
- External resources include our persistant files, which are checked as part of our testing. These can be found in the reports, under [Continuous Integration](#continuous-integration)
- TO BE ADDED BY 12/7: enhanced description of external integration tests

## Iteration 1

### Imported Code
- None


### Documented API
- Our API is a code-calls-code-over-network API, not a user-facing CLI
- Documentation for all implemented entry points can be found via our Swagger
- For documentation, see: [here - yaml format](/byte-bandits/src/main/resources/openapi/documentation.yaml) or [here - swagger UI](https://app.swaggerhub.com/apis-docs/HUGOMATOUSEK/security-tools_api/0.0.3)



### System Tests Corresponding to API
- We used Postman to conduct code-calls-code-over-network system-level tests exercising every API entry point
- For each endpoint, we forced each possible status code (OK or Bad Gateway)
- We simulated multiple clients by using different JWT tokens, we have simulated this via postman tests by using multiple tokens.
- Our storage endpoint is our persistent data aspect, and this was tested through Postman as well
- For Postman responses, see: [here](/testing-screenshots/Iteration1/tests.md)


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
- For the Detekt style checker report, see: [here - all formats](/detekt) or [here - txt](/detekt/detekt.txt)
- Note that we still have some style issues (predominantly WildcardImport, MagicNumber, and Exception related). This is still work in progress as we try to address these - where applicable (which is not always the case). A portion of these is caused by the development stage (not all custom Exceptions are defined, a number of variables is hard-coded, etc.) and a portion of these is caused by using the standard rule set, which is extremely pedantic. We could use the @Supress notation, but since we plan to address the issues as much as possible, we decided againts it.


### Build, Run, & Test Instructions
- Load the project into IntelliJ (or build directly with `gradle build` from the project [directory](/byte-bandits))
- Run the Gradle Build
- Grade build will run the tests
- To run, use either IntelliJ or run directly with `gradle run` from the project [directory](/byte-bandits)
  

### Tagged Revisions
- Current iteration is tagged as `0.0.4-first-iteration`
