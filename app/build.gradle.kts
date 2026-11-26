import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

val localProps = Properties()
val localPropsFile = rootProject.file("local.properties")
if (localPropsFile.exists()) {
    localPropsFile.inputStream().use {
        localProps.load(it)
    }
}

    android {
        namespace = "ca.uqac.cours.moodsic_projetlibre_2026"
        compileSdk {
            version = release(37)
        }

        defaultConfig {
            applicationId = "ca.uqac.cours.moodsic_projetlibre_2026"
            minSdk = 24
            targetSdk = 37
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            buildConfigField(
                "String",
                "SUPABASE_URL",
                "\"${localProps.getProperty("SUPABASE_URL") ?: ""}\""
            )
            buildConfigField(
                "String",
                "SUPABASE_ANON_KEY",
                "\"${localProps.getProperty("SUPABASE_ANON_KEY") ?: ""}\""
            )
            buildConfigField(
                "String",
                "GOOGLE_WEB_CLIENT_ID",
                "\"${localProps.getProperty("GOOGLE_WEB_CLIENT_ID") ?: ""}\""
            )
        }

        buildTypes {
            release {
                optimization {
                    enable = false
                }
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        buildFeatures {
            compose = true
            buildConfig = true
        }
    }


    dependencies {

        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.compose.ui.tooling)
        implementation("androidx.credentials:credentials:1.5.0")
        implementation("androidx.credentials:credentials-play-services-auth:1.5.0")
        implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
        implementation("io.coil-kt:coil-compose:2.7.0")
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")

        implementation("androidx.compose.material:material-icons-core") //Pour get les icons de base
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
        implementation("androidx.navigation:navigation-compose:2.10.2")
        implementation("androidx.datastore:datastore-preferences:1.2.1")
        implementation("androidx.media3:media3-exoplayer:1.11.1")
        implementation("androidx.media3:media3-common:1.11.1")
        implementation("org.maplibre.compose:maplibre-compose:0.12.1")
        implementation("com.google.android.gms:play-services-location:21.3.0")

        implementation(platform("io.github.jan-tennert.supabase:bom:3.9.0"))
        implementation("io.github.jan-tennert.supabase:postgrest-kt")
        implementation("io.github.jan-tennert.supabase:auth-kt")
        implementation("io.github.jan-tennert.supabase:storage-kt")
        implementation("io.github.jan-tennert.supabase:realtime-kt")
        implementation("io.ktor:ktor-client-android:3.6.0")
    }
