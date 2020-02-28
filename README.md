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

## Usage
Let your entity implement `Unique` interface to provide the property that will be unique -- 
most probably what identifies this instance. For example,

```kotlin
class Person(val name: String, val ssn: String) : UniqueList.Unique<String> {
    override val id: String
        get() = ssn
}
```
Use this instance in the `UniqueList` as shown next.
```kotlin
val person1 = Person("Charlo", "456-7890")
val person2 = Person("Charlo same ssn", "456-7890")
val person3 = Person("Maxwell", "6784-4325")

val uniqueList = UniqueList<Person, String>()

uniqueList.addAll(listOf(person1, person2, person3))

println(uniqueList)
```

Output on the console is:

```kotlin
[Person(name=Charlo, ssn=456-7890), Person(name=Maxwell, ssn=6784-4325)]
```

A use case in Android's RecyclerViewAdapter is described in this [post](https://medium.com/@charlesmuchene/unique-items-list-kotlin-9be2f6a0c109).

#### Caveat
`UniqueList` imposes conformance to the `Unique` interface on 
the elements to be stored hence useful for container types you own.

## License

MIT license. See the [LICENSE file](LICENSE) for details.