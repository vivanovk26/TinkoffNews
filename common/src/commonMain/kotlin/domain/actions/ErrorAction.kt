package domain.actions

/**
 * @author Vladimir Ivanov
 */
internal class ErrorAction(
    val throwable: Throwable
) : Action
