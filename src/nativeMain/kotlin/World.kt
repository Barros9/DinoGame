class World(
    width: Int
) {

    val blocks = IntArray(width) {
        if (it == (width / 2.50).toInt() || it == (width / 1.50).toInt() || it == (width / 1.05).toInt()) {
            0 // cactus
        } else {
            1 // brick
        }
    }

    fun cactusStung(contactPoint: Pair<Int, Int>, index: Int, width: Int, height: Int): Boolean {
        val (x, y) = contactPoint
        val position = (x + index) % width
        return blocks[position] == 0 && (y == height - 1 || y == height - 2 || y == height - 3)
    }
}