import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form3 extends JFrame {
    private JTextField tipotxt;
    private JTextField preciotxt;
    private JTextField pagotxt;
    private JPanel panel3;
    private JButton ingresarproducto;
    private JButton buscarbutton;
    private JButton regresarClienteButton;
    private JTextField idtxt;

    public form3() {
        setTitle("Insertar Producto");
        setContentPane(panel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setPreferredSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        ingresarproducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_cliente;
                String tipo = tipotxt.getText();
                double precio;
                double pago;

                try {
                    id_cliente = Integer.parseInt(idtxt.getText());
                    precio = Double.parseDouble(preciotxt.getText());
                    pago = Double.parseDouble(pagotxt.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores numéricos válidos para id cliente, precio y pago.");
                    return;
                }

                String url = "jdbc:mysql://localhost:3306/floricola";
                String user = "root";
                String password = "123456";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    String query = "insert into Producto (id_cliente, tipo_arreglo, precio, pago) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1,id_cliente);
                    preparedStatement.setString(2, tipo);
                    preparedStatement.setDouble(3, precio);
                    preparedStatement.setDouble(4, pago);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Producto insertado exitosamente!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar el producto en la base de datos.");
                }
            }
        });

        buscarbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form4();
                setVisible(false);
            }
        });

        regresarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new form2();
                setVisible(false);
            }
        });
    }

}
