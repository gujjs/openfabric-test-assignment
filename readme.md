# Backend Junior Developer (Intern) Test

Welcome! and thank you for applying!

## Requirement

Your task is to add the necessary features to the current project's API blueprint structure to enable
it to manage **Docker** containers, also referred to as workers, as fallows:

🔄 Integrate the 🐳 Docker Java library (https://github.com/docker-java/docker-java/blob/main/docs/getting_started.md) to enable connect and manage 👷‍♀️ workers.

➕ Add the required fields and database migration for the 👷‍♂️ Worker entity to keep **all** the information associated on available at the container level (like 🔌 ports, 👨‍💼 name, 🟢 status, etc..)

🆕 Add required entities and tables to track the 👷‍♂️ Worker statistics.

🆙 Update the 👷‍♂️ WorkerController to add actions for:
* 📄 List workers (paginated)
* ▶️ Start and ⏹️ Stop worker
* 🔍 Get worker information
* 📊 Get worker statistics

## Constraints and restrictions

You are free to use any package or library you see feet as long as you follow these rules:

* 👎 You can't copy and paste from other peoples work

## Run


1. `docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=9c231a1c61a5fe322dd1 -e POSTGRES_USER=openfabric -e POSTGRES_DB=test -d postgres`
2. `./gradlew build`
3. `java -jar openfabric-api/build/libs/openfabric-api-1.0.0-SNAPSHOT.jar`

Once you have established a connection to the database, you can test the application by using Swagger.

You can access Swagger by navigating to the following link: http://localhost:8080/swagger-ui/#/.

## Submission

Your solution must be uploaded on GitHub, and submit us the link in **max 1 week** after receiving the task.

## Note

Keep in mind that this is the project that will be used to evaluate your skills.
So we do expect you to make sure that the app is fully functional and doesn't have any obvious missing pieces.