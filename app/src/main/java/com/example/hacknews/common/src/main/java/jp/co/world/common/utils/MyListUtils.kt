package jp.co.world.common.utils

object MyListUtils {

    inline fun<reified T> isEqual(first: List<T>, second: List<T>): Boolean {
        if (first.size != second.size) {
            return false
        }
        return first.toTypedArray() contentEquals second.toTypedArray()
    }
}