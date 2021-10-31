data class Dino(
    var positionX: Int,
    var positionY: Int,
    var state: PositionState = Ground
) {

    val realPositionY: Int
        get() {
            return when (state) {
                Ground -> positionY
                Up -> positionY - 2
                is Top -> positionY - 4
                Down -> positionY - 2
            }
        }

    val contactPoint: Pair<Int, Int>
        get() {
            return Pair(
                positionX + 9,
                realPositionY - 3
            )
        }

    fun jump() {
        if (state == Ground) {
            state = Up
        }
    }

    fun update() {
        state = when (val s = state) {
            Ground -> Ground
            Up -> Top(duration = 10)
            is Top -> if (s.duration > 0) Top(s.duration - 1) else Down
            Down -> Ground
        }
    }
}