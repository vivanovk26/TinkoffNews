package domain.model

/**
 * @author Vladimir Ivanov
 */
expect class Article(
    id: String,
    name: String,
    text: String,
    publicationDate: Long
)
