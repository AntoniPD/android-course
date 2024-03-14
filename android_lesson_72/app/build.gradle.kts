plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.android_lesson_7"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android_lesson_7"
        minSdk = 34
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
    buildToolsVersion = "34.0.0"
}

dependencies {

    //-- default --
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //  The following dependencies are needed in order to call apis
    implementation("com.squareup.retrofit2:retrofit:2.1.0")
//  Gson is for parsing json to a model
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")
    //
    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
}