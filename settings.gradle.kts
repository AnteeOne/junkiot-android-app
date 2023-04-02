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

include(":lib:controll:api")
include(":lib:controll:impl")
