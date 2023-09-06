object Dependencies {
    
    object Compose {
        private const val version = "1.5.0"
        const val activityCompose = "androidx.activity:activity-compose:1.5.1"
        const val composeBom = "androidx.compose:compose-bom:2022.10.00"

        const val ui ="androidx.compose.ui:ui:$version"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:$version"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material3 = "androidx.compose.material3:material3:1.1.1"

        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val testManifest = "androidx.compose.ui:ui-test-manifest"

        const val navigation = "androidx.navigation:navigation-compose:2.7.1"
        const val coil = "io.coil-kt:coil-compose:2.4.0"
    }

    object Core {
        const val ktx = "androidx.core:core-ktx:1.8.0"
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.8.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    }

}