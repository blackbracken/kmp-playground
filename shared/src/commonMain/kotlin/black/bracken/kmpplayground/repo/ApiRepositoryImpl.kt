package black.bracken.kmpplayground.repo

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class ApiRepositoryImpl : ApiRepository {

    override suspend fun getBreeds(): List<String> {
        val resp = HttpClient()
            .get("https://dog.ceo/api/breeds/list/all")
            .bodyAsText()
            .let { Json.parseToJsonElement(it) }

        return resp.jsonObject["message"]?.jsonObject?.keys?.toList() ?: emptyList()
    }

}