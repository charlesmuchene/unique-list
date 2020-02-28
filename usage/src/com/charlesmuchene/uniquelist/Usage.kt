@file:JvmName("Usage")

package com.charlesmuchene.uniquelist

import UniqueList

data class Person(val name: String, val ssn: String) : UniqueList.Unique<String> {
    override val id: String
        get() = ssn
}

fun main() {

    val person1 = Person("Charlo", "456-7890")
    val person2 = Person("Charlo same ssn", "456-7890")
    val person3 = Person("Maxwell", "6784-4325")

    val uniqueList = UniqueList<Person, String>()

    uniqueList.addAll(listOf(person1, person2, person3))

    println(uniqueList)

}