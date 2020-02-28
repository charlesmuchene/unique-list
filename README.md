# UniqueList

This is an implementation of the `List` Abstract Data Type that only contains **unique** elements.

`UniqueList` is random accessible to allow for constant 
time access, `O(1)`, and uses an internal `Set` to maintain the uniqueness
of its elements.

## Implementation
`UniqueList` extends an `ArrayList` to provide resizable list implementation
as well as the required random access (see [ArrayList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)).

It has a major restriction on the elements that can be contained; 
they must conform to the `Unique` interface encapsulated in the implementation.

```kotlin
interface Unique<T> {
    val id: T
}
```
where `T` should produce a unique hash code. This is used in maintenance of the elements' uniqueness.

**Note**
* An implementation could just duplicate the contained elements 
(provided they have a unique hashcode) and add them into the set. However,
this takes **twice** the memory! Therefore, the extra ceremony with the `Unique` interface
provides us with a lean addition in the storage for the required benefits.

* `UniqueList` is not thread safe.

## License

MIT license. See the [LICENSE file](LICENSE) for details.