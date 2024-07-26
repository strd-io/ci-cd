package example.com

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun tasksCanBeFoundByPriority() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.get("/tasks")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun unusedPriorityProduces404() = testApplication {
        val response = client.get("/tasks/invalid")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }
}
