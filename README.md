
# Let It Up 🎸

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Firebase](https://img.shields.io/badge/Powered%20By-Firebase-FFCA28?logo=firebase&logoColor=white)](https://firebase.google.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**Let It Up** is an immersive Android application designed to transform the concert-going experience. By synchronizing device hardware (flash and screen brightness) with real-time lyrics, it creates a unified crowd-interaction platform that bridges the gap between performer and audience.

---

## 📱 Visual Showcase

<div align="center">
  <table style="border: none;">
    <tr>
      <td align="center"><img src="https://user-images.githubusercontent.com/66652532/216084006-4a4f1e0d-0f46-4e7b-83a2-dfcb5052bdd4.png" width="160" /><br/><sub><b>Splash Screen</b></sub></td>
      <td align="center"><img src="https://user-images.githubusercontent.com/66652532/216088166-5e3232bd-44ce-4275-91c0-61a9e44eeb9d.png" width="160" /><br/><sub><b>Login</b></sub></td>
      <td align="center"><img src="https://user-images.githubusercontent.com/66652532/216087921-affab7ae-e7c5-4740-bcc3-6d1ee9515d6f.png" width="160" /><br/><sub><b>Navigation</b></sub></td>
      <td align="center"><img src="https://user-images.githubusercontent.com/66652532/216093075-25d8be78-44ce-4275-91c0-61a9e44eeb9d.png" width="160" /><br/><sub><b>Song Selection</b></sub></td>
      <td align="center"><img src="https://user-images.githubusercontent.com/66652532/216093226-d84e7933-05c0-4eac-8b1d-976cba87557f.png" width="160" /><br/><sub><b>Lyrics & Flash</b></sub></td>
    </tr>
  </table>
</div>

---

## ✨ Key Features

- **🔄 Synchronized Flash & Lyrics**: Real-time lyric fetching and display synchronized with rhythmic camera flash pulses.
- **🔆 Dynamic Brightness Control**: Automatically modulates screen brightness and colors to match the concert atmosphere.
- **🤝 Crowd Interaction**: Encourages mass participation, allowing entire audiences to sync their devices for collective visual effects.
- **🔐 Secure Authentication**: Integrated Firebase Phone Authentication for seamless and secure user onboarding.

---

## 🛠 Technical Stack & Architecture

### Core Technologies
*   **Networking**: [Retrofit 2](https://square.github.io/retrofit/) with GSON converter for type-safe API consumption.
*   **Backend**: [Firebase](https://firebase.google.com/) (Realtime Database, Storage, and Authentication).
*   **Image Loading**: [Picasso](https://square.github.io/picasso/) for efficient remote image caching and display.
*   **Lyrics Engine**: [MusixMatch API](https://developer.musixmatch.com/) integration.

### Engineering Patterns
*   **Architecture**: Follows clean Android principles with a focus on UI/UX responsiveness.
*   **Design Patterns**: Extensively uses the **Adapter Pattern** for flexible list management.
*   **Concurrency**: Managed via `Handler`, `Runnable`, and `AsyncTask` for smooth hardware interactions without blocking the Main UI Thread.

---

## 🚀 Getting Started

### Prerequisites
- Android 4.0 (API Level 14) or higher.
- Device with a functional Camera Flash.
- Write Settings permission (for Brightness Control).

### Installation
1.  **Clone the Repository**
    ```bash
    git clone https://github.com/liwaakal/Let-It-Up.git
    ```
2.  **Open in Android Studio**
    Import the project and sync the Gradle files.
3.  **Firebase Configuration**
    Add your `google-services.json` to the `app/` directory.
4.  **API Keys**
    Add your MusixMatch API key in the `LyricsAndFlashActivity.java`.
5.  **Build & Run**
    Deploy to your physical device or emulator.

---

## 📖 Usage
1.  **Login**: Verify your phone number to enter the platform.
2.  **Pick a Song**: Select from a curated list or search for your favorite track.
3.  **Sync**: The app will automatically fetch lyrics and begin the flash synchronization.
4.  **Adjust**: Use the brightness control module to fine-tune your device's visual output.

---

<p align="center">
  Developed with ❤️ as a final project for Advanced Android Development.
</p>


