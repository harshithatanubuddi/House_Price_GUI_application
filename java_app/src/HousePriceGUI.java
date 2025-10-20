import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.net.*;
import java.io.*;

public class HousePriceGUI extends JFrame {

    private JTextField areaField, bedroomField, bathroomField, storiesField, parkingField;
    private JComboBox<String> mainroadBox, guestroomBox, basementBox, hotwaterBox, acBox, prefareaBox, furnishBox;

    public HousePriceGUI() {
        setTitle("🏠 Housing Price Prediction");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(15, 2, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);

        JLabel heading = new JLabel("🏡 Housing Price Prediction", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(0, 102, 204));
        add(heading, BorderLayout.NORTH);

        // Add fields with descriptions
        panel.add(new JLabel("Area (in sq.ft):"));
        areaField = new JTextField();
        panel.add(areaField);

        panel.add(new JLabel("Bedrooms:"));
        bedroomField = new JTextField();
        panel.add(bedroomField);

        panel.add(new JLabel("Bathrooms:"));
        bathroomField = new JTextField();
        panel.add(bathroomField);

        panel.add(new JLabel("Stories:"));
        storiesField = new JTextField();
        panel.add(storiesField);

        panel.add(new JLabel("Main Road (yes/no):"));
        mainroadBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(mainroadBox);

        panel.add(new JLabel("Guest Room (yes/no):"));
        guestroomBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(guestroomBox);

        panel.add(new JLabel("Basement (yes/no):"));
        basementBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(basementBox);

        panel.add(new JLabel("Hot Water Heating (yes/no):"));
        hotwaterBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(hotwaterBox);

        panel.add(new JLabel("Air Conditioning (yes/no):"));
        acBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(acBox);

        panel.add(new JLabel("Parking Spaces:"));
        parkingField = new JTextField();
        panel.add(parkingField);

        panel.add(new JLabel("Preferred Area (yes/no):"));
        prefareaBox = new JComboBox<>(new String[]{"yes", "no"});
        panel.add(prefareaBox);

        panel.add(new JLabel("Furnishing Status:"));
        furnishBox = new JComboBox<>(new String[]{"semi-furnished", "unfurnished", "furnished"});
        panel.add(furnishBox);

        JButton predictBtn = new JButton("Predict Price");
        predictBtn.setBackground(new Color(0, 153, 76));
        predictBtn.setForeground(Color.WHITE);
        predictBtn.setFont(new Font("Arial", Font.BOLD, 16));

        predictBtn.addActionListener(e -> sendPredictionRequest());
        panel.add(new JLabel()); // empty cell
        panel.add(predictBtn);
    }

    private void sendPredictionRequest() {
        try {
            // Build raw JSON string manually
            String json = String.format(
                    "{\"area\":%s,\"bedrooms\":%s,\"bathrooms\":%s,\"stories\":%s," +
                            "\"mainroad\":\"%s\",\"guestroom\":\"%s\",\"basement\":\"%s\"," +
                            "\"hotwaterheating\":\"%s\",\"airconditioning\":\"%s\",\"parking\":%s," +
                            "\"prefarea\":\"%s\",\"furnishingstatus\":\"%s\"}",
                    areaField.getText(), bedroomField.getText(), bathroomField.getText(), storiesField.getText(),
                    mainroadBox.getSelectedItem(), guestroomBox.getSelectedItem(), basementBox.getSelectedItem(),
                    hotwaterBox.getSelectedItem(), acBox.getSelectedItem(), parkingField.getText(),
                    prefareaBox.getSelectedItem(), furnishBox.getSelectedItem()
            );

            //URL url = new URL("http://127.0.0.1:5000/predict");
			URI uri = new URI("http://127.0.0.1:5000/predict");
URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.flush();
            os.close();

            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }
            br.close();

            String response = responseBuilder.toString();

            // Simple parsing: look for the number inside "predicted_price"
            int index = response.indexOf("predicted_price");
            if (index != -1) {
                int colonIndex = response.indexOf(":", index);
                int end = response.indexOf("}", colonIndex);
                String price = response.substring(colonIndex + 1, end).trim();
                JOptionPane.showMessageDialog(this, "Estimated Price: ₹" + price, "Prediction", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid response from server!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HousePriceGUI().setVisible(true));
    }
}
