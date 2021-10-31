import kotlinx.cinterop.CPointer
import platform.osx.*

typealias TerminalWindow = CPointer<WINDOW>

fun createWindow(): TerminalWindow? {
    initscr()
    cbreak()
    noecho()
    nodelay(stdscr, true)
    return newwin(0, 0, 0, 0)
}

val TerminalWindow.size: Pair<Int, Int>
    get() {
        val x = getmaxx(this) - 2
        val y = getmaxy(this) - 2
        return x to y
    }

fun TerminalWindow.cleanupWindow() {
    nocbreak()
    delwin(this)
    endwin()
}

fun TerminalWindow.drawBox() {
    wclear(this)
    box(this, 0u, 0u)
}

fun TerminalWindow.refreshWindow() {
    wrefresh(this)
}

fun TerminalWindow.drawChar(positionX: Int, positionY: Int, toPrint: String) {
    mvwprintw(this, positionY, positionX, toPrint)
}

fun TerminalWindow.drawDino(positionX: Int, positionY: Int) {
    mvwprintw(this, positionY - 1, positionX, "    '^")
    mvwprintw(this, positionY - 2, positionX, "    //")
    mvwprintw(this, positionY - 3, positionX, " '-._, )/'")
    mvwprintw(this, positionY - 4, positionX, ",___.--' /'-`")
    mvwprintw(this, positionY - 5, positionX, "        /(\\_/)")
    mvwprintw(this, positionY - 6, positionX, "          e-e")
}

fun TerminalWindow.drawCactus(positionX: Int, positionY: Int) {
    mvwprintw(this, positionY, positionX, "###")
    mvwprintw(this, positionY - 1, positionX, "  |")
    mvwprintw(this, positionY - 2, positionX, "  |_)")
    mvwprintw(this, positionY - 3, positionX, "(_|")
}