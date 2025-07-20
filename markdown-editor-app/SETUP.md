# Android Studio セットアップガイド

## 🚀 プロジェクト作成手順

### 1. Android Studio起動
1. **「New Project」** をクリック
2. **「Empty Activity」** を選択
3. **Next**をクリック

### 2. プロジェクト設定
```
Name: YDM Blog Editor
Package name: com.ydmlabo.markdowneditor
Save location: C:\Users\yompc\Desktop\my-blogv\markdown-editor-app
Language: Kotlin
Minimum SDK: API 24 (Android 7.0)
Build configuration language: Kotlin DSL
```

### 3. 必要な依存関係

#### build.gradle.kts (Module: app)
```kotlin
dependencies {
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
    
    // Room Database
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    
    // Hilt (DI)
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    
    // Markdown
    implementation("org.commonmark:commonmark:0.21.0")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // GitHub API
    implementation("org.kohsuke:github-api:1.315")
}
```

## 📱 最初の画面設計

### メイン画面のレイアウト案
```
┌─────────────────────┐
│   YDM Blog Editor   │ ← トップバー
├─────────────────────┤
│  📝 新規記事作成     │
├─────────────────────┤
│  記事一覧           │
│  ┌─────────────────┐ │
│  │ 📄 初投稿       │ │
│  │ 2024/05/20     │ │
│  └─────────────────┘ │
│  ┌─────────────────┐ │
│  │ 📄 記事タイトル  │ │
│  │ 2025/07/18     │ │
│  └─────────────────┘ │
└─────────────────────┘
```

## 🎨 カラーテーマ

### YDM.LABOブログに合わせたカラー
```kotlin
// colors.xml
<color name="ydm_primary">#00FFFF</color>      // シアン
<color name="ydm_background">#1C1C1C</color>   // ダークグレー
<color name="ydm_surface">#2E2E2E</color>      // ライトグレー
<color name="ydm_text">#F0F0F0</color>         // 白
```

## 📝 次のステップ
1. Android Studioでプロジェクト作成
2. 依存関係の追加
3. 基本的なUI実装
4. Markdownエディターの実装

**準備ができたら、次のステップに進みましょう！**
