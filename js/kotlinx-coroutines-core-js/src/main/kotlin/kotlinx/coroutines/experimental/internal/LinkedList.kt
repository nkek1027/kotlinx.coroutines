package kotlinx.coroutines.experimental.internal

import kotlinx.coroutines.experimental.JobNode
import kotlinx.coroutines.experimental.JobSupport

private typealias Node = LinkedListNode

public open class LinkedListNode {
    public var next = this
        private set
    public var prev = this
        private set

    fun addLast(node: Node) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

public open class LinkedListHead : LinkedListNode() {
    public val isEmpty: Boolean get() = next === this

    /**
     * Iterates over all elements in this list of a specified type.
     */
    public inline fun <reified T : Node> forEach(block: (T) -> Unit) {
        var cur: Node = next
        while (cur != this) {
            if (cur is T) block(cur)
            cur = cur.next
        }
    }

    // just a defensive programming -- makes sure that list head sentinel is never removed
    public final override fun remove() = throw UnsupportedOperationException()
}

