import java.util.function.Predicate

class UniqueList<E : UniqueList.Unique<T>, T> : ArrayList<E>() {

    /**
     * Ensures the uniqueness of this list's elements
     */
    private val set = hashSetOf<T>()

    override fun add(element: E): Boolean {
        if (!set.contains(element.id)) {
            set.add(element.id)
            return super.add(element)
        }
        return false
    }

    override fun add(index: Int, element: E) {
        if (!set.contains(element.id)) {
            set.add(element.id)
            super.add(index, element)
        }
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val filtered = filterElements(elements)
        trackElements(filtered)
        return super.addAll(filtered)
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val filtered = filterElements(elements)
        trackElements(filtered)
        return super.addAll(index, filtered)
    }

    override fun clear() {
        set.clear()
        super.clear()
    }

    override fun contains(element: E): Boolean {
        return set.contains(element.id)
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        for (element in elements)
            if (!set.contains(element.id))
                return false
        return true
    }

    override fun remove(element: E): Boolean {
        if (!set.contains(element.id)) return false
        set.remove(element.id)
        return super.remove(element)
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        set.removeAll(elements.map { it.id })
        return super.removeAll(elements)
    }

    override fun removeAt(index: Int): E {
        val element = super.removeAt(index)
        set.remove(element.id)
        return element
    }

    override fun removeIf(filter: Predicate<in E>): Boolean {
        set.removeAll(this.filter(filter::test).map { it.id })
        return super.removeIf(filter)
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        set.removeAll(this.subList(fromIndex, toIndex).map { it.id })
        super.removeRange(fromIndex, toIndex)
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        set.retainAll(elements.map { it.id })
        return super.retainAll(elements)
    }

    private fun trackElements(filtered: List<E>) {
        val filteredIds = filtered.map { it.id }
        set.addAll(filteredIds)
    }

    private fun filterElements(elements: Collection<E>): List<E> {
        return elements.filterNot { element ->
            set.contains(element.id)
        }
    }

    /**
     * Interface marking a unique element to be used
     * in [UniqueList]
     *
     * NB: [T] should produce unique hash code
     */
    interface Unique<T> {

        /**
         * Uniqueness identifier property.
         */
        val id: T

    }

}