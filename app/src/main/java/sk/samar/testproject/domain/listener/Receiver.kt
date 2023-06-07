package sk.samar.testproject.domain.listener

interface Receiver<T> {
    fun receive(data: T)
}