import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")

}

fun getLocalProperty(key: String, defaultValue: String): String {
    val properties = Properties()
    val localProperties = rootProject.file("local.properties")
    if (localProperties.exists()) {
        properties.load(FileInputStream(localProperties))
    }
    return properties.getProperty(key, defaultValue)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GEMINI_API_KEY", "\"${getLocalProperty("GEMINI_API_KEY", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }

}
dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat.v170)
    implementation(libs.material.v1110)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v121)
    androidTestImplementation(libs.espresso.core.v361)
    implementation("org.apache.httpcomponents:httpclient:4.5.14") {
        exclude(group = "org.apache.httpcomponents", module = "httpcore")
    }
    implementation(libs.google.cloud.aiplatform)
}