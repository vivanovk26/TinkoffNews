package domain.actions

import kotlinx.coroutines.CoroutineScope

/**
 * @author Vladimir Ivanov
 */
interface ActionListener : CoroutineScope {

    fun onNextAction(action: Action)
}
