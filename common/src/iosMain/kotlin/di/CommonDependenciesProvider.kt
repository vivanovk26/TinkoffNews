package di

/**
 * @author Vladimir Ivanov
 */
object CommonDependenciesProvider {

    fun getInteractorsResolver(): InteractorsResolver {
        return InteractorsResolverImpl(
            commonDependenciesResolver = CommonDependenciesResolverImpl()
        )
    }
}
