package kotlinx.coroutines

import reactor.blockhound.BlockHound
import reactor.blockhound.integration.BlockHoundIntegration
import kotlinx.coroutines.scheduling.CoroutineScheduler

@Suppress("UNUSED")
class CoroutinesBlockHoundIntegration : BlockHoundIntegration {
    override fun applyTo(builder: BlockHound.Builder) {
        builder.addDynamicThreadPredicate({ it is CoroutineScheduler.Worker })
        builder.nonBlockingThreadPredicate({ current -> current.or({ o: Any? -> o is CoroutineScheduler.Worker &&
                o.state == CoroutineScheduler.WorkerState.CPU_ACQUIRED }) })
    }
}
