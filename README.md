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
  * **Shape Selection Dropdown:** Easily switch between Free Draw and geometric shape tools (Line, Rectangle, Oval).
  * **Eraser Tool:** Dedicated erase tool for clean error corrections.
  * **Fill Tool:** Flood fill / bucket tool to quickly color closed shapes or areas.
  * **Shift-to-Straighten:** Hold `Shift` while drawing a Line to lock it perfectly horizontal or vertical, or while drawing a Rectangle/Oval to lock it to a perfect square/circle — works with Fill on or off.

* **🔌 Circuit Design Suite:**
  * **Circuit Design Menu:** A dedicated toolbar menu with click-and-drag schematic symbols for **Wire**, **Resistor**, **Capacitor**, **Inductor**, **Battery**, **Ground**, **Switch**, **Diode**, and **LED**.
  * **Select / Move Tool:** Click any placed circuit component to move its body, or drag either end to resize/re-angle it — complete with live selection handles.
  * **Magnet (Snap) Toggle:** Optional snapping that aligns new or moved components and textboxes to the background grid and to the endpoints of nearby components, for clean, connected circuits.
  * **Textbox Labels:** Add movable, editable text labels (e.g. `R1 = 220Ω`) anywhere on the canvas to annotate component values — double-click a label to edit it.
  * **Delete Key Support:** Select a component or textbox and press `Delete`/`Backspace` to remove it.

* **🔲 Canvas & Workspace Controls:**
  * **Grid Overlay Toggle:** Toggleable background grid lines for accurate alignment and precision design.
  * **Clear Canvas:** Reset the drawing board with a single click.
  * **Undo / Redo System:** Seamless action stack to undo or redo drawing steps, including circuit component and textbox edits — also available via `Ctrl+Z` / `Ctrl+Y` keyboard shortcuts.
  * **Live Cursor Tracker:** Real-time `x` and `y` pixel coordinate display in the bottom status bar.

* **💾 Image Export:**
  * Integrated native file chooser (`JFileChooser`) to save creations as **PNG** image files.
  * Exports flatten the raster canvas together with all placed circuit components and textboxes, so the saved PNG always matches what's on screen.

---

## 🛠️ Tech Stack & Requirements

* **Language:** Java (JDK 8 or higher / JDK 26 recommended)
* **GUI Framework:** Java Swing (`javax.swing.*`) & AWT (`java.awt.*`)
* **Development Environment:** Visual Studio Code / IntelliJ IDEA / Eclipse

---

## 📁 Project Structure

```
Sobuj's Drawing Pad\
├── Drawing_Pad.java      # Main application source code
├── README.md             # Project documentation
└── Screenshots\          # UI screenshots for documentation
    ├── Tool Controls .png
    ├── Color Chooser Palette 1 .png
    ├── Color Chooser Palette 2 .png
    ├── Color Chooser Palette 3 .png
    ├── Color Chooser Palette 4 .png
    ├── Color Chooser Palette 5 .png
    ├── Canvas Drawing & Opacity .png
    ├── Freehand Drawing & Shapes .png
    ├── Grid Overlay View .png
    ├── Basic Circuit Design .png
    ├── Easy Circuit Design with Grid Function .png
    ├── Magnet Snapping .png
    ├── Textbox Value Labels .png
    ├── Selecting & Moving Circuit Components 1 .png
    ├── Selecting & Moving Circuit Components 2 .png
    ├── Shift-Constrained Straight Lines & Perfect Shapes .png
    ├── Native File Export 1 .png
    └── Native File Export 2 .png
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
5. **Straight Lines & Perfect Shapes:** Hold `Shift` while dragging with the Line tool to lock it horizontal/vertical, or with the Rectangle/Oval tool to lock it to a perfect square/circle.
6. **Designing Circuits:** Click `Circuit Design` in the toolbar, pick a component (Wire, Resistor, Capacitor, Inductor, Battery, Ground, Switch, Diode, LED), then click-and-drag on the canvas to place it.
7. **Moving & Editing Components:** Open `Circuit Design` → `Select / Move`, click a component to select it, then drag its body to move it or drag an end to resize/re-angle it. Press `Delete`/`Backspace` to remove the selected item.
8. **Snapping with Magnet:** Toggle the `Magnet` button to snap components and textboxes to the grid and to each other's endpoints while placing or moving them.
9. **Adding Value Labels:** Click `Textbox`, then click on the canvas and type a label (e.g. `R1 = 220Ω`). Double-click a placed label to edit its text.
10. **Undo/Redo:** Use the `Undo`/`Redo` buttons or the `Ctrl+Z` / `Ctrl+Y` shortcuts to step back and forth through your edits.
11. **Saving Your Work:** Click `Save PNG`, select your preferred folder, enter a file name, and click `Save`.

---

## 📷 Screenshots

| Tool Controls & Color Chooser |
|:---:|
<img width="1920" height="1018" alt="Tool Controls   Color Chooser " src="https://github.com/user-attachments/assets/33471a6f-049c-4ac7-bf2a-cd4d83425a62" />


---
| Canvas Drawing & Opacity |
| :---: |
<img width="1920" height="1020" alt="Canvas Drawing   Opacity " src="https://github.com/user-attachments/assets/4b301d42-b5a2-40ee-813c-399a96dfc4c0" />


---
| Color Chooser Palette | 
|:---:|
<img width="765" height="473" alt="Color Chooser Palette 1 " src="https://github.com/user-attachments/assets/8dd497a9-b363-4a3c-b5dd-a0515a0f550f" />
<img width="765" height="473" alt="Color Chooser Palette 2 " src="https://github.com/user-attachments/assets/550e0784-8141-4240-9057-9e07110a493f" />
<img width="765" height="473" alt="Color Chooser Palette 3 " src="https://github.com/user-attachments/assets/32160d60-e3e3-48db-9e67-02093288770d" />
<img width="765" height="473" alt="Color Chooser Palette 4 " src="https://github.com/user-attachments/assets/a75c137e-ea16-438c-a16c-a80cdae0e5ba" />
<img width="765" height="473" alt="Color Chooser Palette 5 " src="https://github.com/user-attachments/assets/babb3039-0e95-48b0-ad74-6326f5151de3" />

---
| Freehand Drawing & Shapes |
|:---:|
<img width="1920" height="1020" alt="Freehand Drawing   Shapes " src="https://github.com/user-attachments/assets/50fc4795-0a29-4a9d-b533-96a4efeea2c3" />

---
| Grid Overlay View |
| :---: |
<img width="1920" height="1020" alt="Grid Overlay View " src="https://github.com/user-attachments/assets/644b4f55-2aad-4957-9bcf-72bd7f91ba89" />

---
| Basic Circuit Design |
| :---: |
<img width="1920" height="1020" alt="Basic Circuit Design " src="https://github.com/user-attachments/assets/e370d912-ae51-40cf-8c3b-3ec5fb07123e" />
Circuit Design is much easier with the Grid function
<img width="1920" height="1020" alt="Easy Circuit Design with Grid Function " src="https://github.com/user-attachments/assets/f3a22a0d-3fc7-40cc-af9f-0c8cc8f1efc2" />

---
| Magnet Snapping |
| :---: |
<img width="106" height="38" alt="Magnet Snapping " src="https://github.com/user-attachments/assets/f2735f2b-2472-4521-a733-ac33cc1314c0" />


---
| Textbox Value Labels |
| :---: |
<img width="1920" height="1020" alt="Textbox Value Labels " src="https://github.com/user-attachments/assets/ac25132f-d71c-4e09-ad0d-677ae456e2dc" />

---
| Selecting & Moving Circuit Components |
| :---: |
<img width="1535" height="815" alt="Selecting   Moving Circuit Components 1 " src="https://github.com/user-attachments/assets/dd09a335-0b88-4b6a-af59-9b8f2a78bec0" />
<img width="1920" height="1020" alt="Selecting   Moving Circuit Components 2 " src="https://github.com/user-attachments/assets/d6e6eafb-100a-4ed5-85ad-0111e4c0b426" />

---
| Shift-Constrained Straight Lines & Perfect Shapes |
| :---: |
<img width="1920" height="1020" alt="Shift-Constrained Straight Lines   Perfect Shapes " src="https://github.com/user-attachments/assets/5e81e3b0-c50f-4e40-b8df-923d90d7920f" />

---
| Native File Export |
| :---: |
<img width="1535" height="815" alt="Native File Export 1 " src="https://github.com/user-attachments/assets/d3ea6312-9b74-4cb9-b5e9-653334766af5" />
<img width="1536" height="706" alt="Native File Export 2 " src="https://github.com/user-attachments/assets/7769f558-84bb-4880-836f-cac200ef478a" />

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
