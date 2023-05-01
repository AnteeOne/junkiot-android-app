package tech.antee.junkiot.controll.impl.ktx

fun <Key, Value> Map<Key, List<Value>>.putToListAndCopy(key: Key, value: Value): Map<Key, List<Value>> {
    val updatedValues = get(key)?.also { oldValues ->
        oldValues.toMutableList().apply { add(value) }
    } ?: run {
        listOf(value)
    }
    return HashMap(this).apply {
        put(key, updatedValues)
    }
}

fun <Key, Value> Map<Key, Value>.putAndCopy(key: Key, value: Value): Map<Key, Value> {
    return HashMap(this).apply {
        put(key, value)
    }
}
