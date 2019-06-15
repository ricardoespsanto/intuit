# intuit
An E-Board for managing elections.

## How to build and run this application
`./gradlew clean build -x test && java -jar build/libs/eboard-0.0.1-SNAPSHOT.jar`

## The UI 
The user interface for this application is a simple terminal set of menus.
These menus are accessible from the prompt that executes upon startup. 
type `help` to find out what actions/features are available. 
hint: use tabs to autocomplete interactions.


## Assumptions 
The system currently runs backed by a h2 in memory db. For a production system a proper database, or even set of, 
would have to be chosen and implemented properly. This solution would probably be implemented in a distributed 
fashion with several micro-services each backed by its own persistence layer.
I am assuming that load tests, performance tests, profiling and other production related tasks would 
be performed for each subsystem thus allowing us to iteratively tune and pick the right tools and 
configuration parameters for the job. 

While the validation as been implemented and is mostly working, one would expect a set of isolated and 
communicating services would be backed by a REST layer that would automate and ease some of the 
programmatic details of the checks described by the system. E.g.: Maximum number of ideas.

Given that the requirements were not clear about authentication and authorisation mechanisms it was
assumed that the system would be operated by clerks that would have pre-validated Identity and 
authorised / de-authorised manually and offline all the checks. This has allowed me to simplify the 
access to each functionality by passing in references (IDs or Names) and allowing the system to 
choose the relevant citizen, contender, idea, etc.

Names have been assumed to be unique for simplicity sake. Ids would otherwise be used throughout.

Tests have been omitted to speed up the development process.