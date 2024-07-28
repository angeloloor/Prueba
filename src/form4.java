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
    private JTextField buscarClienteTxt;

    public form4() {
        setTitle("Búsqueda de Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);

        panel4 = new JPanel(new BorderLayout());
        setContentPane(panel4);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        bustxt = new JTextField(10);
        buscarClienteTxt = new JTextField(10);
        BuscarButton = new JButton("Buscar");
        regresarProductoButton = new JButton("Regresar Producto");
        RegresarClienteButton = new JButton("Regresar Cliente");
        regresarLoginButton = new JButton("Regresar Login");

        inputPanel.add(new JLabel("ID Producto:"));
        inputPanel.add(bustxt);
        inputPanel.add(new JLabel("ID Cliente:"));
        inputPanel.add(buscarClienteTxt);
        inputPanel.add(BuscarButton);
        inputPanel.add(new JPanel());

        panel4.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        panel4.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(regresarProductoButton);
        buttonPanel.add(RegresarClienteButton);
        buttonPanel.add(regresarLoginButton);

        panel4.add(buttonPanel, BorderLayout.SOUTH);

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
                dispose();
            }
        });

        RegresarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form2();
                dispose();
            }
        });

        regresarLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });

        setVisible(true);
    }

    public void buscarProducto() {
        String idProductoStr = bustxt.getText();
        String idClienteStr = buscarClienteTxt.getText();

        if (idProductoStr.isEmpty() && idClienteStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del producto o el ID del cliente para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idProducto = -1;
        int idCliente = -1;

        try {
            if (!idProductoStr.isEmpty()) {
                idProducto = Integer.parseInt(idProductoStr);
            }
            if (!idClienteStr.isEmpty()) {
                idCliente = Integer.parseInt(idClienteStr);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/floricola";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM Producto p JOIN Cliente c ON p.id_cliente = c.id_cliente WHERE 1=1";
            if (idProducto != -1) {
                query += " AND p.id_producto = ?";
            }
            if (idCliente != -1) {
                query += " AND c.id_cliente = ?";
            }

            PreparedStatement pst = conn.prepareStatement(query);

            int paramIndex = 1;
            if (idProducto != -1) {
                pst.setInt(paramIndex++, idProducto);
            }
            if (idCliente != -1) {
                pst.setInt(paramIndex, idCliente);
            }

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
                resultArea.setText("No se encontraron resultados.");
            } else {
                resultArea.setText(resultText.toString());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
