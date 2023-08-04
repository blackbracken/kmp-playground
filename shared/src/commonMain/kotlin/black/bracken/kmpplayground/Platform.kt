package black.bracken.kmpplayground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform