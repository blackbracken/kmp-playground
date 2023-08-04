package black.bracken.kmpplayground.repo

interface ApiRepository {

    suspend fun getBreeds(): List<String>

}