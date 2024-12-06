plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Se estiver usando Kapt
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" // Se estiver usando KSP
}

android {
    namespace = "com.example.foodtech"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodtech"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1") // Certifique-se de usar a dependência correta
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.car.ui.lib) // Adiciona suporte para coroutines no Room
    kapt("androidx.room:room-compiler:2.6.1") // Usando Kapt para o processamento de anotações do Room

    // Coroutines and Lifecycle dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("androidx.cardview:cardview:1.0.0")

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
