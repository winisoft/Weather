import com.android.build.api.variant.VariantFilter

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Config.compileSdk)
    defaultConfig {
        applicationId = "stevemerollis.codetrial.weather"
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    flavorDimensions("environment", "audience")
    productFlavors {
        create("validation") {
            dimension = "audience"
            applicationIdSuffix = ".test"
        }
        create("demo") {
            dimension = "audience"
        }
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
        }
    }

    variantFilter = Action<VariantFilter> {
        repeat(flavors.filter { flavor ->
            flavor.name.contains("validation", true)
            && buildType.name.contains("release")
        }.size) {
            this@Action.ignore = true
        }
    }

    sourceSets {

        getByName("main") {
            java.srcDirs(
                "src/main/kotlin",
                "src/main/ext",
                "src/config/java",
                "src/config/kotlin",
                "src/integration/kotlin",
                "src/integration/tools"
            )
        }

        getByName("test") {
            java.srcDirs(
                "src/test/kotlin",
                "src/test/ext",
                "src/test/tools"
            )
        }

        getByName("dev") {
            java.srcDir("src/dev/kotlin")
            res.srcDirs("src/main/res", "src/dev/res")
        }
    }

    lint {
        isAbortOnError = false
        isCheckReleaseBuilds = false
        disable.addAll(
            listOf("MissingDefaultResource", "MissingTranslation", "EarlySyncBuildOutput")
        )
    }

    kotlinOptions {
        jvmTarget = "11"
        useIR = true
        freeCompilerArgs = freeCompilerArgs.plus(listOf("-Xopt-in=kotlin.RequiresOptIn"))
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.annotation)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.CodeQuality.leakCanary)
    implementation(Dependencies.Hilt.core)
//    implementation(Dependencies.Hilt.common)
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.androidCompiler)
    kapt(Dependencies.Hilt.androidx_compiler)
    kapt(Dependencies.Hilt.dagger_compiler)
    implementation(Dependencies.Jetpack.Lifecycle.savedState)
    implementation(Dependencies.Jetpack.Lifecycle.liveDataKtx)
    implementation(Dependencies.Jetpack.Lifecycle.runtimeKtx)
    implementation(Dependencies.Jetpack.Lifecycle.extensions)
    implementation(Dependencies.Jetpack.Lifecycle.commonJava8)
    implementation(Dependencies.Jetpack.Lifecycle.viewModelKtx)
    implementation(Dependencies.Jetpack.Lifecycle.liveDataKtx)
    implementation(Dependencies.Jetpack.Lifecycle.commonJava8)
    implementation(Dependencies.Jetpack.Navigation.navigationFragment)
    implementation(Dependencies.Jetpack.Navigation.navigationUi)
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.stdlib_jdk8)
    implementation(Dependencies.KotlinX.coroutinesCore)
    implementation(Dependencies.KotlinX.coroutinesAndroid)
    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.moshiCodegen)
    implementation(Dependencies.Network.moshiAdapters)
    implementation(Dependencies.Network.moshiConverter)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.loggingInterceptor)
    implementation(Dependencies.Util.timber)
    testImplementation(Dependencies.Test.assertJ)
    testImplementation(Dependencies.Test.junit5_api)
    testRuntimeOnly(Dependencies.Test.junit5_engine)
    implementation("com.github.MatteoBattilana:WeatherView:3.0.0")
    implementation(group = "androidx.core", name = "core-ktx", version = "1.3.2")
    implementation(group = "androidx.test.espresso", name = "espresso-idling-resource", version = "3.4.0-alpha05")
    androidTestImplementation(Dependencies.AndroidTest.espresso)
    androidTestImplementation(Dependencies.AndroidTest.runner)
    androidTestImplementation(Dependencies.AndroidTest.espressoIntents)
    androidTestImplementation(Dependencies.AndroidTest.hamcrest)
    implementation("com.vmadalin:easypermissions-ktx:0.1.0")

    val composeExt2Version = "1.0.0-alpha03"
    val composeExtVersion = "1.0.0-beta04"
    implementation("androidx.compose.compiler", name = "compiler", version = composeExtVersion)
    implementation(group = "androidx.compose.ui", name = "ui", version = composeExtVersion)
    implementation(group = "androidx.compose.material", name = "material", version = composeExtVersion)
    implementation("androidx.compose.material", "material-icons-core", version = composeExtVersion)
    implementation("androidx.compose.material", "material-icons-extended", version = composeExtVersion)
    implementation("androidx.compose.ui:ui:1.0.0-beta04")
    // Tooling support (Previews, etc.)
    implementation(group = "androidx.compose.ui", name = "ui-tooling", version = "1.0.0-beta04")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation", "foundation", version = composeExtVersion)
    // Material Design
    implementation("androidx.compose.material", "material", composeExtVersion)
    // Integration with activities
    implementation("androidx.activity", "activity-compose", version = "1.3.0-alpha06")
    // Integration with ViewModels
    implementation("androidx.lifecycle", "lifecycle-viewmodel-compose", version = composeExt2Version)
    // Integration with observables
    implementation("androidx.compose.runtime", "runtime-livedata", version = composeExtVersion)
    implementation("br.com.devsrsouza.compose.icons.android", "font-awesome", "0.2.0")
    implementation("br.com.devsrsouza.compose.icons.android", "erikflowers-weather-icons", "0.2.0")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta04")

}
