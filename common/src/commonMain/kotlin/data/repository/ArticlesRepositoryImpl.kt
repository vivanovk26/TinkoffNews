package data.repository

import data.network.mappers.ApiMapper
import data.network.services.ApiService
import domain.model.Article

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesRepositoryImpl(
    private val apiService: ApiService,
    private val apiMapper: ApiMapper
) : ArticlesRepository {

    override suspend fun getArticles(): List<Article> {
        return apiMapper.mapArticleResponse(apiService.getArticles())
    }
}
