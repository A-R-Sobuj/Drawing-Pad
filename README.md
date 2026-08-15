# Sobuj's Drawing Pad 🎨

A lightweight, feature-packed desktop digital drawing and sketching application built with **Java (Swing / AWT)**. **Sobuj's Drawing Pad** offers an intuitive GUI with versatile drawing tools, customizable brush stroke properties, color pickers, canvas grid controls, and PNG export functionality.

---

## 📑 Table of Contents
- [✨ Features](#-features)
- [🛠️ Tech Stack & Requirements](#️-tech-stack--requirements)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Compilation & Execution](#compilation--execution)
- [📖 How to Use](#-how-to-use)
- [📷 Screenshots](#-screenshots)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)

---

## ✨ Features

* **🎨 Advanced Color Chooser:**
  * Multi-tabbed color selector with support for **Swatches**, **HSV**, **HSL**, **RGB**, and **CMYK** modes.
  * Palette includes recent colors history and a live text/box sample preview.

* **🖌️ Customizable Brush & Stroke Settings:**
  * **Stroke Size:** Adjustable slider to set brush thickness dynamically.
  * **Alpha / Opacity:** Adjustable slider to set color transparency for shading and layering effects.
  * **Dynamic Brush Indicator:** A live preview icon next to the controls displaying current stroke size and visual feedback.

* **✏️ Drawing Tools & Modes:**
  * **Free Draw:** Smooth freehand sketching and drawing.
  * **Shape Selection Dropdown:** Easily switch between Free Draw and geometric shape tools.
  * **Eraser Tool:** Dedicated erase tool for clean error corrections.
  * **Fill Tool:** Flood fill / bucket tool to quickly color closed shapes or areas.

* **🔲 Canvas & Workspace Controls:**
  * **Grid Overlay Toggle:** Toggleable background grid lines for accurate alignment and precision design.
  * **Clear Canvas:** Reset the drawing board with a single click.
  * **Undo / Redo System:** Seamless action stack to undo or redo drawing steps.
  * **Live Cursor Tracker:** Real-time `x` and `y` pixel coordinate display in the bottom status bar.

* **💾 Image Export:**
  * Integrated native file chooser (`JFileChooser`) to save creations as **PNG** image files.

---

## 🛠️ Tech Stack & Requirements

* **Language:** Java (JDK 8 or higher / JDK 26 recommended)
* **GUI Framework:** Java Swing (`javax.swing.*`) & AWT (`java.awt.*`)
* **Development Environment:** Visual Studio Code / IntelliJ IDEA / Eclipse

---

## 📁 Project Structure

```
sobuj-drawing-pad/
├── Drawing_Pad.java        # Main application source code
├── README.md               # Project documentation
└── screenshots/            # UI screenshots for documentation
    ├── main_window.png
    ├── color_chooser.png
    ├── grid_view.png
    └── save_dialog.png
```

---

## 🚀 Getting Started

### Prerequisites

Ensure that you have Java Development Kit (JDK) installed on your system. You can verify your installation by running:

```bash
java -version
javac -version
```

### Compilation & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/sobuj-drawing-pad.git
   cd sobuj-drawing-pad
   ```

2. **Compile the source code:**
   ```bash
   javac Drawing_Pad.java
   ```

3. **Run the application:**
   ```bash
   java Drawing_Pad
   ```

---

## 📖 How to Use

1. **Selecting Colors:** Click on the `Color` button to open the advanced color picker window. Choose any color using Swatches, RGB, HSL, HSV, or CMYK tabs, then click `OK`.
2. **Adjusting Brush Properties:** Use the `Stroke` slider to change line thickness and the `Alpha` slider to change opacity.
3. **Drawing & Eraser:** Select `Free Draw` from the dropdown menu to start sketching. Click `Eraser` when you want to erase parts of your drawing.
4. **Grid Alignment:** Click the `Grid` button to toggle background grid lines on or off.
5. **Saving Your Work:** Click `Save PNG`, select your preferred folder, enter a file name, and click `Save`.

---

## 📷 Screenshots

| Tool Controls & Color Chooser |
|:---:|
<img width="1919" height="1015" alt="Screenshot 2026-08-15 144155" src="https://github.com/user-attachments/assets/10a6c84e-5e32-474b-9834-5270030c031b" />

---
| Canvas Drawing & Opacity |
| :---: |
<img width="1359" height="929" alt="Screenshot 2026-08-15 151550" src="https://github.com/user-attachments/assets/cb78939e-302c-4491-b5a8-fb3f8c9de6e6" />

---
| Color Chooser Palette | 
|:---:|
<img width="765" height="474" alt="Screenshot 2026-08-15 151647" src="https://github.com/user-attachments/assets/4d580feb-ba30-4c9b-8e48-187308082512" />
<img width="765" height="474" alt="Screenshot 2026-08-15 151650" src="https://github.com/user-attachments/assets/c9ff5be5-594f-45b9-b5a0-36db2ecea6ff" />
<img width="765" height="474" alt="Screenshot 2026-08-15 151654" src="https://github.com/user-attachments/assets/4f5b5035-5887-42fb-a4c8-bc2564701634" />
<img width="765" height="474" alt="Screenshot 2026-08-15 151657" src="https://github.com/user-attachments/assets/7edf8efa-01f7-491d-a51d-38a3a7162cc9" />
<img width="765" height="474" alt="Screenshot 2026-08-15 151659" src="https://github.com/user-attachments/assets/a650d5b5-47c7-40d2-99d7-bc0123c20288" />

---
| Freehand Drawing & Shapes |
|:---:|
<img width="1359" height="929" alt="Screenshot 2026-08-15 152010" src="https://github.com/user-attachments/assets/636b8dea-d585-4db0-8c31-be919ca82904" />

---
| Grid Overlay View |
| :---: |
<img width="1359" height="929" alt="Screenshot 2026-08-15 152143" src="https://github.com/user-attachments/assets/01d3b6bf-9ae6-4269-ac68-6bc4f2d35459" />

---
---
| Native File Export |
| :---: |
<img width="1358" height="924" alt="Screenshot 2026-08-15 152250" src="https://github.com/user-attachments/assets/878549b8-e9e0-4d0c-9faf-695ab2f1e135" />

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the issues page if you want to contribute.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the MIT License.
