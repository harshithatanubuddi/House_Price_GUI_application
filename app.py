from flask import Flask, request, jsonify
import pickle
import pandas as pd

# === Load model, encoders, and scaler ===
with open("house_model.pkl", "rb") as f:
    model, encoders, scaler = pickle.load(f)

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()

    # Convert to DataFrame
    df = pd.DataFrame([data])

    # Apply encoders
    for col in encoders:
        if col in df.columns:
            df[col] = encoders[col].transform(df[col])

    # Scale features
    df_scaled = scaler.transform(df)

    # Predict
    prediction = model.predict(df_scaled)[0]

    return jsonify({"predicted_price": round(prediction, 2)})

if __name__ == '__main__':
    app.run(debug=True)
