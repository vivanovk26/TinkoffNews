package domain.interactors

import data.repository.ArticlesRepository
import domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : ArticlesListInteractor {

    override fun getArticles(): Flow<List<Article>> {
        return flow {
            println("getArticles() $coroutineContext")
            emit(articlesRepository.getArticles())
        }
    }
}
