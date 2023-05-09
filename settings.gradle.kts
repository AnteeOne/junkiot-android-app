rootProject.name = "Junkiot"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(":app")

include(":core:styles")
include(":core:strings")
include(":core:utils")

include(":common:domain")
include(":common:data")
include(":common:data-remote")
include(":common:data-local")
include(":common:di")
include(":common:ui")
include(":common:ui-components")
include(":common:multi-compose")

include(":feature:main:api")
include(":feature:main:impl")

include(":feature:controller:list:api")
include(":feature:controller:list:impl")

include(":feature:simulator:list:api")
include(":feature:simulator:list:impl")

include(":feature:simulator:light-sensor:api")
include(":feature:simulator:light-sensor:impl")

include(":lib:controll:api")
include(":lib:controll:impl")

include(":lib:tensorflow-bridge:api")
include(":lib:tensorflow-bridge:impl")
