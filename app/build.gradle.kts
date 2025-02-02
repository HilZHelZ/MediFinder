plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services) // Google Services plugin for Firebase
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.hospitalfindertest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hospitalfindertest"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX Core and UI components
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.cardview:cardview:1.0.0")

    // Firebase dependencies
    implementation(libs.firebase.auth) // Firebase Authentication
    implementation(libs.firebase.messaging) // Firebase Cloud Messaging for push notifications

    // Barcode and QR code scanning
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("me.dm7.barcodescanner:zxing:1.9.8") {
        exclude(group = "com.android.support") // Exclude deprecated Android Support libraries
    }

    // Google Maps and Location SDKs
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Unit testing
    testImplementation(libs.junit)

    // Android testing
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

// Apply the Google Services plugin to enable Firebase features
apply(plugin = "com.google.gms.google-services")
