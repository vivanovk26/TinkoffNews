package domain.model

actual class Article actual constructor(
    val id: String,
    val name: String,
    val text: String,
    val publicationDate: Long
)
