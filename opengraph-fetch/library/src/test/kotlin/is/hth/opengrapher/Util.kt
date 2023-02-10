package `is`.hth.opengrapher

internal fun <T> List<T>.second(): T {
    if (size < 2)
        throw NoSuchElementException("List contains less than 2 elements!")
    return this[1]
}
