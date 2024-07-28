import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class form4 extends JFrame {
    private JTextField bustxt;
    private JButton BuscarButton;
    private JButton regresarProductoButton;
    private JButton RegresarClienteButton;
    private JButton regresarLoginButton;
    private JPanel panel4;
    private JTextArea resultArea;

    public form4() {
        setTitle("Búsqueda de Producto");
        setContentPane(panel4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        panel4.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        BuscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();

            }
        });

        regresarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3();
                setVisible(false);
            }
        });

        RegresarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form2();
                setVisible(false);
            }
        });

        regresarLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                setVisible(false);
            }
        });
    }

    private void buscarProducto() {
        String idProductoStr = bustxt.getText();
        if (idProductoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del producto para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idProducto;
        try {
            idProducto = Integer.parseInt(idProductoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID del producto inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/floricola";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM Producto p JOIN Cliente c ON p.id_cliente = c.id_cliente WHERE p.id_producto = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idProducto);

            ResultSet rs = pst.executeQuery();

            StringBuilder resultText = new StringBuilder();
            while (rs.next()) {
                resultText.append("ID Producto: ").append(rs.getInt("id_producto")).append("\n");
                resultText.append("ID Cliente: ").append(rs.getInt("id_cliente")).append("\n");
                resultText.append("Tipo Arreglo: ").append(rs.getString("tipo_arreglo")).append("\n");
                resultText.append("Precio: ").append(rs.getDouble("precio")).append("\n");
                resultText.append("Pago: ").append(rs.getDouble("pago")).append("\n");
                resultText.append("Nombre Cliente: ").append(rs.getString("nombre")).append("\n");
                resultText.append("Cédula Cliente: ").append(rs.getString("cedula")).append("\n\n");
            }

            if (resultText.length() == 0) {
                resultArea.setText("No se encontraron productos con el ID: " + idProducto);
            } else {
                resultArea.setText(resultText.toString());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
