package tech.antee.junkiot.tensorflow_bridge.audio.core.models

enum class AudioLabel(val labelValue: String) {
    Nothing("Nothing"),
    Clapping("Clapping"),
    FingerSnapping("Finger snapping"),
    Hands("Hands"),
    BabyCry("Baby cry"),
    Dog("Dog"),
    Cat("Cat"),
    Speech("Speech")
}
