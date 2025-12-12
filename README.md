# House Price Prediction Project

This repository contains both **Python** and **Java** implementations for a house price prediction system.  
It demonstrates a complete workflow involving machine learning model training, API-based prediction, and a Java Swing desktop interface.

---

## Project Structure

- **python_app/**  
  Contains the Python backend built with Flask and a trained Scikit-learn model (Pickle).  
  This service exposes an API endpoint for predicting house prices.

- **java_app/**  
  Contains the Java Swing GUI application.  
  The Java frontend sends input data to the Python API and displays the predicted output.

- **.gitignore**  
  Root-level ignore file for excluding temporary/system-specific files.

---

## How to Use

Each component has its own setup and usage instructions:

- For the **Python ML API**, see:  
  `python_app/README.md`

- For the **Java GUI application**, see:  
  `java_app/README.md`

---

## Requirements

- **Python 3.x**  
  Install dependencies listed in `python_app/requirements.txt`

- **Java JDK 8+**  
  Required to compile and run the Swing-based UI

---

## Summary

This project showcases:

- Machine learning model deployment using Python  
- Java ↔ Python cross-language communication  
- Real-time prediction via REST API  
- A modular structure separating UI, ML logic, and API services  

---

