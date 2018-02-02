package mockit

@PublishedApi internal inline fun <reified T: Any> Expectations.anyK(): T {
    return with(type<T>()) as T
}

@PublishedApi internal inline fun <reified T: Any> Verifications.anyK(): T {
    return with(type<T>()) as T
}

fun <T : Any> type(): Delegate<T> {
    return object : Delegate<T> {
        fun check(value: T): Boolean {
            return true
        }
    }
}
