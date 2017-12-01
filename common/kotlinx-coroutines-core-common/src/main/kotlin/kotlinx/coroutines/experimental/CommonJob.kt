package kotlinx.coroutines.experimental

import kotlin.coroutines.experimental.CoroutineContext

expect public interface Job : CoroutineContext.Element {
    public companion object Key : CoroutineContext.Key<Job>
    public val isActive: Boolean
    public val isCompleted: Boolean
    public val isCancelled: Boolean
    public fun getCancellationException(): CancellationException
    public fun start(): Boolean
    @Suppress("EXPECTED_DECLARATION_WITH_DEFAULT_PARAMETER")
    public fun cancel(cause: Throwable? = null): Boolean
    public val children: Sequence<Job>
    public suspend fun join()
    @Suppress("EXPECTED_DECLARATION_WITH_DEFAULT_PARAMETER")
    public fun invokeOnCompletion(onCancelling: Boolean = false, handler: CompletionHandler): DisposableHandle
}

public typealias CompletionHandler = (cause: Throwable?) -> Unit

public expect interface DisposableHandle {
    public fun dispose()
}

public expect class CompletionHandlerException(message: String, cause: Throwable) : RuntimeException

public open expect class CancellationException(message: String) : IllegalStateException

public expect class JobCancellationException(
    message: String,
    cause: Throwable?,
    job: Job
) : CancellationException {
    val job: Job
}

public expect suspend fun Job.cancelAndJoin()
@Suppress("EXPECTED_DECLARATION_WITH_DEFAULT_PARAMETER")
public expect fun Job.cancelChildren(cause: Throwable? = null)
public expect suspend fun Job.joinChildren()

public expect object NonDisposableHandle : DisposableHandle {
    override fun dispose()
}
