# Java House Price Prediction GUI

This folder contains the **Java Swing-based desktop application** for the House Price Prediction project.  
The Java UI collects user inputs, sends them to the Python Flask backend, receives the predicted price, and displays it inside a popup window.

This demonstrates a real-world **cross-language integration workflow** between Java and Python.

---

## Contents

- **src/HousePriceGUI.java**  
  Main Java source file containing the Swing user interface and HTTP request logic.

- **.gitignore**  
  Java-specific ignore rules (compiled `.class` files, IDE configs, cache folders, etc.)

- **README.md**  
  Documentation for the Java GUI.

---

## How to Run the Java GUI

### Compile the Java source code
```bash
javac src/HousePriceGUI.java
### Run the GUI application
\`\`\`bash
java -cp src HousePriceGUI
\`\`\`

The Python backend must already be running at:  
**http://127.0.0.1:5000/predict**

If the backend is not running, the Java app will show a **connection error**.
## How Java Communicates With Python

The Java app sends a **POST request** to the Python Flask API using an HTTP client.

### Example JSON sent from Java:
\`\`\`json
{
  "area": 2200,
  "bedrooms": 3,
  "bathrooms": 2,
  "stories": 2
}
\`\`\`

### Example JSON received back:
\`\`\`json
{
  "predicted_price": 7431415.18
}
\`\`\`

The predicted price is then shown in a **Swing popup dialog**.
## GUI Features

- Input fields for **area**, **bedrooms**, **bathrooms**, and **stories**  
- **"Predict"** button triggers the ML request  
- Responsive dialog showing the **predicted house price**  
- Input validation for **numeric fields**  
- Clear separation of **UI logic** and **network request logic**  
- Modular design for improved **readability** and **maintenance**  

---

## Requirements

- **Java JDK 8+**  
- Internet permission (default)  
- Python backend running locally  

---

## Notes

- You can modify the backend URL inside `HousePriceGUI.java`  
  if your Flask API uses a different host or port.
- Ensure that both Java and Python are running from the correct directories.
- If extending the GUI (dropdowns, validation, styling), keep logic modular.

---

## License

This project is intended for **educational and demonstration purposes**.
