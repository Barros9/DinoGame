import platform.osx.*

lateinit var window: TerminalWindow
lateinit var game: Game

fun main() {
    window = createWindow() ?: return
    startHomePage()
}

fun startHomePage() {
    val (width, height) = window.size
    game = Game(width, height)

    while (true) {
        window.drawBox()
        mvwprintw(window, 10, 15, "Press SPACE to jump and to the start game or Q to quit")
        when (wgetch(window)) {
            32 -> break
            113 -> window.cleanupWindow()
        }
    }
    startGame()
}

fun startGame() {
    var speed = 100
    var timer = 1

    while (true) {
        window.drawBox()

        if (timer % 100 == 0) {
            speed -= 10
        }

        if (speed > 0) {
            delay_output(speed)
        }

        val input = getch()
        if (input != ERR) {
            ungetch(input)
            when (getch()) {
                32 -> game.jump()
                113 -> break
            }
        }

        if (game.isOver) {
            mvwprintw(window, 10, 15, "Game Over :(")
            window.refreshWindow()
            delay_output(2000)
            break
        }

        game.draw(window)
        game.update()

        timer++

        window.refreshWindow()
    }
    startHomePage()
}
