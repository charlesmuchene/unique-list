import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Unique list tests
 */
class UniqueListTests {

    private val uniqueList = UniqueList<Computer, String>()

    @BeforeEach
    fun before() {
        uniqueList.clear()
    }

    @Test
    fun `Batch add call yields unique elements' list`() {
        val computer1 = Computer("C4UA9042", "Machine")
        val computer2 = Computer("C4UA9042", "Another Machine")

        assumeTrue(computer1.serial.isNotEmpty())
        assumeTrue(computer2.serial.isNotEmpty())

        uniqueList.addAll(listOf(computer1, computer2))

        assertAll("Computers",
            { assertEquals(1, uniqueList.size) },
            { assertEquals(computer1.serial, uniqueList.first().serial) })

    }

    @Test
    fun `Individual repeated add call yields unique elements in the list`() {
        val computer = Computer("C4UA9042", "Machine")

        repeat(10) {
            uniqueList.add(computer)
        }

        assertAll("Computers",
            { assertEquals(1, uniqueList.size) },
            { assertEquals(computer.serial, uniqueList.first().serial) })
    }

    @Test
    fun `Contains method finds the requested element`() {
        val computer1 = Computer("C4UA9042", "Machine")
        val computer2 = Computer("OP3389AN", "Another Machine")

        val computers = listOf(computer1, computer2)

        uniqueList.addAll(computers)

        assertAll("Computers",
            { assertTrue(uniqueList.contains(computer1)) },
            { assertTrue(uniqueList.containsAll(computers)) })
    }

    @Test
    fun `Retain all method yields a list with the given collection`() {
        val computer1 = Computer("C4UA9042", "Machine")
        val computer2 = Computer("OP3389AN", "Another Machine")
        val computer3 = Computer("FLJ784LFA", "Computer")
        uniqueList.addAll(listOf(computer1, computer2, computer3))

        uniqueList.retainAll(listOf(computer1, computer3))

        assertEquals(2, uniqueList.size)
        assertEquals(computer3.serial, uniqueList.last().serial)

    }
}