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

| Tool Controls |
|:---:|
<img width="1920" height="1020" alt="Tool Controls " src="https://github.com/user-attachments/assets/9f8d92c6-de41-4666-a156-e1801a7b4229" />

---

| Color Chooser Palette | 
|:---:|
<img width="765" height="473" alt="Color Chooser Palette 1 " src="https://github.com/user-attachments/assets/9a27b90c-4f28-4732-a571-43ade99c5495" />
<img width="765" height="473" alt="Color Chooser Palette 2 " src="https://github.com/user-attachments/assets/5b5e719a-052e-4533-be49-a916e17d2362" />
<img width="765" height="473" alt="Color Chooser Palette 3 " src="https://github.com/user-attachments/assets/400f07f9-b6ed-4751-9db1-2a6a5ed60c60" />
<img width="765" height="473" alt="Color Chooser Palette 4 " src="https://github.com/user-attachments/assets/3b5cb9f1-62f3-41fb-901a-c6bbff52c231" />
<img width="765" height="473" alt="Color Chooser Palette 5 " src="https://github.com/user-attachments/assets/180b9677-78ec-4aa3-8679-ccffaded11ec" />

---

| Canvas Drawing & Opacity |
| :---: |
<img width="1920" height="1020" alt="Canvas Drawing   Opacity " src="https://github.com/user-attachments/assets/fa36b87e-eb7d-4cd7-b308-31e262dffac7" />

---

| Freehand Drawing & Shapes |
|:---:|
<img width="1920" height="1020" alt="Freehand Drawing   Shapes " src="https://github.com/user-attachments/assets/be8d421b-77c3-4202-9640-2fbd5971c75c" />

---

| Grid Overlay View |
| :---: |
<img width="1920" height="1020" alt="Grid Overlay View " src="https://github.com/user-attachments/assets/d550b55e-c62b-4b92-b0a9-1b437b838b40" />

---

| Basic Circuit Design |
| :---: |
<img width="1920" height="1020" alt="Basic Circuit Design " src="https://github.com/user-attachments/assets/87bcf7de-0205-45c9-ac13-784ad5fa46b5" />
Circuit design is easier with the Grid feature
<img width="1920" height="1020" alt="Easy Circuit Design with Grid Function " src="https://github.com/user-attachments/assets/102ba1a1-cec5-4761-be8a-0c94ccf70b6f" />

---

| Magnet Snapping |
| :---: |
<img width="103" height="37" alt="Magnet Snapping " src="https://github.com/user-attachments/assets/f82b50ba-894b-46be-9510-fd39b0b95910" />

---

| Textbox Value Labels |
| :---: |
<img width="1920" height="1020" alt="Textbox Value Labels " src="https://github.com/user-attachments/assets/7202fe75-3ead-48ce-a165-08a560b43da2" />

---

| Selecting & Moving Circuit Components |
| :---: |
<img width="1535" height="815" alt="Selecting   Moving Circuit Components 1 " src="https://github.com/user-attachments/assets/4b6da8ff-ead9-445a-b40e-780e78aab4b6" />
Circuit components can be resized and moved
<img width="1920" height="1020" alt="Selecting   Moving Circuit Components 2 " src="https://github.com/user-attachments/assets/73877d88-e0d2-40f6-a3be-570d9a9ab4f7" />

---

| Shift-Constrained Straight Lines & Perfect Shapes |
| :---: |
<img width="1920" height="1020" alt="Shift-Constrained Straight Lines   Perfect Shapes " src="https://github.com/user-attachments/assets/4106aab6-57c0-47fc-8f18-d6e41b8d9e7f" />

---

| Native File Export |
| :---: |
<img width="1535" height="815" alt="Native File Export 1 " src="https://github.com/user-attachments/assets/654674fa-c8ed-4ab0-82df-0755d3185d16" />
<img width="1536" height="731" alt="Native File Export 2 " src="https://github.com/user-attachments/assets/5646daeb-92bd-440f-8fbf-35544d811d96" />

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
