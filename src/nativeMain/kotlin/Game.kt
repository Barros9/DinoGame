class Game(
    private val width: Int,
    private val height: Int,
    private var index: Int = 0,
    private val dino: Dino = Dino(width / 10, height),
    private val world: World = World(width)
) {
    val isOver: Boolean
        get() {
            return world.cactusStung(
                dino.contactPoint,
                index,
                width,
                height
            )
        }


    fun draw(window: TerminalWindow) {
        for (i in 0 until width) {
            val relativeIndex = (i + index) % width

            if (world.blocks[relativeIndex] == 0) {
                window.drawCactus(i, height)
            } else {
                window.drawChar(i, height, "#")
            }
        }
        window.drawDino(dino.positionX, dino.realPositionY)
    }

    fun jump() {
        dino.jump()
    }

    fun update() {
        index = (index + 1) % width
        dino.update()
    }
}