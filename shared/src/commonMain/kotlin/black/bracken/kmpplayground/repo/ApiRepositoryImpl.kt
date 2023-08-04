package black.bracken.kmpplayground.repo

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class ApiRepositoryImpl : ApiRepository {

    override suspend fun getBreeds(): List<String> {
        return listOf(
            HttpClient()
                .get("https://dog.ceo/api/breeds/list/all")
                .bodyAsText()
        )
    }

}