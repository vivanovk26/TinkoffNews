package domain.interactors

import domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * @author Vladimir Ivanov
 */
interface ArticlesListInteractor {

    fun getArticles(): Flow<List<Article>>
}
