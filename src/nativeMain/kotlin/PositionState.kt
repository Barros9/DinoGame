sealed class PositionState
object Ground : PositionState()
object Up : PositionState()
class Top(val duration: Int) : PositionState()
object Down : PositionState()
