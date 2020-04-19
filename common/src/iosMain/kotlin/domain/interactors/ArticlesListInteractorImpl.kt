package domain.interactors

import data.repository.ArticlesRepository
import kotlinx.coroutines.*

/**
 * @author Vladimir Ivanov
 */
internal class ArticlesListInteractorImpl(
    private val articlesRepository: ArticlesRepository
) : ArticlesListInteractor, CoroutineScope by MainScope() {

    override fun getArticles(interactorListener: InteractorListener) {
        launch {
            print("Start: $coroutineContext")
            interactorListener.onStart()
            try {
                val result = withContext(Dispatchers.Default) {
                    println("Success before: $coroutineContext")
                    delay(5000L)
                    println("Success after: $coroutineContext")

                    articlesRepository.getArticles()
                }
                interactorListener.onSuccess(result)
            } catch (throwable: Throwable) {
                println("Error: $throwable")
                interactorListener.onError(throwable)
            } finally {
                println("Completed: $coroutineContext")
                interactorListener.onCompletion()
            }
        }
    }
}
