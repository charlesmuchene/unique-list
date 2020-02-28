/**
 * Computer class
 */
data class Computer(val serial: String, val name: String, override val id: String = serial) :
    UniqueList.Unique<String>