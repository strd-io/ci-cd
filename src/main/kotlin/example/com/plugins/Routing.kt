package example.com.plugins

import example.com.model.TaskRepository
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")

        route("/tasks") {
            get {
                val tasks = TaskRepository.allTasks()
                call.respond(tasks)
            }
        }
    }
}
