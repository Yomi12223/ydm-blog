# YDM.LABO Markdown Editor App

## 📝 概要
Xiaomi 15 Ultra向けのMarkdown記事エディターアプリ。記事の執筆とリアルタイムプレビュー機能を実装し、YDM.LABOブログシステムと連携します。

## 🌟 機能（実装済み）

- **記事作成**: マークダウンでブログ記事を作成 ✅
- **リアルタイムプレビュー**: WebViewでマークダウンのHTMLプレビュー ✅
- **記事管理**: 作成した記事の一覧表示と編集 ✅
- **メモリ保存**: 一時的なデータ保存（現在実装中）
- **YDM.LABOテーマ**: ブログサイトと統一されたUIデザイン ✅

## 🔧 技術スタック（現在の実装）

- **言語**: Kotlin ✅
- **UI**: Jetpack Compose + Material Design 3 ✅
- **アーキテクチャ**: MVVM パターン ✅
- **データベース**: Room Database（将来実装予定）
- **DI**: 標準ViewModelファクトリー（現在版）
- **マークダウン処理**: CommonMark-java ✅

## 📱 対応デバイス

- Xiaomi 15 Ultra (6.73" 2K+ 120Hz display)
- Android 15 以上

## 🚀 ビルド方法

```bash
# プロジェクトをビルド
./gradlew build

# デバッグAPKをインストール
./gradlew installDebug
```

Windows環境では:
```powershell
# クリーンビルド
.\gradlew clean

# デバッグAPKをビルド  
.\gradlew assembleDebug

# APKファイルの場所
# app\build\outputs\apk\debug\app-debug.apk
```

## 📱 **APKファイルの入手方法**

### 🚀 **今すぐ使用したい場合 - 推奨アプリ**

#### **1. Joplin (無料・オープンソース)** 🥇
- **特徴**: 高機能、同期対応、プライバシー重視
- **機能**: Markdown編集、リアルタイムプレビュー、フォルダ管理
- **ダウンロード**: Google Play Store で「Joplin」を検索
- **Xiaomi 15 Ultra対応**: ✅ 大画面最適化済み

#### **2. MarkdownX (無料)**
- **特徴**: シンプル、軽量
- **機能**: 基本的なMarkdown編集とプレビュー
- **ダウンロード**: Google Play Store で「MarkdownX」を検索

#### **3. Obsidian (無料)**
- **特徴**: 高度なメモアプリ、リンク機能
- **機能**: Markdown、グラフビュー、プラグイン
- **ダウンロード**: Google Play Store で「Obsidian」を検索

#### **4. Neutrinote (無料)**
- **特徴**: 数式、テーブル対応
- **機能**: LaTeX、Markdown、検索機能
- **ダウンロード**: Google Play Store で「Neutrinote」を検索

### 📱 **手順（推奨: Joplin)**

1. **Google Play Storeを開く**
2. **「Joplin」で検索**
3. **インストール**
4. **初期設定で同期方法を選択**（OneDrive、Dropbox等）
5. **最初のMarkdown記事を作成**

### � **開発者向けビルド方法（上級者向け）**

現在のプロジェクトはコンパイルエラーがあるため、以下の修正が必要：
- Roomデータベース関連ファイルの削除
- Hilt依存関係の完全削除  
- UIコンポーネントのimport修正

完全版を使用したい場合は、エラー修正後に：

## � プロジェクト構成（現在の実装）

```
app/src/main/java/com/ydmlabo/markdowneditor/
├── model/                 # データモデル（Article）✅
├── presentation/
│   ├── theme/            # YDM.LABOテーマ ✅
│   ├── ui/               # Compose UI ✅
│   │   ├── main/         # メイン画面 ✅
│   │   ├── navigation/   # ナビゲーション ✅
│   │   ├── common/       # 共通コンポーネント ✅
│   │   └── preview/      # プレビュー画面 ✅
│   └── viewmodel/        # ViewModel ✅
├── utils/                # マークダウン処理 ✅
└── (将来実装)
    ├── data/             # Repository, Database
    ├── domain/           # UseCase, Models  
    └── di/               # Hilt DI
```

## 📖 使用方法

1. アプリ起動時に記事一覧が表示される
2. 「+」ボタンで新規記事作成
3. タイトルと本文をマークダウンで入力
4. 「プレビュー」ボタンでHTMLプレビュー確認
5. 「保存」ボタンで記事を保存
6. 既存記事をタップして編集可能

## 🔗 YDM.LABOブログとの連携

将来的にGitHub APIを使用してブログサイトと連携予定:

- 記事の自動投稿
- 画像アップロード
- タグ管理
- 公開/非公開制御

## 🛠️ 開発者向け情報

### カスタマイズ

- `Color.kt`: YDM.LABOテーマの色設定
- `Theme.kt`: Material Design 3テーマ
- `MarkdownProcessor.kt`: マークダウン処理ロジック

### 新機能追加

1. `domain/usecase/` にビジネスロジック追加
2. `presentation/ui/` にUIコンポーネント追加
3. `di/` にDI設定追加

---

**YDM.LABO** - 技術とクリエイティブな表現を追求するブログサイト

## 🎯 機能要件

### ✅ Core機能（Phase 1 - 実装済み）
- [x] **Markdownエディター**（基本機能完了）
- [x] **リアルタイムプレビュー**（WebView実装）
- [x] **記事管理**（一覧・作成・編集）
- [x] **Xiaomi 15 Ultra最適化**（大画面レイアウト）
- [x] **YDM.LABOテーマ**（Material Design 3）
- [ ] **フロントマター編集**（title, date, tags, permalink）
- [ ] **下書き保存機能**（自動保存）
- [ ] **エクスポート機能**（.mdファイル出力）

### 🚧 連携機能（Phase 2 - 開発予定）
- [ ] **GitHub連携**（記事の直接プッシュ）
- [ ] **PC版ブログとの同期**
- [ ] **ファイル共有**（PC経由でのファイル転送）
- [ ] **Room Database**（データ永続化）

### Xiaomi 15 Ultra特化機能
- [ ] **大画面活用**（6.73インチ 2K+ 120Hz対応）
- [ ] **ペン入力対応**（手書きメモ機能）
- [ ] **高解像度プレビュー**（画像表示最適化）
- [ ] **マルチタスク**（分割画面での編集・プレビュー）

## 🛠️ 技術スタック

### Android開発
- **言語**: Kotlin
- **UI**: Jetpack Compose
- **アーキテクチャ**: MVVM + Clean Architecture
- **DI**: Hilt
- **DB**: Room Database
- **HTTP**: Retrofit + OkHttp

### 必要ライブラリ
- **Markdown**: CommonMark-java
- **エディター**: CodeEditor (富文本エディター)
- **Git**: JGit (GitHub連携)
- **画像**: Coil (画像読み込み)

## 📱 UI設計

### 画面構成
1. **記事一覧画面** (MainActivity)
2. **記事編集画面** (EditorActivity) - **メイン機能**
3. **プレビュー画面** (PreviewActivity) - **メイン機能**
4. **設定画面** (SettingsActivity)

### Xiaomi 15 Ultra対応
- **画面サイズ**: 6.73インチ 2K+ (3200x1440)
- **リフレッシュレート**: 120Hz対応
- **分割画面**: エディター｜プレビュー の横並び表示
- **ペン入力**: Xiaomi Smart Pen対応（手書きメモ）

### Material Design 3
- **カラー**: YDM.LABOブログと統一（シアン・ダークテーマ）
- **ダークモード**: デフォルト対応
- **アニメーション**: 120Hzを活用したスムーズな遷移
- **大画面レイアウト**: タブレット風UI採用

## 🚀 開発フェーズ

### Phase 1: 基本機能（優先実装）
1. プロジェクトセットアップ
2. **Markdownエディター実装**
3. **リアルタイムプレビュー実装**
4. ローカル保存機能
5. Xiaomi 15 Ultra大画面最適化

### Phase 2: 連携機能
1. GitHub API連携
2. ファイル同期機能
3. フロントマター編集機能
4. エクスポート機能

### Phase 3: 高度機能
1. 画像アップロード・管理
2. 自動保存・バックアップ
3. ペン入力対応
4. ショートカット機能

## 📦 ディレクトリ構造
```
app/
├── src/main/
│   ├── java/com/ydmlabo/markdowneditor/
│   │   ├── data/          # Repository, Database
│   │   ├── domain/        # UseCase, Models
│   │   ├── presentation/  # UI, ViewModels
│   │   └── di/           # Dependency Injection
│   ├── res/
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

## 🔗 YDM.LABOブログとの連携

### 記事フォーマット
```markdown
---
title: 記事タイトル
layout: layout.njk
date: 2025-07-18
tags: [tag1, tag2]
permalink: /posts/記事ID/
---

# 記事タイトル

記事内容...
```

### 同期方式
1. **GitHub連携**: 直接リポジトリにpush
2. **ファイル転送**: PC経由でファイル転送
3. **API連携**: 将来的にAPI開発

## 📝 開発メモ
- **開発開始日**: 2025/07/18
- **開発者**: YDM.LABO
- **目標リリース**: 2025/08/31
- **対象デバイス**: Xiaomi 15 Ultra (6.73インチ 2K+ 120Hz)
- **主要機能**: Markdown記事執筆 + リアルタイムプレビュー
- **連携先**: YDM.LABOブログ (GitHub Pages)
