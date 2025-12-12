# Python House Price Prediction Backend

This folder contains the **Python machine learning backend** for the House Price Prediction project.  
It loads a trained Scikit-learn model, exposes a **Flask API endpoint** for real-time prediction, and communicates with the Java Swing UI.

---

## Contents

- **app.py** – Flask API that loads the model and performs prediction  
- **Untitled.ipynb** – Notebook for model training  
- **model/house_model.pkl** – Trained ML model  
- **data/housing_dataset.csv** – Dataset for training  
- **requirements.txt** – Python dependencies  
---
## How to Run the Backend

### 1. Install dependencies  
\`\`\`bash
pip install -r requirements.txt
\`\`\`

### 2. Start the Flask server  
\`\`\`bash
python app.py
\`\`\`

The API will be available at:  
http://127.0.0.1:5000/predict

---

## API Endpoint

### POST `/predict`

#### Request Example  
\`\`\`json
{
  "area": 2200,
  "bedrooms": 3,
  "bathrooms": 2,
  "stories": 2
}
\`\`\`

#### Response Example  
\`\`\`json
{
  "predicted_price": 7431415.18
}
\`\`\`

---

## Java ↔ Python Integration Workflow

1. **Java Swing UI** collects user inputs.  
2. Java sends an **HTTP POST** request to `/predict` with JSON payload.  
3. **Python Flask backend** receives the request and parses incoming features.  
4. The backend loads the **Pickle ML model** (if not already loaded) and performs inference using Scikit-learn.  
5. Flask returns a **JSON response** containing the predicted house price.  
6. Java parses the JSON response and displays the predicted value in a **popup window**.

This demonstrates a real-world **cross-language ML deployment pipeline** (desktop client ↔ ML service).

---

## Model Information

- Built using **Scikit-learn**.  
- Dataset size: **10,000+ records**.  
- Includes preprocessing, scaling, and regression modeling.  
- Model serialized as `model/house_model.pkl` (Pickle) for fast inference.  
- Designed for offline/desktop usage where a lightweight API serves predictions to the Java client.

---

## Notes

- Ensure the Flask backend is running **before** launching the Java application.  
- If you change host/port in `app.py`, update the Java client's request URL accordingly.  
- Keep the folder structure intact so the backend can locate the model and data files.  
- For reproducibility, include environment information (Python version, major library versions) in `requirements.txt`.

---

## License

This project is provided for **educational and demonstration purposes**.
